<?php

namespace App\Services;

use App\Repositories\Store\StoreRepository;
use App\Repositories\Store\CollectStoreRepository;

use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class StoreService
{
    private $storeRepository;
    private $collectStoreRepository;

    /**
     * StoreService constructor.
     * @param StoreRepository $storeRepository
     * @param CollectStoreRepository $collectStoreRepository
     */
    public function __construct(
        StoreRepository $storeRepository,
        CollectStoreRepository $collectStoreRepository
        )
    {
        $this->storeRepository = $storeRepository;
        $this->collectStoreRepository = $collectStoreRepository;
    }
	
	public function getDataListBySql($sql)
	{
		$list = DB::select($sql);	
		
		return $list;
	}

	//简单执行
	public function sqlUpdate($sql)
	{
		$res = DB::update($sql);	
		
		return $res;
	}

    /**
     * 店铺列表
     * @return array
     */
    public function storeList($uid)
    {
        $store_list = $this->storeRepository->all($uid);

        return $list;
    }

    /**
     * 店铺详情
     * @return array
     */
    public function detail($id, $page, $per_page = 10, $cate_key, $sort, $order = 'ASC', $cat_id = 0, $uid)
    {
        $rootPath = app('request')->root();
        $rootPath = dirname(dirname($rootPath)) . '/';
        $detail = $this->storeRepository->detail($id);
        $detail['0']['sellershopinfo']['logo_thumb'] = get_image_path(str_replace('../', '', $detail['0']['sellershopinfo']['logo_thumb']));

        $goods = $this->storeRepository->goods($id, $page, $per_page, $cate_key, $sort, $order, $cat_id);

        $category = $this->storeRepository->store_category($id);
        foreach ($goods as $key => $value) {
            $goods[$key]['goods_name'] = $value['goods_name'];
            $goods[$key]['goods_thumb'] = get_image_path($value['goods_thumb']);
            $goods[$key]['shop_price'] = price_format($value['shop_price'], true);
            $goods[$key]['yuan_shop'] = $value['shop_price'];
            $goods[$key]['cat_id'] = $value['cat_id'];
            $goods[$key]['market_price'] = price_format($value['market_price'], true);
            $goods[$key]['yuan_market'] = $value['market_price'];
            $goods[$key]['goods_number'] = $value['goods_number'];
        }
        $collnum = $this->storeRepository->collnum($id);
        $collect = $this->storeRepository->collect($id, $uid);
        $list['detail'] = $detail['0'];
        $list['goods'] = $goods;
        $list['category'] = $category;
        $list['collnum'] = $collnum;//关注人数
        $list['collect'] = $collect;//关注状态
        $list['root_path'] = $rootPath;
		
		
		
		
		//获取所有的小程序广告。$id  get_image_path
		$getAdsSql = 
		"
			select DISTINCT(ad_position),ad_id,ad_image ,ad_type,target_id,goods_id,is_cashon
			from dsc_seller_wxapp_ads where user_id = '$id' and is_delete = 0 order by ad_position asc 
		";
		
		$adList = DB::select($getAdsSql);	
		 
		 $ad1 = array();
		 $ad2 = array();
		 $ad3 = array();
		 $ad4 = array();
		 $ad5 = array();
		 $ad6 = array();
 
		for($i=0;$i<count($adList);$i++)
		 {
			
			$ad_image = $adList[$i]->ad_image;
			
			$adList[$i]->ad_image = get_image_path($ad_image);
			
			$ad_type = $adList[$i]->ad_type;
			
			$target_id = $adList[$i]->target_id;
			
			$goods_id = $adList[$i]->goods_id;

             $is_cashon = $adList[$i]->is_cashon;
			
			$targetUrl = "";
			
			if($ad_type == 1)
			{
			    if($is_cashon == 0){
                    $targetUrl = "../../pages/goods/index?objectId=".$goods_id;
                }else{
                    $targetUrl = "../../pages/goods/index?objectId=".$goods_id.'&cash=ondelivery';
                }
			}
			else if($ad_type == 2)
			{
				$targetUrl = "../../pages/bargain/goods?objectId=".$target_id;
			}
			else if($ad_type == 3)
			{
				$targetUrl = "../../pages/group/goods?objectId=".$goods_id;
			}
			
			$adList[$i]->target_url = $targetUrl;
			
			$ad_position = $adList[$i]->ad_position;
			
			if($ad_position == 1)
			{
				$ad1 = $adList[$i];
			}
			else if($ad_position == 2)
			{
				$ad2 = $adList[$i];
			}
			else if($ad_position == 3)
			{
				$ad3 = $adList[$i];
			}
			else if($ad_position == 4)
			{
				$ad4 = $adList[$i];
			}
			else if($ad_position == 5)
			{
				$ad5 = $adList[$i];
			}
			else if($ad_position == 6)
			{
				$ad6 = $adList[$i];
			}
			
		 }
		 
		  $list['ad_list'] = $adList;
		  
		  $list['ad1'] = $ad1;
		   $list['ad2'] = $ad2;
		    $list['ad3'] = $ad3;
			 $list['ad4'] = $ad4;
			  $list['ad5'] = $ad5;
			   $list['ad6'] = $ad6;
		
		
        return $list;
    }

    /**
     * 关注店铺
     * @return array
     */
    public function attention($id, $uid)
    {
        $collectStore = $this->collectStoreRepository->findOne($id, $uid);
        if (empty($collectStore)) {
            $result = $this->collectStoreRepository->addCollectStore($id, $uid);
            $result = [
                    'collect' => 'true',
                    'collnum' => $this->storeRepository->collnum($id)
            ];
        } else {
            $result = $this->collectStoreRepository->deleteCollectStore($id, $uid);
            $result = [
                    'collect' => '0',
                    'collnum' => $this->storeRepository->collnum($id)
            ];
        }
        return $result;
    }
}
