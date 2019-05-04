<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\FavourableActivityRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Http\Models\Wxapp\FavourableActivityModel;
use App\Http\Models\Wxapp\GoodsActivityModel;
use App\Http\Models\Wxapp\SecKillModel;
use App\Http\Models\Wxapp\SecKillTimeBucketModel;
use App\Http\Models\Wxapp\TeamCategoryModel;
use App\Http\Models\Wxapp\TeamGoodsModel;
use App\Http\Models\Wxapp\TeamLogModel;

class FaatRepository implements FavourableActivityRepositoryInterface
{

    private $favourableActivityModel;
    private $adRepository;
    private $secKillModel;
    private $secKillTimeBucketModel;
    private $goodsActivityModel;
    private $teamCategoryModel;
    private $teamGoodsModel;
    private $teamLogModel;

    public function __construct(
        FavourableActivityModel $favourableActivityModel,
        AdRepository $adRepository,
        SecKillModel $secKillModel,
        SecKillTimeBucketModel $secKillTimeBucketModel,
        GoodsActivityModel $goodsActivityModel,
        TeamCategoryModel $teamCategoryModel,
        TeamGoodsModel $teamGoodsModel,
        TeamLogModel $teamLogModel
    )
    {
        $this->favourableActivityModel = $favourableActivityModel;
        $this->adRepository = $adRepository;
        $this->secKillModel = $secKillModel;
        $this->secKillTimeBucketModel = $secKillTimeBucketModel;
        $this->goodsActivityModel = $goodsActivityModel;
        $this->teamCategoryModel = $teamCategoryModel;
        $this->teamGoodsModel = $teamGoodsModel;
        $this->teamLogModel = $teamLogModel;
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
                $timeBucket->begin_time = strtotime($now_date . ' ' . $timeBucket->begin_time);
                $timeBucket->end_time = strtotime($now_date . ' ' . $timeBucket->end_time);
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

    public function getGroupBuy()
    {
        $time = time();
        $where = [
            ['review_status', '=', 3],
            ['is_finished', '=', 0],
            ['start_time', '<=', $time],
            ['end_time', '>', $time]
        ];
        $goodsActivitys = $this->goodsActivityModel->getGoodsActivitys($where);
        foreach ($goodsActivitys as $goodsActivity) {
            $goodsActivity->ext_info = unserialize($goodsActivity->ext_info);
            if ($goodsActivity->goods) {
                foreach ($goodsActivity->goods->toArray() as $key => $value) {
                    if ($key == 'goods_thumb' || $key == 'goods_img' || $key == 'original_img') {
                        $goodsActivity->goods_thumb = FileHandle::getImgByOssUrl($value);
                        $goodsActivity->goods_img = FileHandle::getImgByOssUrl($value);
                        $goodsActivity->original_img = FileHandle::getImgByOssUrl($value);
                    } elseif ($key == 'shop_price' || $key == 'market_price') {
                        $goodsActivity->$key = $value;
                        $goodsActivity->shop_price_format = Common::priceFormat($value);
                        $goodsActivity->market_price_format = Common::priceFormat($value);
                    } else {
                        $goodsActivity->$key = $value;
                    }
                }
            }

            unset($goodsActivity->goods);
        }
        return $goodsActivitys;
    }

    public function getTeamNav()
    {
        $res = $this->teamCategoryModel->getTeamCates(['parent_id' => 0]);
        foreach ($res as $re) {
            $re->tc_img = FileHandle::getImgByOssUrl($re->tc_img);
        }
        return $res;
    }

    public function getTeam($data)
    {
        $res = $this->teamCategoryModel->getTeamCates(['parent_id' => $data['id']]);

        $whereIn = [];
        $whereIn[] = $data['id'];
        foreach ($res as $re) {
            $whereIn[] = $re->id;
        }
        $goodses = $this->teamCategoryModel->getTeamCatesByGoods($whereIn);
        foreach ($goodses as $goods) {
            $goods->tc_img = FileHandle::getImgByOssUrl($goods->tc_img);
            foreach ($goods->goods as $gs) {
                $gs->original_img = FileHandle::getImgByOssUrl($gs->original_img);
                $gs->team_price_format = Common::priceFormat($gs->team_price);
            }
        }
        $team['goodses'] = $goodses;
        $team['banner'] = array();
        return $team;
    }

    public function getTeamGoods($data, $uid = 0)
    {
        $goods_id = $data['goods_id'];
        $team_id = $data['team_id'];

        $result = [
            'error' => 0,
            'user_id' => 0,
            'goods_img' => '',         //商品相册
            'goods_info' => '',        //商品信息
            'team_log' => '',          //已成功开团信息
            'new_goods' => '',         //拼团新品
            'goods_properties' => ''   // 商品属性 规格
        ];
        $result['user_id'] = $uid;

        $time = time();

        //是否收藏

        // 商品信息
        $goodsInfo = $this->teamGoodsModel->goodsInfo(['goods_id' => $goods_id]);
        if ($goodsInfo) {
            $mobile_descs = unserialize($goodsInfo->goods->desc_mobile);
            $goodsInfo->goods->mobile_descs = $mobile_descs;
            $goodsInfo->goods->goods_thumb = FileHandle::getImgByOssUrl($goodsInfo->goods->goods_thumb);
            $goodsInfo->goods->original_img = FileHandle::getImgByOssUrl($goodsInfo->goods->original_img);
            $goodsInfo->goods->goods_img = FileHandle::getImgByOssUrl($goodsInfo->goods->goods_img);
            foreach ($goodsInfo->ggallery as $gallery) {
                $gallery->img_original = FileHandle::getImgByOssUrl($gallery->img_original);
                $gallery->img_url = FileHandle::getImgByOssUrl($gallery->img_url);
            }
            //初始值
            $goodsInfo->team_id = 0;

            //验证参团活动是否结束
            if ($team_id) {
                $team_info = $this->teamLogModel->getTeamLog(['team_id' => $team_id]);
                if ($goodsInfo->is_team != 1 || $team_info->status == 1) {
                    return '该拼团活动已结束，去查看新的活动吧';
                }
                $goodsInfo->team_id = $team_id;
            }

            if ($goodsInfo->goods->is_on_sale == 0) {
                return '商品已下架';
            }
            $goodsInfo->goods->team_price_format = Common::priceFormat($goodsInfo->team_price);  //拼团价格
            $goodsInfo->goods->shop_price_format = Common::priceFormat($goodsInfo->goods->shop_price);
            $goodsInfo->goods->market_price_formated = Common::priceFormat($goodsInfo->goods->market_price);

            $result['goods_info'] = $goodsInfo;  //商品信息

            // 商家信息
            $ruId = $goodsInfo->goods->user_id;

            //获取该商品已成功开团信息
            $team_log = $this->teamLogModel->getTeamLogByAllInfo($goods_id);;
            if ($team_log) {
                foreach ($team_log as $key => $val) {
                    $validity_time = $val['start_time'] + ($val['validity_time'] * 3600) + (8 * 3600);
                    $team_log[$key]['end_time'] = $validity_time; //剩余时间
                    //统计该拼团已参与人数
                    $team_num = $this->teamRepository->surplusNum($val['team_id']);
                    $team_log[$key]['surplus'] = $val['team_num'] - $team_num;//还差几人
                    //用户名、头像
                    $user_info = $this->userRepository->userInfo($val['team_parent_id']);
                    $team_log[$key]['user_name'] = !empty($user_info['nick_name']) ? $user_info['nick_name'] : $user_info['user_name'];
                    $team_log[$key]['user_picture'] = get_image_path($user_info['user_picture']);

                    //验证是否参团
                    $team_log[$key]['is_team'] = 0;
                    $team_join = $this->teamRepository->teamJoin($uid, $val['team_id']);
                    if ($team_join > 0) {
                        $team_log[$key]['is_team'] = 1;
                    }
                    //过滤到期的拼团
                    if ($validity_time <= $time) {
                        unset($team_log[$key]);
                    }
                }
                $result['team_log'] = $team_log;
            }
            return $result;
        }else{
            return '该拼团活动已结束，去查看新的活动吧';
        }

        //获取拼团新品
        $new_goods = $this->teamRepository->teamNewGoods('is_new', $goodsInfo['user_id']);
        foreach ($new_goods as $key => $val) {
            $new_goods[$key]['goods_thumb'] = get_image_path($val['goods_thumb']);
            $new_goods[$key]['shop_price'] = price_format($val['shop_price'], true);
            $new_goods[$key]['team_price'] = price_format($val['team_price'], true);
        }
        $result['new_goods'] = $new_goods;

        // 商品相册
        $goodsGallery = $this->goodsRepository->goodsGallery($goods_id);
        foreach ($goodsGallery as $k => $v) {
            $goodsGallery[$k] = get_image_path($v['img_url']);
        }
        $result['goods_img'] = $goodsGallery;

        // 商品属性 规格
        $result['goods_properties'] = $this->goodsRepository->goodsProperties($goods_id);
    }
}