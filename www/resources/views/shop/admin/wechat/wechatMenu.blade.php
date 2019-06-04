@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 自定义菜单</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>微信自定义菜单最多可添加3个一级菜单、5个二级菜单。</li>
                    <li>微信自定义菜单分为关键词click，网址view两种类型。click是响应关键词指令，view则是直接跳转URL地址（填写绝对路径）。</li>
                    <li>每次修改自定义菜单后，由于微信客户端缓存，需要24小时左右微信客户端才会显示生效。测试时可以尝试重新关注微信公众号，或者清除微信缓存。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/wechatmenu/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加菜单　</a>
                </div>
                <div class="main-info">

                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="text-center" width="5%"><a>ID</a></th>
                            <th width="15%"><a>菜单名称</a></th>
                            <th width="15%">菜单关键词</th>
                            <th width="25%">外链URL</th>
                            <th width="15%">类型</th>
                            <th width="15%">是否显示</th>
                            <th width="15%">排序</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($menus) > 0)
                            @foreach($menus as $menu)
                                <tr>
                                    <td class="text-center">
                                        {{$menu->id}}
                                    </td>
                                    <td>
                                        {{$menu->name}}
                                    </td>
                                    <td>{{$menu->key}}</td>
                                    <td>
                                        {{$menu->url}}
                                    </td>
                                    <td>
                                        {{$menu->type}}
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch  @if($menu->status) active @endif " data-type="is_show" title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$menu->id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <input class="form-control input-sm chang-order" type="text"
                                               data-id="{{$menu->id}}" name="sort_order"
                                               value="{{$menu->sort}}">
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/wechatmenu/'.$menu->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$menu->id}}">删除</a>
                                    </td>
                                </tr>
                                @if(count($menu->subMenus) > 0)
                                    @foreach($menu->subMenus as $subMenu)
                                        <tr>
                                            <td class="text-center">
                                                {{$subMenu->id}}
                                            </td>
                                            <td>
                                                {{$subMenu->name}}
                                            </td>
                                            <td>{{$subMenu->key}}</td>
                                            <td>
                                                {{$subMenu->url}}
                                            </td>
                                            <td>
                                                {{$subMenu->type}}
                                            </td>
                                            <td>
                                                <div class="switch-wrap clearfix">
                                                    <div class="switch  @if($subMenu->status) active @endif " data-type="is_show" title="是">
                                                        <div class="circle"></div>
                                                        <input type="hidden" value="{{$subMenu->id}}">
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                {{$subMenu->sort}}
                                            </td>
                                            <td class="text-center">
                                                <a type="button" href="javascript:;"
                                                   class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                                <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                                   data-id="{{$subMenu->id}}">删除</a>
                                            </td>
                                        </tr>
                                    @endforeach
                                @endif
                            @endforeach
                        @else
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        </tbody>
                    </table>
                    <div class="text-center">
                        <a href="javascript:;" class="btn btn-danger create-menu">生成自定义菜单</a>
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
                    "{{url('admin/wechatmenu/change')}}",
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

            $('.chang-order').change(function () {
                $.post(
                    '{{url("admin/wechatmenu/change")}}',
                    {
                        id: $(this).data('id'),
                        type: 'order',
                        val: $(this).val(),
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            $('.create-menu').click(function () {
                var that = this;
                $.post(
                    "{{url('admin/wechatreply/')}}/" + Id,
                    {'_method': 'delete', '_token': '{{csrf_token()}}'},
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        if (data.code === 1) {
                            $(that).parent().parent().remove();
                        }
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
                        "{{url('admin/wechatreply/')}}/" + Id,
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