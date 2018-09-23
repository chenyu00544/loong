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

    interface Nav{
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
}
