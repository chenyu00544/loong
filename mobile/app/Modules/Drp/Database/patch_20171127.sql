
--
-- 表的结构 `wechat_template 模板消息 新会员加入通知 `
--
INSERT INTO `{pre}wechat_template` (`code`, `content`, `template`, `title`, `add_time`, `status`, `wechat_id`) VALUES
('OPENTM202967310', '', '{{first.DATA}}会员编号：{{keyword1.DATA}}加入时间：{{keyword2.DATA}}{{remark.DATA}}', '新会员加入通知', 1460698436, 0, 1),
('OPENTM206328970', '', '{{first.DATA}}商品名称：{{keyword1.DATA}}商品佣金：{{keyword2.DATA}}下单时间：{{keyword3.DATA}}订单状态：{{keyword4.DATA}}{{remark.DATA}}', '分销订单下单成功通知', 1460698436, 0, 1);




