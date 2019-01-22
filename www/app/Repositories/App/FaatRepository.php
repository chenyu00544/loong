<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\FavourableActivityRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Http\Models\App\FavourableActivityModel;
use App\Http\Models\App\SecKillModel;
use App\Http\Models\App\SecKillTimeBucketModel;

class FaatRepository implements FavourableActivityRepositoryInterface
{

    private $favourableActivityModel;
    private $adRepository;
    private $secKillModel;
    private $secKillTimeBucketModel;

    public function __construct(
        FavourableActivityModel $favourableActivityModel,
        AdRepository $adRepository,
        SecKillModel $secKillModel,
        SecKillTimeBucketModel $secKillTimeBucketModel
    )
    {
        $this->favourableActivityModel = $favourableActivityModel;
        $this->adRepository = $adRepository;
        $this->secKillModel = $secKillModel;
        $this->secKillTimeBucketModel = $secKillTimeBucketModel;
    }

    public function getFaatByGroupId($id)
    {
        $where = [
            ['group_id', '=', $id],
            ['start_time', '<', time()],
            ['end_time', '>', time()],
            ['terminal_type', '=', 'app'],
        ];
        $column = [
            'act_id', 'act_name', 'group_id', 'start_time', 'end_time', 'terminal_type', 'activity_thumb', 'sort_order', 'color', 'app_icon'
        ];
        $banner = $this->adRepository->getAdPositionAndAdsByFaat($id);
        $res = $this->favourableActivityModel->getFaats($where, $column);
        foreach ($res as $re) {
            $re->activity_thumb = FileHandle::getImgByOssUrl($re->activity_thumb);
            $re->app_icon = FileHandle::getImgByOssUrl($re->app_icon);
            foreach ($re->goods as $goods) {
                $goods->original_img = FileHandle::getImgByOssUrl($goods->original_img);
                $goods->goods_thumb = FileHandle::getImgByOssUrl($goods->goods_thumb);
                $goods->goods_img = FileHandle::getImgByOssUrl($goods->goods_img);
                $goods->shop_price_format = Common::priceFormat($goods->shop_price);
                $goods->market_price_format = Common::priceFormat($goods->market_price);
            }
        }

        $return['faat'] = $res;
        $return['banner'] = $banner;
        $return['coupons'] = [];
        return $return;
    }

    public function getSecondKill()
    {
        $time = time();
        $now_date = date('Y-m-d', $time);
        $where = [
            ['review_status', '=', 3],
            ['is_putaway', '=', 1],
            ['start_time', '<', $time],
            ['end_time', '>', $time],
            ['ru_id', '=', 0],
        ];
        $seckill = $this->secKillModel->getSecKill($where);
        if ($seckill) {
            $timeBuckets = $this->secKillTimeBucketModel->getSecKillTimeByGoods([], $seckill->sec_id);
            foreach ($timeBuckets as $timeBucket) {
                $timeBucket->begin_time = strtotime($now_date.' '.$timeBucket->begin_time);
                $timeBucket->end_time = strtotime($now_date.' '.$timeBucket->end_time);
                foreach ($timeBucket->killGoods as $killGoods) {
                    $killGoods->goods_thumb = FileHandle::getImgByOssUrl($killGoods->goods_thumb);
                    $killGoods->goods_img = FileHandle::getImgByOssUrl($killGoods->goods_img);
                    $killGoods->original_img = FileHandle::getImgByOssUrl($killGoods->original_img);
                    $killGoods->sec_price_format = Common::priceFormat($killGoods->sec_price);
                    $killGoods->shop_price_format = Common::priceFormat($killGoods->shop_price);
                    $killGoods->market_price_format = Common::priceFormat($killGoods->market_price);
                }
            }
            $seckill->goods = $timeBuckets;
            return $seckill;
        } else {
            return false;
        }
    }
}