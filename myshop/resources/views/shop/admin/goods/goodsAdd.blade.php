@extends('shop.layouts.index')
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
                                <dl data-step="2" class="cur">
                                    <dt class="cursor">2</dt>
                                    <dd class="s-text">选择商品分类</dd>
                                </dl>
                                <dl data-step="3" class="cur">
                                    <dt class="cursor">3</dt>
                                    <dd class="s-text">填写商品信息</dd>
                                </dl>
                                <dl class="last" data-step="4">
                                    <dt class="pointer">4</dt>
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

                        <!--第三步 通用信息-->
                        <div class="step step-three" ectype="step" data-step="3" style="">
                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>填写通用信息</h3>
                                </div>
                                <div class="step-content">

                                    <div class="item item-com-cate">
                                        <div class="step-label">商品分类：</div>
                                        <div class="step-value">
                                            <span class="fl">家居、家具、家装、厨具 > 家装建材 > 灯饰照明</span>
                                            <a href="javascript:;" class="edit-category" ectype="edit-category">
                                                <i class="glyphicon glyphicon-edit"></i>
                                            </a>
                                            <a href="javascript:;" class="category-dialog">添加扩展分类</a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品货号：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_sn" class="form-control max-wd-190 hg30 fl "
                                                   autocomplete="off" onblur="checkGoodsSn(this.value,'0')" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">如果您不输入商品货号，系统将自动生成一个唯一的货号。</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品名称：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_name"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品简单描述：</div>
                                        <div class="step-value">
                                        <textarea class="form-control max-wd-350" rows="5"
                                                  name="goods_brief"></textarea>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">出售价：</div>
                                        <div class="step-value">
                                            <input type="text" name="shop_price" class="form-control max-wd-190 hg30 fl"
                                                   value="0">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">市场价：</div>
                                        <div class="step-value">
                                            <input type="text" name="market_price"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="0">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">成本价：</div>
                                        <div class="step-value">
                                            <input type="text" name="cost_price" class="form-control max-wd-190 hg30 fl"
                                                   value="0">
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
                                                <input type="text" name="brand_name" id="brand_name"
                                                       class="form-control max-wd-190 hg30 fl" data-filter="brand_name"
                                                       ectype="require" autocomplete="off" value="请选择" readonly="">
                                                <a href="javascript:;" class="btn btn-info btn-sm mar-left-20"
                                                   ectype="ajaxBrand">添加</a>
                                                <input type="hidden" name="brand_id" id="brand_id" value="0">
                                                <div class="form_prompt"></div>
                                            </div>
                                            <div class="brand-select-container" style="display: none;">
                                                <div class="brand-top">
                                                    <div class="letter">
                                                        <ul>
                                                            <li><a href="javascript:void(0);" data-letter="">全部品牌</a>
                                                            </li>
                                                            @for($letter = 65; $letter<=90;$letter++)
                                                                <li><a href="javascript:void(0);"
                                                                       data-letter="{{chr($letter)}}">{{chr($letter)}}</a>
                                                                </li>
                                                            @endfor
                                                            <li><a href="javascript:void(0);" data-letter="QT">其他</a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    <div class="b-search">
                                                        <input name="search_brand_keyword" id="search_brand_keyword"
                                                               ectype="require" type="text"
                                                               class="form-control max-wd-190 hg30 fl"
                                                               autocomplete="off" placeholder="1">
                                                        <a href="javascript:void(0);" class="btn-mini"><i
                                                                    class="glyphicon glyphicon-search"></i></a>
                                                    </div>
                                                </div>
                                                <div class="brand-list ps-container ps-active-y">
                                                    <ul>
                                                        <li data-id="0" data-name="请选择品牌" class="blue cursor">取消选择</li>
                                                        <li data-id="204" data-name="金士顿"><em>J</em>金士顿</li>
                                                        <li data-id="93" data-name="同庆和堂"><em>T</em>同庆和堂</li>
                                                        <li data-id="103" data-name="Masentek"><em>M</em>Masentek</li>
                                                        <li data-id="102" data-name="欧亚马"><em>O</em>欧亚马</li>
                                                    </ul>
                                                    <div class="brand-not" style="display: none;">没有符合"<strong></strong>"条件的品牌
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
                                                    <img src="{{url('styles/admin/images/upload_images.jpg')}}">
                                                </div>
                                            </div>
                                            <div class="form_prompt">
                                            </div>
                                            <div class="notic">图片尺寸建议800*800</div>
                                            <input type="hidden" name="original_img" value="">
                                            <input type="hidden" name="goods_img" value="">
                                            <input type="hidden" name="goods_thumb" value="">
                                            <div id="" class="moxie-shim moxie-shim-html5"
                                                 style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
                                                <input id="" type="file"
                                                       style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;"
                                                       accept="image/jpeg,image/png,image/gif"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">　</div>
                                        <div class="step-value">
                                            <a href="javascript:;" class="btn btn-primary btn-sm">图片库选择</a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品运费：</div>
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
                                <div class="step-title"><i class="ui-step"></i><h3>详细描述</h3></div>
                                <div class="tabs mar-top-20">
                                    <ul class="fl">
                                        <li class="fl curr"><a href="javascript:;"><i class="glyphicon glyphicon-blackboard"></i>电脑端</a>
                                        </li>
                                        <li class="fl"><a href="javascript:;"><i class="glyphicon glyphicon-phone"></i>手机端</a>
                                        </li>
                                    </ul>
                                </div>
                                <script id="editor" name="content" type="text/plain" style="height:500px;"></script>
                            </div>

                            <div class="step-pn clearfix">
                                <div class="goods-btn">
                                    <a href="javascript:;" class="btn btn-default mar-all-10 prev" data-step="2"
                                       data-type="step" ectype="stepSubmit">上一步，选择商品分类</a>
                                    <a href="javascript:;" class="btn btn-info mar-all-10 next" data-step="4"
                                       data-type="step" data-down="false" ectype="stepSubmit">下一步，填写商品属性</a>
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
    <script>
        var ue = UE.getEditor('editor');

        $(function () {

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
            })

            //填写商品信息上一步
            $('.prev').on('click', function () {
                step($(this).data('step'));
            });

            //填写商品信息下一步
            $('.next').on('click', function () {
                step($(this).data('step'));
            });

            //点击设置步骤指示条
            $('.stepflex dl').on('click', function () {
                step($(this).data('step'));
            });
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
                            if ($(this).data('cat_level') == level) {
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
                })
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
    </script>
@endsection
@endsection