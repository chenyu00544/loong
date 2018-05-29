<?php

namespace App\Services;

use App\Extensions\Scws4;
use App\Repositories\Goods\GoodsRepository;

/**
 * Class SearchService
 * @package App\Services
 */
class SearchService
{

    private $goodsRepository;

    public function __construct(GoodsRepository $goodsRepository)
    {
        $this->goodsRepository = $goodsRepository;
    }

    public function get_search_goods($keyword, $area_id, $id, $page, $per_page, $shop, $sort_key, $sort_value, $warehouse_id)
    {
        if(!empty($keyword)){
            $keywords = $this->scws($keyword);
            $list = $this->goodsRepository->searchBy($keywords, $page, $per_page, $sort_key, $sort_value, ['goods_id','goods_thumb','goods_name','shop_price','market_price','keywords','sort_order']);

            for ($i = 0; $i < count($list); $i++) {
                $list[$i]['goods_thumb'] = get_image_path($list[$i]['goods_thumb']);
                $list[$i]['market_price_formated'] = price_format($list[$i]['market_price'], false);
                $list[$i]['shop_price_formated'] = price_format($list[$i]['shop_price'], false);
            }
        }elseif (!empty($id)){
            $list = $this->goodsRepository->cateBy($id, $page, $per_page, $sort_key, $sort_value, ['goods_id','goods_thumb','goods_name','shop_price','market_price','keywords','sort_order']);

            for ($i = 0; $i < count($list); $i++) {
                $list[$i]['goods_thumb'] = get_image_path($list[$i]['goods_thumb']);
                $list[$i]['market_price_formated'] = price_format($list[$i]['market_price'], false);
                $list[$i]['shop_price_formated'] = price_format($list[$i]['shop_price'], false);
            }

        }

        return $list;
    }

    public function scws($keyword)
    {
        define('ROOT_PATH', str_replace('\\', '/', base_path()));
        $scws = new Scws4();
        $keyword_segmentation = $scws->segmentate($keyword, true);
        $keywords = explode(',', $keyword_segmentation);
        return $keywords;
    }
}
