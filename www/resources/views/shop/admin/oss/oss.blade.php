@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">文件管理 - 阿里云OSS配置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示OSS配置的列表信息。</li>
                    <li>可以直接在列表页面进行编辑和删除。</li>
                    <li>OSS可用于图片、音视频、日志等海量文件的存储。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/oss/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-1">Bucket名称</th>
                            <th class="col-sm-1">Bucket地域</th>
                            <th class="col-sm-2">域名绑定</th>
                            <th class="col-sm-2">外网域名</th>
                            <th class="col-sm-2">内网域名</th>
                            <th class="col-sm-2">是否使用</th>
                            <th class="col-sm-2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($alioss) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($alioss as $ali)
                            <tr>
                                <th>{{$ali->id}}</th>
                                <td>
                                    {{$ali->bucket}}
                                </td>
                                <td>
                                    {{$ali->regional_name}}
                                </td>
                                <td>
                                    @if($ali->is_cname==0)
                                        否
                                    @else
                                        是
                                    @endif
                                    <br>
                                    {{$ali->endpoint}}
                                </td>
                                <td class="wsn">
                                    {{$ali->outside_site}}
                                </td>
                                <td class="wsn">
                                    {{$ali->inside_site}}
                                </td>
                                <td>
                                    @if($ali->is_use==0)
                                        <img src="{{url('styles/images/no.png')}}" class="pointer">
                                    @else
                                        <img src="{{url('styles/images/yes.png')}}" class="pointer">
                                    @endif
                                </td>
                                <td>
                                    <a type="button" href="{{url('admin/oss/'.$ali->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$ali->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$alioss->links()}}
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
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/oss/')}}/" + Id,
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