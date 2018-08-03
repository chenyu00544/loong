@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家 - 添加流程步骤</div>
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
                                <select name="fields_steps" class="form-control select">
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
                                <select name="steps_style" class="form-control select">
                                    <option value="0">基本信息</option>
                                    <option value="1">店铺类型</option>
                                    <option value="2">类目信息</option>
                                    <option value="3">品牌信息</option>
                                    <option value="4">店铺信息</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="m-item m-item-curr">
                                <div class="handle" ectype="addMerchants"><i class="sc_icon sc_icon_jia"></i></div>
                                <div class="info">
                                    <div class="p-item">
                                            <span class="ipt_text">
                                                <strong class="fl">表单字段：</strong>
                                                <input type="text" name="merchants_date[]" class="text w150">
                                            </span>
                                        <span class="ipt_text">
                                                <strong class="fl">数据类型：</strong>
                                                <div class="imitate_select select_w140">
                                                    <div class="cite">VARCHAR</div>
                                                    <ul style="display: none;" class="ps-container">
                                                        <li><a href="javascript:;" data-value="VARCHAR" class="ftx-01">VARCHAR</a></li>
                                                        <li><a href="javascript:;" data-value="CHAR"
                                                               class="ftx-01">CHAR</a></li>
                                                        <li><a href="javascript:;" data-value="INT"
                                                               class="ftx-01">INT</a></li>
                                                        <li><a href="javascript:;" data-value="MEDIUMINT"
                                                               class="ftx-01">MEDIUMINT</a></li>
                                                        <li><a href="javascript:;" data-value="SMALLINT" class="ftx-01">SMALLINT</a></li>
                                                        <li><a href="javascript:;" data-value="TINYINT" class="ftx-01">TINYINT</a></li>
                                                        <li><a href="javascript:;" data-value="TEXT"
                                                               class="ftx-01">TEXT</a></li>
                                                        <li><a href="javascript:;" data-value="DECIMAL" class="ftx-01">DECIMAL</a></li>
                                                    <div class="ps-scrollbar-x-rail"
                                                         style="width: 139px; display: none; left: 0px; bottom: 3px;"><div
                                                                class="ps-scrollbar-x"
                                                                style="left: 0px; width: 0px;"></div></div><div
                                                                class="ps-scrollbar-y-rail"
                                                                style="top: 0px; height: 224px; display: none; right: 0px;"><div
                                                                    class="ps-scrollbar-y"
                                                                    style="top: 0px; height: 0px;"></div></div></ul>
                                                    <input name="merchants_dateType[]" type="hidden" value="VARCHAR"
                                                           id="merchants_dateType_val">
                                                </div>
                                            </span>
                                        <span class="ipt_text">
                                                <strong class="fl">表单标题：</strong>
                                                <input type="text" name="merchants_formName[]" class="text w150"
                                                       autocomplete="off">
                                            </span>
                                        <span class="ipt_text">
                                                <strong class="fl">数据长度：</strong>
                                                <input type="text" name="merchants_length[]" class="text w50"
                                                       autocomplete="off">
                                            </span>
                                        <span style="display:none">
												&nbsp;&nbsp;
												是否为空&nbsp;
												<select name="merchants_notnull[]">
												<option value="NOT NULL" selected="selected">NOT NULL</option>
												<option value="NULL">NULL</option>
												</select>
												&nbsp;&nbsp;
												数据编码&nbsp;
												<select name="merchants_coding[]">
												<option value="GBK">GBK</option>
												<option value="UTF8" selected="selected">UTF8</option>
												</select>
											</span>
                                        <span class="ipt_text">
                                                <strong class="fl">显示排序：</strong>
                                                <input type="text" name="fields_sort[]" class="text w50"
                                                       autocomplete="off">
                                            </span>
                                        <span class="ipt_text">
                                                <strong class="fl">必选项：</strong>
                                                <div class="imitate_select select_w60">
                                                    <div class="cite">否</div>
                                                    <ul class="ps-container" style="display: none;">
                                                        <li><a href="javascript:;" data-value="0"
                                                               class="ftx-01">否</a></li>
                                                        <li><a href="javascript:;" data-value="1"
                                                               class="ftx-01">是</a></li>
                                                    <div class="ps-scrollbar-x-rail"
                                                         style="width: 59px; display: none; left: 0px; bottom: 3px;"><div
                                                                class="ps-scrollbar-x"
                                                                style="left: 0px; width: 0px;"></div></div><div
                                                                class="ps-scrollbar-y-rail"
                                                                style="top: 0px; height: 56px; display: none; right: 0px;"><div
                                                                    class="ps-scrollbar-y"
                                                                    style="top: 0px; height: 0px;"></div></div></ul>
                                                    <input name="will_choose_0" type="hidden" value="0">
                                                </div>
                                            </span>
                                    </div>
                                    <div class="p-item">
                                        	<span class="ipt_text">
                                            	<strong class="fl">表单注释：</strong>
                                                <input type="text" name="formName_special[]" class="text w400"
                                                       autocomplete="off">
                                            </span>
                                        <span class="ipt_text">
                                            	<strong class="fl">表单类型：</strong>
                                                <div class="imitate_select select_w140" data-tab="formType">
                                                    <div class="cite">文本字段(input)</div>
                                                    <ul style="display: none;" class="ps-container">
                                                        <li><a href="javascript:;" data-value="input" class="ftx-01">文本字段(input)</a></li>
                                                        <li><a href="javascript:;" data-value="textarea" class="ftx-01">文本区域(textarea)</a></li>
                                                        <li><a href="javascript:;" data-value="radio" class="ftx-01">单选按钮(radio)</a></li>
                                                        <li><a href="javascript:;" data-value="checkbox" class="ftx-01">多选按钮(checkbox)</a></li>
                                                        <li><a href="javascript:;" data-value="select" class="ftx-01">下拉菜单(select)</a></li>
                                                        <li><a href="javascript:;" data-value="other" class="ftx-01">其他(other)</a></li>
                                                    <div class="ps-scrollbar-x-rail"
                                                         style="width: 139px; display: none; left: 0px; bottom: 3px;"><div
                                                                class="ps-scrollbar-x"
                                                                style="left: 0px; width: 0px;"></div></div><div
                                                                class="ps-scrollbar-y-rail"
                                                                style="top: 0px; height: 168px; display: none; right: 0px;"><div
                                                                    class="ps-scrollbar-y"
                                                                    style="top: 0px; height: 0px;"></div></div></ul>
                                                    <input name="merchants_form[]" type="hidden" value="input">
                                                </div>
                                            </span>
                                        <span class="ipt_text merchantsForm" ectype="merchantsForm_text">
                                            	<strong class="fl">表单长度：</strong>
                                                <input type="text" name="merchants_formSize[]" class="text w50"
                                                       autocomplete="off">
                                            </span>
                                        <span class="ipt_text merchantsForm" ectype="merchantsForm_textarea">
                                            	<strong class="fl">行数以及宽度：</strong>
                                                <input type="text" name="merchants_rows[]" class="text w50"
                                                       autocomplete="off">
                                                <span class="bolang">&nbsp;&nbsp;~&nbsp;&nbsp;</span>
                                                <input type="text" name="merchants_cols[]" class="text w50"
                                                       autocomplete="off">
                                            </span>
                                        <span class="ipt_text merchantsForm" style="" ectype="merchantsForm_select">
												<strong class="fl">选择类型：</strong>
												<div class="imitate_select select_w140">
													<div class="cite">地区类型</div>
													<ul style="display: none;">
														<li><a href="javascript:;" data-value="textArea" class="ftx-01">地区类型</a></li>
														<li><a href="javascript:;" data-value="dateTime" class="ftx-01">日期类型</a></li>
														<li><a href="javascript:;" data-value="dateFile" class="ftx-01">文件上传</a></li>
													</ul>
													<input name="merchants_formOther[]" type="hidden" value="">
												</div>

												<span name="merchantsForm_dateTime[]"
                                                      ectype="merchantsForm_select_content" class="fl hide">
													<strong class="fl">日期表单长度：</strong>
													<input name="merchants_formOtherSize[]" type="text" size="10"
                                                           value="" class="text w50 valid">
												</span>
											</span>
                                        <span class="ipt_text merchantsForm relative" ectype="merchantsForm_checkbox"
                                              style="width:580px;">
                                            	<div class="ipt-icon"><i class="sc_icon sc_icon_jia2"></i></div>
                                                <div class="item-item">
                                                	<strong class="fl">单选或多选按钮名称：</strong>
                                                    <input type="text" name="radio_checkbox_0[]" class="text w150"
                                                           autocomplete="off">
                                                    <strong class="fl">显示排序：</strong>
                                                    <input type="text" name="rc_sort_0[]" class="text w50"
                                                           autocomplete="off">
                                                    <a href="javascript:;" class="btn_trash"><i
                                                                class="icon icon-trash"></i>删除</a>
                                                </div>
                                            </span>
                                        <span class="ipt_text merchantsForm relative" ectype="merchantsForm_select_opt">
                                            	<div class="ipt-icon"><i class="sc_icon sc_icon_jia2"></i></div>
                                                <div class="item-item">
                                                    <strong class="fl">下拉菜单值：</strong>
                                                    <input type="text" name="select_0[]" class="text w150"
                                                           autocomplete="off">
                                                    <a href="javascript:;" class="btn_trash"><i
                                                                class="icon icon-trash"></i>删除</a>
                                                </div>
                                            </span>
                                    </div>
                                </div>
                                <div class="m-btn-trash"><a href="javascript:;" class="btn_trash"><i
                                                class="icon icon-trash"></i>删除</a></div>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">特殊说明：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="fields_special" class="form-control ww" row="5"
                                          placeholder="特殊说明"
                                          style="min-height:100px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">显示位置：</label>
                            <div class="col-sm-4">
                                <select name="steps_style" class="form-control select">
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

        });
    </script>
@endsection
@endsection