@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 订单列表</div>
        <div class="content">
            @if($seller != '')
                <div class="tabs mar-top-5">
                    <ul class="fl">
                        <li class="@if($seller == 'selfsale') curr @endif fl">
                            <a href="{{url('admin/order/selfsale')}}">自营</a>
                        </li>
                        <li class="@if($seller == 'seller') curr @endif fl">
                            <a href="{{url('admin/order/seller')}}">店铺</a>
                        </li>
                    </ul>
                </div>
            @endif
            @if(!empty($id))
                <div class="tabs mar-top-20">
                    <ul class="fl">
                        @foreach($userNav as $nav)
                            <li class="@if($unav == $nav['navType']) curr @endif fl">
                                <a href="{{url('admin/users/'.$nav['navType'].'/'.$id)}}">{{$nav['title']}}</a>
                            </li>
                        @endforeach
                    </ul>
                </div>
            @endif
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>订单相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>

            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($searchNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/order/'.$nav['navType'].'?seat=0'.(empty($seller)?'':'&seller='.$seller).(empty($search['team_id'])?'':'&team='.$search['team_id']).(empty($search['sec_id'])?'':'&seckill='.$search['sec_id']).(empty($search['group_id'])?'':'&group_id='.$search['group_id']))}}">
                                {{$nav['title']}}(<font class="red">{{$nav['count']}}</font>)</a>
                        </li>
                    @endforeach
                </ul>
            </div>

            <div class="fromlist clearfix">
                @if(empty($id))
                    <div class="clearfix mar-bt-5">
                        <div class="fr wd250 pad-top-7">
                            <form action="{{url('admin/order/'.$navType)}}" method="get">
                                {{csrf_field()}}

                                <input type="hidden" name="seller" value="{{$seller}}">
                                <input type="text" name="keywords" value="{{$search['keywords']}}"
                                       class="form-control input-sm max-wd-190" placeholder="订单编号">
                                <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr"
                                       value="查询">
                            </form>
                        </div>
                    </div>
                @endif
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th width="25%">订单号</th>
                            <th width="6%" class="text-center">价格</th>
                            <th width="3%" class="text-center">数量</th>
                            <th width="7%" class="text-center">售后</th>
                            <th width="6%" class="text-center">商家名称</th>
                            <th width="7%" class="text-center">用户名</th>
                            <th width="16%" class="text-center">收货人</th>
                            <th width="12%" class="text-center">金额标签</th>
                            <th width="6%" class="text-center">订单状态</th>
                            <th width="7%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if(count($orders) == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @endif
                    </table>
                    @foreach($orders as $order)
                        <table class="order-list mar-bt-5">
                            <colgroup>
                                <col width="28%">
                                <col width="6%">
                                <col width="3%">
                                <col width="7%">
                                <col width="6%">
                                <col width="7%">
                                <col width="16%">
                                <col width="12%">
                                <col width="6%">
                                <col width="7%">
                            </colgroup>
                            <tbody class="">
                            <tr class="tr-order-sn">
                                <td colspan="10">
                                    <div class="">
                                        <span class="sign mar-right-10">
                                            <input type="checkbox" name="checkboxes" value="{{$order->order_id}}"
                                                   class="checkbox check-all-list fl"
                                                   id="checkbox_{{$order->order_id}}">
                                        </span>
                                        <span class="words">订单号：{{$order->order_sn}}</span>
                                        <span class="words">下单时间：{{date('Y-m-d H:i:s', $order->add_time)}}</span>
                                    </div>
                                </td>
                            </tr>
                            @foreach($order->Goods as $goods)
                                <tr class="">
                                    <td class="td-product">
                                        <div>
                                            <div class="img fl">
                                                <img width="80"
                                                     src="{{$goods->original_img}}">
                                            </div>
                                            <div class="product-info">
                                                <div class="name mar-bt-5">
                                                    <a href="{{$goods->goods_id}}"
                                                       target="_blank">{{$goods->goods_name}}
                                                        @if($goods->brand_name)
                                                            <span class="oranges">[ {{$goods->brand_name}}
                                                                ]</span>
                                                        @endif
                                                    </a>
                                                </div>
                                                @if($goods->goods_attr)
                                                    <div class="attr gray">
                                                        <span>{{$goods->goods_attr}}</span>
                                                    </div>
                                                @endif
                                                <div class="goods_sn">商品编号：{{$goods->goods_sn}}
                                                    @if($order->TradeSnapshot['trade_id'])
                                                        <a href="{{url('tradesnapshot/'.$order->TradeSnapshot['trade_id'])}}"
                                                           target="_blank">
                                                            <span class="oranges mar-left-10">[交易快照]</span>
                                                        </a>
                                                    @endif
                                                </div>
                                                <div class="order_icon_items">
                                                    @if($order->is_zc_order == 1)
                                                        <div class="order_icon order_icon_zc fl" title="众筹订单">众筹订单</div>
                                                    @endif
                                                    @if(!empty($order->is_stages) && $order->is_stages == 1)
                                                        <div class="order_icon order_icon_bt fl" title="白条订单">白条订单</div>
                                                    @endif
                                                    @if(!empty($order->is_store_order) && $order->is_store_order == 1)
                                                        <div class="order_icon order_icon_so fl" title="门面订单">门面订单</div>
                                                    @endif
                                                    @if(!empty($order->is_drp_order) && $order->is_drp_order == 1)
                                                        <div class="order_icon order_icon_fx fl" title="分销订单">分销订单</div>
                                                    @endif
                                                    @if($order->team_id > 0)
                                                        <div class="order_icon order_icon_team fl" title="拼团订单">拼团订单
                                                        </div>
                                                    @elseif($order->extension_code == 'group_buy')
                                                        <div class="order_icon order_icon_tg fl" title="团购订单">团购订单</div>
                                                    @elseif($order->extension_code == 'exchange_goods')
                                                        <div class="order_icon order_icon_jf fl" title="积分订单">积分订单</div>
                                                    @elseif($order->extension_code == 'auction')
                                                        <div class="order_icon order_icon_pm fl" title="拍卖订单">拍卖订单</div>
                                                    @elseif($order->extension_code == 'snatch')
                                                        <div class="order_icon order_icon_db fl" title="夺宝奇兵订单">夺宝奇兵订单
                                                        </div>
                                                    @elseif($order->extension_code == 'presale')
                                                        <div class="order_icon order_icon_ys fl" title="预售订单">预售订单</div>
                                                    @elseif($order->extension_code == 'seckill')
                                                        <div class="order_icon order_icon_ms fl" title="秒杀订单">秒杀订单</div>
                                                    @elseif($order->extension_code == 'team_buy')
                                                        <div class="order_icon order_icon_team fl" title="拼团订单">拼团订单
                                                        </div>
                                                    @elseif($order->extension_code == 'bargain_buy')
                                                        <div class="order_icon order_icon_bargain fl" title="预售订单">预售订单
                                                        </div>
                                                    @elseif($order->extension_code == 'wholesale')
                                                        <div class="order_icon order_icon_wholesale fl" title="批发订单">
                                                            批发订单
                                                        </div>
                                                    @elseif($order->extension_code == '')
                                                        <div class="order_icon order_icon_pt fl" title="普通订单">普通订单</div>
                                                    @endif
                                                    @if($order->extension_code == 'virtual_card')
                                                        <div class="order_icon order_icon_xn fl" title="虚拟订单">虚拟订单</div>
                                                    @elseif($order->extension_code == 'package_buy')
                                                        <div class="order_icon order_icon_package fl" title="礼品订单">礼品订单
                                                        </div>
                                                    @endif
                                                    @if(!empty($order->ret_id) && $order->ret_id > 0)
                                                        <div class="order_icon order_icon_return fl" title="退货订单">退货订单
                                                        </div>
                                                    @endif
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="td-price text-center">
                                        <div>{{$goods->goods_price}}</div>
                                    </td>
                                    <td class="td-number text-center">
                                        <div>{{$goods->o_goods_number}}</div>
                                    </td>
                                    <td class="text-center">
                                        <div>
                                            <div></div>
                                        </div>
                                    </td>
                                    @if($loop->index == 0)
                                        <td class="text-center" rowspan="{{$order->Goods->count()}}">
                                            <div>
                                                <font class="gray">{{$order->TradeSnapshot['rz_shopName']}}</font>
                                            </div>
                                        </td>

                                        <td class="text-center" rowspan="{{$order->Goods->count()}}">
                                            <div>
                                                <a href="#">{{$order->User['user_name']}}</a>
                                            </div>
                                        </td>
                                        <td rowspan="{{$order->Goods->count()}}">
                                            <div class="gray">{{$order->consignee}}<br> TEL:
                                                {{$order->mobile}}<br>
                                                {{$order->province}} {{$order->city}} {{$order->district}} {{$order->address}}
                                            </div>
                                        </td>
                                        <td class="text-center" rowspan="{{$order->Goods->count()}}">
                                            <div>
                                                <span class="order-price fwb fs-16">¥{{$order->goods_amount + $order->tax + $order->shipping_fee + $order->insure_fee + $order->pay_fee + $order->pack_fee + $order->card_fee - $order->discount}}</span>
                                                <div class="price-shipping gray">(顺丰速运：¥{{$order->shipping_fee}}
                                                    )
                                                </div>
                                                <div class="price-shipping gray">
                                                    <p>支付方式：{{$order->pay_name}}</p>
                                                    <p>来源：{{$order->froms}}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-center" rowspan="{{$order->Goods->count()}}">
                                            <div>
                                                <div>@if($order->order_status == 0)
                                                        未确认
                                                    @elseif($order->order_status == 1)
                                                        已确认
                                                    @elseif($order->order_status == 2)
                                                        <font class="red">已取消</font>
                                                    @elseif($order->order_status == 3)
                                                        <font class="red">无效</font>
                                                    @elseif($order->order_status == 4)
                                                        <font class="oranges">退货</font>
                                                    @endif
                                                    <br>
                                                    @if($order->pay_status == 0)
                                                        未付款
                                                    @elseif($order->pay_status == 1)
                                                        付款中
                                                    @elseif($order->pay_status == 2)
                                                        已付款
                                                    @elseif($order->pay_status == 3)
                                                        部分付款
                                                    @elseif($order->pay_status == 4)
                                                        已退款
                                                    @elseif($order->pay_status == 5)
                                                        部分退款
                                                    @endif
                                                    <br>
                                                    @if($order->shipping_status == 0)
                                                        未发货
                                                    @elseif($order->shipping_status == 1)
                                                        已发货
                                                    @elseif($order->shipping_status == 2)
                                                        已收货
                                                    @elseif($order->shipping_status == 3)
                                                        备货中
                                                    @elseif($order->shipping_status == 5)
                                                        发货中
                                                    @endif
                                                </div>
                                            </div>
                                        </td>
                                        <td class="text-center" rowspan="{{$order->Goods->count()}}">
                                            <div>
                                                <div class="btn-wrap">
                                                    <p>
                                                        <a href="{{url('admin/order/'.$order->order_id.'/edit')}}"
                                                           class="btn btn-info">查看</a>
                                                        @if($order->order_status == 2 || $order->order_status == 3)
                                                            <a href="Javascript:;"
                                                               data-id="{{$order->order_id}}"
                                                               class="btn btn-danger mar-top-20 btn-del">移除</a>
                                                        @endif
                                                    </p>
                                                </div>
                                            </div>
                                        </td>
                                    @endif
                                </tr>
                            @endforeach
                            </tbody>
                        </table>
                    @endforeach
                    <div class="clearfix bg-color-dray pad-top-4 bt-batch">
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-confirm btn-sm mar-all-8"
                               disabled="disabled" data-type="confirm">确认</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-invalid btn-sm mar-all-8"
                               disabled="disabled" data-type="invalid">无效</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-cancel btn-sm mar-all-8"
                               disabled="disabled" data-type="cancel">取消</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-remove btn-sm mar-all-8"
                               disabled="disabled" data-type="remove">移除</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-print btn-sm mar-all-8"
                               disabled="disabled" data-type="print">打印订单</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-print_shipping btn-sm mar-all-8"
                               disabled="disabled" data-type="print_shipping">打印快递单</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$orders->links()}}
                    </div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            //批量修改
            $('.bt-batch').on('click', 'a', function () {
                var order_ids = [];
                $(".check-all-list").each(function () {
                    if ($(this).is(':checked')) {
                        order_ids.push($(this).val());
                    }
                });

                if (order_ids.length == 0) {
                    return;
                }

                var select_type = $(this).data('type');
                if (select_type == 'print') {
                    return;
                } else if (select_type == 'print_shipping') {
                    return;
                }
                if (order_ids.length > 0) {
                    $.post(
                        '{{url("admin/order/changes")}}',
                        {
                            ids: order_ids,
                            type: select_type,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    );
                }
            });

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked');
                $(".check-all-list").each(function () {
                    $(this).prop("checked", flage);
                    if (flage) {
                        $('.bt-batch').find('a').removeAttr('disabled');
                        $(this).parent().parent().parent().parent().addClass('current');
                        $(this).parent().parent().parent().parent().parent().addClass('current');
                    } else {
                        $('.bt-batch').find('a').attr('disabled', true);
                        $(this).parent().parent().parent().parent().removeClass('current');
                        $(this).parent().parent().parent().parent().parent().removeClass('current');
                    }
                });

            });

            $(".check-all-list").on('click', function () {
                if ($(this).is(':checked')) {
                    $('.bt-batch').find('a').removeAttr('disabled');
                    $(this).parent().parent().parent().parent().addClass('current');
                    $(this).parent().parent().parent().parent().parent().addClass('current');
                } else {
                    var bool = true;
                    $(".check-all-list").each(function () {
                        if ($(this).is(':checked')) {
                            bool = false;
                        }
                    });
                    if (bool) {
                        $('.bt-batch').find('a').attr('disabled', true);
                    }
                    $(this).parent().parent().parent().parent().removeClass('current');
                    $(this).parent().parent().parent().parent().parent().removeClass('current');
                }
            });

            //删除
            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/order/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection