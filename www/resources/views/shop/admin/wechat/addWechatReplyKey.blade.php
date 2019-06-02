@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">
            <a href="{{$backUrl.'/keywords'}}" class="s-back">返回</a>
            微信设置 - 关键字自动回复</div>
        <div class="content">
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
                <div class="main-info">
                    <form name="conf" action="{{url('admin/wechatreply/replyauto/set')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-2 control-label">规则名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="rule_name" class="form-control input-sm"
                                       value=""
                                       placeholder="规则名最多60个字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">关 键 字 ：</label>
                            <div class="col-sm-4">
                                <input type="text" name="rule_keywords" class="form-control input-sm"
                                       value=""
                                       placeholder="添加多个关键字，用英文逗号','隔开">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">回复内容：</label>
                            <div class="col-sm-8">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <ul class="nav nav-pills" role="tablist">
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-pencil fs-18"
                                                                       title="文字"
                                                                       data-model="text"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-picture fs-18"
                                                                       title="图片"
                                                                       data-model="image"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-volume-up fs-18"
                                                                       data-model="voice" title="语音"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-film fs-18"
                                                                       data-model="video" title="视频"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-list-alt fs-18"
                                                                       title="图文消息" data-model="news"></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                        <textarea type="text" name="content" class="form-control"
                                                      placeholder="如：vcvbuy"
                                                      rows="10"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="media_id" value="">
                        <input type="hidden" name="type" value="keywords">
                        <input type="hidden" name="id" value="">
                        <div class="form-group">
                            <div class="col-sm-1 control-label"></div>
                            <div class="">
                                <input type="submit" value="　保存　" class="btn btn-danger clearfix">
                            </div>
                        </div>
                    </form>
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
        });
    </script>
@endsection
@endsection