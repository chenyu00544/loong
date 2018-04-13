@extends('shop.layouts.index')
@section('content')
<<<<<<< HEAD
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
=======
    <body class="iframe_body" style="overflow-y: scroll;">
    <div class="warpper shop_special">
>>>>>>> 3446ec3a04598a6da640fcdbe28208fa77238de4
        <div class="title">{{$lang['system_set']}} - {{$lang['shop_setup']}}</div>
        <div class="content">
            <div class="tabs">
                <ul class="fl">
                    @foreach($conf as $item)
                        <li class="@if($loop->index == 0) curr @endif fl"><a
                                    href="javascript:void(0);">{{$item->name}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商店相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist">
                <div class="main-info">
                    <form enctype="multipart/form-data" name="conf" action="{{url('admin/shopconf')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        @foreach($conf as $item)
                            <div class="switch-info" @if($loop->index != 0)style="display:none" @endif>
                                @foreach($item->vars as $var)
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">{{$var['name']}}
                                            ：</label>
                                        <div class="col-sm-4 n-wd400">
                                            {!! $var['html'] !!}
                                        </div>
                                        @if($var['desc'])
                                            <div class="notic col-sm-3">{{nl2br($var['desc'])}}</div>@endif
                                    </div>

                                @endforeach
                                <div class="item">
                                    <div class="label">&nbsp;</div>
                                    <div class="">
                                        <input type="submit" value="　{{$lang['sure']}}　"
                                               class="btn btn-danger clearfix">
                                    </div>
                                </div>
                            </div>
                        @endforeach
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center"><a href="#">copy</a></div>
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}",{type:1, parent:parent},function(data){
                    if(data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_province').html($html);
                    }
                });
            });

<<<<<<< HEAD
            $('.shop_province').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}",{type:2, parent:parent},function(data){
                    if(data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_city').html($html);
=======
        /*url重写验证*/
        var ReWriteSelected = null;
        var ReWriteRadiobox = document.getElementsByName("value[209]");

        for (var i = 0; i < ReWriteRadiobox.length; i++) {
            if (ReWriteRadiobox[i].checked) {
                ReWriteSelected = ReWriteRadiobox[i];
            }
        }

        function ReWriterConfirm(sender) {
            if (sender == ReWriteSelected) return true;
            var res = true;
            if (sender != ReWriteRadiobox[0]) {
                var res = confirm('{$rewrite_confirm}');
            }

            if (res == false) {
                ReWriteSelected.checked = true;
            }
            else {
                ReWriteSelected = sender;
            }
            return res;
        }

        function addCon_amount(obj) {
            var obj = $(obj);
            var tbl = obj.parents('#consumtable');
            var fald = true;
            var fald2 = true;
            var error = "";
            var volumeNum = obj.siblings("input");
            var it_val = "";

            var new_it_val = obj.siblings("input[name='invoice_type[]']").val();

            tbl.find(".mt10").each(function () {
                var it_input = $(this).find("input[name='invoice_type[]']");
                it_val = it_input.val();

                if (new_it_val == it_val) {
                    obj.siblings("input[name='invoice_type[]']").addClass("error");
                    fald2 = false;
                    error = "类型已存在";
                }
            });
            if (fald2 == true) {
                volumeNum.each(function (index, element) {
                    var val = $(this).val();
                    if (val == "") {
                        $(this).addClass("error");
                        fald = false;
                        error = "类型和税率不能为空";
                    } else if (!(/^[0-9]+.?[0-9]*$/.test(val)) && index == 1) {
                        $(this).addClass("error");
                        fald = false;
                        error = "税率必须为数字";
                    } else {
                        $(this).removeClass("error");
                        fald = true;
>>>>>>> 3446ec3a04598a6da640fcdbe28208fa77238de4
                    }
                });
            })

        });
    </script>
@endsection
@endsection