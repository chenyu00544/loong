@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">广告管理 - 广告位置列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($type == 'pc') curr @endif fl">
                        <a href="{{url('admin/adspos/pc')}}">PC端</a>
                    </li>
                    <li class="@if($type == 'web') curr @endif fl">
                        <a href="{{url('admin/adspos/web')}}">WEB端</a>
                    </li>
                    <li class="@if($type == 'app') curr @endif fl">
                        <a href="{{url('admin/adspos/app')}}">APP端</a>
                    </li>
                    <li class="@if($type == 'wxapp') curr @endif fl">
                        <a href="{{url('admin/adspos/wxapp')}}">微信小程序</a>
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
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <div class="fl">
                        <a href="{{url('admin/adspos/create')}}"
                           class="btn btn-success btn-add btn-sm">添加广告位置</a>
                    </div>
                    <div class="fr wd250 pad-top-7">
                        <form action="{{url('admin/adspos/'.$type)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="广告位名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="13%">广告位名称</th>
                            <th width="12%">商家名称</th>
                            <th width="10%">位置宽度</th>
                            <th width="12%">位置高度</th>
                            <th width="12%">广告位结构</th>
                            <th width="12%">广告位描述</th>
                            <th width="12%">排序</th>
                            <th width="10%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($adPoses->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($adPoses as $adPos)
                                <tr class="">
                                    <td>{{$adPos->position_id}}</td>
                                    <td>{{$adPos->position_name}}</td>
                                    <td>@if($adPos->user_id){{$adPos->rz_shopName}}@else 自营 @endif</td>
                                    <td>{{$adPos->ad_width}}</td>
                                    <td>{{$adPos->ad_height}}</td>
                                    <td>{{$adPos->position_model}}</td>
                                    <td>{{$adPos->position_desc}}</td>
                                    <td><input type="text" name="sort" autocomplete="off" class="form-control input-sm chang-order wd-80" value="{{$adPos->sort}}" data-id="{{$adPos->position_id}}"></td>
                                    <td class="text-center">
                                        <a type="button"
                                           href="{{url('admin/ad/adshow/'.$type.'/'.$adPos->position_id)}}"
                                           class="btn btn-info btn-edit btn-sm">查看</a>
                                        <a type="button"
                                           href="{{url('admin/adspos/'.$adPos->position_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$adPos->position_id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$adPoses->links()}}
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
            $('body').on('change', 'input[name=sort]', function () {
                var id = $(this).data('id');
                var sort = $(this).val();
                $.post(
                    '{{url("admin/adspos/change")}}',
                    {
                        id: id,
                        sort: sort,
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
                        "{{url('admin/adspos/')}}/" + Id,
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