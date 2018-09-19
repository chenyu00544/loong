package com.vcvb.chenyu.shop.constant;

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
}
