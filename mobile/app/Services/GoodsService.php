<?php

namespace App\Services;

use App\Extensions\Wxapp;
use App\Repositories\Cart\CartRepository;
use App\Repositories\Goods\GoodsRepository;
use App\Repositories\Store\StoreRepository;
use App\Repositories\Coupons\CouponsRepository;
use App\Repositories\Goods\GoodsAttrRepository;
use App\Repositories\Goods\CollectGoodsRepository;
use App\Repositories\User\UserRepository;
use App\Repositories\Wechat\WxappConfigRepository;

use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class GoodsService
{
    private $goodsRepository;
    private $goodsAttrRepository;
    private $collectGoodsRepository;
    private $CouponsRepository;
    private $shopService;
    private $cartRepository;
    private $StoreRepository;
    private $userRepository;
    private $WxappConfigRepository;

    public function __construct(
        GoodsRepository $goodsRepository,
        GoodsAttrRepository $goodsAttrRepository,
        CollectGoodsRepository $collectGoodsRepository,
        CouponsRepository $couponsRepository,
        ShopService $shopService,
        CartRepository $cartRepository,
        UserRepository $userRepository,
        StoreRepository $StoreRepository,
        WxappConfigRepository $WxappConfigRepository
    )
    {
        $this->goodsRepository = $goodsRepository;
        $this->goodsAttrRepository = $goodsAttrRepository;
        $this->collectGoodsRepository = $collectGoodsRepository;
        $this->couponsRepository = $couponsRepository;
        $this->shopService = $shopService;
        $this->cartRepository = $cartRepository;
        $this->StoreRepository = $StoreRepository;
        $this->userRepository = $userRepository;
        $this->WxappConfigRepository = $WxappConfigRepository;
    }
	
	 	//DB::select($sql);
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
     * 商品列表
     * @param int $categoryId
     * @param $keywords
     * @param int $page
     * @param int $size
     * @param $sortKey
     * @param $sortVal
     * @return mixed
     */
	 
	 
    public function getGoodsList($categoryId = 0, $keywords = '', $page = 1, $size = 10, $sortKey = '', $sortVal = '', $warehouse_id = 0, $area_id = 0)
    {
        $page = empty($page) ? 1 : $page;

        $field = [
            "goods_id",    //商品id
            "goods_name", //商品名称
            "shop_price",  //商品价格
            "goods_thumb", //商品图片
            "goods_number",   //商品销量
            "market_price",   //商品原价格
            "sales_volume"  //商品库存
        ];

        $list = $this->goodsRepository->findBy('category', $categoryId, $page, $size, $field, $keywords, $sortKey, $sortVal);

        foreach ($list as $k => $v) {
            $list[$k]['goods_thumb'] = get_image_path($v['goods_thumb']);
            $list[$k]['market_price_formated'] = price_format($v['market_price'], false);
            $list[$k]['shop_price'] = $this->goodsRepository->getGoodsOneAttrPrice($v['goods_id']);
            $list[$k]['shop_price_formated'] = price_format($v['shop_price'], false);
        }

        return $list;
    }
	
	
	//加上扩展属性的
	    public function getGoodsListMy($categoryId = 0, $keywords = '', $page = 1, $size = 10, $sortKey = '', $sortVal = '', $warehouse_id = 0, $area_id = 0)
    {
		 
		$page = empty($page) ? 1 : $page;

        $field = [
           "goods_id",    //商品id
           "goods_name", //商品名称
           "shop_price",  //商品价格
           "goods_thumb", //商品图片
           "goods_number",   //商品销量
           "market_price",   //商品原价格
           "sales_volume",  //商品库存
		   "goods_brief",  //商品库存
        ];

		//默认为1  
		$cateLevel = 1;
		
		$categoryT = DB::select("select parent_id from dsc_category where cat_id =  '$categoryId'  ");

		if($categoryT){
            $parent_id_1 = $categoryT[0]->parent_id;
        }

		if(empty($parent_id_1))	//说明是顶层  有两级
		{
			$cateLevel = 1;
			
			$getSubSql = "select cat_id from dsc_category where parent_id = '$categoryId' ";
			
			$subList = DB::select($getSubSql);

			$subCateStr = "";
			
			for($i=0;$i<count($subList);$i++)
			{
				$subCateId = $subList[$i]->cat_id;
				
				$subCateStr .= $subCateId.',';

				$getSubSql2 = "select cat_id from dsc_category where parent_id = '$subCateId' ";
				
				$subList2 = DB::select($getSubSql2);
				
				for($j=0;$j<count($subList2);$j++)
				{
					$subCateId2 = $subList2[$j]->cat_id;
					
					///1485  359 
					$subCateStr .= $subCateId2.',';
					
				}
				
			}
			
			//如果存在，去掉最后一个，
				if(!empty($subList))
				{
					$subCateStr = substr($subCateStr,0,-1);
				}
				
			
			$begin = ($page - 1) * $size;
			
			$sql = "select DISTINCT(a.`goods_id`), a.`goods_id`, a.`goods_name`, a.`shop_price`, a.`goods_thumb`, a.`goods_number`, a.`market_price`, a.`sales_volume`, a.`goods_brief`,a.`is_best`,a.`is_hot`,a.`is_new`
				from `dsc_goods` as a left join `dsc_goods_cat`  as b on a.`goods_id`  = b.`goods_id` 
				where (a.`cat_id` in ($subCateStr) or b.`cat_id` in ($subCateStr)) and a.`is_on_sale` = 1 
				and a.`is_delete` = 0 order by a.`is_best` desc, a.`is_hot` desc, a.`is_new` desc limit $begin,$size";

			//echo $sql;echo "<BR/>";echo "<BR/>";
		
			$list2 = DB::select($sql);	
			
			//print_r($list2);
			
			  $list = array();
		  
			//转化成数组
			for($i=0;$i<count($list2);$i++)
			{
				$listT = $list2[$i];
				
				 foreach ($listT as $key => $value) 
				 {
					$list[$i][$key] = $value;
				}
			}
			
			//print_r($list);
		}
		else
		{
			///这里可能是二级 也可能是三级   如果是二级的话  还需要获取子分类。
			
			//这里使用先获取的策略，如果是0个，其实就说明是三级  用等于即可 否则用in
			
			$getSubSql = "select cat_id from dsc_category where parent_id = '$categoryId' ";
			
			$subList = DB::select($getSubSql);
			
			$subCateStr = "";
			 
			if(empty($subList))
			{
				$begin = ($page - 1) * $size;
			
				$sql = "select DISTINCT(a.`goods_id`), a.`goods_id`, a.`goods_name`, a.`shop_price`, a.`goods_thumb`, a.`goods_number`, 
				a.`market_price`, a.`sales_volume`, a.`goods_brief`  from `dsc_goods` as a left join `dsc_goods_cat`  as b on a.`goods_id`  = b.`goods_id` 
				where (a.`cat_id` = '$categoryId' or b.`cat_id` = '$categoryId') and a.`is_on_sale` = 1 and a.`is_delete` = 0 order by a.`is_best` desc, a.`is_hot` desc, a.`is_new` desc limit $begin,$size";
			 
				$list2 = DB::select($sql);	
				
				$list = array();
			   
				for($i=0;$i<count($list2);$i++)
				{
					$listT = $list2[$i];
					
					 foreach ($listT as $key => $value) 
					 {
						 
						$list[$i][$key] = $value;
					}
				}
				
			}
			else
			{
				 //基本上进入这里
				for($i=0;$i<count($subList);$i++)
				{
					$subCateId = $subList[$i]->cat_id;
					
					if($i == count($subList) -1 )
					{
						$subCateStr .= $subCateId;
					}
					else
					{
						$subCateStr .= $subCateId.',';
					}
					
				}
				
				$begin = ($page - 1) * $size;
			
				$sql = "select DISTINCT(a.`goods_id`),a.`goods_id`, a.`goods_name`, a.`shop_price`, a.`goods_thumb`, a.`goods_number`, a.`market_price`, a.`sales_volume`, a.`goods_brief` 
				from `dsc_goods` as a left join `dsc_goods_cat`  as b on a.`goods_id`  = b.`goods_id` 
				where (a.`cat_id` in ($subCateStr) or b.`cat_id` in ($subCateStr)) and a.`is_on_sale` = 1 
				and a.`is_delete` = 0 order by a.`is_best` desc, a.`is_hot` desc, a.`is_new` desc limit $begin,$size";
			
				//echo $sql;echo "<BR/>";echo "<BR/>";
			
				$list2 = DB::select($sql);	
				
				//print_r($list2);
				
				  $list = array();
			  
				//转化成数组
				for($i=0;$i<count($list2);$i++)
				{
					$listT = $list2[$i];
					
					 foreach ($listT as $key => $value) 
					 {
						 
						$list[$i][$key] = $value;
					}
				}
				
				//print_r($list);
				
			}
			
			
		}
		
		if (empty($list))
		{
            $list = [];
            return $list;
        }
		
		/*
		DB::connection()->enableQueryLog();
		DB::table('dsc_goods');
		$queries = DB::getQueryLog();
		print_r($queries);
		
		
        foreach ($list as $k => $v)
		{
            $list[$k]['goods_thumb'] = get_image_path($v['goods_thumb']);
            $list[$k]['market_price_formated'] = price_format($v['market_price'], false);
            $list[$k]['shop_price'] = $this->goodsRepository->getGoodsOneAttrPrice($v['goods_id']);
            $list[$k]['shop_price_formated'] = price_format($v['shop_price'], false);
			$list[$k]['goods_brief'] = $v['goods_brief'];
        }
		
		
		print_r($list);
		*/
		for($i=0;$i<count($list);$i++)
		{
			//$v = $list[$i];
			$list[$i]['goods_thumb'] = get_image_path($list[$i]['goods_thumb']);
			$list[$i]['market_price_formated'] = price_format($list[$i]['market_price'], false);
			
            //$list[$i]['shop_price'] = $this->goodsRepository->getGoodsOneAttrPrice($list[$i]['goods_id']);//错误?
			
            $list[$i]['shop_price_formated'] = price_format($list[$i]['shop_price'], false);
			//$list[$i]['goods_brief'] = $list[$i]['goods_brief'];
		}
		
		 
		 
        return $list;
    }


    /**
     * 商品详情
     * @param $id
     * @param $uid
     * @return array
     */
    public function goodsDetail($id, $uid)
    {
        $result = [
            'error' => 0,
            'goods_img' => '',
            'goods_info' => '',
            'goods_comment' => '',
            'goods_properties' => ''
        ];
        $rootPath = app('request')->root();
        $rootPath = dirname(dirname($rootPath)) . '/';
        $shopconfig = app('App\Repositories\ShopConfig\ShopConfigRepository');
        $timeFormat = $shopconfig->getShopConfigByCode('time_format');

        //
        $collect = $this->collectGoodsRepository->findOne($id, $uid);
        $goodsComment = $this->goodsRepository->goodsComment($id);

        foreach ($goodsComment as $k => $v) {
            $goodsComment[$k]['add_time'] = local_date('Y-m-d', $v['add_time']);
            $goodsComment[$k]['user_name'] = $this->goodsRepository->getGoodsCommentUser($v['user_id']);
        }

        $result['goods_comment'] = $goodsComment;
        $result['total_comment_number'] = count($result['goods_comment']);
        // 商品信息
        $goodsInfo = $this->goodsRepository->goodsInfo($id);
        if ($goodsInfo['is_on_sale'] == 0) {
            return ['error' => 1, 'msg' => '商品已下架'];
        }
        $goodsInfo['goods_thumb'] = get_image_path($goodsInfo['goods_thumb']);
        $goodsInfo['goods_price_formated'] = price_format($goodsInfo['goods_price'], true);
        $goodsInfo['market_price_formated'] = price_format($goodsInfo['market_price'], true);

        if (!empty($goodsInfo['desc_mobile'])) {
            $goodsInfo['desc_mobile'] = preg_replace("/height\=\"[0-9]+?\"/", "", $goodsInfo['desc_mobile']);
            $goodsInfo['desc_mobile'] = preg_replace("/width\=\"[0-9]+?\"/", "", $goodsInfo['desc_mobile']);
            $goodsInfo['desc_mobile'] = preg_replace("/style=.+?[*|\"]/i", "", $goodsInfo['desc_mobile']);
            $goodsInfo['goods_desc'] = preg_replace('/<div[^>]*(tools)[^>]*>(.*?)<\/div>(.*?)<\/div>/is', '', $goodsInfo['desc_mobile']);
        } elseif (!empty($goodsInfo['goods_desc'])) {
            $open_oss = $shopconfig->getShopConfigByCode('open_oss');
            if ($open_oss == 1) {
                $bucket_info = get_bucket_info();
                $bucket_info['endpoint'] = empty($bucket_info['endpoint']) ? $bucket_info['outside_site'] : $bucket_info['endpoint'];
                $goodsInfo['goods_desc'] = str_replace(['src="/images/upload', 'src="images/upload'], 'src="' . $bucket_info['endpoint'] . 'images/upload', $goodsInfo['goods_desc']);
            } else {
                $goodsInfo['goods_desc'] = str_replace(['src="/images/upload', 'src="images/upload'], 'src="' . $rootPath . '/images/upload', $goodsInfo['goods_desc']);
            }
        } else {
            $goodsInfo['goods_desc'] = 'xxx';
            // 查询关联商品描述
//        $sql = "SELECT ld.goods_desc FROM {pre}link_desc_goodsid AS dg, {pre}link_goods_desc AS ld WHERE dg.goods_id = {$this->goods_id}  AND dg.d_id = ld.id AND ld.review_status > 2";
//        $link_desc = Db::Query($sql);
//        if (!empty($info['desc_mobile'])) {
//            // 处理手机端商品详情 图片（手机相册图） data/gallery_album/
//            if (C('shop.open_oss') == 1) {
//                $bucket_info = get_bucket_info();
//                $bucket_info['endpoint'] = empty($bucket_info['endpoint']) ? $bucket_info['outside_site'] : $bucket_info['endpoint'];
//                $desc_preg = get_goods_desc_images_preg($bucket_info['endpoint'], $info['desc_mobile'], 'desc_mobile');
//                $goods_desc = preg_replace('/<div[^>]*(tools)[^>]*>(.*?)<\/div>(.*?)<\/div>/is', '', $desc_preg['desc_mobile']);
//            } else {
//                $goods_desc = preg_replace('/<div[^>]*(tools)[^>]*>(.*?)<\/div>(.*?)<\/div>/is', '', $info['desc_mobile']);
//            }
//        }
//        if (empty($goodsInfo['desc_mobile']) && empty($goodsInfo['goods_desc'])) {
//            $goods_desc = $link_desc;
//        }
        }

        $result['goods_info'] = array_merge($goodsInfo, ['is_collect' => (empty($collect)) ? 0 : 1]);

        // 商家信息
        $ruId = $goodsInfo['user_id'];
        unset($result['goods_info']['user_id']);
        if ($ruId > 0) {
            $result['shop_name'] = $this->shopService->getShopName($ruId);

            $result['coll_num'] = $this->StoreRepository->collnum($ruId);

            $detail = $this->StoreRepository->detail($ruId);
            $result['detail'] = $detail['0'];
            $result['detail']['sellershopinfo']['logo_thumb'] = get_image_path(str_replace('../', '', $detail['0']['sellershopinfo']['logo_thumb']));
        }

        $coupont = $this->couponsRepository->goodsCoupont($id, $goodsInfo['user_id'], $uid);
        // foreach ($coupont as $key => $value) {
        //         $coupont[$key]['cou_end_time'] = local_date('Y.m.d', $value['cou_end_time']);
        //         $coupont[$key]['cou_start_time'] = local_date('Y.m.d', $value['cou_start_time']);

        // }
        $result['coupont'] = $coupont;

        // 商品相册
        $goodsGallery = $this->goodsRepository->goodsGallery($id);
        foreach ($goodsGallery as $k => $v) {
            $goodsGallery[$k] = get_image_path($v['img_url']);
        }
        $result['goods_img'] = $goodsGallery;

        // 商品属性 规格
        $result['goods_properties'] = $this->goodsRepository->goodsProperties($id);

        // 购物车商品数量
        $result['cart_number'] = $this->cartRepository->goodsNumInCartByUser($uid);
        $result['root_path'] = $rootPath;

        return $result;
    }

    /**
     * 商品属性价格与库存
     * @param int $goodsId
     * @param int $attr_id
     * @param int $num
     * @param int $store_id 门店id
     * @return array
     *
     */
    public function goodsPropertiesPrice($goods_id, $attr_id, $num = 1, $warehouse_id = 0, $area_id = 0, $store_id = 0)
    {
        $result = [
            'stock' => '',       //库存
            'market_price' => '',      //市场价
            'qty' => '',               //数量
            'spec_price' => '',        //属性价格
            'goods_price' => '',           //商品价格(最终使用价格)
            'attr_img' => ''           //商品属性图片
        ];
        // $goods = $this->goodsRepository->goodsInfo($goods_id);//商品详情

        $result['stock'] = $this->goodsRepository->goodsAttrNumber($goods_id, $attr_id, $warehouse_id, $area_id, $store_id);
        $result['market_price'] = $this->goodsRepository->goodsMarketPrice($goods_id, $attr_id, $warehouse_id, $area_id);
        $result['market_price_formated'] = price_format($result['market_price'], true);
        $result['qty'] = $num;
        $result['spec_price'] = $this->goodsRepository->goodsPropertyPrice($goods_id, $attr_id, $warehouse_id, $area_id);
        $result['spec_price_formated'] = price_format($result['spec_price'], true);
        $result['goods_price'] = $this->goodsRepository->getFinalPrice($goods_id, $num, true, $attr_id, $warehouse_id, $area_id);
        $result['goods_price_formated'] = price_format($result['goods_price'], true);
        $attr_img = $this->goodsRepository->getAttrImgFlie($goods_id, $attr_id);
        if (!empty($attr_img)) {
            $result['attr_img'] = get_image_path($attr_img['attr_img_flie']);
        }

        return $result;
    }

    /**
     * 商品分享
     * @param int $goodsId
     * @param int $attr_id
     * @param int $num
     * @param int $store_id 门店id
     * @return array
     *
     */
    public function goodsShare($id, $uid, $path = "", $width = 430, $type = "goods")
    {
        $goodsInfo = $this->goodsRepository->goodsInfo($id);// 商品信息

        $ruId = $goodsInfo['user_id'];

        $detail = $this->StoreRepository->detail($ruId);// 商家信息
        $app_name = $this->WxappConfigRepository->getWxappConfig();
        $shop_name = empty($detail) ? $app_name['0']['wx_appname'] : $detail['0']['rz_shopName'] ;

        $result = $this->get_wxcode($path, $width);

        $rootPath = dirname(base_path());

        $imgDir = $rootPath. "/data/gallery_album/ewm/";
        if (!is_dir($imgDir)) {
            mkdir($imgDir);
        }
        $qrcode = $imgDir . $type . '_' . $uid . '_' . $id .'.png';
        file_put_contents($qrcode, $result);

        $rootPath = app('request')->root();
        $rootPath = dirname(dirname($rootPath)) . '/';

        $image_name = $rootPath."data/gallery_album/ewm/" . basename($qrcode);

        $userInfo = $this->userRepository->userInfo($uid);// 分享人信息

        $user = [
            'name' => $userInfo['nick_name'],  //分享人名字
            'id' => $userInfo['id'],  //分享人ID
            'pic' => get_image_path($userInfo['user_picture']),   //分享人头像
            'shop_name' => $shop_name,        //店铺名字
            'image_name' => $image_name
        ];
        $goods_cont = [
            'id' => $goodsInfo['goods_id'],  //推荐商品ID
            'name' => $goodsInfo['goods_name'],  //商品名称
            'pic' => get_image_path($goodsInfo['goods_thumb'])   //推荐商品图
        ];

        $share['user'] = $user;
        $share['goods_cont'] = $goods_cont;

        return $share;
    }

    private function get_wxcode($path, $width)
    {
        $config = [
            'appid' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appid'),
            'secret' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appsecret'),
        ];

        $wxapp = new Wxapp($config);
        $result = $wxapp->getWaCode($path, $width, false);
        if (empty($result)) {
            return false;
        }

        return $result;
    }

    /**
     * 领取优惠券， (coupons_user,coupons)
     * @param int $cou_id 优惠券id
     * //1.根据优惠券id查询，是否还有剩余优惠券，查coupon_user表默认只能领取一次，
     * //2.领取优惠券，（优惠券数量减少，coupons_user 添加一条数据记录用户获取优惠券，）
     */
    public function getCoupon($cou_id, $uid)
    {
        $ticket = 1;      // 默认每次领取一张优惠券
        $time = gmtime();

        $result = $this->couponsRepository->getCoutype($cou_id);

        $type = $result['cou_type'];      //优惠券类型
        $cou_rank = $result['cou_ok_user'];  //可以使用优惠券的rank
        $ranks = explode(",", $cou_rank);

        $result = $this->couponsRepository->getCoups($cou_id, $uid, $ticket);

        return $result;
     }

}
