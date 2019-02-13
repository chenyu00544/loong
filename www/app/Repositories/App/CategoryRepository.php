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
use App\Facades\RedisCache;
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
        if ($cate = RedisCache::get('category')) {
//            return $cate;
        }
        $column = [
            'id', 'cat_name', 'parent_id', 'sort_order', 'cat_alias_name', 'touch_icon', 'is_show'
        ];
        $where['is_show'] = 1;
        $where['parent_id'] = 0;
        $res = $this->categoryModel->getComCates($where, $column);
        $req = [];
        foreach ($res as $k =>$re) {
            if ($re->subCate->count() > 0) {
                foreach ($re->subCate as $sub_cate) {
                    $sub_cate->touch_icon = FileHandle::getImgByOssUrl($sub_cate->touch_icon);
                    $title = [
                        'id' => $sub_cate->id,
                        'cat_alias_name' => $sub_cate->cat_alias_name,
                        'touch_icon' => $sub_cate->touch_icon,
                    ];
                    $sub_cate->title = $title;
                    if (!empty($sub_cate->subCate)) {
                        foreach ($sub_cate->subCate as $sub_cate2) {
                            $sub_cate2->touch_icon = FileHandle::getImgByOssUrl($sub_cate2->touch_icon);
                        }
                    }
                    if ($sub_cate->ads) {
                        foreach ($sub_cate->ads as $ad) {
                            $ad->ad_code = FileHandle::getImgByOssUrl($ad->ad_code);
                        }
                    }
                }
                $req[] = $re;
            }
        }
        RedisCache::set('category', $req);
        return $req;
    }

    public function getCatesByGoods($id)
    {
        
    }
}