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

    public function getGoodsAttr($id)
    {
        //商品属性详细信息
        $goodsAttr = $this->goodsAttrModel->getGoodsAttrsJoinAttr(['goods_id' => $id, 'attr_checked' => 1]);

        $goodsAttrO = [];
        $goodsAttrM = [];
        $goodsAttrM_bak = [];
        $attr_values = [];
        foreach ($goodsAttr as $value) {
            $goodsAttrArr[$value->goods_attr_id] = $value;
            $val_bat = $value;
            $attrValues = explode("\r\n", $val_bat->attr_values);
            $val_bat->attr_values = $attrValues;
            if ($value->attr_type == 0) {//唯一属性
                $goodsAttrO[] = $val_bat;
            } else {//单选属性
                $attr_values[$value->attr_id][] = $value->attr_value;
                $attr_sorts[$value->attr_id][] = $value->attr_sort;
                $goods_attr_ids[$value->attr_id][] = $value->goods_attr_id;
                $attr_img_flies[$value->attr_id][] = $value->attr_img_flie;
                $attr_gallery_flies[$value->attr_id][] = $value->attr_gallery_flie;
                $goodsAttrM_bak[$value->attr_id] = $val_bat;
            }
        }

        foreach ($attr_values as $key => $value) {
            $i = 0;
            foreach ($goodsAttrM_bak[$key]->attr_values as $k => $val) {
                if (in_array($val, $value)) {
                    $select[$k] = 1;

                    $attr_sorts_bak[$key][] = $attr_sorts[$key][$i];
                    $goods_attr_ids_bak[$key][] = $goods_attr_ids[$key][$i];
                    $attr_img_flies_bak[$key][] = $attr_img_flies[$key][$i];
                    $attr_gallery_flies_bak[$key][] = $attr_gallery_flies[$key][$i];
                    $i++;
                } else {
                    $select[$k] = 0;
                    $attr_sorts_bak[$key][] = 0;
                    $goods_attr_ids_bak[$key][] = 0;
                    $attr_img_flies_bak[$key][] = 0;
                    $attr_gallery_flies_bak[$key][] = 0;
                }
            }
            $goodsAttrM_bak[$key]->attr_sorts = $attr_sorts_bak[$key];
            $goodsAttrM_bak[$key]->goods_attr_ids = $goods_attr_ids_bak[$key];
            $goodsAttrM_bak[$key]->attr_img_flies = $attr_img_flies_bak[$key];
            $goodsAttrM_bak[$key]->attr_gallery_flies = $attr_gallery_flies_bak[$key];
            $goodsAttrM_bak[$key]->selected = $select;
            $goodsAttrM[] = $goodsAttrM_bak[$key];
        }
        $req['goods_attr_o'] = $goodsAttrO;
        $req['goods_attr_m'] = $goodsAttrM;

        return $req;
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