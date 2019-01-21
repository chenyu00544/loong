@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商家 - 添加流程步骤</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识“*”的选项为必填项，其余为选填项。</li>
                    <li>请谨慎填写表单创建相关数据。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="applyprocess" action="{{url('admin/applyprocess')}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>所属流程：</label>
                            <div class="col-sm-4">
                                <select name="fields_steps" class="form-control wa">
                                    @foreach($process as $pro)
                                        <option value="{{$pro->id}}">{{$pro->process_title}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>内容标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="fields_titles" class="form-control" value=""
                                       placeholder="内容标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">标题注释：</label>
                            <div class="col-sm-3">
                                <input type="text" name="titles_annotation" class="form-control" value=""
                                       placeholder="标题注释">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">显示样式：</label>
                            <div class="col-sm-4">
                                <select name="steps_style" class="form-control wa">
                                    <option value="0">基本信息</option>
                                    <option value="1">店铺类型</option>
                                    <option value="2">类目信息</option>
                                    <option value="3">品牌信息</option>
                                    <option value="4">店铺信息</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="f-item-section">
                                <div class="f-item f-item-curr clearfix">
                                    <div class="add-item fl"><i class="sc-icon sc-icon-add cursor"></i></div>
                                    <div class="item-info fl">
                                        <div class="p-item clearfix">
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">表单字段：</strong>
                                                <input type="text" name="merchants_date[]"
                                                       class="form-control xwd150 input-sm">
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">数据类型：</strong>
                                                <select name="merchants_dateType[]"
                                                        class="form-control wa input-sm">
                                                    <option value="VARCHAR">VARCHAR</option>
                                                    <option value="CHAR">CHAR</option>
                                                    <option value="INT">INT</option>
                                                    <option value="MEDIUMINT">MEDIUMINT</option>
                                                    <option value="SMALLINT">SMALLINT</option>
                                                    <option value="TINYINT">TINYINT</option>
                                                    <option value="DECIMAL">DECIMAL</option>
                                                    <option value="TEXT">TEXT</option>
                                                </select>
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">表单标题：</strong>
                                                <input type="text" name="merchants_formName[]"
                                                       class="form-control xwd150 input-sm">
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">数据长度：</strong>
                                                <input type="text" name="merchants_length[]"
                                                       class="form-control xwd100 input-sm">
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">显示排序：</strong>
                                                <input type="text" name="fields_sort[]"
                                                       class="form-control xwd100 input-sm">
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">必选项：</strong>
                                                <select name="will_choose[]" class="form-control wa input-sm">
                                                    <option value="0">否</option>
                                                    <option value="1">是</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="p-item clearfix">
                                            <div class="form-text fl clearfix" style="width: 465px">
                                                <strong class="fl lh30 mar-right-5">表单注释：</strong>
                                                <input type="text" name="formName_special[]"
                                                       class="form-control xwd400 input-sm">
                                            </div>
                                            <div class="form-text fl clearfix">
                                                <strong class="fl lh30 mar-right-5">表单类型：</strong>
                                                <select name="merchants_form[]" class="form-control wa input-sm">
                                                    <option value="input">文本字段(input)</option>
                                                    <option value="textarea">文本区域(textarea)</option>
                                                    <option value="radio">单选按钮(radio)</option>
                                                    <option value="checkbox">多选按钮(checkbox)</option>
                                                    <option value="select">下拉菜单(select)</option>
                                                    <option value="other">其他(other)</option>
                                                </select>
                                            </div>

                                            <div class="form-text fl clearfix merchants-other disn">
                                                <strong class="fl lh30 mar-right-5">选择类型：</strong>
                                                <select name="merchants_formOther[]"
                                                        class="form-control wa input-sm">
                                                    <option value="textArea">地区类型</option>
                                                    <option value="dateTime">日期类型</option>
                                                    <option value="file">文件上传</option>
                                                </select>
                                            </div>
                                            <div class="form-text fl clearfix merchants-other-date disn">
                                                <strong class="fl lh30 mar-right-5">日期表单长度：</strong>
                                                <input type="text" name="merchants_formOtherSize[]"
                                                       class="form-control xwd100 input-sm">
                                            </div>
                                        </div>
                                        <div class="p-item clearfix">
                                            <div class="merchants-text">
                                                <div class="form-text fl clearfix">
                                                    <strong class="fl lh30 mar-right-5">表单长度：</strong>
                                                    <input type="text" name="merchants_formSize[]"
                                                           class="form-control xwd100 input-sm">
                                                </div>
                                            </div>
                                            <div class="merchants-textarea disn">
                                                <div class="form-text fl clearfix">
                                                    <strong class="fl lh30 mar-right-5">行数以及宽度：</strong>
                                                    <input type="text" name="merchants_rows[]"
                                                           class="form-control xwd100 input-sm fl">
                                                </div>
                                                <div class="form-text fl clearfix">
                                                    <strong class="fl lh30 mar-right-5">~</strong>
                                                    <input type="text" name="merchants_cols[]"
                                                           class="form-control xwd100 input-sm fl">
                                                </div>
                                            </div>
                                            <div class="merchants-radio disn">
                                                <div class="merchants-wrap fl">
                                                    <div class="add-sub-item fl"><i
                                                                class="sc-icon sc-icon-add-small cursor"></i></div>
                                                    <div class="fl">
                                                        <div class="form-text fl clearfix"
                                                             style="width: 525px">
                                                            <strong class="fl lh30 mar-right-5">单选或多选按钮名称：</strong>
                                                            <input type="text" name="radio_checkbox[0][]"
                                                                   class="form-control xwd400 input-sm fl radio_checkbox">
                                                        </div>
                                                    </div>
                                                    <div class="sub-sub-item fl"></div>
                                                </div>
                                            </div>
                                            <div class="merchants-select disn">
                                                <div class="merchants-wrap fl">
                                                    <div class="add-sub-item fl"><i
                                                                class="sc-icon sc-icon-add-small cursor"></i></div>
                                                    <div class="fl">
                                                        <div class="form-text fl clearfix">
                                                            <strong class="fl lh30 mar-right-5">下拉菜单值：</strong>
                                                            <input type="text" name="select[0][]"
                                                                   class="form-control xwd150 input-sm fl select">
                                                        </div>
                                                    </div>
                                                    <div class="sub-sub-item fl"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">特殊说明：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="fields_special" class="form-control" row="5"
                                          placeholder="特殊说明"
                                          style="min-height:100px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">显示位置：</label>
                            <div class="col-sm-4">
                                <select name="special_type" class="form-control select">
                                    <option value="0">请选择</option>
                                    <option value="1">上面</option>
                                    <option value="2">下面</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
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
            $('body').on('click', '.add-item', function () {
                var item = $(this).next().html();
                var html = '<div class="f-item clearfix">' +
                    '<div class="add-item fl"></div>' +
                    '<div class="item-info fl">' + item + '</div>' +
                    '<div class="sub-item-form fl"><i class="sc-icon sc-icon-sub-small cursor"></i></div></div>';
                $(this).parent().parent().append(html);
                var count = $('.f-item').length;
                $('.merchants-radio').each(function (k,v) {
                    $(v).find('.radio_checkbox').attr('name', 'radio_checkbox[' + k + '][]');
                });
                $('.merchants-select').each(function (k,v) {
                    $(v).find('.select').attr('name', 'select[' + k + '][]');
                });
            });

            $('body').on('click', '.sub-item-form', function () {
                $(this).parent().remove();
                var count = $('.f-item').length;
                $('.merchants-radio').each(function (k,v) {
                    $(v).find('.radio_checkbox').attr('name', 'radio_checkbox[' + k + '][]');
                });
                $('.merchants-select').each(function (k,v) {
                    $(v).find('.select').attr('name', 'select[' + k + '][]');
                });
            });

            $('body').on('click', '.add-sub-item', function () {
                var item = $(this).next().html();
                var html = '<div class="merchants-wrap fl">' +
                    '<div class="add-sub-item fl"></div>' +
                    '<div class="fl">' + item + '</div>' +
                    '<div class="sub-sub-item fl"><i class="sc-icon sc-icon-sub-small cursor"></i></div>';
                $(this).parent().parent().append(html);
            });

            $('body').on('click', '.sub-sub-item', function () {
                $(this).parent().remove();
            });

            $('body').on('change', 'select[name="merchants_form[]"]', function () {
                var parent = $(this).parent().parent().parent();
                parent.find('.merchants-other').hide();
                parent.find('.merchants-other-date').hide();
                parent.find('.merchants-text').hide();
                parent.find('.merchants-textarea').hide();
                parent.find('.merchants-radio').hide();
                parent.find('.merchants-select').hide();

                switch ($(this).val()) {
                    case 'input':
                        parent.find('.merchants-text').show();
                        break;
                    case 'textarea':
                        parent.find('.merchants-textarea').show();
                        break;
                    case 'radio':
                    case 'checkbox':
                        parent.find('.merchants-radio').show();
                        break;
                    case 'select':
                        parent.find('.merchants-select').show();
                        break;
                    case 'other':
                        parent.find('.merchants-other').show();
                        break;
                }
            });

            $('body').on('change', 'select[name="merchants_formOther[]"]', function () {
                var parent = $(this).parent();
                parent.next().hide();
                switch ($(this).val()) {
                    case 'dateTime':
                        parent.next().show();
                        break;
                }
            });
        });
    </script>
@endsection
@endsection