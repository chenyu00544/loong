@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">权限 管理 - 管理员列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>该页面展示了所有入驻平台的管理员列表。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/privilege/create')}}"
                       class="btn btn-success btn-add btn-sm">添加管理员</a>

                    <div class="fr wd250 mar-top-10">
                        <form action="{{url('admin/privilege/')}}" method="get">
                            {{csrf_field()}}
                            <input type="text" name="keywords" value=""
                                   class="form-control input-sm max-wd-190" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="10%"><a>用户名</a></th>
                            <th width="10%"><a>父管理员</a></th>
                            <th width="15%">商家名称</th>
                            <th width="20%">Email地址</th>
                            <th width="15%">加入时间</th>
                            <th width="15%">最后登录时间</th>
                            <th class="text-center" width="25%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($usersList) > 0)
                            @foreach($usersList as $user)
                                <tr>
                                    <td>
                                        {{$user->user_name}}
                                    </td>
                                    <td>{{$user->parent_name}}</td>
                                    <td>
                                        @if($user->ru_id == 0) 商城管理员 @else {{$user->ru_name}} @endif
                                    </td>
                                    <td>
                                        {{$user->email}}
                                    </td>
                                    <td>
                                        {{date('Y-m-d H:i:s', $user->add_time)}}
                                    </td>
                                    <td>
                                        @if($user->last_login) {{date('Y-m-d H:i:s', $user->last_login)}} @else N/A @endif
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/privilege/'.$user->user_id)}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">分派权限</a>
                                        <a type="button" href="{{url('admin/privilege/'.$user->user_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$user->user_id}}">删除</a>
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
                    <div class="page_list">
                        {{$usersList->links()}}
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

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/privilege/')}}/" + Id,
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