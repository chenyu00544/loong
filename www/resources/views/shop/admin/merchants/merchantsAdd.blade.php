@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>店铺 - 添加店铺</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>请先选择商城已注册会员进行添加店铺操作。</li>
                    <li>请根据提示信息准确无误填写店铺信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" name="conf" action="{{url('admin/storelist')}}"
                          method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <div class="merchants-section">
                            <div class="tit"><h4>请选择会员</h4></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">会员名称：</label>
                                <div class="col-sm-6">
                                    <select name="user_id" class="form-control input-sm fl wd-250">
                                        <option value="0">名称</option>
                                    </select>

                                    <input type="text"
                                           class="form-control input-sm fl wd-120 mar-left-20 user_keywords"
                                           value="" placeholder="会员名称"/>
                                    <a href="javascript:;"
                                       class="btn btn-primary search btn-sm mar-left-10 fl">查询</a>
                                </div>
                            </div>
                        </div>

                        @foreach($shopSteps as $shopStep)
                            @foreach($shopStep->mst as $mst)
                                <div class="merchants-section">
                                    <div class="tit"><h4>{{$mst->fields_titles}}</h4></div>
                                    @if($mst->steps_style == 0)
                                        @foreach($mst->textFields as $key => $field)
                                            @if($mst->fieldsForm[$key]['type'] == 'radio')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        @foreach($mst->fieldsForm[$key]['value'] as $k => $val)
                                                            <label class="radio-inline fl">
                                                                <input type="radio" name="{{$field}}" value="{{$val}}"
                                                                       @if($k == 0) checked @endif> {{$val}}
                                                            </label>
                                                        @endforeach
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'input')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="{{$field}}"
                                                               class="form-control input-sm"
                                                               value=""
                                                               placeholder="{{$mst->fieldsFormName[$key]}}"/>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'textarea')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                    <textarea name="{{$field}}" class="form-control" row="5"
                                                              placeholder="{{$mst->fieldsFormName[$key]}}"></textarea>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'select')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        <select name="{{$field}}" class="form-control input-sm">
                                                            <option value="0">请选择</option>
                                                            @foreach($mst->fieldsForm[$key]['value'] as $k => $val)
                                                                <option value="{{$val}}">{{$val}}</option>
                                                            @endforeach
                                                        </select>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'other')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-6">
                                                        @if($mst->fieldsForm[$key]['value'][0] == 'dateFile')
                                                            <input type="file" name="{{$field}}"
                                                                   class=""
                                                                   value=""/>
                                                        @elseif($mst->fieldsForm[$key]['value'][0] == 'textArea')
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_country fl wd-120">
                                                                <option value="0">国家</option>
                                                                @foreach($regions as $region)
                                                                    <option value="{{$region['id']}}">{{$region['name']}}</option>
                                                                @endforeach
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_province fl mar-left-10 wd-120">
                                                                <option value="0">省/直辖市</option>
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_city fl mar-left-10 wd-120">
                                                                <option value="0">市</option>
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_district fl mar-left-10 wd-120">
                                                                <option value="0">区/县</option>
                                                            </select>
                                                        @elseif($mst->fieldsForm[$key]['value'][0] == 'dateTime')
                                                            @if($mst->fieldsForm[$key]['value'][1] == '1--30')
                                                                <input type="text" name="{{$field}}"
                                                                       class="form-control input-sm wd-180"
                                                                       value="2000-01-01"/>
                                                            @else
                                                                <input type="text" name="{{$field}}"
                                                                       class="form-control input-sm fl wd-300"
                                                                       value="2000-01-01～2099-12-31"/>
                                                                <div class="col-sm-3">
                                                                    <div class="checkbox">
                                                                        <label class="mar-right-10">
                                                                            <input type="checkbox" name="shopTime_term"
                                                                                   value="1">永久
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            @endif
                                                        @endif
                                                    </div>
                                                </div>
                                            @endif
                                        @endforeach
                                    @elseif($mst->steps_style == 1)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>期望店铺类型：</label>
                                            <div class="col-sm-3">
                                                <select name="shoprz_type" class="form-control input-sm">
                                                    <option value="0">请选择...</option>
                                                    <option value="1">旗舰店</option>
                                                    <option value="2">专卖店</option>
                                                    <option value="3">专营店</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3 subShoprz_type disn">
                                                <select name="subShoprz_type" class="form-control input-sm">
                                                    <option value="0">请选择...</option>
                                                    <option value="1">厂商直营旗舰店</option>
                                                    <option value="2">厂商授权旗舰店</option>
                                                    <option value="3">卖场型旗舰店</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group authorizeFile disn">
                                            <label class="col-sm-3 control-label">授权有效期：</label>
                                            <div class="col-sm-6">
                                                <input type="text" name="shop_expireDate"
                                                       class="form-control input-sm wd-300 fl"
                                                       value="2000-01-01～2099-12-31"/>
                                                <div class="col-sm-3">
                                                    <div class="checkbox">
                                                        <label class="mar-right-10">
                                                            <input type="checkbox" name="shop_permanent" value="1">永久
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group authorizeFile disn">
                                            <label class="col-sm-3 control-label">相关授权书：</label>
                                            <div class="col-sm-3">
                                                <input type="file" name="authorizeFile" class="fl">
                                            </div>
                                        </div>

                                        <div class="form-group shop_hypermarketFile disn">
                                            <label class="col-sm-3 control-label">相关授权书：</label>
                                            <div class="col-sm-3">
                                                <input type="file" name="shop_hypermarketFile" class="fl">
                                            </div>
                                        </div>

                                    @elseif($mst->steps_style == 2)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>主营类目：</label>
                                            <div class="col-sm-3">
                                                <select name="shop_categoryMain"
                                                        class="form-control input-sm">
                                                    <option value="0">请选择...</option>
                                                    @foreach($cates as $cate)
                                                        <option value="{{$cate->id}}">{{$cate->cat_name}}</option>
                                                    @endforeach
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font class="red">*</font>
                                                详细类目：</label>
                                            <div class="col-sm-3">
                                                <select name="addCategoryMain" class="form-control input-sm">
                                                    <option value="0">请选择...</option>
                                                    @foreach($cates as $cate)
                                                        <option value="{{$cate->id}}">{{$cate->cat_name}}</option>
                                                    @endforeach
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group subcate disn">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6">
                                                <div class="checkbox">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6">
                                                <div class="checkbox" style="padding-top: 0;">
                                                    <label class="mar-right-20 pad-all-15">
                                                        <input type="checkbox" name="all_list" value="1">全选/反选
                                                    </label>
                                                    <a href="javascript:;" class="btn btn-info btn-sm cate-add">添加</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered cate">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%" class="text-center">序号</th>
                                                        <th width="35%" class="text-center">一级类目</th>
                                                        <th width="35%" class="text-center">二级类目</th>
                                                        <th width="20%" class="text-center">操作</th>
                                                    </tr>
                                                    </thead>
                                                    @if(count($mcts['mcts']) == 0)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="4" style="height: 80px;">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($mcts['mcts'] as $mct)
                                                            <tr class="">
                                                                <td class="text-center">
                                                                    <input type="hidden" name="cat_id[]"
                                                                           value="{{$mct->cat_id}}">
                                                                    {{$loop->index+1}}</td>
                                                                <td class="text-center">{{$mct->parent_name}}</td>
                                                                <td class="text-center">{{$mct->cat_name}}</td>
                                                                <td class="text-center">
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger cate-del btn-sm"
                                                                       data-id="{{$mct->ct_id}}">删除</a>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font class="red">*</font>
                                                对应类目行业资质：</label>
                                            <div class="col-sm-3">
                                                <a class="skyblue lh30">《行业资质标准》</a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered doc-t">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%" class="text-center">类目名称</th>
                                                        <th width="20%" class="text-center">资质名称</th>
                                                        <th width="30%" class="text-center">资质电子版</th>
                                                        <th width="30%" class="text-center">到期日</th>
                                                    </tr>
                                                    </thead>
                                                    @if(count($mcts['dt']) == 0)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="4">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($mcts['dt'] as $dt)
                                                            <tr class="">
                                                                <td class="text-center">
                                                                    <input type="hidden" name="dtf[]"
                                                                           value="{{$dt->mdf->dtf_id}}">
                                                                    {{$mct->parent_name}}</td>
                                                                <td class="text-center">{{$dt->dt_title}}</td>
                                                                <td class="text-center">
                                                                    <input type="file" name="permanent_file[]"
                                                                           class="fl" style="line-height: 1.4">
                                                                    <span>
                                                                        <a href="{{url(empty($dt->mdf->permanent_file)?'':$dt->mdf->permanent_file)}}"
                                                                           target="_blank" class="nyroModal">
                                                                            <i class="glyphicon glyphicon-picture top5"
                                                                               data-tooltipimg="" ectype="tooltip"
                                                                               data-toggle="tooltip"
                                                                               title="tooltip"></i>
                                                                        </a>
                                                                    </span>
                                                                </td>
                                                                <td class="text-center">
                                                                    <input type="date" class="form-control wd-160 fl"
                                                                           value="{{date('Y-m-d', empty($dt->mdf->permanent_date)?0:$dt->mdf->permanent_date)}}"
                                                                           name="permanent_date[]">
                                                                    <label class="checkbox fl mar-left-40"
                                                                           style="padding-top: 3px;">
                                                                        <input type="checkbox"
                                                                               name="cate_title_permanent[]" value="1"
                                                                               style="margin-top: 8px"
                                                                               @if(!empty($dt->mdf->cate_title_permanent)) checked @endif>永久
                                                                    </label>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>
                                    @elseif($mst->steps_style == 3)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                <a href="javascript:;"
                                                   class="btn btn-info btn-sm add-shop-brand">添加品牌</a>
                                            </label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered shop-brand">
                                                    <thead>
                                                    <tr>
                                                        <th width="7%" class="text-center">序号</th>
                                                        <th width="15%" class="text-center">品牌中文名称</th>
                                                        <th width="15%" class="text-center">品牌英文名称</th>
                                                        <th width="10%" class="text-center">品牌首字母</th>
                                                        <th width="10%" class="text-center">品牌LOGO</th>
                                                        <th width="14%" class="text-center">品牌类型</th>
                                                        <th width="14%" class="text-center">经营类型</th>
                                                        <th width="15%" class="text-center">操作</th>
                                                    </tr>
                                                    </thead>
                                                    @if(count($brands) == 0)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="8">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($brands as $brand)
                                                            <tr class="text-center">
                                                                <input type="hidden" name="bid[]"
                                                                       value="{{$brand->bid}}">
                                                                <td>{{$brand->bid}}</td>
                                                                <td>{{$brand->brand_name}}</td>
                                                                <td>{{$brand->brand_name_letter}}</td>
                                                                <td>{{$brand->brand_first_char}}</td>
                                                                <td>
                                                                    <span>
                                                                        <a href="{{url($brand->brand_logo)}}"
                                                                           target="_blank" class="nyroModal">
                                                                            <i class="glyphicon glyphicon-picture top2"
                                                                               data-tooltipimg="" ectype="tooltip"
                                                                               data-toggle="tooltip"
                                                                               title="tooltip"></i>
                                                                        </a>
                                                                    </span>
                                                                </td>
                                                                <td>{{$brand->brand_type}}</td>
                                                                <td>{{$brand->brand_operate_type}}</td>
                                                                <td class="text-center">
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger brand-edit btn-sm mar-right-10"
                                                                       data-id="{{$brand->bid}}">修改</a>
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger brand-del btn-sm"
                                                                       data-id="{{$brand->bid}}">删除</a>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>
                                    @elseif($mst->steps_style == 4)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>期望店铺类型：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label"><font
                                                            class="red">期望店铺类型</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">旗舰店命名规范：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label">店铺名称：品牌名|类目描述|旗舰店/官方旗舰店 <font class="red">(也可自定义,如：***官方旗舰店)</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>店铺名称：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label"><font
                                                            class="red">仅作为参考，最终已审核通过的店铺名称为准。</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6 brand-select-group">
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">选择品牌名：</label>
                                                    <div class="col-sm-6">
                                                        <select name="shoprz_brandName" class="form-control input-sm">
                                                            <option value="0">请选择品牌名称</option>
                                                            @foreach($brands as $brand)
                                                                <option value="{{$brand->bid}}">{{$brand->brand_name}}</option>
                                                            @endforeach
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">类目描述关键词：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="shop_class_keyWords"
                                                               class="form-control input-sm"
                                                               value="" placeholder="店铺名称"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">选择店铺后缀：</label>
                                                    <div class="col-sm-6">
                                                        <select name="shopNameSuffix"
                                                                class="form-control input-sm">
                                                            <option value="旗舰店">旗舰店</option>
                                                            <option value="专卖店">专卖店</option>
                                                            <option value="专营店">专营店</option>
                                                            <option value="馆">馆</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"><font
                                                                class="red">*</font>自创店铺名称：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="rz_shopName"
                                                               class="form-control input-sm"
                                                               value="" placeholder="自创店铺名称"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"><font
                                                                class="red">*</font>期望店铺登陆用户名：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="hopeLoginName"
                                                               class="form-control input-sm"
                                                               value="" placeholder="店铺名称"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    @endif
                                </div>
                            @endforeach
                        @endforeach
                        <div class="merchants-section">
                            <div class="tit"><h4>管理员操作</h4></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设置是否审核其商品：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="review_goods" value="0"> 否
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="review_goods" value="1" checked> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否为自营店铺：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="self_run" value="0" checked> 否
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="self_run" value="1"> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否关闭店铺：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="shop_close" value="0" checked> 关闭
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="shop_close" value="1"> 开启
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">审核使用店铺名称：</label>
                                <div class="col-sm-2">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="shopname_audit" value="0" checked> 未审核
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="shopname_audit" value="1"> 已审核
                                    </label>
                                </div>
                                <div class="notic col-sm-4" style="line-height: 24px;"><font class="red">（店铺申请使用店铺名称类型：入驻品牌店铺名称）</font>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">店铺信息审核：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="merchants_audit" value="0" checked> 未审核
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="merchants_audit" value="1"> 通过
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="merchants_audit" value="2"> 未通过
                                    </label>
                                </div>
                            </div>
                            <div class="form-group seller-grade disn">
                                <label class="col-sm-3 control-label">店铺等级：</label>
                                <div class="col-sm-3">
                                    <select name="grade_id" class="form-control input-sm">
                                        @foreach($grades as $grade)
                                            <option value="{{$grade->id}}">{{$grade->grade_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                            <div class="form-group seller-grade disn">
                                <label class="col-sm-3 control-label">等级年限：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="year_num" class="form-control input-sm wd-120"
                                           value="1" placeholder="店铺名称"/>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　"
                                       class="btn btn-danger clearfix">
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
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                layer.load();
                var that = this;
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 1, parent: parent}, function (data) {
                    layer.closeAll('loading');
                    if (data.data.length > 0) {
                        var html = '<option value="0">省/直辖市</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $(that).next().html(html);
                    }

                });
            });
            $('.shop_province').change(function () {
                layer.load();
                var that = this;
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 2, parent: parent}, function (data) {
                    layer.closeAll('loading');
                    if (data.data.length > 0) {
                        var html = '<option value="0">市</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $(that).next().html(html);
                    }
                });
            });
            $('.shop_city').change(function () {
                layer.load();
                var that = this;
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 3, parent: parent}, function (data) {
                    layer.closeAll('loading');
                    if (data.data.length > 0) {
                        var html = '<option value="0">区/县</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $(that).next().html(html);
                    }
                });
            });


            //搜索会员名称
            $('.search').on('click', function () {
                layer.load();
                var keywords = $('.user_keywords').val();
                $.post("{{url('admin/search')}}", {
                    type: 4,
                    keywords: keywords,
                    '_token': '{{csrf_token()}}'
                }, function (data) {
                    layer.closeAll('loading');
                    if (data.data.length > 0) {
                        $html = '<option value="0">名称</option>';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('select[name=user_id]').html($html);
                    }
                });
            });

            $('input[name=review_status]').click(function () {
                $('.review_content').hide();
                if ($(this).val() == 2) {
                    $('.review_content').show();
                }
            });


            //选择店铺类型
            $('select[name=shoprz_type]').change(function () {
                $('.shop_hypermarketFile').hide();
                $('.authorizeFile').hide();
                $('.subShoprz_type').hide();
                if ($(this).val() == 1) {
                    $('.subShoprz_type').show();
                }
            });
            $('select[name=subShoprz_type]').change(function () {
                $('.shop_hypermarketFile').hide();
                $('.authorizeFile').hide();
                if ($(this).val() == 2) {
                    $('.authorizeFile').show();
                } else if ($(this).val() == 3) {
                    $('.shop_hypermarketFile').show();
                }
            });


            //选择可经营类目
            $('select[name=addCategoryMain]').change(function () {
                var parent_id = $(this).val();
                if (parent_id != 0) {
                    $.post("{{url('admin/comcate/getcates/')}}/" + parent_id, {
                        '_token': "{{csrf_token()}}",
                        id: parent_id
                    }, function (data) {
                        var html = '';
                        if (data.data) {
                            $.each(data.data, function (k, v) {
                                html += '<label class="mar-right-20 pad-all-15">' +
                                    '<input type="checkbox" name="subcate[]" class="check-all" data-name="' + v.cat_name + '" value="' + v.id + '">' + v.cat_name + '</label>';
                            });
                        }
                        if (html != '') {
                            $('.subcate').show();
                        } else {
                            $('.subcate').hide();
                        }
                        $('.subcate .checkbox').html(html);
                    });
                }
            });
            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked');
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });
            $('.cate-add').click(function () {
                var cates = [];
                var uid = $('select[name=user_id]').val();
                $(".check-all").each(function (k, v) {
                    if ($(v).is(':checked')) {
                        cates.push($(v).val());
                    }
                });

                $.post("{{url('admin/storelist/cate/add')}}", {
                    '_token': '{{csrf_token()}}',
                    user_id: uid,
                    cat_ids: cates
                }, function (data) {
                    var html = '';
                    if (data.code == 1) {
                        var parent_name = '';
                        $.each(data.data.cate, function (k, v) {
                            parent_name = v.parent_name;
                            html += '<tr class=""><td class="text-center"><input type="hidden" name="cat_id[]" value="' + v.cat_id + '">' + (k + 1) + '</td>' +
                                '<td class="text-center">' + v.parent_name + '</td>' +
                                '<td class="text-center">' + v.cat_name + '</td>' +
                                '<td class="text-center">' +
                                '<a type="button" href="javascript:;" class="btn btn-danger cate-del btn-sm" data-id="' + v.ct_id + '">删除</a>' +
                                '</td></tr>';
                        });
                        $('.cate tbody').html(html);
                        var html_t = '';
                        $.each(data.data.dt, function (k, v) {
                            html_t += '<tr class=""><td class="text-center"><input type="hidden" name="dtf[]" value="' + v.mdf.dtf_id + '">' + parent_name + '</td>' +
                                '<td class="text-center">' + v.dt_title + '</td>' +
                                '<td class="text-center"><input type="file" name="permanent_file[]" style="line-height: 1.4"></td>' +
                                '<td class="text-center">' +
                                '<input type="date" class="form-control wd-160 fl" name="permanent_date[]">' +
                                '<label class="checkbox fl mar-left-40" style="padding-top: 3px;">' +
                                '<input type="checkbox" name="cate_title_permanent[]" value="1" style="margin-top: 8px">永久' +
                                '</label>' +
                                '</td></tr>';
                        });
                        $('.doc-t tbody').html(html_t);
                    }
                });


            });
            //删除类目
            $('.cate').on('click', '.cate-del', function () {
                var that = this;
                var id = $(this).data('id');
                $.post("{{url('admin/storelist/cate/del')}}/" + id, {
                    '_token': '{{csrf_token()}}'
                }, function (data) {
                    if (data.code == 1) {
                        $(that).parent().parent().remove();
                    }
                });
            });


            //添加店铺品牌
            $('.add-shop-brand').click(function () {
                var user_id = $('select[name=user_name]').val();
                layer.open({
                    type: 2,
                    area: ['1100px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加品牌',
                    content: ["{{url('admin/dialog/merchants/brand')}}/" + user_id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            $('.shop-brand').on('click', '.brand-edit', function () {
                var id = $(this).data('id');
                layer.open({
                    type: 2,
                    area: ['1100px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加品牌',
                    content: ["{{url('admin/dialog/merchants/brand/edit')}}/" + id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            $('.shop-brand').on('click', '.brand-del', function () {
                var that = this;
                var id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post("{{url('admin/dialog/merchants/brand/del')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        if (data.code == 1) {
                            $(that).parent().parent().remove();
                        }
                    });
                }, function () {
                });
            });

            $('input[name=merchants_audit]').on('click', function () {
                $('.seller-grade').hide();
                if ($(this).val() == 1) {
                    $('.seller-grade').show();
                }
            });

            //时间控件
            $('input[name=business_term]').daterangepicker(optionDateRangDay, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                var e = end.format('YYYY-MM-DD');
                var t = s + '～' + e;
                $('input[name=business_term]').val(t);
            });
            $('input[name=shop_expireDate]').daterangepicker(optionDateRangDay, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                var e = end.format('YYYY-MM-DD');
                var t = s + '～' + e;
                $('input[name=shop_expireDate]').val(t);
            });
            $('input[name=establish_date]').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                $('input[name=establish_date]').val(s);
            });
        });
    </script>
@endsection
@endsection