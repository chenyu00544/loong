--
-- 表的结构 `砍价活动商品信息表 dsc_bargain_goods`
--
CREATE TABLE IF NOT EXISTS `{pre}bargain_goods` (
	`id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '活动id',
	`bargain_name` varchar(255) NOT NULL DEFAULT '' COMMENT '砍价活动标题',
	`goods_id` mediumint(8) NOT NULL  COMMENT '商品id',
	`start_time` int(10) DEFAULT '0' COMMENT '活动开始时间',
	`end_time` int(10) DEFAULT '0' COMMENT '活动结束时间',
	`add_time` int(10) DEFAULT '0' COMMENT '添加时间',
	`goods_price` DECIMAL( 10, 2 ) NOT NULL DEFAULT  '0.00' COMMENT '活动原价',
	`min_price` int(10) DEFAULT '0' COMMENT '价格区间（最小值）',
	`max_price` int(10) DEFAULT '0' COMMENT '价格区间（最大值）',
	`target_price` DECIMAL( 10, 2 ) NOT NULL DEFAULT  '0.00' COMMENT '砍价目标价格',
	`total_num` int(10) DEFAULT '0' COMMENT '参与人数',
	`is_hot` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否热销',
	`is_audit` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未审核，1未通过，2已审核',
	`isnot_aduit_reason` varchar(255) NOT NULL DEFAULT '' COMMENT '审核未通过原因',
	`bargain_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '砍价介绍',
	`status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '活动状态（0进行中、1关闭）',
	`is_delete` tinyint(10) NOT NULL DEFAULT '0' COMMENT '活动删除状态（1删除）',
	PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;

--
-- 表的结构 `活动商品属性  （dsc_activity_goods_attr）`
--

CREATE TABLE IF NOT EXISTS `{pre}activity_goods_attr` (
	`id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '',
	`bargain_id` mediumint(8) NOT NULL DEFAULT '0'  COMMENT '活动id',
	`goods_id` mediumint(8) NOT NULL  COMMENT '商品id',
	`product_id` mediumint(8) NOT NULL DEFAULT '0'  COMMENT '属性id',
	`target_price` DECIMAL( 10, 2 ) NOT NULL DEFAULT  '0.00' COMMENT '砍价目标价格',
	`type` varchar(20) NOT NULL DEFAULT '' COMMENT '活动类型',
	PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;

--
-- 表的结构 `创建砍价活动记录表  dsc_bargain_statistics_log`
--

CREATE TABLE IF NOT EXISTS `{pre}bargain_statistics_log` (
	`id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '创建砍价记录id',
	`bargain_id` mediumint(8) NOT NULL  COMMENT '活动id',
	`goods_attr_id` varchar(20) NOT NULL DEFAULT '0'  COMMENT '属性id',
	`user_id` mediumint(8) NOT NULL  COMMENT '会员id',
	`final_price` DECIMAL( 10, 2 ) NOT NULL DEFAULT  '0.00' COMMENT '砍后最终购买价',
	`add_time` int(10) DEFAULT '0' COMMENT '添加时间',
	`count_num` int(10) DEFAULT '0' COMMENT '参与人次',
	`status` tinyint(10) NOT NULL DEFAULT '0' COMMENT '状态（1成功）',
	PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;

--
-- 表的结构 `砍价活动统计 dsc_bargain_statistics`
--

CREATE TABLE IF NOT EXISTS `{pre}bargain_statistics` (
	`id` mediumint(8) NOT NULL AUTO_INCREMENT COMMENT '砍价记录id',
	`bs_id` mediumint(8) NOT NULL  COMMENT '创建活动id',
	`user_id` mediumint(8) NOT NULL  COMMENT '会员id',
	`subtract_price` DECIMAL( 10, 2 ) NOT NULL DEFAULT  '0.00' COMMENT '砍掉商品价格',
	`add_time` int(10) DEFAULT '0' COMMENT '添加时间',
	PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 ;

--
-- 转存表中的数据 `dsc_admin_action` 权限
--
INSERT INTO `{pre}admin_action` VALUES ('', '7', 'bargain_manage', '', '1');

--
-- 转存表中的数据 `dsc_touch_ad_position`
--

INSERT INTO `{pre}touch_ad_position` (`position_id`, `user_id`, `position_name`, `ad_width`, `ad_height`, `position_desc`, `position_style`, `is_public`, `theme`, `tc_id`, `tc_type`) VALUES
(1020, 0, '砍价首页banner', 360, 180, '', '{foreach $ads as $ad}<div class="swiper-slide">{$ad}</div>{/foreach}', 0, 'ecmoban_dsc2017', 0, '');

--
-- 转存表中的数据 `dsc_touch_ad`
--

INSERT INTO `{pre}touch_ad` (`position_id`, `media_type`, `ad_name`, `ad_link`, `link_color`, `ad_code`, `start_time`, `end_time`, `link_man`, `link_email`, `link_phone`, `click_count`, `enabled`, `is_new`, `is_hot`, `is_best`, `public_ruid`, `ad_type`, `goods_name`) VALUES
(1020, 0, '砍价首页banner-01', '', '', '1509663779787829146.jpg', 1508708579, 1574372579, '', '', '', 0, 1, 0, 0, 0, 0, 0, '0');


--
--  增加砍价模板消息 `dsc_wechat_template`
--

INSERT INTO `{pre}wechat_template` VALUES ('', '', 'OPENTM410292733', null, '{{first.DATA}}商品名称：{{keyword1.DATA}}底价：{{keyword2.DATA}}{{remark.DATA}}', '砍价成功提醒', '1494467185', '0', '1');



