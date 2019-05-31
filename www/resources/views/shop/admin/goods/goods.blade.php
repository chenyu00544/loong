@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 商品列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/goods/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/goods/seller')}}">店铺</a>
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
                    @foreach($goodsNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/goods/'.$nav['navType'].'?seller='.$seller)}}">{{$nav['title']}}(<font
                                        class="red">{{$nav['count']}}</font>)</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/goods/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加商品　</a>
                    @if($navType == 'examine' || $navType == 'examined' || $navType == 'noexamine')
                        <a href="{{url('admin/goods/noexamine')}}"
                           class="btn btn-default btn-add btn-sm">未审核</a>
                        <a href="{{url('admin/goods/examined')}}"
                           class="btn btn-default btn-add btn-sm">审核未通过</a>
                    @endif

                    <div class="fr wd250 pad-top-7">
                        <form action="{{url('admin/goods/'.$navType)}}" method="get">
                            {{csrf_field()}}
                            <input type="hidden" name="seller" value="{{$seller}}">
                            <input type="text" name="keywords" value="{{$keywords}}"
                                   class="form-control input-sm max-wd-190" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 40px">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="col-sm-1 text-center"><a>编号</a></th>
                            <th class="col-sm-3"><a>商品名称</a></th>
                            <th class="col-sm-1">商家名称</th>
                            <th class="col-sm-2">价格/货号/运费</th>
                            <th class="col-sm-1">标签</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-1">SKU/库存</th>
                            <th class="col-sm-1">审核状态</th>
                            <th class="col-sm-1">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($goodsList) > 0)
                            @foreach($goodsList as $goods)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$goods->goods_id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$goods->goods_id}}">
                                    </td>
                                    <td class="text-center">{{$goods->goods_id}}</td>
                                    <td>
                                        <div class="tDiv goods-info">
                                            <div class="img fl pad-all-5">
                                                <a href="" target="_blank" title="">
                                                    <img src="{{$goods->original_img}}" width="68" height="68">
                                                </a>
                                            </div>
                                            <div class="desc fl pad-all-5">
                                                <div class="name">
                                                <span class="max-wd-190 line-hg-20" title="{{$goods->goods_name}}"
                                                      data-toggle="tooltip"
                                                      data-placement="bottom">{{$goods->goods_name}}</span>
                                                </div>
                                                <p class="brand">品牌：
                                                    <em class="em-blue">{{$goods->brand_name}}</em>　　
                                                    @if($goods->is_shipping)
                                                        <em class="free"></em>
                                                    @endif
                                                    @if($goods->stages)
                                                        <em class="byStage"></em>
                                                    @endif
                                                    @if(!$goods->is_alone_sale)
                                                        <em class="parts"></em>
                                                    @endif
                                                    @if($goods->is_promote)
                                                        @if(time() > $goods->promote_end_date)
                                                            <em class="sale-end"></em>
                                                        @else
                                                            <em class="sale"></em>
                                                        @endif
                                                    @endif
                                                    @if($goods->is_limit_buy)
                                                        @if(time() > $goods->limit_buy_end_date)
                                                            <em class="purchaseEnd"></em>
                                                        @else
                                                            <em class="purchase"></em>
                                                        @endif
                                                    @endif
                                                    @if($goods->is_distribution)
                                                        <em class="distribution">{$lang.distribution}</em>
                                                    @endif
                                                </p>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        @if(!empty($goods->store->shop_name))
                                            <font class="blue">{{$goods->store->shop_name}}</font>
                                        @else
                                            <font class="red">直营</font>
                                        @endif
                                    </td>
                                    <td>
                                        <div class="tDiv">
                                            <div class="tDiv_item">
                                                <span class="fl">价格：</span>
                                                <div class="value">
                                                    @if($goods->model_attr == 1)
                                                        <input name="goods_model_price"
                                                               data-goodsid="{{$goods->goods_id}}"
                                                               class="btn btn-primary btn-sm" value="warehouse_price"
                                                               type="button">
                                                    @elseif($goods->model_attr == 2)
                                                        <input name="goods_model_price"
                                                               data-goodsid="{{$goods->goods_id}}"
                                                               class="btn btn-primary btn-sm" value="region_price"
                                                               type="button">
                                                    @else
                                                        <span>{{$goods->shop_price}}</span>
                                                    @endif
                                                </div>
                                            </div>

                                            <div class="tDiv_item">
                                                <span class="fl">货号：</span>
                                                <div class="value">
                                                    <span>{{$goods->goods_sn}}</span>
                                                </div>
                                            </div>

                                            <div class="tDiv_item">
                                                <span class="fl">运费：</span>
                                                <div class="value">
                                                    <a href="" target="_blank">
                                                        按运费模板
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix"><span class="fl">精品：</span>
                                            <div class="switch @if($goods->is_best) active @endif" data-type="is_best"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$goods->goods_id}}">
                                            </div>
                                        </div>
                                        <div class="switch-wrap clearfix"><span class="fl">新品：</span>
                                            <div class="switch @if($goods->is_new) active @endif" data-type="is_new"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$goods->goods_id}}">
                                            </div>
                                        </div>
                                        <div class="switch-wrap clearfix"><span class="fl">热销：</span>
                                            <div class="switch @if($goods->is_hot) active @endif" data-type="is_hot"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$goods->goods_id}}">
                                            </div>
                                        </div>
                                        <div class="switch-wrap clearfix"><span class="fl">上架：</span>
                                            <div class="switch @if($goods->is_on_sale) active @endif"
                                                 data-type="is_on_sale"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$goods->goods_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-info btn-weight-order btn-sm"
                                           data-goodsid="{{$goods->goods_id}}">权重排序</a>
                                    </td>
                                    <td>
                                        @if(empty($goods->products))
                                            {{$goods->goods_number}}
                                        @else
                                            <a href="javascript:;" class="sku" data-goodsid="{{$goods->goods_id}}" data-userid="{{$goods->user_id}}"><i
                                                        class="glyphicon glyphicon-edit fs-18"></i></a>
                                        @endif
                                    </td>
                                    <td>
                                        @if($goods->review_status == 1)
                                            <font class="oranges" id="examine_{{$goods->goods_id}}">未审核</font>
                                        @elseif($goods->review_status == 2)
                                            <font class="red" id="examine_{{$goods->goods_id}}">审核不通过</font><br/>
                                            <i class="dark-red" title="{{$goods->review_content}}"
                                               data-toggle="tooltip">（提示）</i>
                                        @elseif($goods->review_status == 3 || $goods->review_status == 4)
                                            <font class="blue" id="examine_{{$goods->goods_id}}">审核已通过</font>
                                        @elseif($goods->review_status == 5)
                                            <font class="navy2" id="examine_{{$goods->goods_id}}">无需审核</font>
                                        @endif
                                    </td>
                                    <td>
                                        @if($navType == 'delete')
                                            <a type="button" href="{{url('admin/goods/backto/'.$goods->goods_id)}}"
                                               class="btn btn-warning btn-edit btn-sm fl mar-all-5">还原</a>
                                            <a type="button" href="{{url('admin/goods/del/'.$goods->goods_id)}}"
                                               class="btn btn-danger btn-del btn-sm fl mar-all-5">删除</a>
                                        @else
                                            <a type="button" href="javascript:;" data-id="{{$goods->goods_id}}"
                                               class="btn btn-primary btn-examine btn-sm fl mar-all-5">审核</a>
                                            <a type="button" href="{{url('admin/goods/'.$goods->goods_id.'/edit')}}"
                                               class="btn btn-info btn-edit btn-sm fl mar-all-5">查看</a>
                                            <a type="button" href="{{url('admin/goods/'.$goods->goods_id.'/edit')}}"
                                               class="btn btn-warning btn-edit btn-sm fl mar-all-5">编辑</a>
                                            <a type="button" class="btn btn-danger btn-del btn-sm fl mar-all-5"
                                               data-id="{{$goods->goods_id}}">删除</a>
                                        @endif
                                    </td>
                                </tr>
                            @endforeach
                        @else
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        </tbody>
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl mar-all-5 checkwrap">
                            <label class="label-tip">
                                <input type="checkbox" name="all_list" value=""
                                       class="checkbox check-all fl ">全选</label>
                        </div>
                        <div class="fl mar-all-5">
                            <select name="select_type" class="form-control col-md-2 input-sm">
                                <option value="0">请选择</option>
                                <option value="is_best_on">精品</option>
                                <option value="is_best_off">取消精品</option>
                                <option value="is_new_on">新品</option>
                                <option value="is_new_off">取消新品</option>
                                <option value="is_hot_on">热销</option>
                                <option value="is_hot_off">取消热销</option>
                                <option value="is_sale_on">上架</option>
                                <option value="is_sale_off">下架</option>
                                <option value="is_delete">回收站</option>
                            </select>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-5">确定</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$goodsList->links()}}
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

            //sku
            $('.sku').click(function () {
                var goods_id = $(this).data('goodsid');
                layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '商品SKU',
                    content: ["{{url('admin/goods/sku/')}}" + "/" + goods_id, 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            // $('.check-all').click(function () {
            //     document.cookie = "name=123123123";
            //
            //     var search = "name="//查询检索的值
            //     var returnvalue = "";//返回值
            //     if (document.cookie.length > 0) {
            //         sd = document.cookie.indexOf(search);
            //         if (sd != -1) {
            //             sd += search.length;
            //             end = document.cookie.indexOf(";", sd);
            //             if (end == -1)
            //                 end = document.cookie.length;
            //             //unescape() 函数可对通过 escape() 编码的字符串进行解码。
            //             returnvalue = unescape(document.cookie.substring(sd, end))
            //         }
            //     }
            //     alert(returnvalue);
            // });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/goods/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection