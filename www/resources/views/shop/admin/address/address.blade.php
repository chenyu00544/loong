@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员管理 - 收货地址列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($userNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/users/'.$nav['navType'].'/'.$id)}}">{{$nav['title']}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="5%">收货人</th>
                            <th width="15%">地址</th>
                            <th width="10%">手机</th>
                            <th width="20%">邮件地址</th>
                            <th width="8%">电话</th>
                            <th width="8%">邮政编码</th>
                            <th width="8%">地址别名</th>
                            <th width="8%">最佳送货时间</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($addressList) > 0)
                            @foreach($addressList as $address)
                                <tr>
                                    <td>{{$address->consignee}}</td>
                                    <td>[{{$address->province}} {{$address->city}} {{$address->district}}
                                        ]{{$address->address}}</td>
                                    <td>{{$address->mobile}}</td>
                                    <td>{{$address->email}}</td>
                                    <td>{{$address->tel}}</td>
                                    <td>{{$address->zipcode}}</td>
                                    <td>{{$address->building}}</td>
                                    <td>{{$address->best_time}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/address/'.$address->address_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$address->address_id}}">删除</a>
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
                var uid = $(this).data('uid');
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }
                $.post(
                    "{{url('admin/users/changes')}}",
                    {'_token': '{{csrf_token()}}', 'type': 'validated', 'value': val, 'uid': uid},
                    function (data) {
                    }
                );
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
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/address/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                $(that).parent().parent().remove();
                            }
                        }
                    );
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection