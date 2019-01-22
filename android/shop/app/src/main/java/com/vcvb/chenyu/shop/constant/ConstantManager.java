package com.vcvb.chenyu.shop.constant;

import android.os.Environment;

import com.vcvb.chenyu.shop.R;

public interface ConstantManager {

    public String VERSION = "1.0.0";
    public int REGISTER_FOR_MY = 1001;

    interface CheckToken {

        public String FUNCTION = "XXXXXX";

        public String USERNAME = "XXXXX";

        public String ACCESS_TOKEN = "XXXXXXX";

        public String ACCESS_ID = "XXXXXXX";
    }

    interface ImgPath {
        public String PATH = Environment.getExternalStorageDirectory() + "/Android/data/com.vcvb"
                + ".chenyu" + ".shop/files/";
    }

    interface ResultStatus {
        public int ADDRESSRESULT = 1002;
        public int ADD_ADDRESS_RESULT = 1003;
        public int ADD_ORDER_RESULT = 1004;
        public int COLLECT_RESULT = 1005;
        public int PHONE = 1006;
        public int MSG_RESULT = 1007;
        public int ORDER_RESULT = 1008;
        public int RORDER_RESULT = 1009;
        public int EVA_ORDER_RESULT = 1010;
    }

    interface PhotoAlbum {
        public int PHOTOALBUM_REQUEST = 2001;
        public int REQUEST_IMAGE_CAPTURE = 2002;
        public int CROP_PHOTO = 2003;
    }

    interface User {

        public String LOGO = "LOGO";

        public int NICKNAME = 3001;

        public int USERINFO = 3002;

        public String ACCESS_ID = "XXXXXXX";
    }

    interface IsFrom {
        public int FROM_HOME = 4001;
        public int FROM_CATEGORY = 4002;
        public int FROM_SEARCHINFO = 4003;
    }

    interface Pay {
        public int PAY_SUCCESS = 5001;
    }

    interface Menu {
        public int HOME = R.id.view33;
        public int MESSAGE = R.id.view35;
        public int CART = R.id.view36;
    }

    interface Nav {
        public int BACK = R.id.nav_back;
        public int BACK_1 = R.id.imageView23;
        public int MORE = R.id.more;
        public int MORE_1 = R.id.imageView94;
        public int COLLECTION = R.id.collection;
        public int ADD = R.id.textView122;
        public int SWITCH = R.id.imageView74;
        public int SEARCH = R.id.view43;
        public int SEARCHBOTTON = R.id.textView153;
        public int SETUP = R.id.imageView94;
        public int SHARE = R.id.imageView120;
    }

    interface Url {
        public String URIHOST = "http://www.vcvbuy.com/api/";
        public String URI_TYPE_APP = "app/";
        public String GETGEO = URIHOST + "region/geo/coder";
        public String GETDEVICEID = URIHOST + URI_TYPE_APP + "login/deviceid"; //获取未登录UID
        public String ALLREGION = URIHOST + "region/all/format"; //获取地址数据包
        public String SEND_SMS = URIHOST + URI_TYPE_APP + "sms/send"; //发送验证码
        public String USER_PROTOCOL = "http://www.vcvbuy.com/mobile/article/detail?id=6"; //用户协议
        public String USER_PRIVACY = "http://www.vcvbuy.com/mobile/article/detail?id=7"; //隐私相关政策
        public String USER_SERVICE = "http://www.vcvbuy.com/mobile/article/detail?id=8"; //服务条款
        public String GET_VERSION = URIHOST + URI_TYPE_APP + "version"; //版本号
        public String GET_BOOT_PAGE = URIHOST + URI_TYPE_APP + "index/boot/page"; //引导页

        public String HOME = URIHOST + URI_TYPE_APP + "index";
        public String HOMELOADMORE = URIHOST + URI_TYPE_APP + "index/loadmore";

        public String GOODS = URIHOST + URI_TYPE_APP + "goods/index";
        public String GOODSDETAIL = URIHOST + URI_TYPE_APP + "goods/detail";

        //fixme 购物车操作
        public String CART_LIST = URIHOST + URI_TYPE_APP + "cart/index";
        public String ADD_CART = URIHOST + URI_TYPE_APP + "cart/add";
        public String SET_CART = URIHOST + URI_TYPE_APP + "cart/set";
        public String DEL_CART = URIHOST + URI_TYPE_APP + "cart/del";

        //fixme 用户账号操作
        public String LOGIN = URIHOST + URI_TYPE_APP + "login/index";
        public String REGISTER = URIHOST + URI_TYPE_APP + "login/reg";
        public String GET_USER_INFO = URIHOST + URI_TYPE_APP + "user/index";
        public String SET_USER_INFO = URIHOST + URI_TYPE_APP + "user/set";
        public String USER_REAL = URIHOST + URI_TYPE_APP + "user/real";
        public String SET_USER_REAL = URIHOST + URI_TYPE_APP + "user/real/set";

        //fixme 收藏操作
        public String COLLECT_GOODSES = URIHOST + URI_TYPE_APP + "collect/goodses";
        public String ADD_COLLECT_GOODS = URIHOST + URI_TYPE_APP + "collect/goods/add";
        public String ADD_COLLECT_GOODS_CART = URIHOST + URI_TYPE_APP + "collect/goods/cart";
        public String ADD_COLLECT_BRAND = URIHOST + URI_TYPE_APP + "collect/brand/add";
        public String ADD_COLLECT_STORE = URIHOST + URI_TYPE_APP + "collect/store/add";
        public String BROWSE_GOODS = URIHOST + URI_TYPE_APP + "collect/browse";
        public String SET_BROWSE_GOODS = URIHOST + URI_TYPE_APP + "collect/browse/set";

        //fixme 用户地址操作
        public String USERADDRESSES = URIHOST + URI_TYPE_APP + "user/addresses";
        public String GETADDRESS = URIHOST + URI_TYPE_APP + "user/address/get";
        public String ADDADDRESS = URIHOST + URI_TYPE_APP + "user/address/add";
        public String SETADDRESS = URIHOST + URI_TYPE_APP + "user/address/set"; //添加和设置合并 这个暂时无用
        public String DELADDRESS = URIHOST + URI_TYPE_APP + "user/address/del";
        public String SETDEFADDRESS = URIHOST + URI_TYPE_APP + "user/address/setdef";

        //fixme 订单操作
        public String ORDERS = URIHOST + URI_TYPE_APP + "order/index";
        public String GET_ORDER = URIHOST + URI_TYPE_APP + "order/get";
        public String ADD_ORDER = URIHOST + URI_TYPE_APP + "order/add";
        public String CANCEL_ORDER = URIHOST + URI_TYPE_APP + "order/cancel";
        public String CONFIRM_TAKE_ORDER = URIHOST + URI_TYPE_APP + "order/confirm/take";

        //fixme 售后操作
        public String ORDER_AFTER_SALE_ORDERS = URIHOST + URI_TYPE_APP + "order/after/sale/orders";
        public String ORDER_AFTER_SALE = URIHOST + URI_TYPE_APP + "order/after/sale";
        public String ORDER_RETURN_GOODS = URIHOST + URI_TYPE_APP + "order/return/goods";
        public String ORDER_LOGISTICS = URIHOST + URI_TYPE_APP + "order/logistics/get";

        //fixme 付款地址
        public String ALI_PAY = URIHOST + URI_TYPE_APP + "pay/alipay";
        public String WX_PAY = URIHOST + URI_TYPE_APP + "pay/wxpay";
        public String UNION_PAY = URIHOST + URI_TYPE_APP + "pay/unionpay";

        //fixme 分类
        public String CATEGORY = URIHOST + URI_TYPE_APP + "category/index";

        //fixme 搜索
        public String SEARCH = URIHOST + URI_TYPE_APP + "search/index";
        public String SEARCH_FILTER_BY = URIHOST + URI_TYPE_APP + "search/filter/by";
        public String SEARCH_FILTER_TO = URIHOST + URI_TYPE_APP + "search/filter/to";
        public String SEARCH_KEYWORDS = URIHOST + URI_TYPE_APP + "search/keywords";
        public String SEARCH_KEYWORDS_CHANGE = URIHOST + URI_TYPE_APP + "search/keywords/change";

        //fixme 通知
        public String NOTIFY = URIHOST + URI_TYPE_APP + "notify/index";
        public String NOTIFY_GET = URIHOST + URI_TYPE_APP + "notify/get";

        //fixme 活动
        public String FAAT = URIHOST + URI_TYPE_APP + "faat/index";
        public String FAAT_BRAND = URIHOST + URI_TYPE_APP + "faat/brand";
        public String FAAT_SECKILL = URIHOST + URI_TYPE_APP + "faat/seckill";
        public String FAAT_GROUPBUY = URIHOST + URI_TYPE_APP + "faat/group/buy";

        //fixme 评价
        public String COMMENT_LABEL = URIHOST + URI_TYPE_APP + "comment/label";
        public String COMMENT_LIST = URIHOST + URI_TYPE_APP + "comment/list";
        public String COMMENT_ADD = URIHOST + URI_TYPE_APP + "comment/add";

        //fixme 品牌
        public String BRAND = URIHOST + URI_TYPE_APP + "brand/index";
    }
}
