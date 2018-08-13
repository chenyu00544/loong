@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家 - 分派权限</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav == 'info') curr @endif fl">
                        <a href="{{url('admin/storelist/info/'.$id)}}">店铺信息</a>
                    </li>
                    <li class="@if($nav == 'priv') curr @endif fl">
                        <a href="{{url('admin/storelist/priv/'.$id)}}">店铺权限</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>根据不同的入驻商家等级进行分派权限。。</li>
                    <li>修改权限将导致商家后台改变，请谨慎操作。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/storelist/priv/allot')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <input type="hidden" name="ru_id" value="{{$id}}">
                        @if(!empty($admin))
                            <input type="hidden" name="auid" value="{{$admin->user_id}}">
                        @else
                            <input type="hidden" name="auid" value="0">
                        @endif
                        <div class="step-privilege clearfix">
                            <div class="title">
                                <div class="checkbox-item fl mar-right-20">
                                    <label class="ui-label"><em>*</em>商家登录账号</label>
                                </div>
                            </div>
                            <div class="pad-all-30">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font class="red">*</font>登录名称：</label>
                                    <div class="col-sm-3">
                                        <input type="text" name="login_name"
                                               class="form-control input-sm"
                                               value="@if(!empty($admin->user_name)){{$admin->user_name}}@endif" placeholder="登录名称"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">密 码：</label>
                                    <div class="col-sm-3">
                                        <input type="text" name="password"
                                               class="form-control input-sm"
                                               value="" placeholder="密码"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        @foreach($sellernavs['index'] as $key => $value)
                            <div class="step-privilege clearfix">
                                <div class="title">
                                    <div class="checkbox-item fl mar-right-20">
                                        <input type="checkbox" name="{{$key}}[code]" class="ui-checkbox nav-title"
                                               id="return_type_{{$key}}"
                                               value="{{$key}}"
                                               @if(!empty($admin->action_list) && in_array($key, $admin->action_list)) checked @endif>
                                        <input type="hidden" name="{{$key}}[name]" value="{{$value}}">
                                        <label class="ui-label mar-left-5"
                                               for="return_type_{{$key}}">{{$value}}</label>
                                    </div>
                                </div>
                                <div class="privilege-sub clearfix">
                                    @foreach($sellernavs[$key] as $k => $val)
                                        <div class="checkbox-item fl">
                                            <input type="checkbox" name="{{$k}}[code]"
                                                   class="ui-checkbox nav-sub-title"
                                                   id="return_type_{{$k}}"
                                                   value="{{$k}}"
                                                   @if(!empty($admin->action_list) && in_array($k, $admin->action_list)) checked @endif>
                                            <input type="hidden" name="{{$k}}[name]" value="{{$val['name']}}">
                                            <input type="hidden" name="{{$k}}[url]" value="{{$val['url']}}">
                                            <label class="ui-label mar-left-5"
                                                   for="return_type_{{$k}}">{{$val['name']}}</label>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                        @endforeach
                    </form>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 control-label lh36">
                <div class="privilege-select-all clearfix">
                    <div class="checkbox-item fr">
                        <input type="checkbox" name="select_all" class="ui-checkbox"
                               id="all_s"
                               value="">
                        <label class="ui-label mar-left-5"
                               for="all_s">全选</label>
                    </div>
                </div>
            </div>
            <div class="">
                <input type="submit" value="　确定　" class="btn btn-danger clearfix btn-sure">
                <a type="button" class="btn btn-default clearfix mar-left-20"
                   href="javascript:history.go(-1)">返回</a>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('input[name=select_all]').click(function () {
                if ($(this).is(':checked')) {
                    $('.ui-checkbox').prop('checked', true);
                } else {
                    $('.ui-checkbox').prop('checked', false);
                }
            });

            $('.nav-title').click(function () {
                if ($(this).is(':checked')) {
                    $(this).parent().parent().parent().find('.nav-sub-title').prop('checked', true);
                } else {
                    $(this).parent().parent().parent().find('.nav-sub-title').prop('checked', false);
                }
            });

            $('.nav-sub-title').click(function () {
                var that = this;
                var i = 0;
                $(this).parent().parent().find('.nav-sub-title').each(function (k, v) {
                    if ($(v).is(':checked')) {
                        i++;

                    }
                });
                if (i > 0) {
                    $(that).parent().parent().parent().find('.nav-title').prop('checked', true);
                } else {
                    $(that).parent().parent().parent().find('.nav-title').prop('checked', false);
                }
            });

            $('.btn-sure').click(function () {
                $('.form-horizontal').submit();
            });
        });
    </script>
@endsection
@endsection