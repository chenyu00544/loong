<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\CategoryRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\App\CategoryModel;

class CategoryRepository implements CategoryRepositoryInterface
{
    private $categoryModel;

    public function __construct(
        CategoryModel $categoryModel
    )
    {
        $this->categoryModel = $categoryModel;
    }

    public function getCates()
    {
        $column = [
            'id', 'cat_name', 'parent_id', 'sort_order', 'cat_alias_name', 'touch_icon', 'is_show'
        ];
        $where['is_show'] = 1;
        $where['parent_id'] = 0;
        $res = $this->categoryModel->getComCates($where, $column);
        foreach ($res as $re) {
            $re->touch_icon = FileHandle::getImgByOssUrl($re->touch_icon);
            if(!empty($re->subCate)){
                foreach ($re->subCate as $sub_cate){
                    if($sub_cate->ads){
                        $sub_cate->ads->ad_code = FileHandle::getImgByOssUrl($sub_cate->ads->ad_code);
                    }
                    $sub_cate->touch_icon = FileHandle::getImgByOssUrl($sub_cate->touch_icon);
                    if(!empty($sub_cate->subCate)){
                        foreach ($sub_cate->subCate as $sub_cate2){
                            $sub_cate2->touch_icon = FileHandle::getImgByOssUrl($sub_cate2->touch_icon);
                        }
                    }
                }
            }
        }
        return $res;
    }
}