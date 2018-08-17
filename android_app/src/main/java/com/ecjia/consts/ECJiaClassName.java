package com.ecjia.consts;

public class ECJiaClassName {

    public enum ActivityName {
        MOBILEBUY("com.ecjia.hamster.activity.ECJiaMobilebuyGoodsActivity"),
        CHECKIN("com.ecjia.hamster.activity.ECJiaCheckInActivity"),
        NEWGOODS("com.ecjia.hamster.activity.ECJiaPromotionalGoodsActivity"),
        QRSHARE("com.ecjia.hamster.activity.ECJiaShareQRCodeActivity"),
        STREETS("com.ecjia.hamster.activity.ECJiaShopListFragmentActivity"),
        THEME("com.ecjia.hamster.activity.ECJiaTopicListActivity"),
        PROMOTIONAL("com.ecjia.hamster.activity.ECJiaPromotionalGoodsActivity"),
        GROUPBUY("com.ecjia.hamster.activity.ECJiaGroupbuyGoodsActivity"),
        ORDERS("com.ecjia.hamster.activity.ECJiaOrderListActivity"),
        ADDRESS("com.ecjia.hamster.activity.ECJiaAddressManageActivity"),
        COLLECT_GOODS("com.ecjia.hamster.activity.ECJiaCollectActivity"),
        COLLECT_SHOP("com.ecjia.hamster.activity.ECJiaShopCollectActivity"),
        HISTORY("com.ecjia.hamster.activity.ECJiaLastBrowseActivity"),
        QRCODE("com.ecjia.hamster.activity.ECJiaMyCaptureActivity"),
        MAP("com.ecjia.hamster.activity.ECJiaMapActivity"),
        TODAYHOT("com.ecjia.hamster.activity.ECJiaFindHotNewsActivity"),
        MESSAGE("com.ecmoban.android.missmall.ECJiaPushActivity"),
        FEEDBACK("com.ecjia.hamster.activity.ECJiaConsultActivity"),
        HELP("com.ecjia.hamster.activity.ECJiaHelpListActivity"),
        SHAKE("com.ecjia.hamster.activity.ECJiaShakeActivity"),
        SECKILL("com.ecjia.hamster.activity.ECJiaSeckillActivity");
        
        private String a;

        private ActivityName(String str) {
            this.a = str;
        }

        public String getActivityName() {
            return this.a;
        }
    }
}
