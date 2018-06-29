@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 商品列表</div>
        <div class="content">
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
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商城相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($searchNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/order/'.$nav['navType'].'?seller='.$seller)}}">{{$nav['title']}}(<font
                                        class="red">{{$nav['count']}}</font>)</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <div class="fr wd250">
                        <form action="{{url('admin/order/'.$navType)}}" method="get">
                            {{csrf_field()}}

                            <input type="hidden" name="seller" value="{{$seller}}">
                            <input type="text" name="keywords" value="{{$keywords}}"
                                   class="form-control input-sm max-wd-190" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr lh22" value="查询">
                        </form>
                    </div>
                </div>
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
                                                   class="checkbox check-all fl"
                                                   id="checkbox_{{$order->order_id}}">
                                        </span>
                                        <span class="words">订单号：{{$order->order_sn}}</span>
                                        <span class="words">下单时间：2018-06-29 08:57:39</span>
                                    </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td class="td-product">
                                    <div>
                                        <div class="img fl">
                                            <img width="80"
                                                 src="http://www.shopnc.com/images/201703/thumb_img/0_thumb_G_1490155064450.jpg">
                                        </div>
                                        <div class="product-info">
                                            <div class="name mar-bt-5">
                                                <a href="../goods.php?id=705" target="_blank">
                                                    绿联 数据线安卓高速单头加长手机通用华为魅族小米2a快充电器2米 铝合金外壳+纯铜芯，充电快寿命长 <span
                                                            class="oranges">[ 匡威 ]</span>
                                                </a>
                                            </div>
                                            <div class="attr gray">
                                                <span>颜色:暗夜黑</span>
                                            </div>
                                            <div class="goods_sn">商品编号：ECS000705<a
                                                        href="../trade_snapshot.php?act=trade&amp;tradeId=21&amp;snapshot=true"
                                                        target="_blank"><span class="oranges mar-left-10">[交易快照]</span></a>
                                            </div>
                                            <div class="order_icon_items">
                                                <div class="order_icon order_icon_pt" title="普通订单">普通订单</div>

                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td class="td-price text-center">
                                    <div>18.00</div>
                                </td>
                                <td class="td-number text-center">
                                    <div>1</div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <div></div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <font class="gray">绿联专卖店</font>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <a href="users.php?act=edit&id=60">chenyu</a>
                                    </div>
                                </td>
                                <td>
                                    <div class="gray">test<br> TEL:
                                        18858786747<br>甘肃
                                        白银 白银区 test
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <span class="order-price fwb fs-16">¥22.30</span>
                                        <div class="price-shipping gray">(顺丰速运：¥4.30)</div>
                                        <div class="price-shipping gray">
                                            <p>支付方式：余额支付</p>
                                            <p>来源：PC</p>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <div>已确认<br>已付款<br>已发货</div>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <div>
                                        <div class="btn-wrap">
                                            <p><a href="{{url('admin/order/'.'1'.'/edit')}}"
                                                  class="btn btn-info">查看</a>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    @endforeach
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-confirm btn-sm mar-all-8"
                               disabled="disabled">确认</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-invalid btn-sm mar-all-8"
                               disabled="disabled">无效</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-cancel btn-sm mar-all-8"
                               disabled="disabled">取消</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-remove btn-sm mar-all-8"
                               disabled="disabled">移除</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-print btn-sm mar-all-8"
                               disabled="disabled">打印订单</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-print_shipping btn-sm mar-all-8"
                               disabled="disabled">打印快递单</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$orders->links()}}
                    </div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.nyroModal').nyroModal();

            $("[data-toggle='tooltip']").tooltip();

            //开关
            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0
                    $(this).removeClass('active');
                } else {
                    val = 1
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/goods/change")}}',
                    {
                        id: id,
                        type: tag,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            //权重
            $('.btn-weight-order').click(function () {
                var goods_id = $(this).data('goodsid');

                layer.open({
                    type: 2,
                    area: ['1000px', '350px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '权重排序',
                    content: "{{url('admin/goods/weight/order/')}}" + "/" + goods_id
                });
            });

            //审核
            $('.btn-examine').click(function () {
                var goods_id = $(this).data('id');

                layer.open({
                    type: 2,
                    area: ['500px', '370px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '审核',
                    content: ["{{url('admin/goods/examine/')}}" + "/" + goods_id, 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //批量修改
            $('.btn-sure').click(function () {

                var goods_ids = $("input[name=checkboxes]");

                var ids = [];
                $.each(goods_ids, function (k, v) {
                    if ($(v).is(':checked')) {
                        ids.push($(v).val());
                    }
                });
                var select_type = $("select[name=select_type]").val();

                if (ids.length > 0 && select_type != 0) {
                    $.post(
                        '{{url("admin/goods/changes")}}',
                        {
                            ids: ids,
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
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });

            //删除
            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/goods/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            if (data.code == 1) {
                                layer.msg(data.msg, {icon: data.code});
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 1000);
                            } else {
                                layer.msg(data.msg, {icon: data.code});
                            }

                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection