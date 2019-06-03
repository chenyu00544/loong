@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 自动回复</div>
        <div class="content">
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($navType == 'subscribe') curr @endif fl">
                        <a href="{{url('admin/wechatreply/subscribe')}}">关注自动回复</a>
                    </li>
                    <li class="@if($navType == 'msg') curr @endif fl">
                        <a href="{{url('admin/wechatreply/msg')}}">消息自动回复</a>
                    </li>
                    <li class="@if($navType == 'keywords') curr @endif fl">
                        <a href="{{url('admin/wechatreply/keywords')}}">关键词自动回复</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>自动回复的类型
                        共分三种：关注自动回复、消息自动回复、关键词自动回复。回复内容可以设置为文字，图片，语音，视频。文本消息回复内容可以直接填写，长度限制1024字节（大约200字，含标点以及其他特殊字符），其他素材需要先在素材管理中添加。
                    </li>
                    <li>三、关键词自动回复：即自己添加的规则关键词自动回复。</li>
                    <li>★ 字数限制：微信公众平台认证与非认证用户的关键字自动回复设置规则上限为200条规则（每条规则名，最多可设置60个汉字），每条规则内最多设置10条关键字（每条关键字，最多可设置30个汉字）
                    </li>
                    <li>★ 规则设置：一个规则您可设置多个关键字，建议使用常用关键字，如关键词：help,帮助。采取中英文结合的方式最佳。如果用户发送的信息中含有您设置的其中一个关键字，则系统会匹配自动回复。
                    </li>
                    <li>★ 注意事项：关键词 不能设置系统已经存在的关键词，如功能扩展当中的hot、best、news等。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/wechatreply/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加关键字　</a>
                </div>
                <div class="main-info">

                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="text-center" width="5%"><a>ID</a></th>
                            <th width="15%"><a>规则</a></th>
                            <th width="15%">消息类型</th>
                            <th width="15%">内容</th>
                            <th width="15%">回复类型</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($subscribes) > 0)
                            @foreach($subscribes as $subscribe)
                                <tr>
                                    <td class="text-center">
                                        {{$subscribe->id}}
                                    </td>
                                    <td>
                                        {{$subscribe->rule_name}}
                                    </td>
                                    <td>{{$subscribe->type}}</td>
                                    <td>

                                    </td>
                                    <td>
                                        {{$subscribe->reply_type}}
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/wechatreply/'.$subscribe->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$subscribe->id}}">删除</a>
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
            $('.nav-pills a').click(function () {
                var model = $(this).data('model');
                if (model == 'text') {
                    $('.panel-body').html('<textarea type="text" name="content" class="form-control"\n' +
                        '                                              placeholder="" rows="10"></textarea>')
                } else {
                    layer.open({
                        type: 2,
                        area: ['800px', '425px'],
                        fixed: true, //不固定
                        maxmin: true,
                        title: '素材选择',
                        content: ["{{url('admin/wechatmaterial/modal/')}}/" + model],
                        success: function (layero, index) {
                            layer.iframeAuto(index)
                        }
                    });
                }
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