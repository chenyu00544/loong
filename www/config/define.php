<?php
return array(
    /* 订单状态 */
    'OS_UNCONFIRMED'    => 0, // 未确认
    'OS_CONFIRMED'      => 1, // 已确认
    'OS_CANCELED'       => 2, // 已取消
    'OS_INVALID'        => 3, // 无效
    'OS_RETURNED'       => 4, // 退货
    'OS_SPLITED'        => 5, // 已分单
    'OS_SPLITING_PART'  => 6, // 部分分单
    'OS_RETURNED_PART'  => 7, // 部分已退货
    'OS_ONLY_REFOUND'   => 8, // 仅退款

    /* 配送状态 */
    'SS_UNSHIPPED'      => 0, // 未发货
    'SS_SHIPPED'        => 1, // 已发货
    'SS_RECEIVED'       => 2, // 已收货
    'SS_PREPARING'      => 3, // 备货中
    'SS_SHIPPED_PART'   => 4, // 已发货(部分商品)
    'SS_SHIPPED_ING'    => 5, // 发货中(处理分单)
    'OS_SHIPPED_PART'   => 6, // 已发货(部分商品)

    /* 支付状态 */
    'PS_UNPAYED'        => 0, // 未付款
    'PS_PAYING'         => 1, // 付款中
    'PS_PAYED'          => 2, // 已付款
    'PS_PAYED_PART'     => 3, // 部分付款--预售定金
    'PS_REFOUND'        => 4, // 已退款
    'PS_REFOUND_PART'   => 5, // 部分退款

    '10000' => '成功',
    '10001' => '系统错误',
    '10002' => '参数错误',
    '10003' => '操作错误',
    '30001' => '购物车以满',
    '30002' => '商品已下架',
    '30003' => '库存不足',
    '40001' => '限定十个地址，已添加满'
);