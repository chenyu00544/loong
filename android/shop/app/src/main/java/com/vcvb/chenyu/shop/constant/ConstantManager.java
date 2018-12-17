package com.vcvb.chenyu.shop.constant;

import android.os.Environment;

import com.vcvb.chenyu.shop.R;

public interface ConstantManager {

    public int REGISTER_FOR_MY = 1001;

    interface CheckToken {

        public String FUNCTION = "XXXXXX";

        public String USERNAME = "XXXXX";

        public String ACCESS_TOKEN = "XXXXXXX";

        public String ACCESS_ID = "XXXXXXX";
    }

    interface ImgPath {
        public String PATH = Environment.getExternalStorageDirectory() + "/Android/data/com.vcvb.chenyu" +
                ".shop/files/";
    }

    interface ResultStatus {
        public int ADDRESSRESULT = 1002;
        public int ADD_ADDRESS_RESULT = 1003;
        public int ADD_ORDER_RESULT = 1004;
        public int COLLECT_RESULT = 1005;
        public int PHONE = 1006;
        public int MSG_RESULT = 1007;
        public int ORDER_RESULT = 1008;
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

    interface Item {
        public int HEADER = 6001;
        public int FOOTER = 6002;
        public int ITEMS = 6003;
        public int ITEMS_1 = 6004;
        public int ITEMS_2 = 6005;
        public int ITEMS_3 = 6006;
        public int ITEMS_4 = 6007;
        public int ITEMS_5 = 6008;
        public int ITEMS_6 = 6009;
        public int ITEMS_7 = 6010;
        public int BANNER = 6011;
        public int ITEMHEADER = 6012;
        public int ITEMFOOTER = 6013;

        public int ADDGOODSNUM = 6014;
        public int SUBGOODSNUM = 6015;
        public int CHECKBOXONCE = 6016;
        public int CHECKBOXSUBALL = 6017;
        public int CHECKBOXALL = 6018;
    }

    interface Url {
        public String URIHOST = "http://www.vcvbuy.com/api/";
        public String GETGEO = URIHOST + "region/geo/coder";
        public String GETDEVICEID = URIHOST + "app/login/deviceid"; //获取未登录UID
        public String ALLREGION = URIHOST + "region/all/format"; //获取地址数据包
        public String SEND_SMS = URIHOST + "app/sms/send"; //发送验证码
        public String USER_PROTOCOL = "http://www.vcvbuy.com/mobile/article/detail?id=6"; //用户协议
        public String USER_PRIVACY = "http://www.vcvbuy.com/mobile/article/detail?id=7"; //隐私相关政策
        public String USER_SERVICE = "http://www.vcvbuy.com/mobile/article/detail?id=8"; //服务条款

        public String HOME = URIHOST + "app/index";
        public String HOMELOADMORE = URIHOST + "app/index/loadmore";

        public String GOODS = URIHOST + "app/goods/index";
        public String GOODSDETAIL = URIHOST + "app/goods/detail";

        //fixme 购物车操作
        public String CART_LIST = URIHOST + "app/cart/index";
        public String ADD_CART = URIHOST + "app/cart/add";
        public String SET_CART = URIHOST + "app/cart/set";
        public String DEL_CART = URIHOST + "app/cart/del";

        //fixme 用户账号操作
        public String LOGIN = URIHOST + "app/login/index";
        public String REGISTER = URIHOST + "app/login/reg";
        public String GET_USER_INFO = URIHOST + "app/user/index";
        public String SET_USER_INFO = URIHOST + "app/user/set";
        public String USER_REAL = URIHOST + "app/user/real";
        public String SET_USER_REAL = URIHOST + "app/user/real/set";

        //fixme 收藏操作
        public String COLLECT_GOODSES = URIHOST + "app/collect/goodses";
        public String ADD_COLLECT_GOODS = URIHOST + "app/collect/goods/add";
        public String ADD_COLLECT_GOODS_CART = URIHOST + "app/collect/goods/cart";
        public String ADD_COLLECT_BRAND = URIHOST + "app/collect/brand/add";
        public String ADD_COLLECT_STORE = URIHOST + "app/collect/store/add";
        public String BROWSE_GOODS = URIHOST + "app/collect/browse";
        public String SET_BROWSE_GOODS = URIHOST + "app/collect/browse/set";

        //fixme 用户地址操作
        public String USERADDRESSES = URIHOST + "app/user/addresses";
        public String GETADDRESS = URIHOST + "app/user/address/get";
        public String ADDADDRESS = URIHOST + "app/user/address/add";
        public String SETADDRESS = URIHOST + "app/user/address/set"; //添加和设置合并 这个暂时无用
        public String DELADDRESS = URIHOST + "app/user/address/del";
        public String SETDEFADDRESS = URIHOST + "app/user/address/setdef";

        //fixme 订单操作
        public String ORDERS = URIHOST + "app/order/index";
        public String GET_ORDER = URIHOST + "app/order/get";
        public String ADD_ORDER = URIHOST + "app/order/add";
        public String CANCEL_ORDER = URIHOST + "app/order/cancel";

        //fixme 付款地址
        public String ALI_PAY = URIHOST + "app/pay/alipay";
        public String WX_PAY = URIHOST + "app/pay/wxpay";
        public String UNION_PAY = URIHOST + "app/pay/unionpay";

        //fixme 分类
        public String CATEGORY = URIHOST + "app/category/index";

        //fixme 搜索
        public String SEARCH = URIHOST + "app/search/index";
        public String SEARCH_FILTER_BY = URIHOST + "app/search/filter/by";
        public String SEARCH_FILTER_TO = URIHOST + "app/search/filter/to";
        public String SEARCH_KEYWORDS = URIHOST + "app/search/keywords";
        public String SEARCH_KEYWORDS_CHANGE = URIHOST + "app/search/keywords/change";

        //fixme 通知
        public String NOTIFY = URIHOST + "app/notify/index";
        public String NOTIFY_GET = URIHOST + "app/notify/get";

        //fixme 活动
        public String FAAT = URIHOST + "app/faat/index";
        public String FAAT_BRAND = URIHOST + "app/faat/brand";
    }
}
