package com.vcvb.chenyu.shop.constant;

import com.vcvb.chenyu.shop.R;

public interface ConstantManager {

    public int REGISTER_FOR_MY = 1001;

    interface CheckToken {

        public String FUNCTION = "XXXXXX";

        public String USERNAME = "XXXXX";

        public String ACCESS_TOKEN = "XXXXXXX";

        public String ACCESS_ID = "XXXXXXX";
    }

    interface ResultStatus {
        public Integer ADDRESSRESULT = 1002;

    }

    interface PhotoAlbum {
        public int PHOTOALBUM_REQUEST = 2001;
        public int REQUEST_IMAGE_CAPTURE = 2002;
        public int CROP_PHOTO = 2003;
    }

    interface User {

        public String LOGO = "LOGO";

        public int NICKNAME = 3001;

        public String SEX = "XXXXXXX";

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
        public int MESSAGE = R.id.view33;
        public int HOME = R.id.view35;
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
    }

    interface Url {
        public String URIHOST = "http://www.vcvbuy.com/api/";
        public String HOME = URIHOST + "app/index";
        public String HOMELOADMORE = URIHOST + "app/index/loadmore";
        public String GOODSDETAIL = URIHOST + "app/goods/detail";
        public String GETGEO = URIHOST + "region/geo/coder";
    }
}
