@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员设置 - 会员等级</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员等级相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($usersNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/'.$nav['navType'])}}">{{$nav['title']}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/userrank/create')}}"
                       class="btn btn-success btn-add btn-sm">添加会员等级</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">会员等级名称</th>
                            <th class="col-sm-2">积分下限</th>
                            <th class="col-sm-2">积分上限</th>
                            <th class="col-sm-2">初始折扣率(%)</th>
                            <th class="col-sm-1">特殊会员组</th>
                            <th class="col-sm-1">显示价格</th>
                            <th class="col-sm-2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($ranks) > 0)
                            @foreach($ranks as $rank)
                                <tr>
                                    <th>{{$rank->rank_name}}</th>
                                    <td>{{$rank->min_points}}</td>
                                    <td>{{$rank->max_points}}</td>
                                    <td>{{$rank->discount}}</td>
                                    <td>@if($rank->special_rank) <img src="{{url('styles/images/yes.png')}}" alt="no" class="mt10"> @else <img src="{{url('styles/images/no.png')}}" alt="no" class="mt10"> @endif</td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($rank->show_price) active @endif" data-type="show_price"
                                                 title="是" data-id="{{$rank->rank_id}}">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$rank->show_price}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <a type="button" href="{{url('admin/userrank/'.$rank->rank_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$rank->rank_id}}">删除</a>
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
                var id = $(this).data('id');
                $.post(
                    '{{url("admin/userrank/changes")}}',
                    {
                        id: id,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
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
                        "{{url('admin/userrank/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
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