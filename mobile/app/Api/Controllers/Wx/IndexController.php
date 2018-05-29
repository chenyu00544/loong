<?php


namespace App\Api\Controllers\Wx;

use App\Services\IndexService;

//use App\Services\GoodsService;

use App\Api\Controllers\Controller;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

/**
 * Class IndexController
 * @package App\Api\Controllers\Wx
 */
class IndexController extends Controller
{


    /** @var IndexService */
    private $indexService;

    //private $goodsService;


    /**
     * IndexController constructor.
     * @param IndexService $indexService
     */
    public function __construct(IndexService $indexService)//,GoodsService $goodsService
    {
        $this->indexService = $indexService;

        //$this->goodsService = $goodsService;
    }

    /////////////////////////加载更多商品，为0表示首页，其他的则是具体的分类。
    public function loadmore(Request $request)
    {

        $cate_id = $request->get('cate_id');
        $page_now = $request->get('page_now');

        $size = 10;

        $goodsList = null;

        if (empty($cate_id)) {
            $goodsList = $this->indexService->bestGoodsList($page_now);
        } else {
            $goodsList = $this->indexService->getGoodsList($cate_id, '', $page_now);
        }

        $data = array();

        $data['goods_list'] = $goodsList;

        $is_has_more = '0';//默认没有

        if (count($goodsList) == $size) {
            $is_has_more = '1';
        }

        $data['is_has_more'] = $is_has_more;

        return $this->apiReturn($data);
    }


    /**
     * 首页
     * @return mixed
     */
    public function index()
    {
        // 获取banner
        $banners = $this->indexService->getBanners();
        $data['banner'] = $banners;
        // 获取广告位
        $adsense = $this->indexService->getAdsense();
        $data['adsense'] = $adsense;
        // 获取推荐商品列表
        $goodsList = $this->indexService->bestGoodsList(1);
        $data['goods_list'] = $goodsList;

        $nav = $this->indexService->getNav();


        //////仅判断是否还有更多商品
        $size = 10;


        /////填充
        for ($i = 0; $i < count($nav); $i++) {
            $navId = $nav[$i]['id'];
            $nav[$i]['nav_id'] = $navId;//另外保存原始
            $nav[$i]['id'] = 'index' . $navId;
            $nav[$i]['text'] = $nav[$i]['name'];
            //$nav[$i]['goods_list'] = $goodsList;

            ///getBanner
            //$nav[$i]['ad_list'] = $this->indexService->getBanner($navId);

            $imageList = $this->indexService->getBanner($navId);
            $adList = array();
            $bannerList = array();
            //拆分两种风格广告。

            for ($j = 0; $j < count($imageList); $j++) {
                $adType = $imageList[$j]['ad_type'];//0 banner  1 goods


                if (empty($adType)) {
                    $bannerList[] = $imageList[$j];
                } else {
                    $adList[] = $imageList[$j];
                }
            }

            $nav[$i]['banner_list'] = $bannerList;

            $nav[$i]['ad_list'] = $adList;


            $navType = $nav[$i]['nav_type'];
            $goodsCateId = $nav[$i]['goods_cate_id'];

            /*
            if($navType == 1)
            {
                $nav[$i]['goods_list'] = $this->indexService->hotGoodsList();
            }
            else if($navType == 2)
            {
                $nav[$i]['goods_list'] = $goodsList;
            }
            else  if($navType == 3)
            {
                 $nav[$i]['goods_list'] = $this->indexService->newGoodsList();
            }


            else
           */

            /////////////重要，懒加载，这个懒加载为虚拟的懒加载，仅仅是客户端中一开始不显示，滑动到的时候才显示
            //服务器端的数据还是全部加载，因为主要的时间消耗在了客户端图片中，所以导致初始化速度非常慢

            if ($i == 0) {
                $nav[$i]['is_init'] = "1";
            } else {
                $nav[$i]['is_init'] = "0";
            }


            if ($navType == 5 && !empty($goodsCateId)) {
                //$this->indexService->newGoodsList();//
                $goodsListT = $this->indexService->getGoodsList($goodsCateId, '', 1);//$this->indexService->newGoodsList();//
                $nav[$i]['goods_list'] = $goodsListT;

                $is_has_more = '0';//默认没有

                if (count($goodsListT) == $size) {
                    $is_has_more = '1';
                }

                $nav[$i]['is_has_more'] = $is_has_more;
                $nav[$i]['page_now'] = 2;

                $nav[$i]['cate_id'] = $goodsCateId;

                ///追加变量，客户端用
                $nav[$i]['is_loading_more'] = '0';//是否正在加载中

                //$nav[$i]['test'] = $this->indexService->getGoodsList($goodsCateId);


                /*
                $this->goodsService->getGoodsList($goodsCateId, $request->get('keyword'),
                $request->get('page'), $request->get('per_page'), $request->get('sort_key'), $request->get('sort_value'),
                $request->get('warehouse_id'), $request->get('area_id'));
                */
                //$this->indexService->getGoodsList($goodsCateId);
            } else {
                $nav[$i]['goods_list'] = $goodsList;

                $is_has_more = '0';//默认没有

                if (count($goodsList) == $size) {
                    $is_has_more = '1';
                }

                $nav[$i]['is_has_more'] = $is_has_more;
                $nav[$i]['page_now'] = 2;    //相对应的是下一页 不是第一页

                $nav[$i]['cate_id'] = '0';

                ///追加变量，客户端用
                $nav[$i]['is_loading_more'] = '0';//是否正在加载中
            }

        }


        //获取首页弹出广告。
        $adIdT = $this->indexService->getDataListBySql("select * from dsc_touch_ad_wxapp where  show_page_type = 1  and is_show = 1 and ad_type <> 9  order by ad_sort asc limit 0,1 ");

        if (!empty($adIdT)) {
            //	$AdId = $adIdT[0]->ad_id;//$adIdT[0]['ad_id'];
            $adIdT[0]->ad_image = 'https://www.missmall.com/' . $adIdT[0]->ad_image;//get_image_path($adIdT[0]->ad_image);//'https://www.missmall.com/'.$adIdT[0]->ad_image;
            $data['dialog_ad'] = $adIdT[0];
            $data['is_dialog_ad'] = '1';
        } else {
            $data['is_dialog_ad'] = '0';
        }


        //以上，是之前的，之前的保留，现在获取每一个nav的广告。
        for ($i = 0; $i < count($nav); $i++) {
            $navId = $nav[$i]['nav_id'];


            $adSql = "select * from dsc_touch_ad_wxapp where  show_page_type = 1  and is_show = 1  and nav_id = '$navId' and ad_type <> 9 order by ad_sort asc limit 0,1 ";

            $adIdT = $this->indexService->getDataListBySql($adSql);

            //$nav[$i]['ad_sql'] = $adSql;

            if (!empty($adIdT)) {
                $adIdT[0]->ad_image = 'https://www.missmall.com/' . $adIdT[0]->ad_image;
                $nav[$i]['dialog_ad'] = $adIdT[0];
                $nav[$i]['is_dialog_ad'] = '1';
            } else {
                $nav[$i]['is_dialog_ad'] = '0';
            }


        }

        $data['nav_list'] = $nav;

        /*
        ///////测试
        $test =  $this->indexService->getDataListBySql("select value from dsc_shop_config  where code = 'site_domain' limit 0,1 ");

        //get_object_vars($test);

         $arr = array();

       //转化成数组
        foreach ($test as $key => $value)
        {
           $arr[$key] = $value;
       }

          $data['test'] = $arr;//$test[0]->value;



          $getSubSql = "select cat_id from dsc_category where parent_id = '1' ";

        $subList = $this->indexService->getDataListBySql($getSubSql);

        $arr2 = array();

         foreach ($subList as $key => $value)
        {
           $arr2[$key] = $value;
       }

       $data['subList'] = $arr2;

       */


        return $this->apiReturn($data);
    }

    /**
     * 小程序专题首页
     * @return mixed
     */
    public function sellerIndex(Request $request)
    {
        $this->validate($request, [
            'sellertoken' => 'required',
            'nav_id' => 'integer',
        ]);
        $data = [];
        $token = $request->get('sellertoken');
        $nav_id = $request->get('nav_id');
        $wxapp = DB::select("select * from dsc_wxapp where token =  '" . $token . "'  ");
        if (count($wxapp)) {
            $ru_id = $wxapp[0]->ru_id;
            $navs = DB::select("select * from dsc_wxapp_nav where ru_id =  '" . $ru_id . "' and ifshow = '1'");
            if ($nav_id == 0) {
                $nav_id = $navs[0]->id;
            }
            // 获取banner
            $banners = DB::select("select * from dsc_wxapp_slide_ads where ru_id =  '" . $ru_id . "'and nav_id = '" . $nav_id . "' and ifshow = '1' order by vieworder desc");
            foreach ($banners as $banner) {
                $banner->pic = get_image_path($banner->pic);
            }
            // 获取弹出广告
            $layerads = DB::select("select * from dsc_wxapp_layer_ads where ru_id =  '" . $ru_id . "' and nav_id = '" . $nav_id . "' and ifshow = '1' order by vieworder desc LIMIT 0,1 ");
            foreach ($layerads as $layerad) {
                $layerad->pic = get_image_path($layerad->pic);
            }
            // 获取卡式广告
            $cartads = DB::select("select * from dsc_wxapp_cart_ads where ru_id =  '" . $ru_id . "' and nav_id = '" . $nav_id . "' and ifshow = '1' order by vieworder desc LIMIT 0,10 ");
            foreach ($cartads as $cartad) {
                $cartad->pic = get_image_path($cartad->pic);
            }
            foreach ($navs as $value) {
                $value->is_loading_more = 0;
                $value->is_has_more = 1;
                $value->page = 2;
                if (count($layerads)) {
                    $value->is_dialog_ad = 1;
                } else {
                    $value->is_dialog_ad = 0;
                }
            }
            $data['nav_list'] = $navs;
            $data['ads'][$nav_id]['banners'] = $banners;
            $data['ads'][$nav_id]['layerads'] = $layerads;
            $data['ads'][$nav_id]['cartads'] = $cartads;
        }
        return $this->apiReturn($data);
    }

    /**
     * 小程序专题加载更多首页
     * @return mixed
     */
    public function sellerLoadmore(Request $request)
    {
        $this->validate($request, [
            'nav_id' => 'integer',
            'page' => 'integer',
            'sellertoken' => 'required'
        ]);
        $data = [];
        $token = $request->get('sellertoken');
        $page = $request->get('page') ? $request->get('page') : 1;
        $nav_id = $request->get('nav_id');
        $wxapp = DB::select("select * from dsc_wxapp where token =  '" . $token . "'  ");
        if (count($wxapp)) {
            $ru_id = $wxapp[0]->ru_id;
            // 获取卡式广告
            $cartads = DB::select("select * from dsc_wxapp_cart_ads where ru_id =  '" . $ru_id . "' and nav_id = '" . $nav_id . "' and ifshow = '1' order by vieworder desc LIMIT " . (($page - 1) * 10) . ",10 ");
            if (!count($cartads)) {
                $data['is_has_more'] = 0;
            }
            foreach ($cartads as $cartad) {
                $cartad->pic = get_image_path($cartad->pic);
            }
            $data['cartads'] = $cartads;
        }
        return $this->apiReturn($data);
    }

    public function addFrom(Request $request)
    {
        $this->validate($request, [
            'from_id' => 'required',
            'user_wxid' => 'required',
            'sellertoken' => 'required'
        ]);
        $token = $request->get('sellertoken');
        $wxapp = DB::select("select * from dsc_wxapp where token =  '" . $token . "'  ");
        if (count($wxapp)) {
            $ru_id = $wxapp[0]->ru_id;
        }
        $data['from_id'] = $request->get('from_id');
        $data['wxid'] = $request->get('user_wxid');
        $data['create_time'] = date('Y-m-d H:i:s');
        $data['create_time_stamp'] = time();
        $data['is_use'] = 0;
        $data['is_overdue'] = 0;
        $data['ru_id'] = $ru_id;

        $str_key = '';
        $str_val = '';
        foreach ($data as $key => $value) {
            $str_key .= $key . ',';
            $str_val .= "'" . $value . "',";
        }
        $str_key = substr($str_key, 0, -1);
        $str_val = substr($str_val, 0, -1);
        DB::insert("insert into dsc_wxapp_fromid ($str_key) values ($str_val)");
    }
}
