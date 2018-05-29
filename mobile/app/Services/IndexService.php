<?php

namespace App\Services;

use Illuminate\Http\Request;
use App\Repositories\Shop\ShopRepository;
use App\Repositories\Goods\GoodsRepository;


use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

/**
 * 商店首页服务
 * Class IndexService
 * @package App\Services
 */
class IndexService
{
    private $goodsRepository;
    private $shopRepository;
    private $root_url;

    /**
     * IndexService constructor.
     * @param GoodsRepository $goodsRepository
     * @param ShopRepository $shopRepository
     * @param Request $request
     */
    public function __construct(GoodsRepository $goodsRepository, ShopRepository $shopRepository, Request $request)
    {
        $this->goodsRepository = $goodsRepository;
        $this->shopRepository = $shopRepository;
        $this->root_url = dirname(dirname($request->root())) . '/';
    }

	
	
	
	
	
    /**
     * 微信小程序 首页推荐商品
     * @return array
     */
    public function bestGoodsList($page = 1)
    {
        $arr = [
            'goods_id',   //商品id
            'goods_name',   //商品名
            'shop_price',   //商品价格
            'goods_thumb',   //商品图片
            'goods_link',    //商品链接
            'goods_number',   //商品销量
            'market_price',   //商品原价格
            'sales_volume',   //商品库存
        ];
        $goodsList = $this->goodsRepository->findByType('best',10,$page);  //获取推荐商品

        $data = array_map(function ($v) use ($arr) 
		{
            foreach ($v as $ck => $cv) {
                if (!in_array($ck, $arr)) {
                    unset($v[$ck]);
                }
            }

            $v['goods_thumb'] = get_image_path($v['goods_thumb']);
            $v['goods_sales'] = $v['sales_volume'];
            $v['goods_stock'] = $v['goods_number'];
            $v['market_price_formated'] = price_format($v['market_price'], false);
            $v['shop_price_formated'] = price_format($v['shop_price'], false);
            unset($v['goods_number'], $v['sales_volume']);
            return $v;
        }, $goodsList);

        return $data;
    }

    /**
     * 获取banner
     * @return array
     */
    public function getBanners()
    {
        $res = $this->shopRepository->getPositions('weapp', 10);  //获取banner

        $ads = [];

        foreach ($res as $row) {
            if (!empty($row['position_id'])) {
                $src = (strpos($row['ad_code'], 'http://') === false && strpos($row['ad_code'], 'https://') === false) ?
                    "data/afficheimg/$row[ad_code]" : $row['ad_code'];
                $ads[] = [
                    'pic' => get_image_path($src),
                    'banner_id' => $row['ad_id'],
                    'link' => $row['ad_link'],
                ];
            }
        }

        return $ads;
    }

    /**
     * 获取广告位
     * @return array
     */
    public function getAdsense()
    {
        $shopconfig = app('App\Repositories\ShopConfig\ShopConfigRepository');
        $number = $shopconfig->getShopConfigByCode('wx_index_show_number');
        if (empty($number)) {
            $number = 10;
        }

        $adsense = $this->shopRepository->getPositions('', $number);  //获取广告位

        $ads = [];
        foreach ($adsense as $row) {
            if (!empty($row['position_id'])) {
                $src = (strpos($row['ad_code'], 'http://') === false && strpos($row['ad_code'], 'https://') === false) ?
                    "data/afficheimg/$row[ad_code]" : $row['ad_code'];
                $ads[] = [
                    'pic' => get_image_path($src),
                    'adsense_id' => $row['ad_id'],
                    'link' => $row['ad_link'],
                ];
            }
        }
        return $ads;
    }
	
	
	///////////////////////////////
	
	 public function hotGoodsList()
    {
        $arr = [
            'goods_id',   //商品id
            'goods_name',   //商品名
            'shop_price',   //商品价格
            'goods_thumb',   //商品图片
            'goods_link',    //商品链接
            'goods_number',   //商品销量
            'market_price',   //商品原价格
            'sales_volume',   //商品库存
			'goods_brief',   //商品库存
        ];
        $goodsList = $this->goodsRepository->findByType('hot');  //获取推荐商品

        $data = array_map(function ($v) use ($arr) {
            foreach ($v as $ck => $cv) {
                if (!in_array($ck, $arr)) {
                    unset($v[$ck]);
                }
            }

			$v['goods_brief'] = $v['goods_brief'];
            $v['goods_thumb'] = get_image_path($v['goods_thumb']);
            $v['goods_sales'] = $v['sales_volume'];
            $v['goods_stock'] = $v['goods_number'];
            $v['market_price_formated'] = price_format($v['market_price'], false);
            $v['shop_price_formated'] = price_format($v['shop_price'], false);
            unset($v['goods_number'], $v['sales_volume']);
            return $v;
        }, $goodsList);

        return $data;
    }
	
	 public function newGoodsList()
    {
        $arr = [
            'goods_id',   //商品id
            'goods_name',   //商品名
            'shop_price',   //商品价格
            'goods_thumb',   //商品图片
            'goods_link',    //商品链接
            'goods_number',   //商品销量
            'market_price',   //商品原价格
            'sales_volume',   //商品库存
			'goods_brief',   //商品库存
        ];
        $goodsList = $this->goodsRepository->findByType('new');  //获取推荐商品

        $data = array_map(function ($v) use ($arr) {
            foreach ($v as $ck => $cv) {
                if (!in_array($ck, $arr)) {
                    unset($v[$ck]);
                }
            }

			$v['goods_brief'] = $v['goods_brief'];
            $v['goods_thumb'] = get_image_path($v['goods_thumb']);
            $v['goods_sales'] = $v['sales_volume'];
            $v['goods_stock'] = $v['goods_number'];
            $v['market_price_formated'] = price_format($v['market_price'], false);
            $v['shop_price_formated'] = price_format($v['shop_price'], false);
            unset($v['goods_number'], $v['sales_volume']);
            return $v;
        }, $goodsList);

        return $data;
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
           "sales_volume",  //商品库存
		   "goods_brief",  //商品库存
        ];
		
		////额外获取 分类 层次 $value
		
		/**/
		
		//默认为1  
		$cateLevel = 1;
		
		$categoryT = DB::select("select parent_id from dsc_category where cat_id =  '$categoryId'  ");	
		
		//print_r($categoryT);  0 0 0 1497
		
		$parent_id_1 = $categoryT[0]->parent_id;
		
		
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
				/*
				if($i == count($subList) -1 )
				{
					$subCateStr .= $subCateId;  
				}
				else
				{
					$subCateStr .= $subCateId.',';
				}
				*/
				
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
				
							//return $subCateStr;
			
			//echo $subCateStr;echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";
			 
		
			//获取二级
			//$list = $this->goodsRepository->findByIn('category', $subCateStr, $page, $size, $field, $keywords, $sortKey, $sortVal);
			$begin = ($page - 1) * $size;
			
			$sql = "select DISTINCT(a.`goods_id`), a.`goods_id`, a.`goods_name`, a.`shop_price`, a.`goods_thumb`, a.`goods_number`, a.`market_price`, a.`sales_volume`, a.`goods_brief` 
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
				//return $categoryId;
				//否则直接处理。
				 
				//$list = $this->goodsRepository->findBy('category', $categoryId, $page, $size, $field, $keywords, $sortKey, $sortVal);
				
				$begin = ($page - 1) * $size;
			
				$sql = "select DISTINCT(a.`goods_id`), a.`goods_id`, a.`goods_name`, a.`shop_price`, a.`goods_thumb`, a.`goods_number`, 
				a.`market_price`, a.`sales_volume`, a.`goods_brief`  from `dsc_goods` as a left join `dsc_goods_cat`  as b on a.`goods_id`  = b.`goods_id` 
				where (a.`cat_id` = '$categoryId' or b.`cat_id` = '$categoryId') and a.`is_on_sale` = 1 and a.`is_delete` = 0 order by a.`is_best` desc, a.`is_hot` desc, a.`is_new` desc limit $begin,$size";
			 
				$list2 = DB::select($sql);	
				
				//echo $sql;echo "<BR/>";echo "<BR/>";
				 
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
				
				//echo $subCateStr;echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";echo "<BR/>";
					//return $subCateStr;
					
				//	$list = $this->goodsRepository->findByIn('category', $subCateStr, $page, $size, $field, $keywords, $sortKey, $sortVal);
				
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
		
		//$list = $this->goodsRepository->findBy('category', $categoryId, $page, $size, $field, $keywords, $sortKey, $sortVal);
        //$list = $this->goodsRepository->findByIn('category', $categoryId, $page, $size, $field, $keywords, $sortKey, $sortVal);

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
	
	
	//getCateGoods
	public function getCateGoods()
	  {
		  
		  $res = $this->shopRepository->getCateGoods(); 
		  
		  return $res;
	  }
	 
	 /**
     *获取小程序导航
     * @return array
     */
	 
	  public function getNav()
	  {
		  
		  $res = $this->shopRepository->getNav();

		 
		  
		  return $res;
	  }
	  
	  
	   /**
     *获取小程序导航广告位
     * @return array
     */
	 
	  public function getBanner($navId)
	  {
		  
		  $res = $this->shopRepository->getBanner($navId); 
		  
		   //重组get_image_path 
		   for($i=0;$i<count($res);$i++)
		   {
			   $res[$i]['target_ad_image'] = get_image_path($res[$i]['ad_image']);
		   }
		  
		  return $res;
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
}
