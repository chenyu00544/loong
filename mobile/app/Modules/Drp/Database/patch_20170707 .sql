--
-- 表的结构 `店铺列表 dsc_drp_shop 增加以下字段((店铺头像))`
--

ALTER TABLE  `{pre}drp_shop` ADD  `shop_portrait`  varchar(255) NOT NULL DEFAULT '' COMMENT '店铺头像'  AFTER  `shop_img`



