@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">权限管理 - 分派权限 [{{$user->user_name}}]</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示商城所有功能权限。</li>
                    <li>打钩即是分配权限，请谨慎操作。</li>
                    <li>初始化是基本功能权限。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/privilege/distribution/'.$user->user_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        @foreach($navs as $key => $nav)
                            <div class="step-privilege clearfix">
                                <div class="title">
                                    <div class="checkbox-item fl mar-right-20">
                                        <input type="checkbox" name="{{$key}}[code]" class="ui-checkbox nav-title"
                                               id="return_type_{{$key}}"
                                               value="{{$key}}" @if(!empty($user->action_list[$key])) checked @endif>
                                        <input type="hidden" name="{{$key}}[name]" value="{{$nav['name']}}">
                                        <label class="ui-label mar-left-5"
                                               for="return_type_{{$key}}">{{$nav['name']}}</label>
                                    </div>
                                </div>
                                <div class="privilege-sub clearfix">
                                    @foreach($nav['list'] as $k => $val)
                                        <div class="checkbox-item fl">
                                            <input type="checkbox" name="{{$k}}[code]"
                                                   class="ui-checkbox nav-sub-title"
                                                   id="return_type_{{$k}}"
                                                   value="{{$k}}" @if(!empty($user->action_list[$k])) checked @endif>
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