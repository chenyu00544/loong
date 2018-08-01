<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsAttrRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\GoodsAttrModel;

class GoodsAttrRepository implements GoodsAttrRepositoryInterface
{

    private $goodsAttrModel;

    public function __construct(
        GoodsAttrModel $goodsAttrModel
    )
    {
        $this->goodsAttrModel = $goodsAttrModel;
    }

    public function setGoodsAttr($data)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['goods_attr_id'] = $data['id'];
        $original_img = 'attr_image' . DIRECTORY_SEPARATOR . 'attr_image' . DIRECTORY_SEPARATOR . 'original_img';
        $thumb_img = 'attr_image' . DIRECTORY_SEPARATOR . 'attr_image' . DIRECTORY_SEPARATOR . 'thumb_img';
        if (!empty($data['attr_sort'])) {
            $updata['attr_sort'] = $data['attr_sort'];
            $re = $this->goodsAttrModel->setGoodsAttr($where, $updata);
        } elseif (!empty($data['attr_img'])) {
            if (!empty($data['old_attr_img_o'])) {
                FileHandle::deleteFile($data['old_attr_img_o']);
            }
            if (!empty($data['old_attr_img_t'])) {
                FileHandle::deleteFile($data['old_attr_img_t']);
            }
            $updata['attr_img_flie'] = FileHandle::upLoadImage($data['attr_img'], $original_img);
            $updata['attr_gallery_flie'] = FileHandle::upLoadThumbImage($updata['attr_img_flie'], $thumb_img);
            $re = $this->goodsAttrModel->setGoodsAttr($where, $updata);
        }
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

    public function addGoodsAttr($data)
    {
        $updata['attr_id'] = $data['attr_id'];
        $updata['goods_id'] = $data['goods_id'];
        $attr_values = $data['attr_values'];
        $req = [];
        foreach ($attr_values as $val) {
            $updata['attr_value'] = $val;
            $re = $this->goodsAttrModel->addGoodsAttr($updata);
            $req[] = $re;
        }
        return $req;
    }
}