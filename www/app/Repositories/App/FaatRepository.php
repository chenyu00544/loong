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

class FaatRepository implements FavourableActivityRepositoryInterface
{

    private $favourableActivityModel;
    private $adRepository;

    public function __construct(
        FavourableActivityModel $favourableActivityModel,
        AdRepository $adRepository
    )
    {
        $this->favourableActivityModel = $favourableActivityModel;
        $this->adRepository = $adRepository;
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
}