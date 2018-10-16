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
use App\Facades\FileHandle;
use App\Facades\Url;
use App\Http\Models\App\CommentExtModel;
use App\Http\Models\App\CommentLabelModel;
use App\Http\Models\App\CommentModel;
use App\Http\Models\App\GoodsDescriptionModel;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\TransportModel;
use App\Http\Models\App\UsersModel;

class GoodsRepository implements GoodsRepositoryInterface
{
    private $goodsModel;
    private $transportModel;
    private $commentModel;
    private $commentLabelModel;
    private $commentExtModel;
    private $usersModel;
    private $goodsDescriptionModel;

    public function __construct(
        GoodsModel $goodsModel,
        TransportModel $transportModel,
        CommentModel $commentModel,
        CommentLabelModel $commentLabelModel,
        CommentExtModel $commentExtModel,
        UsersModel $usersModel,
        GoodsDescriptionModel $goodsDescriptionModel
    )
    {
        $this->goodsModel = $goodsModel;
        $this->transportModel = $transportModel;
        $this->commentModel = $commentModel;
        $this->commentLabelModel = $commentLabelModel;
        $this->commentExtModel = $commentExtModel;
        $this->usersModel = $usersModel;
        $this->goodsDescriptionModel = $goodsDescriptionModel;
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

    public function getGoodsDetail($goods_id, $user_id = 0)
    {
        $where['goods_id'] = $goods_id;
        $where['is_on_sale'] = 1;
        $where['is_delete'] = 0;
        $where['review_status'] = 3;
        $column = [
            'goods_id', 'cat_id', 'user_id', 'goods_name', 'goods_sn', 'brand_id',
            'goods_number', 'shop_price', 'market_price', 'promote_price', 'promote_start_date', 'promote_end_date',
            'desc_mobile', 'goods_desc', 'goods_id', 'goods_thumb', 'original_img', 'goods_img', 'is_on_sale',
            'is_delete', 'is_best', 'is_new', 'is_hot', 'is_promote', 'is_volume',
            'goods_type', 'is_limit_buy', 'limit_buy_start_date', 'limit_buy_end_date', 'limit_buy_num', 'review_status',
            'sales_volume', 'comments_number', 'tid', 'goods_cause', 'goods_video', 'is_distribution',
            'pinyin_keyword', 'goods_brief',
        ];
        $goods_detail = $this->goodsModel->getGoodsAndExt($where, $column);
        if ($goods_detail) {
            $mobile_descs = Common::pregMatchAll($goods_detail->desc_mobile, '/src=(".*?")/i');
            foreach ($mobile_descs as $k => $mobile_desc) {
                $mobile_descs[$k] = FileHandle::getImgByOssUrl($mobile_desc);
            }
            $goods_detail->mobile_descs = $mobile_descs;
            $goods_detail->goods_video = FileHandle::getImgByOssUrl($goods_detail->goods_video);

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
            $goods_detail->brands = $this->goodsModel->getGoodses(['brand_id' => $goods_detail->brand_id], 1, ['goods_name', 'goods_thumb', 'shop_price', 'promote_price', 'promote_start_date', 'promote_end_date'], 15);

            //用户地址
            $uwhere['user_id'] = $user_id;
            $user = $this->usersModel->getUserByAddress($uwhere, ['user_id', 'address_id']);
            if ($user) {
                foreach ($user->addresses as $address) {
                    if ($address->address_id == $user->address_id) {
                        $user->default_address = $address;
                    }
                }
            }
            $goods_detail->user = $user;
            $goods_detail->goods_description = $this->goodsDescriptionModel->getGoodsDescriptions();

            //商品属性整理
            $goods_detail->gattr;

            foreach ($goods_detail->gattr as $gattr){
                if($gattr->attr){

                }
            }

        }
        return $goods_detail;
    }
}