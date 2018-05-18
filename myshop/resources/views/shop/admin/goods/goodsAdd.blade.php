@extends('shop.layouts.index')
@section('css')
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.css')}}">
@endsection
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品 - 添加新商品</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>请按提示栏信息进行商品添加。</li>
                    <li>默认模式：不区分仓库或地区模式设置价格与库存；仓库模式：按仓库设置价格与库存；地区模式：按地区设置价格与库存。</li>
                    <li>必须点击扫码入库按钮文本框出现光标在使用扫码枪扫码，扫码入库功能必须去店铺基本信息设置里面设置扫码appkey才可以使用。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" action="{{url('admin/goods')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <div class="flexilist">
                            <div class="stepflex">
                                <dl class="first cur" data-step="1">
                                    <dt class="cursor">1</dt>
                                    <dd class="s-text">设置商品模式</dd>
                                </dl>
                                <dl class="cur" data-step="2">
                                    <dt class="cursor">2</dt>
                                    <dd class="s-text">选择商品分类</dd>
                                </dl>
                                <dl class="cur" data-step="3">
                                    <dt class="cursor">3</dt>
                                    <dd class="s-text">填写商品信息</dd>
                                </dl>
                                <dl class="last " data-step="4">
                                    <dt class="cursor">4</dt>
                                    <dd class="s-text">填写商品属性</dd>
                                </dl>
                            </div>
                        </div>

                        <!--第一步 选择商品模式-->
                        <div class="step step-one" ectype="step" data-step="1" style="display: none;">
                            <h3>设置商品模式</h3>
                            <div class="mos clearfix">
                                <div class="mos_item mos_default active" data-model="0" data-stepmodel="3">
                                    <div class="mos_con">
                                        <div class="mos_left"><i class="mos_icon mos_icon_default"></i></div>
                                        <div class="mos_right"><span>默认模式</span></div>
                                    </div>
                                </div>
                                <div class="mos_item mos_warehouse " data-model="1" data-stepmodel="2">
                                    <div class="mos_con">
                                        <div class="mos_left"><i class="mos_icon mos_icon_warehouse"></i></div>
                                        <div class="mos_right"><span>仓库模式</span></div>
                                    </div>
                                </div>
                                <div class="mos_item mos_region " data-model="2" data-stepmodel="2">
                                    <div class="mos_con">
                                        <div class="mos_left"><i class="mos_icon mos_icon_region"></i></div>
                                        <div class="mos_right"><span>地区模式</span></div>
                                    </div>
                                </div>
                                <input type="hidden" name="goods_model" id="goods_model" value="0">
                            </div>
                            <div class="goods-btn">
                                <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="2"
                                   data-type="step" data-down="false" ectype="stepSubmit">下一步，选择商品分类</a>
                            </div>
                        </div>

                        <!--第二步 选择商品分类-->
                        <div class="step step-two" ectype="step" data-step="2" style="display: none;">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>选择商品分类</h3>
                            </div>

                            <div class="step-near fl clearfix">
                                <strong class="fl lh36">您最近使用的商品分类：</strong>
                                <select name="" id="" class="form-control fl max-wd-450">
                                    <option value="0">请选择</option>
                                </select>
                                <a class="btn btn-primary btn-select mar-left-10 fl">添加</a>
                            </div>

                            <div class="sort-info">
                                <div id="cate-add" class="clearfix">
                                    <div class="sort-list sort-list-one">
                                        <div class="sort-list-warp">
                                            <div class="category-list ps-container ps-active-y">
                                                <ul ectype="category" data-cat_level="1">
                                                    <li data-cat_name="" data-cat_id="0" data-cat_level="1"
                                                        class="current">
                                                        <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                                    </li>
                                                    @foreach($comCates as $comCate)
                                                        <li data-cat_id="{{$comCate->id}}"
                                                            data-cat_name="{{$comCate->cat_name}}" data-cat_level="1"
                                                            class="">
                                                            <a href="javascript:;"><i
                                                                        class="sc-icon"></i>{{$comCate->cat_name}}</a>
                                                        </li>
                                                    @endforeach
                                                </ul>
                                            </div>
                                            <div class="sort-point"></div>
                                        </div>
                                    </div>
                                    <div class="sort-list sort-list-one">
                                        <div class="sort-list-warp">
                                            <div class="category-list ps-container ps-active-y">
                                                <ul ectype="category" data-cat_level="2">
                                                    <li data-cat_name="" data-cat_id="0" data-cat_level="2">
                                                        <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="sort-point"></div>
                                    </div>
                                    <div class="sort-list">
                                        <div class="sort-list-warp">
                                            <div class="category-list ps-container">
                                                <ul ectype="category" data-cat_level="3">
                                                    <li data-cat_name="" data-cat_id="0" data-cat_level="3" class="">
                                                        <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="cat_id" id="cat_id" value="" ectype="cat_id">
                                    <div class="choiceClass" id="choiceClass">您当前选择的商品类别是：<strong class="red"></strong>
                                    </div>
                                </div>
                            </div>
                            <div class="goods-btn">
                                <a href="javascript:;" class="btn btn-default mar-all-10 prev" data-step="1"
                                   data-type="step" ectype="stepSubmit">上一步，选择商品模式</a>
                                <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="3"
                                   data-type="step" data-down="false" ectype="stepSubmit">下一步，填写通用信息</a>
                            </div>
                        </div>

                        <!--第三步 填写通用信息-->
                        <div class="step step-three" ectype="step" data-step="3" style="">
                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>填写通用信息</h3>
                                </div>
                                <div class="step-content clearfix">
                                    <div class="item item-com-cate">
                                        <div class="step-label">商品分类：</div>
                                        <div class="step-value">
                                            <span class="fl cate-name"></span>
                                            <a href="javascript:;" class="edit-category" ectype="edit-category"
                                               onclick="step(2)">
                                                <i class="glyphicon glyphicon-edit"></i>
                                            </a>
                                            <a href="javascript:;" class="category-dialog add-cate-extend"
                                               data-goods_id="0">添加扩展分类</a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品货号：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_sn" class="form-control max-wd-190 hg30 fl "
                                                   autocomplete="off" onblur="checkGoodsSn(this.value,'0')" value=""
                                                   placeholder="商品货号">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">如果您不输入商品货号，系统将自动生成一个唯一的货号。</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品名称：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_name"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="" placeholder="商品名称">

                                            <input id="color-picker" type="text" name="goods_name_color"
                                                   class="form-control max-wd-100 hg30 mar-left-20 fl" value="#000000"
                                                   style="background: #000000;color: #ffffff;">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品简单描述：</div>
                                        <div class="step-value">
                                        <textarea class="form-control max-wd-350" rows="5"
                                                  name="goods_brief" placeholder="商品简单描述"></textarea>
                                        </div>
                                    </div>

                                    <input name="suppliers_id" type="hidden" value="0">

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>出售价：</div>
                                        <div class="step-value">
                                            <input type="text" name="shop_price" class="form-control max-wd-190 hg30 fl"
                                                   value="0.00">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">市场价：</div>
                                        <div class="step-value">
                                            <input type="text" name="market_price"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="0.00">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">成本价：</div>
                                        <div class="step-value">
                                            <input type="text" name="cost_price" class="form-control max-wd-190 hg30 fl"
                                                   value="0.00">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品库存：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_number"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="1000">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">库存预警值：</div>
                                        <div class="step-value">
                                            <input type="text" name="warn_number"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="1">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品品牌：</div>
                                        <div class="step-value">
                                            <div class="selection">
                                                <input type="text" id="brand_name"
                                                       class="form-control max-wd-190 hg30 fl" data-filter="brand_name"
                                                       autocomplete="off" value="请选择品牌" readonly="">
                                                <a href="javascript:;" class="btn btn-info btn-sm mar-left-20 brand-add">添加</a>
                                                <input type="hidden" name="brand_id" id="brand_id" value="0">
                                                <div class="form_prompt"></div>
                                            </div>
                                            <div class="brand-select-container" style="display: none;">
                                                <div class="brand-top">
                                                    <div class="letter">
                                                        <ul>
                                                            <li>
                                                                <a href="javascript:;" data-letter="">全部品牌</a>
                                                            </li>
                                                            @for($letter = 65; $letter<=90;$letter++)
                                                                <li>
                                                                    <a href="javascript:;"
                                                                       data-letter="{{chr($letter)}}">{{chr($letter)}}</a>
                                                                </li>
                                                            @endfor
                                                            <li>
                                                                <a href="javascript:;" data-letter="QT">其他</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="b-search">
                                                        <input id="search_brand_keyword" type="text"
                                                               class="form-control max-wd-190 hg30 fl"
                                                               autocomplete="off" placeholder="品牌名">
                                                        <a href="javascript:;" class="btn-mini"><i
                                                                    class="glyphicon glyphicon-search"></i></a>
                                                    </div>
                                                </div>
                                                <div class="brand-list ps-container ps-active-y">
                                                    <ul>
                                                        <li data-id="0" data-name="请选择品牌" class="blue cursor">取消选择</li>
                                                        @foreach($brands as $bVal)
                                                            <li data-id="{{$bVal['id']}}"
                                                                data-name="{{$bVal['brand_name']}}">
                                                                <em>{{$bVal['brand_first_char']}}</em>{{$bVal['brand_name']}}
                                                            </li>

                                                        @endforeach
                                                    </ul>
                                                    <div class="brand-not" style="display: none;">没有符合"<strong
                                                                class="red"></strong>"条件的品牌
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item item-com-img">
                                        <div class="step-label"><em class="require-field">*</em>商品图片：</div>
                                        <div class="step-value">
                                            <div id="goods-figure" class="update-images">
                                                <div class="img">
                                                    <img src="{{url('styles/admin/images/upload_images.jpg')}}"
                                                         class="goods-img-show">
                                                </div>
                                            </div>
                                            <div class="form_prompt">
                                            </div>
                                            <div class="notic">图片尺寸建议800*800</div>
                                            <input type="hidden" name="original_img" value="">
                                            <input type="hidden" name="goods_img" value="">
                                            <input type="hidden" name="goods_thumb" value="">
                                            <div id="" class="moxie-shim moxie-shim-html5"
                                                 style="position: absolute; top: 0px; left: 0px; width: 100px; height: 100px; overflow: hidden; z-index: 0;">
                                                <input id="" type="file" name="goods_img_file"
                                                       style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100px; height: 100px;">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">　</div>
                                        <div class="step-value">
                                            <a href="javascript:;" class="btn btn-primary btn-sm img-lib-main">图片库选择</a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品运费：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="freight" value="1"> 按固定运费
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="freight" value="2" checked> 按运费模板
                                                </label>
                                            </div>
                                            <input type="text" name="shipping_fee"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   autocomplete="off" value="0.00" style="display:none;">
                                            <div id="tid">
                                                <select name="tid" id="" class="form-control max-wd-190 hg30 fl ft-12">
                                                    <option value="3">圆通快递</option>
                                                    <option value="5">京东快递</option>
                                                </select>
                                                <a href="javascript:;" class="btn btn-info btn-sm mar-left-20">添加</a>
                                                <a href="javascript:;" class="btn btn-info btn-sm mar-left-10">编辑</a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">退货标识：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_0" value="0">
                                                    <label class="ui-label mar-left-5" for="return_type_0">维修</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_1" value="1">
                                                    <label class="ui-label mar-left-5" for="return_type_1">退货</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_2" value="2">
                                                    <label class="ui-label mar-left-5" for="return_type_2">换货</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_3" value="3">
                                                    <label class="ui-label mar-left-5" for="return_type_3">仅退款</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品服务：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_reality" class="ui-checkbox"
                                                           value="1"
                                                           id="is_reality">
                                                    <label class="ui-label mar-left-5" for="is_reality">正品保证</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_return" class="ui-checkbox"
                                                           value="1"
                                                           id="is_return">
                                                    <label class="ui-label mar-left-5" for="is_return">包退服务</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_fast" class="ui-checkbox" value="1"
                                                           id="is_fast">
                                                    <label class="ui-label mar-left-5" for="is_fast">闪速配送</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--会员字段 预留↓-->
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <input type="hidden" name="user_price[]" autocomplete="off" value="-1">
                                    <!--会员字段 预留↑-->

                                </div>
                            </div>

                            <div class="step-desc clearfix">
                                <div class="step-title"><i class="ui-step"></i>
                                    <h3>详细描述</h3></div>
                                <div class="tabs mar-top-20">
                                    <ul class="fl">
                                        <li class="fl curr"><a href="javascript:;"><i
                                                        class="glyphicon glyphicon-blackboard"></i>电脑端</a>
                                        </li>
                                        <li class="fl"><a href="javascript:;"><i class="glyphicon glyphicon-phone"></i>手机端</a>
                                        </li>
                                    </ul>
                                </div>
                                <script id="editor" name="content" type="text/plain"></script>
                            </div>

                            <div class="step-special clearfix">
                                <div class="step-title"><i class="ui-step"></i>
                                    <h3>特殊信息</h3></div>
                                <div class="step-content">
                                    <div class="item">
                                        <div class="step-label">促销：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_promote" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_promote" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">限购：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_xiangou" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_xiangou" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">分期：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_stages" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_stages" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">是否为分销商品：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_distribution" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_distribution" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">阶梯价格：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_volume" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_volume" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">满减价格：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_fullcut" value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="is_fullcut" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="step-special clearfix">
                                <div class="step-title"><i class="ui-step"></i>
                                    <h3>特殊信息</h3></div>
                                <div class="step-content">
                                    <div class="item">
                                        <div class="step-label">赠送消费积分数：</div>
                                        <div class="step-value">
                                            <input type="text" name="give_integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">赠送等级积分数：</div>
                                        <div class="step-value">
                                            <input type="text" name="rank_integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">积分购买金额：</div>
                                        <div class="step-value">
                                            <input type="text" name="integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">(此处需填写金额)购买该商品时最多可以使用积分的金额</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品重量：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_weight"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="0">
                                            <select name="weight_unit" id=""
                                                    class="form-control max-wd-100 hg30 fl ft-12 mar-left-20">
                                                <option value="0.001">克</option>
                                                <option value="1">千克</option>
                                            </select>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">赠送等级积分数：</div>
                                        <div class="step-value">
                                            <input type="text" name="rank_integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="0">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">加入推荐：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_best" class="ui-checkbox" value="1"
                                                           id="is_best" checked>
                                                    <label class="ui-label mar-left-5" for="is_best">精品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_new" class="ui-checkbox" value="1"
                                                           id="is_new" checked>
                                                    <label class="ui-label mar-left-5" for="is_new">新品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_hot" class="ui-checkbox" value="1"
                                                           id="is_hot" checked>
                                                    <label class="ui-label mar-left-5" for="is_hot">热销</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">店铺推荐：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="store_best" class="ui-checkbox"
                                                           value="1" id="store_best">
                                                    <label class="ui-label mar-left-5" for="store_best">精品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="store_new" class="ui-checkbox"
                                                           value="1" id="store_new">
                                                    <label class="ui-label mar-left-5" for="store_new">新品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="store_hot" class="ui-checkbox"
                                                           value="1"
                                                           id="store_hot">
                                                    <label class="ui-label mar-left-5" for="store_hot">热销</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">上架：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                                <div class="switch active" data-type="is_on_sale" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="1" name="is_on_sale">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">普通商品销售：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                                <div class="switch active" data-type="is_alone_sale" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="1" name="is_alone_sale">
                                                </div>
                                            </div>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">默认为是，如果勾选否，则该商品不能直接购买，只能作为配件、赠品等商品购买</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">是否为免运费商品：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                                <div class="switch" data-type="is_shipping" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="0" name="is_shipping">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品关键词：</div>
                                        <div class="step-value">
                                            <input type="text" name="keywords"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">商品关键词：请用空格分隔；1.作为站内关键词查询；2.作为搜索引擎收录使用。
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">评论标签：</div>
                                        <div class="step-value">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="goods_product_tag"></textarea>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">
                                                请用','号分割；例：商品好看,很实用,材料很好...（注意逗号要使用英文逗号）<br>商品确认收货后，买家评论时可勾选的“买家印象”处内容
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">服务承诺标签：</div>
                                        <div class="step-value">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="goods_tag"></textarea>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">请用','号分割（注：逗号要使用英文逗号）</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商家备注：</div>
                                        <div class="step-value">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="seller_note"></textarea>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">仅供商家自己看的信息</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="step-pn clearfix">
                                <div class="goods-btn">
                                    <a href="javascript:;" class="btn btn-default mar-all-10 prev" data-step="2"
                                       data-type="step" ectype="stepSubmit">上一步，选择商品分类</a>
                                    <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="4"
                                       data-type="step" data-down="false" ectype="stepSubmit">下一步，填写商品属性</a>
                                    <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="5"
                                       data-type="step" data-down="false" ectype="stepSubmit">完成,发布商品</a>
                                </div>
                            </div>
                        </div>

                        <!--第四步 填写商品属性-->
                        <div class="step step-three" ectype="step" data-step="4" style="display: none;">
                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>商品属性</h3>
                                </div>
                                <div class="step-content">
                                    <div class="step-item">
                                        <div class="step-item-left"><h5>属性分类：</h5></div>
                                        <div class="step-item-right">
                                            <input name="attr_parent_id" type="hidden" value="">
                                            <div class="item-right-li">
                                                <div class="value-select" ectype="type_cat">
                                                    <select class="form-control max-wd-190 fl mar-right-20">
                                                        <option value="0">请选择</option>
                                                    </select>

                                                    <select class="form-control max-wd-190 fl mar-right-20">
                                                        <option value="0">请选择</option>
                                                    </select>
                                                </div>
                                                <a class="btn btn-info" data-type="add_goods_type_cat"
                                                   data-goodsid="615">添加属性分类</a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="step-item">
                                        <div class="step-item-left"><h5>商品类型：</h5></div>
                                        <div class="step-item-right">
                                            <div class="item-right-li">
                                                <div class="value-select" ectype="type_cat">
                                                    <select id="cate_id" class="form-control max-wd-350 fl">
                                                        <option value="0">请选择</option>
                                                    </select>
                                                </div>
                                                <input name="goods_type" type="hidden" value="" id="select_attr_val">
                                                <a class="btn btn-info mar-left-20"
                                                   data-type="add_goods_type_cat" data-goodsid="615">添加商品类型</a>
                                                <a class="btn btn-info mar-left-20"
                                                   data-type="add_goods_type_cat" data-goodsid="615">添加属性</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>商品相册</h3>
                                </div>
                                <div class="step-content">
                                    <div class="goods-album clearfix" id="gallery-img-list">
                                        <ul id="ul-pics">
                                            <li id="gallery-643">
                                                <div class="img" onclick="img_default(643)"><img
                                                            src="{{url('upload/images/201703/thumb_img/0_thumb_P_1489096810306.jpg')}}"
                                                            width="160" height="160" id="external_img_url643"></div>
                                                <div class="info">
                                                    <span class="zt red">主图</span>
                                                    <div class="sort">
                                                        <span>排序：</span>
                                                        <input type="text" value="1" name="old_img_desc[643]"
                                                               class="stext form-control max-wd-50 hg25"
                                                               autocomplete="off" maxlength="3">
                                                        <input type="hidden" value="643" name="img_id[]">
                                                    </div>
                                                    <a href="javascript:;" data-imgid="643" class="delete_img"><i
                                                                class="glyphicon glyphicon-trash"></i></a>
                                                </div>
                                                <div class="info">
                                                    <input name="external_url" type="text"
                                                           class="form-control max-wd-190 external_url_643"
                                                           ectype="external_url" value="" title="" data-imgid="643"
                                                           placeholder="图片外部链接地址"></div>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="step-top-btn gallery-album clearfix" ectype="gallery_album_list"
                                         data-inid="addAlbumimg" data-act="gallery_album_list"
                                         style="position: relative;">
                                        <a href="javascript:;" class="btn btn-danger mar-all-10 line-hg-30 ft-16"
                                           id="addImages" style="position: relative; z-index: 1;">
                                            <i class="glyphicon glyphicon-plus"></i>添加图片
                                        </a>
                                        <a href="javascript:;" class="btn btn-danger mar-all-10 line-hg-30 ft-16"
                                           ectype="gallery_album" data-value="图片库选择">
                                            <i class="glyphicon glyphicon-plus"></i>图片库选择
                                        </a>
                                        <div id="addAlbumimg"></div>
                                    </div>
                                </div>

                                <div class="step-pn clearfix">
                                    <div class="goods-btn">
                                        <a href="javascript:;" class="btn btn-default mar-all-10 prev" data-step="3"
                                           data-type="step" ectype="stepSubmit">上一步，填写通用信息</a>
                                        <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="5"
                                           data-type="step" data-down="false" ectype="stepSubmit">完成,发布商品</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.config.js')}}"></script>
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.all.min.js')}}"></script>
    <script type="text/javascript"
            src="{{url('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.js')}}"></script>
    <script>
        $(function () {

            $('body').on('click', function () {
                $('.brand-select-container').hide();
            });

            var ue = UE.getEditor('editor');
            ue.ready(function () {
                ue.setHeight(500);
            });

            //选择颜色
            $('#color-picker').colorpicker();
            $('#color-picker').on('change', function () {
                $(this).css('background', $(this).val());
                $(this).css('color', '#fff');
            });

            //选择品牌
            $('#brand_name').on('click', function (event) {
                $('.brand-select-container').show();
                event.stopPropagation();
            });
            $('.brand-select-container').on('click', function (event) {
                event.stopPropagation();
            });
            $('.brand-top .letter ul li a').on('click', function () {
                var letter = $(this).data('letter');
                var param = {
                    '_token': '{{csrf_token()}}',
                    letter: letter
                };
                searchBrand(param, letter);
            });
            $('.btn-mini').on('click', function () {
                var keywords = $('#search_brand_keyword').val();
                if (keywords != '') {
                    var param = {
                        '_token': '{{csrf_token()}}',
                        keywords: keywords
                    };
                    searchBrand(param, keywords);
                }
            });
            $('.brand-list ul').on('click', 'li', function () {
                $('.brand-select-container').hide();
                $('#brand_name').val($(this).data('name'));
                $('#brand_id').val($(this).data('id'));
            });

            //添加品牌
            $(".brand-add").on('click', function () {
                layer.open({
                    type: 2,
                    area: ['1100px', '600px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加品牌',
                    content: ["{{url('admin/brand/create')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //直接选择图片
            $("input[name=goods_img_file]").on('change', function () {
                var objUrl = getImageURL(this.files[0]);
                if (objUrl) {
                    $(".goods-img-show").attr("src", objUrl);
                }
            });

            //图片库选择图片
            $('.img-lib-main').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['800px', '540px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '图片库选择图片',
                    content: ["{{url('admin/goods/imagelibrary')}}", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            })

            //第一步 选择模式
            $('.mos_item').on('click', function () {
                $('.mos_item').removeClass('active');
                $(this).addClass('active');
                var model = $(this).data('model');
                $('input[name=goods_model]').val(model);
                $('.step-one').hide();
                $('.step-two').show();
                step(2)
            });

            //第二步选择商品分类
            $('.category-list').on('click', 'li', function () {
                $(this).parent().find('li').removeClass('current');
                $(this).addClass('current');
                var cate_id = $(this).data('cat_id');
                var cate_level = $(this).data('cat_level');
                getNextCate(cate_id, cate_level);
                var c_id = 0;
                $('.current').each(function () {
                    if (cate_id == 0 && cate_level > 1) {
                        if ($(this).data('cat_id') != 0) {
                            c_id = $(this).data('cat_id');
                        } else {
                            cate_id = c_id;
                        }
                    }
                });
                $('input[name=cat_id]').val(cate_id);
            });

            //填写商品信息上一步
            $('.prev').on('click', function () {
                step($(this).data('step'));
            });

            //填写商品信息下一步
            $('.next').on('click', function () {
                if ($(this).data('step') == 3 && $('input[name=cat_id]').val() <= 0) {
                    $('.choiceClass strong').html('您还未选择分类');
                    $('.cate-name').html('');
                    return;
                }
                step($(this).data('step'));
            });

            //点击设置步骤指示条
            $('.stepflex dl').on('click', function () {
                if ($(this).data('step') == 2 && $('input[name=goods_model]').val() == '') {
                    return;
                }

                if ($(this).data('step') == 3 && $('input[name=cat_id]').val() <= 0) {
                    return;
                }

                if ($(this).data('step') == 4 && ($('input[name=goods_name]').val() == '')) {
                    return;
                }
                step($(this).data('step'));
            });

            //添加扩展分类
            $('.add-cate-extend').on('click', function () {

                var goods_id = $(this).data('goods_id');

                layer.open({
                    type: 2,
                    area: ['800px', '540px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加扩展分类',
                    content: ["{{url('admin/goods/cateextend/')}}" + "/" + goods_id, 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            })
        });

        function getNextCate(parent_id, cat_level) {
            var id = parent_id;
            var level = cat_level + 1;
            if (id > 0 && cat_level < 3) {
                var html = '';
                $.post("{{url('admin/comcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                            '   </li>';
                        $.each(data.data, function (k, v) {
                            html += '<li data-cat_name="' + v.cat_name + '" data-cat_id="' + v.id + '" data-cat_level="' + level + '" class="">\n' +
                                '       <a href="javascript:;"><i class="sc-icon"></i>' + v.cat_name + '</a>\n' +
                                '   </li>';
                        });
                        $('.category-list ul').each(function () {
                            if ($(this).data('cat_level') == level) {
                                $(this).html(html);
                            }
                        })
                    } else {
                        $('.category-list ul').each(function () {
                            if ($(this).data('cat_level') >= level) {
                                $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                                    '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                                    '   </li>');
                            }
                        })
                    }
                    var html = '';
                    $('.current').each(function () {
                        html += $(this).data('cat_name') + ' > ';
                    });
                    html = html.substr(0, html.length - 2);
                    $('.choiceClass strong').html(html);
                    $('.cate-name').html(html);
                })
            } else {
                $('.category-list ul').each(function () {
                    if ($(this).data('cat_level') >= level) {
                        $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                            '   </li>');
                    }
                });
                var html = '';
                $('.current').each(function () {
                    html += $(this).data('cat_name') + ' > ';
                });
                html = html.substr(0, html.length - 2);
                $('.choiceClass strong').html(html);
                $('.cate-name').html(html);
            }
        }

        //显示步骤
        function step(num) {
            $('.step').each(function () {
                if ($(this).data('step') == num) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            })
            $('.stepflex dl').each(function () {
                if ($(this).data('step') <= num) {
                    if (!$(this).hasClass('cur')) {
                        $(this).addClass('cur');
                    }
                } else {
                    $(this).removeClass('cur');
                }
            })
        }

        //品牌搜索
        function searchBrand(param, keywords) {
            $.post("{{url('admin/brand/search')}}", param, function (data) {
                if (data.length > 0) {
                    $('.brand-not').hide();
                    var html = '<li data-id="0" data-name="请选择品牌" class="blue cursor">取消选择</li>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<li data-id="' + data[i].id + '" data-name="' + data[i].brand_name + '"><em>' + data[i].brand_first_char + '</em>' + data[i].brand_name + '</li>'
                    }
                    $('.brand-list ul').html(html);
                } else {
                    $('.brand-list ul').html("");
                    $('.brand-not').show();
                    $('.brand-not strong').html(keywords);
                }
            });
        }

    </script>
@endsection
@endsection