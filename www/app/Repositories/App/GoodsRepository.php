<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\GoodsRepositoryInterface;
use App\Facades\Common;
use App\Facades\Url;
use App\Http\Models\App\CommentExtModel;
use App\Http\Models\App\CommentLabelModel;
use App\Http\Models\App\CommentModel;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\TransportModel;

class GoodsRepository implements GoodsRepositoryInterface
{
    private $goodsModel;
    private $transportModel;
    private $commentModel;
    private $commentLabelModel;
    private $commentExtModel;

    public function __construct(
        GoodsModel $goodsModel,
        TransportModel $transportModel,
        CommentModel $commentModel,
        CommentLabelModel $commentLabelModel,
        CommentExtModel $commentExtModel
    )
    {
        $this->goodsModel = $goodsModel;
        $this->transportModel = $transportModel;
        $this->commentModel = $commentModel;
        $this->commentLabelModel = $commentLabelModel;
        $this->commentExtModel = $commentExtModel;
    }

    public function getBestGoods($page = 1)
    {
        $where = ['is_delete' => 0, 'is_best' => 1, 'is_on_sale' => 1];
        $column = ['goods_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume'];
        $goodses = $this->goodsModel->getGoodses($where, $page, $column);
        foreach ($goodses as $value) {
            $value->goods_thumb = Url::getImagePath($value->goods_thumb);
            $value->goods_img = Url::getImagePath($value->goods_img);
            $value->original_img = Url::getImagePath($value->original_img);
            $value->market_price_format = Common::priceFormat($value->market_price);
            $value->shop_price_format = Common::priceFormat($value->shop_price);
            $value->promote_price_format = Common::priceFormat($value->promote_price);
            if ($value->gvp->count() > 0) {
                $value->volume_number = $value->gvp[0]->volume_number;
                $value->volume_price = Common::priceFormat($value->gvp[0]->volume_price);
            }
        }
        return $goodses;
    }

    public function getGoodsDetail($goods_id)
    {
        $where['goods_id'] = $goods_id;
        $goods_detail = $this->goodsModel->getGoods($where);
        if ($goods_detail) {
            //快递
            if ($goods_detail->freight == 2) {
                $twhere['tid'] = $goods_detail->tid;
                $transport = $this->transportModel->getTransport($twhere);
                $goods_detail->transport = $transport;
            }

            //评价
            $goods_detail->comment = $this->commentModel->getComments($goods_id);
            //评价统计
            $commentLabels = $this->commentLabelModel->getCommentLabels();
            foreach ($commentLabels as $commentLabel) {
                $commentLabel->count = $this->commentExtModel->countCommentExt(['id_value' => $goods_id, 'label_id' => $commentLabel->id]);
            }
            $goods_detail->comment = $commentLabels;
            //品牌商品
            $goods_detail->brands = $this->goodsModel->getGoodses(['brand_id' => $goods_detail->brand_id], 1, ['*'], 15);

            //产品固定属性

            //产品可选属性
        }
        return $goods_detail;
    }

}