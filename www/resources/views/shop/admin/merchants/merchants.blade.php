@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家管理 - 店铺列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($snav == 'storelist') curr @endif fl">
                        <a href="{{url('admin/storelist')}}">店铺列表</a>
                    </li>
                    <li class="@if($snav == 'real') curr @endif fl">
                        <a href="{{url('admin/storelist/real')}}">实名认证</a>
                    </li>
                    <li class="@if($snav == 'apply') curr @endif fl">
                        <a href="{{url('admin/storelist/gradeapply')}}">店铺等级申请</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>平台所有入驻商相关信息管理。</li>
                    <li>可对入驻商进行分派权限操作。</li>
                    <li>商家初始化等级用于老商家无等级的情况下，给予初始化为普通商家的操作，新商家入驻后默认为普通商家，请勿操作。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <a href="{{url('admin/storelist/create')}}" class="btn btn-success btn-add btn-sm">添加店铺</a>
                    <a href="{{url('admin/storelist/maudit')}}" class="btn btn-success btn-add btn-sm">入驻未审核</a>
                    <a href="{{url('admin/storelist/saudit')}}" class="btn btn-success btn-add btn-sm">店铺信息未审核</a>
                    <div class="fr wd250">
                        <form action="{{url('admin/storelist/'.$snav)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="店铺名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="10%">[ID]会员名称</th>
                            <th width="12%">店铺名称</th>
                            <th width="8%">公司类型</th>
                            <th width="5%">等级</th>
                            <th width="12%">主营类目</th>
                            <th width="10%">入驻审核状态</th>
                            <th width="7%">排序</th>
                            <th width="5%">店铺街</th>
                            <th width="6%">在线客服</th>
                            <th width="7%">店铺信息</th>
                            <th width="14%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($stores->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($stores as $store)
                                <tr class="">
                                    <td>{{$store->shop_id}}</td>
                                    <td>[{{$store->user_id}}]{{$store->user->user_name}}</td>
                                    <td>
                                        <font class="red">{{$store->rz_shopName}}</font>
                                    </td>
                                    <td>
                                        @if(!empty($store->msf->company_type)) {{$store->msf->company_type}} @else
                                            空 @endif
                                    </td>
                                    <td>
                                        <img src="{{url($store->grade_img)}}" alt="" width="25" height="25">
                                    </td>
                                    <td>
                                        @if(!empty($store->category->cat_name)) {{$store->category->cat_name}} @else
                                            空 @endif
                                    </td>
                                    <td>
                                        @if($store->steps_audit == 1)
                                            @if($store->merchants_audit == 0)
                                                <font class="gray">未审核</font>
                                            @elseif($store->merchants_audit == 1)
                                                <font class="skyblue">审核已通过</font>
                                            @elseif($store->merchants_audit == 2)
                                                <font class="oranges">审核未通过</font>
                                            @endif
                                        @else
                                            <font class="oranges">尚未提交信息</font>
                                        @endif
                                    </td>
                                    <td>
                                        <input class="form-control input-sm order" name="sort_order" type="text"
                                               data-id="{{$store->shop_id}}" value="{{$store->sort_order}}"
                                               autocomplete="off">
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($store->is_street) active @endif"
                                                 data-type="is_street"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$store->shop_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($store->is_im) active @endif" data-type="is_im"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$store->shop_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        @if($store->review_status == 1)
                                            <font class="gray">未审核</font>
                                        @elseif($store->review_status == 2)
                                            <font class="red">审核未通过</font>
                                        @elseif($store->review_status == 3)
                                            <font class="skyblue">审核已通过</font>
                                        @endif
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/storelist/info/'.$store->user_id)}}"
                                           class="btn btn-info btn-edit btn-sm">店铺管理</a>
                                        <a type="button" href="{{url('admin/storelist/'.$store->user_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;" class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$store->cou_id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$stores->links()}}
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
            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/storelist/change")}}',
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

            $('input[name=sort_order]').change(function () {
                var sort = $(this).val();
                var id = $(this).data('id');
                $.post(
                    '{{url("admin/storelist/change")}}',
                    {
                        id: id,
                        type: 'sort',
                        val: sort,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                    }
                );
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/storelist/')}}/" + Id,
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