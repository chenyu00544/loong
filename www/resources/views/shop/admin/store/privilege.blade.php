@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家 - 分派权限</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav == 'store') curr @endif fl">
                        <a href="{{url('admin/store')}}">店铺设置</a>
                    </li>
                    <li class="@if($nav == 'process') curr @endif fl">
                        <a href="{{url('admin/msp')}}">入驻流程</a>
                    </li>
                    <li class="@if($nav == 'privilege') curr @endif fl">
                        <a href="{{url('admin/store/privilege')}}">入驻初始化权限</a>
                    </li>
                    <li class="@if($nav == 'grade') curr @endif fl">
                        <a href="{{url('admin/sellergrade')}}">店铺等级</a>
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
                    <form name="conf" action="{{url('admin/store/allot/')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <div class="step-privilege clearfix">
                            <div class="title">
                                <div class="checkbox-item fl mar-right-20">
                                    <label class="ui-label"><em>*</em>请选择商家等级</label>
                                </div>
                            </div>
                            <div class="privilege-sub clearfix">
                                <div class="text-center wd-250 mar-lr-auto mar-bt-20 mar-top-20">
                                    <select name="grade" class="form-control">
                                        @foreach($gradeprivilege as $grade)
                                            <option value="{{$grade->id}}">{{$grade->grade_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="step-privilege clearfix">
                            <div class="title">
                                <div class="checkbox-item fl mar-right-20">
                                    <label class="ui-label">初始化所有商家管理权限</label>
                                </div>
                            </div>
                            <div class="privilege-sub clearfix">
                                <div class="checkbox-item fl">
                                    <input type="checkbox" name="initialize_allot"
                                           class="ui-checkbox nav-sub-title"
                                           id="initialize_allot"
                                           value="1">
                                    <label class="ui-label mar-left-5"
                                           for="initialize_allot">执行<em>(谨慎操作)</em></label>
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
                                               @if(!empty($gradeprivilege[0]) && in_array($key, $gradeprivilege[0]->mpri->action_list)) checked @endif>
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
                                                   @if(!empty($gradeprivilege[0]) && in_array($k, $gradeprivilege[0]->mpri->action_list)) checked @endif>
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

            $('select[name=grade]').change(function () {
                var id = $(this).val();
                $('input[type=checkbox]').prop('checked', false);
                $.post("{{url('admin/store/searchpriv')}}", {'_token': '{{csrf_token()}}', id: id}, function (data) {
                    for(var i in data){
                        $('#return_type_'+data[i]).prop('checked', true);
                    }
                });
            });
        });
    </script>
@endsection
@endsection