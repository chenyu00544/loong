<?php

namespace App\Modules\Team\Controllers;

use App\Extensions\Scws4;
use App\Modules\Base\Controllers\FrontendController;

class FreeController extends FrontendController
{
    private $sort = 'last_update';
    private $order = 'ASC';

    /**
     * 构造函数
     */
    public function __construct()
    {
        parent::__construct();
        L(require(LANG_PATH . C('shop.lang') . '/team.php'));
        $files = [
            'order',
            'clips',
            'payment',
            'transaction'
        ];
        $this->load_helper($files);
        $this->user_id = $_SESSION['user_id'];
        $this->goods_id = I('id', 0, 'intval');
        $this->tc_id = I('request.tc_id', 0, 'intval');
        $this->page = 1;
        $this->size = 10;
    }

    /**
     * 拼团首页
     */
    public function actionIndex()
    {
		//echo "FREE";exit;
		//$_SESSION['user_id'] = '77';
				/////////////////////////////////////////////////
		//查询用户可领取的优惠券类型。
		////如果没有登录  不处理   直接跳转到普通拼团首页
		if (empty($_SESSION['user_id'])) 
		 { 
            ecs_header("Location: " . url('team/index/index'));
            exit;
        }
		
		//$userId = $_SESSION['user_id'];
		
		//获取该用户所有的可以领取的团购代金券。
		//. " limit $start,$num" 
		$time = gmtime();   
		 $condition = 96;
		 
		 $sql = "select * from " . $GLOBALS['ecs']->table('coupons') . "where  cou_type = '$condition'  and  $time< cou_end_time and review_status = 3 and  $time > cou_start_time" ;
		$tab = $GLOBALS['db']->getAll($sql);
		
		//print_r($tab);
		
		//全部领取！
		 $uid = $_SESSION['user_id'];
        $ticket = 1;      // 默认每次领取一张优惠券
       
		
		for($i=0;$i<count($tab);$i++)
		{
			$cou_id =  $tab[$i]['cou_id'];
			
			 //会员等级判断
            $rank = $_SESSION['user_rank'];
            $sql_cou = "select cou_type,cou_ok_user from {pre}coupons where cou_id = '$cou_id'";
            $rest = $this->db->getRow($sql_cou);
            //等级
            $type = $rest['cou_type'];      //优惠券类型
            $cou_rank = $rest['cou_ok_user'];  //可以使用优惠券的rank
            $ranks = explode(",", $cou_rank);
            if ($type == 2 || $type == 4 && $ranks != 0) 
			{
                if (in_array($rank, $ranks)) 
				{
                    $this->getCoups($cou_id, $uid, $ticket);
                }
				else
				{
                   // die(json_encode(array('msg' => "非预定会员不可领取", 'error' => 5)));  //没有优惠券不能领取
                }
            } else 
			{
				//会执行这里。
                $this->getCoups($cou_id, $uid, $ticket);
            }
			
		}
		
		
		 
		///获取所有可以免单的商品的拼团  这里获取的是： 用户拥有的所有免单优惠券中，范围内的拼团商品，而不是某一张优惠券
		//换句话说，如果其中一张优惠券是所有商品都能用的！   那么，其实就是获取所有的拼团。
		//注意，在使用的时候，也需要加上判断，是否是在可用商品中的。
		//使用的时候，优先使用范围小的（只要不是全部商品即可） 中的第一张即可。
		
		//获取所有代金券。
		$getAllSql = 
		"
			select b.cou_goods from {pre}coupons_user as a 
			left join {pre}coupons as b 
			on a.cou_id = b.cou_id 
			where a.user_id = '$uid' and  a.is_use = 0 and ( b.cou_type = 95 or b.cou_type = 96 )
		";
		
		//echo $getAllSql;
		
		$couList = $GLOBALS['db']->getAll($getAllSql);
		
		//print_r($couList);
		$isAll = false;// 是否是全部商品
		$allGoodsIdArray = array();
		$allGoodsIdStr = "";
		
		for($i=0;$i<count($couList);$i++)
		{
			$cou_goods = $couList[$i]['cou_goods'];
			
			if(empty($cou_goods))//这个经常会进入  所以设置的时候，用户等级一定要设置
			{
				$isAll = true;
				break;
			}
			else
			{
				if(empty($allGoodsIdStr))
				{
					$allGoodsIdStr .= $cou_goods;
				}
				else
				{
					$allGoodsIdStr .= ','.$cou_goods;
				}
				
			}
		}
		
		//echo $allGoodsIdStr;exit;
		if($isAll)
		{
			 
			///直接跳默认的即可。
			ecs_header("Location: " . url('team/index/index'));
            exit;
			 
		}
		
		if(empty($allGoodsIdStr))
		{
			ecs_header("Location: " . url('team/index/index'));
            exit;
		}
		
		//echo $allGoodsIdStr;
		
		//获取范围内的拼团商品。
		   $where .= "  g.is_on_sale = 1 AND g.is_alone_sale = 1 AND g.is_delete = 0 AND g.review_status>2 and tg.is_team = 1 and tg.is_audit = 2 and g.goods_id in ($allGoodsIdStr) ";
			/*
		   if ($tc_id > 0) 
			{
                $one = dao('team_category')->field('id')->where('id =' . $tc_id . ' or parent_id=' . $tc_id)->select();
                if ($one) {
                    foreach ($one as $key) {
                        $one_id[] = $key['id'];
                    }
                    $tc_id = implode(',', $one_id);
                    $where .= " and tg.tc_id in ($tc_id) ";
                }
            }
			*/
           
            $sql = 'SELECT g.goods_id,g.user_id, g.goods_name, g.shop_price, g.goods_name_style, g.comments_number, g.sales_volume, g.market_price, g.goods_thumb ,
			g.goods_img, tg.team_price, tg.team_num FROM ' . $this->ecs->table('team_goods') . 'AS tg LEFT JOIN ' . $this->ecs->table('goods') . ' AS g ON tg.goods_id = g.goods_id ' .
                "WHERE $where ";
            $goods_list = $this->db->query($sql);
			
			//echo $sql;
		
		/*
			SELECT g.goods_id,g.user_id, g.goods_name, g.shop_price, g.goods_name_style, g.comments_number, g.sales_volume, g.market_price, g.goods_thumb ,
			g.goods_img, tg.team_price, tg.team_num FROM dsc_team_goods AS tg LEFT JOIN dsc_goods AS g ON tg.goods_id = g.goods_id WHERE 
			g.is_on_sale = 1 AND g.is_alone_sale = 1 AND g.is_delete = 0 AND g.review_status>2 and tg.is_team = 1 and tg.is_audit = 2 and g.goods_id in (2966,2971)
		*/
	
			
		//print_r($goods_list);
		
		  for ($i=0;$i<count($goods_list);$i++) 
		  {
                $goods_list[$i]['goods_img'] = get_image_path($goods_list[$i]['goods_img']); 
            }
		
		 $this->assign('goods_list', $goods_list);
		 
		// print_r($goods_list);
		
		 $this->display();
		///////////////////////////////////
		/*
        // 获取主频道
        $team_categories = team_categories();
        $this->assign('team_categories', $team_categories);

        // 验证首页下单提示轮播是否开启
        $sql = 'SELECT value FROM ' . $this->ecs->table('shop_config') . " WHERE code = 'virtual_order'";
        $is_open = $this->db->getRow($sql);
        if ($is_open['value'] == 1) {
            $this->assign('is_open', 1);
        }
        $this->assign('page_title', '全部');
        $this->display();
		*/
    }
	
	
	 /**
     *  获取优惠券  拷贝自：Coupont/Controllers/Index.php  
     * @param type $cou_id
     * @param type $uid
     */
    public function getCoups($cou_id, $uid, $ticket)
    {
        $time = gmtime();
        $sql = "SELECT c.*,c.cou_total-COUNT(cu.cou_id) cou_surplus FROM {pre}coupons c LEFT JOIN {pre}coupons_user cu ON c.cou_id=cu.cou_id GROUP BY c.cou_id  HAVING cou_surplus>0 AND c.review_status = 3 AND c.cou_id='" . $cou_id . "' AND c.cou_end_time>$time limit 1";
        $total = $this->db->getRow($sql);
		
        if (!empty($total)) 
		{
            $sql = "select count(cou_id) as num from {pre}coupons_user where user_id = '$uid' and  cou_id = '$cou_id'";
            $num = $this->db->getOne($sql);
            $sql = "select cou_user_num from {pre}coupons where cou_id = '$cou_id'";
            $res = $this->db->getOne($sql);
            //判断是否已经领取了,并且还没有使用(根据创建优惠券时设定的每人可以领取的总张数为准,防止超额领取)
            if ($res > $num) 
			{
                //领取优惠券
                $sql3 = "INSERT INTO {pre}coupons_user (`user_id`,`cou_id`,`uc_sn`) VALUES ($uid,$cou_id,$time ) ";
				
                if ($GLOBALS['db']->query($sql3)) 
				{
                   // die(json_encode(array('msg' => "领取成功！感谢您的参与，祝您购物愉快", 'error' => 2)));  //领取成功！感谢您的参与，祝您购物愉快
                }
            } 
			else 
			{
               // die(json_encode(array('msg' => '领取失败,您已经领取过该券了!每人限领取' . $res . '张', 'error' => 3)));
            }
        }
		else 
		{
            //die(json_encode(array('msg' => "优惠券已领完", 'error' => 4)));  //没有优惠券不能领取
        }
    }
	
	

     /**
      * 拼团频道
      */
    public function actionList()
    {
        $tc_id = I('tc_id', 0, 'intval');
        // 获取主频道
        $team_categories = team_categories();
        $this->assign('team_categories', $team_categories);
        if ($tc_id == 0) {
            foreach ($team_categories as $key => $var) {
                if ($key == 0) {
                    $tc_id = $var['id'];
                }
            }
        }
        // 获取子频道
        $this->assign('team_child', team_get_child_tree($tc_id));
        // 获取频道名称
        $shop = dao('team_category')->field('name')->where(['id' => $tc_id])->find();

        // 验证首页下单提示轮播是否开启
        $sql = 'SELECT value FROM ' . $this->ecs->table('shop_config') . " WHERE code = 'virtual_order'";
        $is_open = $this->db->getRow($sql);
        if ($is_open['value'] == 1) {
            $this->assign('is_open', 1);
        }
        $this->assign('tc_id', $tc_id);
        $this->assign('page_title', $shop['name']);
        $this->display();
    }

    /**
     * ajax拼团频道商品列表
     */
    public function actionTeamlist()
    {
        $this->page = I('page', 1, 'intval');
        $tc_id = I('request.tc_id', 0, 'intval');
        if (IS_AJAX) {
            $where .= " g.is_on_sale = 1 AND g.is_alone_sale = 1 AND g.is_delete = 0 AND g.review_status>2 and tg.is_team = 1 and tg.is_audit = 2 ";
            if ($tc_id > 0) {
                $one = dao('team_category')->field('id')->where('id =' . $tc_id . ' or parent_id=' . $tc_id)->select();
                if ($one) {
                    foreach ($one as $key) {
                        $one_id[] = $key['id'];
                    }
                    $tc_id = implode(',', $one_id);
                    $where .= " and tg.tc_id in ($tc_id) ";
                }
            }
            $arr = [];
            $sql = 'SELECT g.goods_id,g.user_id, g.goods_name, g.shop_price, g.goods_name_style, g.comments_number, g.sales_volume, g.market_price, g.goods_thumb , g.goods_img, tg.team_price, tg.team_num FROM ' . $this->ecs->table('team_goods') . 'AS tg LEFT JOIN ' . $this->ecs->table('goods') . ' AS g ON tg.goods_id = g.goods_id ' .
                "WHERE $where ";
            $goods_list = $this->db->query($sql);
            $total = is_array($goods_list) ? count($goods_list) : 0;
            $res = $this->db->selectLimit($sql, $this->size, ($this->page - 1) * $this->size);
            foreach ($res as $key => $val) {
                $arr[$key]['goods_id'] = $val['goods_id'];
                $arr[$key]['user_id'] = $val['user_id'];
                $arr[$key]['goods_name'] = $val['goods_name'];
                $arr[$key]['shop_price'] = price_format($val['shop_price']);
                $arr[$key]['goods_img'] = get_image_path($val['goods_img']);
                $arr[$key]['goods_thumb'] = get_image_path($val['goods_thumb']);
                $arr[$key]['url'] = url('team/goods/index', ['id' => $val['goods_id']]);
                $arr[$key]['team_price'] = price_format($val['team_price']);
                $arr[$key]['team_num'] = $val['team_num'];
            }

            exit(json_encode(['list' => array_values($arr), 'totalPage' => ceil($total / $this->size)]));
        }
    }

    //首页下单提示轮播
    public function actionvirtualorder()
    {
        //格式化返回数组
        $arr = [
            'err_msg' => '',
            'name' => '',
            'avatar' => '',
            'seconds' => ''
        ];
        $sql = 'SELECT value FROM ' . $this->ecs->table('shop_config') . " WHERE code = 'virtual_order'";
        $is_open = $this->db->getRow($sql);
        if ($is_open['value'] == 1) {
            $_SESSION['user_id'] = !empty($_SESSION['user_id']) ? $_SESSION['user_id'] : 0;
            $sql = 'SELECT user_name, user_id FROM ' . $this->ecs->table('users') . " WHERE user_id <> " . $_SESSION['user_id'] . " ORDER BY rand() LIMIT 1";
            $user = $this->db->getRow($sql);
            if ($user) {

                //用户名、头像
                $user_nick = get_user_default($user['user_id']);
                $arr['name'] = encrypt_username($user_nick['nick_name']);
                $arr['avatar'] = $user_nick['user_picture'];

                //随机秒数
                $arr['seconds'] = rand(1, 8) . "秒前";
            } else {
                $arr ['err_no'] = 1;
            }
        } else {
            $arr ['err_no'] = 1;
        }
        die(json_encode($arr));
    }

    /**
     * 拼团子频道商品列表
     */
    public function actionCategory()
    {
        $this->init_params();
        if (IS_AJAX) {
            $goods_list = team_category_goods($this->tc_id, $this->keywords, $this->size, $this->page, $this->intro, $this->sort, $this->order, $this->brand, $this->price_min, $this->price_max);
            exit(json_encode(['list' => $goods_list['list'], 'totalPage' => $goods_list['totalpage']]));
        }
        // 获取频道名称
        if ($this->tc_id > 0) {
            $shop = dao('team_category')->field('name')->where(['id' => $this->tc_id])->find();
            $this->assign('page_title', $shop['name']);
        } else {
            $this->assign('page_title', L('team_keywords_result'));
        }

        $this->display();
    }


    /**
     * 拼团排行
     */
    public function actionUserranking()
    {
        $this->page = I('page', 1, 'intval');
        $type = isset($_REQUEST ['type']) ? $_REQUEST ['type'] : 'limit_num';
        if (IS_AJAX) {
            $goods_list = team_goods($this->size, $this->page, $type);
            exit(json_encode(['list' => $goods_list['list'], 'totalPage' => $goods_list['totalpage']]));
        }
        $this->assign('type', $type);
        $this->assign('page_title', L('ranking_list'));
        $this->display();
    }

    /**
     * 过滤参数
     */
    private function init_params()
    {
        //$page_size = C('shop.page_size');
        //关键词查询
        $keyword = I('request.keyword');
        if (!empty($keyword)) {
            //分词搜索
            $scws = new Scws4();
            $keyword_segmentation = $scws->segmentate($keyword, true);

            $keywordArr = explode(',', $keyword_segmentation);

            $this->keywords = 'AND (';
            $addAll = [];
            foreach ($keywordArr as $keywordKey => $keywordVal) {
                if ($keywordKey > 0) {
                    $this->keywords .= ' AND ';
                }

                $val = mysql_like_quote(trim($keywordVal));
                $this->keywords .= "(g.goods_name LIKE '%$val%' OR g.goods_sn LIKE '%$val%' OR g.keywords LIKE '%$val%')";

                //
                $valArr[] = $val;
                /**
                 * 处理关键字查询次数
                 */
                $data = ['date' => local_date('Y-m-d'), 'searchengine' => 'ECTouch', 'keyword' => addslashes(str_replace('%', '', $val)), 'count' => 1];
                $condition['date'] = local_date('Y-m-d');
                $condition['searchengine'] = 'ECTouch';
                $condition['keyword'] = addslashes(str_replace('%', '', $val));
                $set = $this->db->table('keywords')->where($condition)->find();
                //
                if (!empty($set)) {
                    $data['count'] = $set['count'] + 1;
                }
                $addAll[] = $data;
            }
            //处理关键字查询次数
            $this->db->addAll($addAll, ['table' => $this->ecs->table('keywords')], true);
            //
            $this->keywords .= ')';

            $goods_ids = [];
            $valArrWhere = ' 1';
            foreach ($valArr as $v) {
                $valArrWhere .= " OR tag_words LIKE '%$v%' ";
            }

            /*记录搜索历史记录*/
            $history = '';
            if (!empty($_COOKIE['ECS']['keywords'])) {
                $history = explode(',', $_COOKIE['ECS']['keywords']);
                array_unshift($history, $keyword); //在数组开头插入一个或多个元素
                $history = array_unique($history);  //移除数组中的重复的值，并返回结果数组。
                cookie('ECS[keywords]', implode(',', $history));
            } else {
                cookie('ECS[keywords]', $keyword);
            }
            $this->assign('history_keywords', $history);
        }
        //属性查询
        $filter_attr_str = I('request.filter_attr', 0);
        if ($filter_attr_str) {
            $filter_attr_str = trim(urldecode($filter_attr_str));
            $filter_attr_str = preg_match('/^[\d,\.,\-,\,]+$/', $filter_attr_str) ? $filter_attr_str : '';
            $filter_attr_reset = explode('.', $filter_attr_str);
            if ($filter_attr_reset) {
                foreach ($filter_attr_reset as $k => $v) {
                    $tmp_attr = explode('-', $v);
                    $this->filter_attr[$tmp_attr[0]] = $tmp_attr[1];
                }
            }
        }

        $this->size = 10;
        $asyn_last = I('request.last', 0, 'intval') + 1;
        $this->page = I('request.page', 1, 'intval');
        $this->brand = I('request.brand', 0, 'intval');
        $this->intro = I('request.intro');
        $this->price_min = I('request.price_min', 0, 'intval');
        $this->price_max = I('request.price_max', 0, 'intval');
        $this->isself = I('request.isself', 0, 'intval');
        $this->hasgoods = I('request.hasgoods', 0, 'intval');
        $this->promotion = I('request.promotion', 0, 'intval');

        /* 排序、显示方式以及类型 */
        $default_display_type = C('shop.show_order_type') == '0' ? 'list' : (C('shop.show_order_type') == '1' ? 'grid' : 'text');
        $default_sort_order_type = C('shop.sort_order_type') == '0' ? 'goods_id' : (C('shop.sort_order_type') == '1' ? 'team_price' : 'last_update');
        $default_sort_order_method = C('shop.sort_order_method') == '0' ? 'desc' : 'asc';
        $sort_array = ['goods_id', 'team_price', 'last_update', 'sales_volume'];
        $order_array = ['asc', 'desc'];
        $display_array = ['list', 'grid', 'text'];
        $goods_sort = I('request.sort');
        $goods_order = I('request.order');
        $goods_display = I('request.display');
        $this->sort = in_array($goods_sort, $sort_array) ? $goods_sort : $default_sort_order_type;
        $this->order = in_array($goods_order, $order_array) ? $goods_order : $default_sort_order_method;
        $this->display = in_array($goods_display, $display_array) ? $goods_display : (isset($_COOKIE['ECS']['display']) ? $_COOKIE['ECS']['display'] : $default_display_type);
        cookie('ECS[display]', $this->display);
        //ecmoban模板堂 --zhuo start
        $sql = "select parent_id from " . $this->ecs->table('category') . " where cat_id = '$this->cat_id'";
        $parent_id = $this->db->getOne($sql);
        $sql = "select parent_id from " . $this->ecs->table('category') . " where cat_id = '$parent_id'";
        $parentCat = $this->db->getOne($sql);

        $province_id = isset($_COOKIE['province']) ? $_COOKIE['province'] : 0;
        $area_info = get_area_info($province_id);
        $this->area_id = $area_info['region_id'];

        $where = "regionId = '$province_id'";
        $date = ['parent_id'];
        $this->region_id = get_table_date('region_warehouse', $where, $date, 2);

        if (isset($_COOKIE['region_id']) && !empty($_COOKIE['region_id'])) {
            $this->region_id = $_COOKIE['region_id'];
        }
        //ecmoban模板堂 --zhuo end
        //$this->children = get_children($this->cat_id);
        // 获得分类的相关信息
        //$this->cat = get_cat_info($this->cat_id);
        /* 获取价格分级 */
        if ($this->cat['grade'] == 0 && $this->cat['parent_id'] != 0) {
            $this->cat['grade'] = get_parent_grade($this->cat_id); //如果当前分类级别为空，取最近的上级分类
        }
        //ecmoban模板堂 --zhuo start
        $leftJoin = '';

        $tag_where = '';
        if (C('shop.open_area_goods') == 1) { //关联地区显示商品
            $leftJoin .= " left join " . $this->ecs->table('link_area_goods') . " as lag on g.goods_id = lag.goods_id ";
            $tag_where = " and lag.region_id = '$this->area_id' ";
        }

        //ecmoban模板堂 --zhuo end
        if ($this->cat['grade'] > 1) {
            /* 需要价格分级 */

            /*
                算法思路：
                    1、当分级大于1时，进行价格分级
                    2、取出该类下商品价格的最大值、最小值
                    3、根据商品价格的最大值来计算商品价格的分级数量级：
                            价格范围(不含最大值)    分级数量级
                            0-0.1                   0.001
                            0.1-1                   0.01
                            1-10                    0.1
                            10-100                  1
                            100-1000                10
                            1000-10000              100
                    4、计算价格跨度：
                            取整((最大值-最小值) / (价格分级数) / 数量级) * 数量级
                    5、根据价格跨度计算价格范围区间
                    6、查询数据库

                可能存在问题：
                    1、
                    由于价格跨度是由最大值、最小值计算出来的
                    然后再通过价格跨度来确定显示时的价格范围区间
                    所以可能会存在价格分级数量不正确的问题
                    该问题没有证明
                    2、
                    当价格=最大值时，分级会多出来，已被证明存在
             */

            //ecmoban模板堂 --zhuo start
            $mm_shop_price = "wg.warehouse_price, wg.warehouse_promote_price, wag.region_price, wag.region_promote_price, g.model_price, g.model_attr ";
            $leftJoin .= " left join " . $this->ecs->table('warehouse_goods') . " as wg on g.goods_id = wg.goods_id and wg.region_id = '$region_id' ";
            $leftJoin .= " left join " . $this->ecs->table('warehouse_area_goods') . " as wag on g.goods_id = wag.goods_id and wag.region_id = '$this->area_id' ";
            //ecmoban模板堂 --zhuo end

            $sql = "SELECT min(IF(g.model_price < 1, g.shop_price, IF(g.model_price < 2, wg.warehouse_price, wag.region_price))) AS min, " .
                " max(IF(g.model_price < 1, g.shop_price, IF(g.model_price < 2, wg.warehouse_price, wag.region_price))) as max " .
                " FROM " . $this->ecs->table('goods') . " AS g " .
                $leftJoin .
                " WHERE ($this->children OR " . get_extension_goods($this->children) . ') AND g.is_delete = 0 AND g.is_on_sale = 1 AND g.is_alone_sale = 1' . $tag_where;
            //获得当前分类下商品价格的最大值、最小值
            $row = $this->db->getRow($sql);

            // 取得价格分级最小单位级数，比如，千元商品最小以100为级数
            $price_grade = 0.0001;
            for ($i = -2; $i <= log10($row['max']); $i++) {
                $price_grade *= 10;
            }

            //跨度
            $dx = ceil(($row['max'] - $row['min']) / ($this->cat['grade']) / $price_grade) * $price_grade;
            if ($dx == 0) {
                $dx = $price_grade;
            }

            for ($i = 1; $row['min'] > $dx * $i; $i++) ;

            for ($j = 1; $row['min'] > $dx * ($i - 1) + $price_grade * $j; $j++) ;
            $row['min'] = $dx * ($i - 1) + $price_grade * ($j - 1);

            for (; $row['max'] >= $dx * $i; $i++) ;
            $row['max'] = $dx * ($i) + $price_grade * ($j - 1);

            $sql = "SELECT (FLOOR((IF(g.model_price < 1, g.shop_price, IF(g.model_price < 2, wg.warehouse_price, wag.region_price)) - $row[min]) / $dx)) AS sn, COUNT(*) AS goods_num  " .
                " FROM " . $this->ecs->table('goods') . " AS g " . $leftJoin .
                " WHERE ($this->children OR " . get_extension_goods($this->children) . ') AND g.is_delete = 0 AND g.is_on_sale = 1 AND g.is_alone_sale = 1' .
                " GROUP BY sn ";

            $price_grade = $this->db->getAll($sql);
            foreach ($price_grade as $key => $val) {
                if ($val['sn'] != '') {
                    $temp_key = $key;
                    $price_grade[$temp_key]['goods_num'] = $val['goods_num'];
                    $price_grade[$temp_key]['start'] = $row['min'] + round($dx * $val['sn']);
                    $price_grade[$temp_key]['end'] = $row['min'] + round($dx * ($val['sn'] + 1));
                    $price_grade[$temp_key]['price_range'] = $price_grade[$temp_key]['start'] . '&nbsp;-&nbsp;' . $price_grade[$temp_key]['end'];
                    $price_grade[$temp_key]['formated_start'] = price_format($price_grade[$temp_key]['start']);
                    $price_grade[$temp_key]['formated_end'] = price_format($price_grade[$temp_key]['end']);
                    $price_grade[$temp_key]['url'] = build_uri('category', ['id' => $this->cat_id, 'bid' => $this->brand, 'price_min' => $price_grade[$temp_key]['start'], 'price_max' => $price_grade[$temp_key]['end'], 'filter_attr' => $filter_attr_str], $this->cat['cat_name']);

                    /* 判断价格区间是否被选中 */
                    if (isset($_REQUEST['price_min']) && $price_grade[$temp_key]['start'] == $this->price_min && $price_grade[$temp_key]['end'] == $this->price_max) {
                        $price_grade[$temp_key]['selected'] = 1;
                    } else {
                        $price_grade[$temp_key]['selected'] = 0;
                    }
                }
            }

            //价格分级
            $this->assign('price_grade', $price_grade);
        }
        if (empty($row)) {
            $row['min'] = 0;
            $row['max'] = 10000;
        }
        //最大最小值范围
        $this->assign('price_range', $row);

        $brand_tag_where = '';
        $brand_leftJoin = '';
        if (C('shop.open_area_goods') == 1) {
            //关联地区显示商品
            $brand_select = " , ( SELECT COUNT(*) FROM " . $GLOBALS['ecs']->table('link_area_goods') . " as lag WHERE lag.goods_id = g.goods_id AND lag.region_id = '$this->area_id' LIMIT 1) AS area_goods_num ";
            $where_having = " AND area_goods_num > 0 ";
        }

        if (C('shop.review_goods') == 1) {
            $brand_tag_where .= ' AND g.review_status > 2 ';
        }
        /* 平台品牌筛选 */
        $sql = "SELECT b.brand_id, b.brand_name, b.brand_logo, COUNT(*) AS goods_num " . $brand_select .
            "FROM " . $GLOBALS['ecs']->table('brand') . "AS b " .
            " LEFT JOIN " . $GLOBALS['ecs']->table('goods') . " AS g ON g.brand_id = b.brand_id AND g.is_on_sale = 1 AND g.is_alone_sale = 1 AND g.is_delete = 0 $brand_tag_where " .
            " LEFT JOIN " . $GLOBALS['ecs']->table('team_goods') . " AS tg ON g.goods_id = tg.goods_id " .
            " WHERE tg.tc_id in ($this->tc_id)  AND b.is_show = 1 " .
            "GROUP BY b.brand_id HAVING goods_num > 0 $where_having ORDER BY b.sort_order, b.brand_id ASC";
        $brands = $GLOBALS['db']->getAll($sql);

        $brands_selected = explode(',', $this->brand);
        foreach ($brands as $key => $val) {
            $temp_key = $key + 1;
            $brands[$temp_key]['brand_id'] = $val['brand_id']; // 同步绑定品牌名称和品牌ID
            $brands[$temp_key]['brand_name'] = $val['brand_name'];
            $brands[$temp_key]['url'] = url('products', [
                'id' => $this->cat_id,
                'bid' => $val['brand_id'],
                'price_min' => $this->price_min,
                'price_max' => $this->price_max,
                'filter_attr' => $this->filter_attr
            ]);

            /* 判断品牌是否被选中 */
            if (in_array($val['brand_id'], $brands_selected)) {             // 修正当前品牌的ID
                $brands[$temp_key]['selected'] = 1;
            } else {
                $brands[$temp_key]['selected'] = 0;
            }
        }
        unset($brands[0]); // 清空索引为0的项目
        $brands[0]['brand_id'] = 0; // 新增默认值
        $brands[0]['brand_name'] = L('all_attribute');
        $brands[0]['url'] = url('products', [
            'cid' => $this->cat_id,
            'bid' => 0,
            'price_min' => $this->price_min,
            'price_max' => $this->price_max,
            'filter_attr' => $this->filter_attr
        ]);
        $brands[0]['selected'] = empty($this->brand) ? 1 : 0;

        ksort($brands);
        $this->assign('brands', $brands);
        if (!empty($this->brand)) {
            $sql = "SELECT brand_name FROM " . $this->ecs->table('brand') . " WHERE brand_id in($this->brand)";
            $brand_name_arr = $this->db->getCol($sql);
            $brand_name = implode('、', $brand_name_arr);
        } else {
            $brand_name = L('all_attribute');
        }
        $this->assign('brand_name', $brand_name);
        $this->ubrand = I('request.ubrand', 0, 'intval');
        $this->assign('ubrand', $this->ubrand);

        /* 属性筛选 */
        $this->ext = ''; // 商品查询条件扩展
        if ($this->cat['filter_attr'] > 0) {
            // 提取出此分类的筛选属性
            $this->cat_filter_attr = explode(',', $this->cat['filter_attr']);
            $all_attr_list = [];
            foreach ($this->cat_filter_attr as $key => $value) {
                $sql = "SELECT a.attr_name, attr_cat_type FROM " . $this->ecs->table('attribute') . " AS a, " . $this->ecs->table('goods_attr') . " AS ga left join  " . $this->ecs->table('goods') . " AS g on g.goods_id = ga.goods_id " . $leftJoin . " WHERE ($this->children OR " . get_extension_goods($this->children) . ") AND a.attr_id = ga.attr_id AND g.is_delete = 0 AND g.is_on_sale = 1 AND g.is_alone_sale = 1 AND a.attr_id='$value'" . $tag_where;
                $attributeInfo = $this->db->getRow($sql);
                if ($attributeInfo) {
                    $all_attr_list[$key]['filter_attr_name'] = $attributeInfo['attr_name'];
                    $all_attr_list[$key]['attr_cat_type'] = $attributeInfo['attr_cat_type'];

                    $all_attr_list[$key]['filter_attr_id'] = $value; //by zhang

                    $sql = "SELECT a.attr_id, MIN(a.goods_attr_id ) AS goods_id, a.attr_value AS attr_value, a.color_value FROM " . $this->ecs->table('goods_attr') . " AS a, " . $this->ecs->table('goods') .
                        " AS g" .
                        " WHERE ($this->children OR " . get_extension_goods($this->children) . ') AND g.goods_id = a.goods_id AND g.is_delete = 0 AND g.is_on_sale = 1 AND g.is_alone_sale = 1 ' .
                        " AND a.attr_id='$value' " .
                        " GROUP BY a.attr_value";

                    $attr_list = $this->db->getAll($sql);

                    $temp_arrt_url_arr = [];
                    //获取当前url中已选择属性的值，并保留在数组中
                    for ($i = 0; $i < count($this->cat_filter_attr); $i++) {
                        $temp_arrt_url_arr[$i] = !empty($this->filter_attr[$i]) ? $this->filter_attr[$i] : 0;
                    }

                    // “全部”的信息生成
                    $temp_arrt_url_arr[$key] = 0;
                    $temp_arrt_url = implode('.', $temp_arrt_url_arr);
                    // 默认数值
                    $all_attr_list[$key]['attr_list'][0]['attr_id'] = 0;
                    $all_attr_list[$key]['attr_list'][0]['attr_value'] = L('all_attribute');
                    $all_attr_list[$key]['attr_list'][0]['url'] = url('products', [
                        'id' => $this->cat_id,
                        'bid' => $this->brand,
                        'price_min' => $this->price_min,
                        'price_max' => $this->price_max,
                        'filter_attr' => $temp_arrt_url
                    ]);
                    $all_attr_list[$key]['attr_list'][0]['selected'] = empty($this->filter_attr[$key]) ? 1 : 0;
                    $all_attr_list[$key]['select_attr_name'] = L('all_attribute');

                    foreach ($attr_list as $k => $v) {
                        $temp_key = $k + 1;
                        // 为url中代表当前筛选属性的位置变量赋值,并生成以‘.’分隔的筛选属性字符串
                        $temp_arrt_url_arr[$key] = $v['goods_id'];
                        $temp_arrt_url = implode('.', $temp_arrt_url_arr);

                        $all_attr_list[$key]['attr_list'][$temp_key]['attr_id'] = $v['goods_id']; // 新增属性参数
                        $all_attr_list[$key]['attr_list'][$temp_key]['attr_value'] = $v['attr_value'];
                        $all_attr_list[$key]['attr_list'][$temp_key]['url'] = url('products', [
                            'id' => $this->cat_id,
                            'bid' => $this->brand,
                            'price_min' => $this->price_min,
                            'price_max' => $this->price_max,
                            'filter_attr' => $temp_arrt_url
                        ]);

                        if (!empty($this->filter_attr[$key]) && $this->filter_attr[$key] == $v['goods_id']) {
                            $all_attr_list[$key]['attr_list'][$temp_key]['selected'] = 1;
                            $all_attr_list[$key]['select_attr_name'] = $v['attr_value'];
                        } else {
                            $all_attr_list[$key]['attr_list'][$temp_key]['selected'] = 0;
                        }
                    }
                }
            }

            $this->assign('filter_attr_list', $all_attr_list);

            // 扩展商品查询条件
            if (!empty($this->filter_attr)) {
                $ext_sql = "SELECT DISTINCT(b.goods_id) as dis FROM " . $this->ecs->table('goods_attr') . " AS a, " . $this->ecs->table('goods_attr') . " AS b " . "WHERE ";
                $ext_group_goods = [];
                // 查出符合所有筛选属性条件的商品id
                foreach ($this->filter_attr as $k => $v) {
                    unset($ext_group_goods);
                    if (!empty($v) && isset($this->cat_filter_attr[$k])) {
                        $sql = $ext_sql . "b.attr_value = a.attr_value AND b.attr_id = " . $this->cat_filter_attr[$k] . " AND a.goods_attr_id in ($v)";
                        $res = $this->db->query($sql);
                        foreach ($res as $value) {
                            $ext_group_goods[] = $value['dis'];
                        }
                        $this->ext .= ' AND ' . db_create_in($ext_group_goods, 'g.goods_id');
                    }
                }
            }
        }

        //自营商品
        if ($this->isself) {
            $this->ext .= ' AND g.user_id = 0';
        }
        $this->assign('show_marketprice', C('shop.show_marketprice'));
        $this->assign('category', $this->tc_id);
        $this->assign('brand_id', $this->brand);
        $this->assign('price_min', $this->price_min);
        $this->assign('price_max', $this->price_max);
        $this->assign('isself', $this->isself);
        $this->assign('filter_attr', $filter_attr_str);
        $this->assign('parent_id', $parent_id);
        $this->assign('parentCat', $parentCat);
        $this->assign('region_id', $this->region_id);
        $this->assign('area_id', $this->area_id);
        $this->assign('page', $this->page);
        $this->assign('size', $this->size);
        $this->assign('sort', $this->sort);
        $this->assign('order', $this->order);
        $this->assign('keywords', $keyword);
        $this->assign('intro', $this->intro);
        $this->assign('hasgoods', $this->hasgoods);
        $this->assign('promotion', $this->promotion);
    }
}
