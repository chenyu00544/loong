@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商家管理 - 添加标准</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识“*”的选项为必填项，其余为选填项。
                    </li
                    >
                    <li>请合理添加标准，并和商家协商一致。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/entrycriteria')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>标准名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="criteria_name" class="form-control" value=""
                                       placeholder="标准名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上级：</label>
                            <div class="col-sm-3">
                                <select name="parent_id" class="form-control">
                                    <option value="0">请选择...</option>
                                    @foreach($parents as $parent)
                                        <option value="{{$parent->id}}">{{$parent->criteria_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>字段类型：</label>
                            <div class="col-sm-3">
                                <select name="type" class="form-control">
                                    <option value="">请选择...</option>
                                    <option value="text">文本框</option>
                                    <option value="select">下拉框</option>
                                    <option value="textarea">文本域</option>
                                    <option value="file">文件上传</option>
                                    <option value="charge">缴纳费用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group charge disn">
                            <label class="col-sm-4 control-label">是否累加：</label>
                            <div class="col-sm-3">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cumulative" value="1"> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cumulative" value="0" checked> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group charge disn">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>缴纳费用：</label>
                            <div class="col-sm-3">
                                <input type="text" name="charge" class="form-control" value="0"
                                       placeholder="缴纳费用">
                            </div>
                        </div>
                        <div class="form-group text disn">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>文本类型：</label>
                            <div class="col-sm-3">
                                <select name="data_type" class="form-control">
                                    <option value="">请选择...</option>
                                    <option value="1">电话号码</option>
                                    <option value="2">邮件</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group text disn">
                            <label class="col-sm-4 control-label">是否必填：</label>
                            <div class="col-sm-3">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_mandatory" value="1"> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_mandatory" value="0" checked> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group select disn">
                            <label class="col-sm-4 control-label">选项值：</label>
                            <div class="col-sm-3">
                                <div class="merchants-wrap fl">
                                    <div class="add-sub-item fl"><i class="sc-icon sc-icon-add-small cursor"></i></div>
                                    <div class="fl">
                                        <div class="form-text fl clearfix">
                                            <input type="text" name="option_value[]" class="form-control xwd150 input-sm fl select">
                                        </div>
                                    </div>
                                    <div class="sub-sub-item fl"></div>
                                </div>
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

            $('select[name=type]').change(function () {
                $('.text').hide();
                $('.charge').hide();
                $('.select').hide();
                if($(this).val() == 'text'){
                    $('.text').show();
                }else if($(this).val() == 'charge'){
                    $('.charge').show();
                }else if($(this).val() == 'select'){
                    $('.select').show();
                }
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
        });
    </script>
@endsection
@endsection