@extends('shop.layouts.index')
@section('css')
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.css')}}">
    <script>
        var productUrl = "{{url('admin/goods/product/')}}/";
        var setGoodsAttrUrl = "{{url('admin/goods/setgoodsattr/')}}";
        var getAttributesUrl = "{{url('admin/attribute/getattributes/')}}/";
        var getCatesUrl = "{{url('admin/typecate/getcates/')}}/";
        var getTypeCatesUrl = "{{url('admin/goodstype/gettypescates/')}}/";
        var getGoodsTypeUrl = "{{url('admin/goodstype/gettypes/')}}/";
        var getGoodsAttrUrl = "{{url('admin/goods/getgoodsattr/')}}/";
        var delProductUrl = "{{url('admin/goods/delproduct/')}}/";
    </script>
@endsection
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 添加新商品</div>
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
                    <form enctype="multipart/form-data" action="{{url('admin/goods/'.$goodsInfo->goods_id)}}"
                          method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <input type="hidden" name="goods_id" value="{{$goodsInfo->goods_id}}">
                        <input type="hidden" name="review_status" value="{{$goodsInfo->review_status}}">
                        <input type="hidden" name="other_catids" value="">
                        <div class="flexilist">
                            <div class="stepflex">
                                <dl class="first cur" data-step="1">
                                    <dt class="cursor">1</dt>
                                    <dd class="s-text">设置商品模式</dd>
                                </dl>
                                <dl class="@if($goodsInfo->cat_id != 0) cur @endif" data-step="2">
                                    <dt class="cursor">2</dt>
                                    <dd class="s-text">选择商品分类</dd>
                                </dl>
                                <dl class="@if($goodsInfo->goods_id != 0) cur @endif" data-step="3">
                                    <dt class="cursor">3</dt>
                                    <dd class="s-text">填写商品信息</dd>
                                </dl>
                                <dl class="last" data-step="4">
                                    <dt class="cursor">4</dt>
                                    <dd class="s-text">填写商品属性</dd>
                                </dl>
                            </div>
                        </div>

                        <!--第一步 选择商品模式-->
                        <div class="step step-one" ectype="step" data-step="1"
                             style="@if($goodsInfo->goods_id != 0) display: none; @endif">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>设置商品模式</h3>
                            </div>
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
                                <input type="hidden" name="goods_model" id="goods_model"
                                       value="{{$goodsInfo->model_price}}">
                            </div>
                            <div class="goods-btn">
                                <a href="javascript:;" class="btn btn-info mar-all-10 next-step" data-step="2"
                                   data-type="step" data-down="false" ectype="stepSubmit">下一步，选择商品分类</a>
                            </div>
                        </div>

                        <!--第二步 选择商品分类-->
                        <div class="step step-two" ectype="step" data-step="2"
                             style="@if($goodsInfo->cat_id != 0) display: none; @endif">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>选择商品分类</h3>
                            </div>

                            <div class="step-near fl clearfix">
                                <strong class="fl lh36">您最近使用的商品分类：</strong>
                                <select name="recently_used_category" id="" class="form-control fl max-wd-450">
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
                                                    <li data-cat_name="" data-cat_id="0" data-cat_level="1">
                                                        <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                                    </li>
                                                    @foreach($comCates[0] as $comCate)
                                                        <li data-cat_id="{{$comCate->id}}"
                                                            data-cat_name="{{$comCate->cat_name}}" data-cat_level="1"
                                                            class="@if($comCate->select == 1) current @endif">
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
                                                    @if(!empty($comCates[1]))
                                                        @foreach($comCates[1] as $comCate)
                                                            <li data-cat_id="{{$comCate->id}}"
                                                                data-cat_name="{{$comCate->cat_name}}"
                                                                data-cat_level="2"
                                                                class="@if($comCate->select == 1) current @endif">
                                                                <a href="javascript:;"><i
                                                                            class="sc-icon"></i>{{$comCate->cat_name}}
                                                                </a>
                                                            </li>
                                                        @endforeach
                                                    @endif
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
                                                    @if(!empty($comCates[2]))
                                                        @foreach($comCates[2] as $comCate)
                                                            <li data-cat_id="{{$comCate->id}}"
                                                                data-cat_name="{{$comCate->cat_name}}"
                                                                data-cat_level="3"
                                                                class="@if($comCate->select == 1) current @endif">
                                                                <a href="javascript:;"><i
                                                                            class="sc-icon"></i>{{$comCate->cat_name}}
                                                                </a>
                                                            </li>
                                                        @endforeach
                                                    @endif
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="cat_id" id="cat_id" value="{{$goodsInfo->cat_id}}">
                                    <div class="choiceClass" id="choiceClass">您当前选择的商品类别是：
                                        <strong class="red">
                                            @foreach($comCateSelect as $cateSelect)
                                                @if($loop->index != 0) > @endif
                                                {{$cateSelect->cat_name}}
                                            @endforeach
                                        </strong>
                                    </div>
                                </div>
                            </div>
                            <div class="goods-btn">
                                <a href="javascript:;" class="btn btn-default mar-all-10 prev-step" data-step="1"
                                   data-type="step" ectype="stepSubmit">上一步，选择商品模式</a>
                                <a href="javascript:;" class="btn btn-info mar-all-10 next-step" data-step="3"
                                   data-type="step" data-down="false" ectype="stepSubmit">下一步，填写通用信息</a>
                            </div>
                        </div>

                        <!--第三步 填写通用信息-->
                        <div class="step step-three" ectype="step" data-step="3"
                             style="@if($goodsInfo->goods_id == 0) display: none; @endif">
                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>填写通用信息</h3>
                                </div>
                                <div class="step-content clearfix">
                                    <div class="item item-com-cate">
                                        <div class="step-label">商品分类：</div>
                                        <div class="step-value">
                                            <span class="fl cate-name">
                                                @foreach($comCateSelect as $cateSelect)
                                                    @if($loop->index != 0) > @endif {{$cateSelect->cat_name}}
                                                @endforeach
                                            </span>
                                            <a href="javascript:;" class="edit-category" ectype="edit-category"
                                               onclick="step(2)">
                                                <i class="glyphicon glyphicon-edit"></i>
                                            </a>
                                            <a href="javascript:;" class="category-dialog add-cate-extend"
                                               data-goods_id="{{$goodsInfo->goods_id}}">添加扩展分类</a>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品货号：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_sn" class="form-control max-wd-190 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->goods_sn}}"
                                                   placeholder="商品货号" readonly>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">如果您不输入商品货号，系统将自动生成一个唯一的货号。</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品名称：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_name"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->goods_name}}"
                                                   placeholder="商品名称">

                                            <input id="color-picker" type="text" name="goods_name_color"
                                                   class="form-control max-wd-100 hg30 mar-left-20 fl"
                                                   value="{{$goodsInfo->goods_name_style}}"
                                                   style="background: #000000;color: #ffffff;">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品简单描述：</div>
                                        <div class="step-value">
                                        <textarea class="form-control max-wd-350" rows="5"
                                                  name="goods_brief"
                                                  placeholder="商品简单描述">{{$goodsInfo->goods_brief}}</textarea>
                                        </div>
                                    </div>

                                    <input name="suppliers_id" type="hidden" value="0">

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>出售价：</div>
                                        <div class="step-value">
                                            <input type="text" name="shop_price" class="form-control max-wd-190 hg30 fl"
                                                   value="{{$goodsInfo->shop_price}}">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">市场价：</div>
                                        <div class="step-value">
                                            <input type="text" name="market_price"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="{{$goodsInfo->market_price}}">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">成本价：</div>
                                        <div class="step-value">
                                            <input type="text" name="cost_price" class="form-control max-wd-190 hg30 fl"
                                                   value="{{$goodsInfo->cost_price}}">
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品库存：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_number"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="{{$goodsInfo->goods_number}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">库存预警值：</div>
                                        <div class="step-value">
                                            <input type="text" name="warn_number"
                                                   class="form-control max-wd-190 hg30 fl"
                                                   value="{{$goodsInfo->warn_number}}">
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
                                                       autocomplete="off"
                                                       value="@if(empty($brandName->brand_name)){{'请选择'}}@else{{$brandName->brand_name}}@endif"
                                                       readonly="">
                                                <a href="javascript:;"
                                                   class="btn btn-info btn-sm mar-left-20 brand-add">添加</a>
                                                <input type="hidden" name="brand_id" id="brand_id"
                                                       value="@if(!empty($brandName->brand_name)){{$goodsInfo->brand_id}}@else{{0}}@endif">
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
                                                    <img src="{{$goodsInfo->original_img}}"
                                                         class="goods-img-show">
                                                    <input type="hidden" name="goods_gallery_id" value="">
                                                </div>
                                            </div>
                                            <div class="form_prompt">
                                            </div>
                                            <div class="notic">图片尺寸建议800*800</div>
                                            <input type="hidden" name="gallery_pic_id" value="">
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

                                    <div class="item item-com-img">
                                        <div class="step-label"><em class="require-field">*</em>主图视频：</div>
                                        <div class="step-value">
                                            <div id="goods-video" class="update-images fl">
                                                <div class="img">
                                                    <img src="{{url('styles/admin/images/update_video.jpg')}}"
                                                         class="goods-img-show">
                                                    <input type="hidden" name="goods_video_url"
                                                           value="{{$goodsInfo->goods_video}}">
                                                </div>
                                            </div>
                                            <div class="goods-video-div fl"
                                                 style="@if($goodsInfo->goods_video == '') display: none; @endif ">
                                                <video class="goods-video mar-left-10" id="goods_video_js" width="200"
                                                       height="200" src="{{url($goodsInfo->goods_video)}}" controls="">
                                                    <source src="" class="goods-video-js" type="video/mp4">
                                                </video>
                                                <div class="video_default fl"></div>
                                                <a href="javascript:;" class="video_remove">
                                                    <i class="glyphicon glyphicon-trash"></i></a>
                                            </div>
                                            <div class="form_prompt fl">
                                            </div>
                                            <div class="notic fl mar-left-10">
                                                <p>像素：398px * 398px</p>
                                                <p>大小：10MB以内的视频</p>
                                                <p>格式：只支持MP4格式</p>
                                            </div>
                                            <div id="" class="moxie-shim moxie-shim-html5"
                                                 style="position: absolute; top: 0px; left: 0px; width: 100px; height: 100px; overflow: hidden; z-index: 0;">
                                                <input id="" type="file" name="goods_video"
                                                       style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100px; height: 100px;">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label"><font class="red">*</font>商品运费：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="freight" value="1"
                                                           @if($goodsInfo->freight == 1) checked @endif> 按固定运费
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input type="radio" name="freight" value="2"
                                                           @if($goodsInfo->freight == 2) checked @endif> 按运费模板
                                                </label>
                                            </div>
                                            <div id="shipping_fee"
                                                 style="@if($goodsInfo->freight == 2) display:none; @endif">
                                                <input type="text" name="shipping_fee"
                                                       class="form-control max-wd-190 hg30 fl"
                                                       autocomplete="off" value="0.00">
                                            </div>
                                            <div id="tid" style="@if($goodsInfo->freight == 1) display:none; @endif">
                                                <select name="tid" id="" class="form-control max-wd-190 hg30 fl ft-12">
                                                    <option value="0">选择</option>
                                                    @foreach($transports as $transport)
                                                        <option value="{{$transport->tid}}"
                                                                @if($transport->tid == $goodsInfo->tid) selected @endif>{{$transport->title}}</option>

                                                    @endforeach
                                                </select>
                                                <a href="javascript:;"
                                                   class="btn btn-info btn-sm transport-add mar-left-20">添加</a>
                                                <a href="javascript:;"
                                                   class="btn btn-info btn-sm transport-edit mar-left-10">编辑</a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">退货标识：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_0" value="0"
                                                           @if($goodsInfo->goods_cause[0] == 1) checked @endif >
                                                    <label class="ui-label mar-left-5" for="return_type_0">维修</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_1" value="1"
                                                           @if($goodsInfo->goods_cause[1] == 1) checked @endif >
                                                    <label class="ui-label mar-left-5" for="return_type_1">退货</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_2" value="2"
                                                           @if($goodsInfo->goods_cause[2] == 1) checked @endif >
                                                    <label class="ui-label mar-left-5" for="return_type_2">换货</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="return_type[]" class="ui-checkbox"
                                                           id="return_type_3" value="3"
                                                           @if($goodsInfo->goods_cause[3] == 1) checked @endif >
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
                                                           value="1" id="is_reality"
                                                           @if(!empty($goodsInfo->goods_sever) && $goodsInfo->goods_sever->is_reality == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="is_reality">正品保证</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_return" class="ui-checkbox"
                                                           value="1" id="is_return"
                                                           @if(!empty($goodsInfo->goods_sever) && $goodsInfo->goods_sever->is_return == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="is_return">包退服务</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_fast" class="ui-checkbox" value="1"
                                                           id="is_fast"
                                                           @if(!empty($goodsInfo->goods_sever) && $goodsInfo->goods_sever->is_fast == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="is_fast">闪速配送</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--会员字段 预留↓-->
                                    <div>
                                        @if(!empty($userRanks))
                                            @foreach($userRanks as $rank)
                                                <input type="hidden" name="user_price[{{$rank->rank_id}}]"
                                                       autocomplete="off"
                                                       value="@if(!empty($goodsInfo->member_price[$rank->rank_id])) {{$goodsInfo->member_price[$rank->rank_id]}} @else -1 @endif">
                                                <input type="hidden" name="user_rank[]" value="{{$rank->rank_id}}">
                                            @endforeach
                                        @endif
                                    </div>
                                    <!--会员字段 预留↑-->
                                </div>
                            </div>

                            <div class="step-desc clearfix">
                                <div class="step-title"><i class="ui-step"></i>
                                    <h3>详细描述</h3></div>
                                <div class="tabs mar-top-20">
                                    <ul class="fl">
                                        <li class="fl curr">
                                            <a href="javascript:;">
                                                <i class="glyphicon glyphicon-blackboard"></i>电脑端
                                            </a>
                                        </li>
                                        <li class="fl">
                                            <a href="javascript:;">
                                                <i class="glyphicon glyphicon-phone"></i>手机端
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <script id="editor" name="content"
                                        type="text/plain">{!! $goodsInfo->goods_desc !!}</script>
                                <div class="web" style="display: none;">
                                    <div class="explain">
                                        <p>
                                            <strong>一、基本要求</strong>
                                            <span><em>1、</em>手机详情总体大小：图片+文字，
                                                <i class="red">图片不超过20张</i>；</span>
                                        </p>
                                        <p>
                                            <strong>二、图片大小</strong>
                                            <span><em>1、</em>建议使用宽度480 ~ 620像素、高度小于等于960像素的图片；</span>
                                            <span><em>2、</em>格式为：JPG\JEPG\GIF\PNG；</span>
                                        </p>
                                    </div>
                                    <div class="pannel">
                                        <div class="pannel-content ps-container">
                                            <div class="section-warp">
                                                @foreach($goodsInfo->desc_mobile_html as $descImg)
                                                    <div class="section s-img clearfix">
                                                        <div class="img">
                                                            <img src="{{$descImg['desc_url']}}" alt=""
                                                                 data-width="{{$descImg['width']}}"
                                                                 data-height="{{$descImg['height']}}">
                                                        </div>
                                                        <div class="tools">
                                                            <i class="move-up glyphicon glyphicon-arrow-up disabled"></i>
                                                            <i class="move-down glyphicon glyphicon-arrow-down"></i>
                                                            <em class="move-remove"><i
                                                                        class="glyphicon glyphicon-remove"></i>删除</em>
                                                            <div class="cover"></div>
                                                        </div>
                                                    </div>
                                                @endforeach
                                            </div>
                                        </div>
                                        <div class="step-top-btn">
                                            <a href="javascript:;" class="btn btn-danger web-desc">
                                                <i class="glyphicon glyphicon-picture"></i> 添加图片</a>
                                        </div>
                                        <div class="desc_mobile">
                                            @foreach($goodsInfo->desc_mobile_html as $descImg)
                                            <div>
                                                <input type="hidden" name="desc_url[]" value="{{$descImg['desc_url']}}">
                                                <input type="hidden" name="desc_width[]" value="{{$descImg['width']}}">
                                                <input type="hidden" name="desc_height[]" value="{{$descImg['height']}}">
                                            </div>
                                            @endforeach
                                        </div>
                                    </div>
                                </div>
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
                                                    <input class="mar-top-5" type="radio" name="is_promote" value="0"
                                                           @if($goodsInfo->is_promote == 0) checked @endif> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_promote" value="1"
                                                           @if($goodsInfo->is_promote == 1) checked @endif> 是
                                                </label>
                                                <div class="controls fl is-promote"
                                                     style="@if($goodsInfo->is_promote == 0) display: none; @endif">
                                                    <div class="input-prepend input-group" style="width: 500px;">
                                                    <span class="add-on input-group-addon"><i
                                                                class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                        <input type="text" style="width: 300px" name="promote_date"
                                                               id="promote_date" class="form-control input-sm"
                                                               value="@if($goodsInfo->is_promote == 1) {{date('Y-m-d H:i:s',$goodsInfo->promote_start_date)}}～{{date('Y-m-d H:i:s',$goodsInfo->promote_end_date)}} @else {{$now_date}} 00:00:00～{{$now_date}} 23:59:59 @endif">
                                                        <input type="text"
                                                               class="form-control max-wd-100 fl mar-left-20 input-sm"
                                                               name="promote_price"
                                                               value="{{$goodsInfo->promote_price}}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">限购：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_limit_buy" value="0"
                                                           @if($goodsInfo->is_limit_buy == 0) checked @endif> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_limit_buy" value="1"
                                                           @if($goodsInfo->is_limit_buy == 1) checked @endif>
                                                    是
                                                </label>
                                                <div class="controls fl is-limit-buy"
                                                     style="@if($goodsInfo->is_limit_buy == 0) display: none; @endif">
                                                    <div class="input-prepend input-group" style="width: 500px;">
                                                    <span class="add-on input-group-addon"><i
                                                                class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                                        <input type="text" style="width: 300px" name="limit_buy_date"
                                                               id="limit_buy_date" class="form-control input-sm"
                                                               value="@if($goodsInfo->is_limit_buy == 1) {{date('Y-m-d H:i:s',$goodsInfo->limit_buy_start_date)}}～{{date('Y-m-d H:i:s',$goodsInfo->limit_buy_end_date)}} @else {{$now_date}} 00:00:00～{{$now_date}} 23:59:59 @endif">
                                                        <input type="text"
                                                               class="form-control max-wd-100 fl mar-left-20 input-sm"
                                                               name="limit_buy_num"
                                                               value="{{$goodsInfo->limit_buy_num}}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">分期：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_stages" value="0"
                                                           checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_stages" value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">是否为分销商品：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_distribution"
                                                           value="0" checked> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_distribution"
                                                           value="1"> 是
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">阶梯价格：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_volume" value="0"
                                                           @if($goodsInfo->is_volume == 0) checked @endif> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_volume" value="1"
                                                           @if($goodsInfo->is_volume == 1) checked @endif> 是
                                                </label>
                                            </div>
                                            <div class="is-volume-div"
                                                 style="@if($goodsInfo->is_volume == 0) display: none; @endif">
                                                <table class="table table-bordered volume-price" style="width: auto;">
                                                    <tbody>
                                                    <tr class="first-tr">
                                                        <td class="text-center">数量</td>
                                                        @foreach($goodsInfo->goods_volume_prices as $goodsVolumePrice)
                                                            <td>
                                                                <input type="text" name="volume_number[]"
                                                                       value="{{$goodsVolumePrice->volume_number}}"
                                                                       class="form-control max-wd-100"
                                                                       autocomplete="off">
                                                                <input type="hidden" name="volume_id[]"
                                                                       value="{{$goodsVolumePrice->id}}"
                                                                       class="text w50"
                                                                       autocomplete="off">
                                                            </td>
                                                        @endforeach
                                                        <td class="" rowspan="3">
                                                            <a href="javascript:;" class="add-v-p"></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">价格</td>
                                                        @foreach($goodsInfo->goods_volume_prices as $goodsVolumePrice)
                                                            <td>
                                                                <input type="text" name="volume_price[]"
                                                                       value="{{$goodsVolumePrice->volume_price}}"
                                                                       class="form-control max-wd-100"
                                                                       autocomplete="off">
                                                            </td>
                                                        @endforeach
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">操作</td>
                                                        @foreach($goodsInfo->goods_volume_prices as $goodsVolumePrice)
                                                            <td class="text-center">
                                                                <a href="javascript:;"
                                                                   class="btn btn-info btn-sm del-v-p"
                                                                   data-id="{{$loop->index+1}}"
                                                                   data-vp_id="{{$goodsVolumePrice->id}}">删除</a>
                                                            </td>
                                                        @endforeach
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">满减价格：</div>
                                        <div class="step-value">
                                            <div class="clearfix">
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_fullcut" value="0"
                                                           @if($goodsInfo->is_fullcut == 0) checked @endif> 否
                                                </label>
                                                <label class="radio-inline fl padtop">
                                                    <input class="mar-top-5" type="radio" name="is_fullcut" value="1"
                                                           @if($goodsInfo->is_fullcut == 1) checked @endif> 是
                                                </label>
                                            </div>
                                            <div class="is-fullcut-div"
                                                 style="@if($goodsInfo->is_fullcut == 0) display: none; @endif">
                                                <table class="table table-bordered fullcut-price" style="width: auto;">
                                                    <tbody>
                                                    <tr class="first-tr">
                                                        <td class="text-center">满价格</td>
                                                        @foreach($goodsInfo->goods_consumption as $goodsConsumption)
                                                            <td>
                                                                <input type="text" name="cfull[]"
                                                                       value="{{$goodsConsumption->cfull}}"
                                                                       class="form-control max-wd-100"
                                                                       autocomplete="off">
                                                                <input type="hidden" name="fullcut_id[]"
                                                                       value="{{$goodsConsumption->id}}"
                                                                       class="text w50"
                                                                       autocomplete="off">
                                                            </td>
                                                        @endforeach
                                                        <td class="" rowspan="3">
                                                            <a href="javascript:;" class="add-f-p"></a>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">减价格</td>
                                                        @foreach($goodsInfo->goods_consumption as $goodsConsumption)
                                                            <td>
                                                                <input type="text" name="creduce[]"
                                                                       value="{{$goodsConsumption->creduce}}"
                                                                       class="form-control max-wd-100"
                                                                       autocomplete="off">
                                                            </td>
                                                        @endforeach
                                                    </tr>
                                                    <tr>
                                                        <td class="text-center">操作</td>
                                                        @foreach($goodsInfo->goods_consumption as $goodsConsumption)
                                                            <td class="text-center">
                                                                <a href="javascript:;"
                                                                   class="btn btn-info btn-sm del-f-p"
                                                                   data-id="{{$loop->index+1}}"
                                                                   data-fc_id="{{$goodsConsumption->id}}">删除</a>
                                                            </td>
                                                        @endforeach
                                                    </tr>
                                                    </tbody>
                                                </table>
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
                                                   autocomplete="off" value="{{$goodsInfo->give_integral}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">赠送等级积分数：</div>
                                        <div class="step-value">
                                            <input type="text" name="rank_integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->rank_integral}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">积分购买金额：</div>
                                        <div class="step-value">
                                            <input type="text" name="integral"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->integral}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">(此处需填写金额)购买该商品时最多可以使用积分的金额</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品重量：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_weight"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->goods_weight}}">
                                            <select name="weight_unit"
                                                    class="form-control max-wd-100 hg30 fl input-sm mar-left-20">
                                                <option value="0.001">克</option>
                                                <option value="1" selected>千克</option>
                                            </select>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10"></div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品单位：</div>
                                        <div class="step-value">
                                            <input type="text" name="goods_unit"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->goods_unit}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">比如：个，件，份，套。默认为个</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">加入推荐：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="checkbox-items">
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_best" class="ui-checkbox" value="1"
                                                           id="is_best" @if($goodsInfo->is_best == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="is_best">精品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_new" class="ui-checkbox" value="1"
                                                           id="is_new" @if($goodsInfo->is_new == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="is_new">新品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="is_hot" class="ui-checkbox" value="1"
                                                           id="is_hot" @if($goodsInfo->is_hot == 1) checked @endif>
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
                                                           value="1" id="store_best"
                                                           @if($goodsInfo->store_best == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="store_best">精品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="store_new" class="ui-checkbox"
                                                           value="1" id="store_new"
                                                           @if($goodsInfo->store_new == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="store_new">新品</label>
                                                </div>
                                                <div class="checkbox-item fl mar-right-20">
                                                    <input type="checkbox" name="store_hot" class="ui-checkbox"
                                                           value="1" id="store_hot"
                                                           @if($goodsInfo->store_hot == 1) checked @endif>
                                                    <label class="ui-label mar-left-5" for="store_hot">热销</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">上架：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                                <div class="switch @if($goodsInfo->is_on_sale == 1) active @endif"
                                                     data-type="is_on_sale" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="{{$goodsInfo->is_on_sale}}"
                                                           name="is_on_sale">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">普通商品销售：</div>
                                        <div class="step-value step-goods-service">
                                            <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                                <div class="switch @if($goodsInfo->is_alone_sale == 1) active @endif"
                                                     data-type="is_alone_sale" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="{{$goodsInfo->is_alone_sale}}"
                                                           name="is_alone_sale">
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
                                                <div class="switch @if($goodsInfo->is_shipping == 1) active @endif"
                                                     data-type="is_shipping" title="是">
                                                    <div class="circle"></div>
                                                    <input type="hidden" value="{{$goodsInfo->is_shipping}}"
                                                           name="is_shipping">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商品关键词：</div>
                                        <div class="step-value">
                                            <input type="text" name="keywords"
                                                   class="form-control max-wd-350 hg30 fl "
                                                   autocomplete="off" value="{{$goodsInfo->keywords}}">
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">商品关键词：请用空格分隔；1.作为站内关键词查询；2.作为搜索引擎收录使用。
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">评论标签：</div>
                                        <div class="step-value">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="goods_product_tag">{{$goodsInfo->goods_product_tag}}</textarea>
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
                                                      name="goods_tag">{{$goodsInfo->goods_tag}}</textarea>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">请用','号分割（注：逗号要使用英文逗号）</div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <div class="step-label">商家备注：</div>
                                        <div class="step-value">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="seller_note">{{$goodsInfo->seller_note}}</textarea>
                                            <div class="form-prompt"></div>
                                            <div class="notic fl mar-left-10">仅供商家自己看的信息</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="step-pn clearfix">
                                <div class="goods-btn">
                                    <a href="javascript:;" class="btn btn-default mar-all-10 prev-step" data-step="2"
                                       data-type="step" ectype="stepSubmit">上一步，选择商品分类</a>
                                    <a href="javascript:;" class="btn btn-info mar-all-10 next-step" data-step="4"
                                       data-type="step" data-down="false" ectype="stepSubmit">下一步，填写商品属性</a>
                                    <input class="btn btn-info mar-all-10" type="submit" value="完成,发布商品">
                                </div>
                            </div>
                        </div>

                        <!--第四步 填写商品属性 vue---id="appFour" v-cloak-->
                        <div class="step step-four" id="appFour" ectype="step" data-step="4" style="display: none;"
                             v-cloak>
                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>商品属性</h3>
                                </div>
                                <div class="step-content">
                                    <div class="step-item">
                                        <div class="step-item-left"><h5>属性分类：</h5></div>
                                        <div class="step-item-right">
                                            <input name="attr_parent_id" type="hidden" value="0">
                                            <div class="item-right-li">
                                                <div class="value-select goods_type_cat">
                                                    <select class="form-control max-wd-110 fl mar-right-20 select"
                                                            v-for="(cates, k) in goodsTypeCates"
                                                            @change="goodsTypeCate($event)"
                                                            v-model="goodsTypeCatesSelect[k]" :data-level="k">
                                                        <option value="0">请选择</option>
                                                        <option :value="cate.cate_id" v-for="cate in cates">
                                                            ${cate.cat_name}
                                                        </option>
                                                    </select>
                                                </div>
                                                <a class="btn btn-info add_goods_type_cat_win"
                                                   data-goodsid="">添加属性分类</a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="step-item">
                                        <div class="step-item-left"><h5>商品类型：</h5></div>
                                        <div class="step-item-right">
                                            <div class="item-right-li">
                                                <div class="value-select goods_type">
                                                    <select id="cate_id"
                                                            class="form-control max-wd-350 fl select-value"
                                                            @change="goodsTypeChange($event)" v-model="goods_type">
                                                        <option value="0">请选择</option>
                                                        <option v-for="goodsType in goodsTypes"
                                                                :value="goodsType.cat_id">${goodsType.cat_name}
                                                        </option>
                                                    </select>
                                                </div>
                                                <input name="goods_type" type="hidden"
                                                       value="{{$goodsInfo->goods_type}}">
                                                <a class="btn btn-info mar-left-20 add_goods_type" data-goodsid="">添加商品类型</a>
                                                <a class="btn btn-info mar-left-20 add_attribute"
                                                   data-goodsid="">添加属性</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="step-item step-item-bg">
                                        <div class="step-item-row step-item-attr-once clearfix"
                                             v-if="goodsAttrO.length > 0">
                                            <div class="step-item-left">
                                                <h5>商品属性：</h5>
                                            </div>
                                            <div class="step-item-right attr-once">
                                                <div class="item-right-list goods-attr-type fl"
                                                     v-for="(attr_o, aok) in goodsAttrO">
                                                    <input type="hidden" name="attr_id_listO[]"
                                                           :value="attr_o.attr_id">
                                                    <div class="label fl"
                                                         :title="attr_o.attr_name">${attr_o.attr_name}：
                                                    </div>
                                                    <div class="value-select">
                                                        <select name="attr_value_list[]"
                                                                class="form-control max-wd-100 fl"
                                                                v-model="attr_o.attr_value">
                                                            <option :value="attr_value"
                                                                    v-for="attr_value in attr_o.attr_values">
                                                                ${attr_value}
                                                            </option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="step-item-row step-item-attr-checkbox clearfix"
                                             v-if="goodsAttrM.length > 0">
                                            <div class="step-item-left">
                                                <h5>商品规格：</h5>
                                            </div>
                                            <div class="step-item-right attr-multi">
                                                <div class="item-right-list fl" v-for="(attr_m, key) in goodsAttrM">
                                                    <div class="label fl">${attr_m.attr_name}：
                                                        <input type="hidden" name="attr_id_listM[]"
                                                               :value="attr_m.attr_id">
                                                    </div>
                                                    <div class="clearfix">
                                                        <label class="checkbox-inline"
                                                               v-for="(v, k) in attr_m.attr_values">

                                                            <input type="checkbox"
                                                                   :name="'attr_value_list1['+attr_m.attr_id+'][]'"
                                                                   class="attr_value_list1"
                                                                   :data-key="key"
                                                                   :data-k="k"
                                                                   :value="v"
                                                                   :data-attr_id="attr_m.attr_id"
                                                                   :checked="attr_m.selected[k]==1"
                                                                   @click="selectValue($event)">${v}
                                                        </label>
                                                        <div class="checkbox-inline" v-if="attr_m.attr_input_type == 0">
                                                            <a href="javascript:;"
                                                               class="btn btn-info btn-sm attr-custom"
                                                               :data-attr_id="attr_m.attr_id">自定义</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="step-item-table" id="attribute-table" v-if="products.length > 0">
                                        <table class="table table-hover table_head">
                                            <thead>
                                            <tr>
                                                <th width="20%" class="text-center">属性</th>
                                                <th width="9%"><em>*</em>市场价 <i
                                                            class="glyphicon glyphicon-edit cursor pro_market"></i></th>
                                                <th width="9%"><em>*</em>销售价 <i
                                                            class="glyphicon glyphicon-edit cursor pro_shop"></i></th>
                                                <th width="9%"><em>*</em>促销价 <i
                                                            class="glyphicon glyphicon-edit cursor pro_promote"></i>
                                                </th>
                                                <th width="9%"><em>*</em>库存 <i
                                                            class="glyphicon glyphicon-edit cursor pro_number"></i></th>
                                                <th width="9%"><em>*</em>预警值 <i
                                                            class="glyphicon glyphicon-edit cursor pro_warning"></i>
                                                </th>
                                                <th width="20%">商品货号</th>
                                                <th width="10%">商品条形码</th>
                                                <th class="text-center" width="10%">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr v-for="product in products">
                                                <td class="text-center">
                                                    <template v-for="(attr_name, k) in product.goods_attr_names">
                                                        <template v-if="k != 0">,</template>
                                                        ${attr_name}
                                                        <input type="hidden" :name="product.attr_ids[k]"
                                                               :value="attr_name">
                                                        <input type="hidden"
                                                               :name="product.goods_attr_id[k]"
                                                               :value="product.goods_attr[k]">
                                                    </template>
                                                </td>
                                                <td>
                                                    <input type="text" name="product_market_price[]"
                                                           class="form-control max-wd-110 hg27" autocomplete="off"
                                                           :value="product.product_market_price">
                                                </td>
                                                <td>
                                                    <input type="text" name="product_price[]"
                                                           class="form-control max-wd-110 hg27" autocomplete="off"
                                                           :value="product.product_price">
                                                </td>
                                                <td>
                                                    <input type="text" name="product_promote_price[]"
                                                           class="form-control max-wd-110 hg27" autocomplete="off"
                                                           :value="product.product_promote_price">
                                                </td>
                                                <td>
                                                    <input type="text" name="product_number[]"
                                                           class="form-control max-wd-110 hg27" autocomplete="off"
                                                           :value="product.product_number">
                                                </td>
                                                <td>
                                                    <input type="text" name="product_warn_number[]"
                                                           class="form-control max-wd-110 hg27" autocomplete="off"
                                                           :value="product.product_warn_number">
                                                </td>
                                                <td>
                                                    <input type="text" name="product_sn[]" class="form-control hg27"
                                                           autocomplete="off" :value="product.product_sn" readonly>
                                                </td>
                                                <td>
                                                    <input type="text" name="product_bar_code[]"
                                                           class="form-control hg27" autocomplete="off"
                                                           :value="product.bar_code">
                                                </td>
                                                <td class="handle">
                                                    <a href="javascript:;" class="btn btn-danger btn-sm product-del" @click="productDel($event)"
                                                       :data-product_id="product.changelog_product_id?0:product.product_id"
                                                       v-if="!product.changelog_product_id">删除</a>
                                                    <span v-if="product.changelog_product_id">N/A</span>
                                                    <input type="hidden" name="product_id[]"
                                                           :value="product.changelog_product_id?0:product.product_id">
                                                    <input type="hidden" name="changelog_product_id[]"
                                                           :value="product.changelog_product_id">
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="step-info clearfix">
                                <div class="step-title">
                                    <i class="ui-step"></i>
                                    <h3>属性图片</h3>
                                </div>
                                <div class="attr-gallerys ps-container ps-active-y"
                                     v-if="products.length > 0">
                                    <div class="step-content attr-gallery clearfix"
                                         v-for="(attrImg, key) in goodsAttrImg">
                                        <div class="attr_tit">${attrImg.attr_name}：</div>
                                        <div class="attr-item fl" v-for="value in attrImg.values">
                                            <div class="txt">${value.attr_value}</div>
                                            <div class="info fl">
                                                <label class="fl hg27">排序：</label>
                                                <input type="text" class="form-control max-wd-100 hg27"
                                                       @blur="attrSortChange"
                                                       :data-goodsattrid="value.goods_attr_id"
                                                       :name="value.attr_sort_n" size="10"
                                                       :value="value.attr_sort"></div>
                                            <a href="javascript:;"
                                               class="btn btn-danger btn-sm up_img mar-left-10 fl"
                                               :data-goodsattrid="value.goods_attr_id"
                                               :data-attrid="value.attr_id">
                                                <input type="file" :id="value.dom_id_n"
                                                       :name="value.attr_id_n"
                                                       :data-goodsattrid="value.goods_attr_id"
                                                       :data-oldattrimg_o="value.attr_img_flie"
                                                       :data-oldattrimg_t="value.attr_gallery_flie"
                                                       @change="upLoadAttrImg($event)"
                                                       style="opacity: 0;max-width: 0;height: 0;margin: 0">
                                                <label :for="value.dom_id_n">上传图片</label>
                                            </a>
                                            <span class="img-show attr-img-show fl">
                                                <a :href="value.attr_img_flie_o" target="_blank"
                                                   class="nyroModal">
                                                    <i class="glyphicon glyphicon-picture top5"
                                                       :data-tooltipimg="value.attr_img_flie_o"
                                                       ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                                </a>
                                            </span>
                                            <input name="attr_id" type="hidden"
                                                   :value="value.attr_id">
                                            <input name="attr_value" type="hidden"
                                                   :value="value.attr_value">
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
                                            @foreach($goodsInfo->goods_gallerys as $goodsGallery)
                                                <li id="gallery">
                                                    <div class="img">
                                                        <img src="{{$goodsGallery->img_original}}" width="160"
                                                             height="160" id="external_img_url">
                                                    </div>
                                                    <div class="info">
                                                        <span class="zt red">主图</span>
                                                        <div class="sort">
                                                            <span>排序：</span>
                                                            <input type="text" value="{{$goodsGallery->img_desc}}"
                                                                   name="old_img_desc[]"
                                                                   class="stext form-control max-wd-50 hg25"
                                                                   autocomplete="off" maxlength="3">
                                                            <input type="hidden" value="{{$goodsGallery->img_id}}"
                                                                   name="img_id[]">
                                                        </div>
                                                        <a href="javascript:;" data-imgid="{{$goodsGallery->img_id}}"
                                                           class="delete_img"><i
                                                                    class="glyphicon glyphicon-trash"></i></a>
                                                    </div>
                                                    <div class="info">
                                                        <input name="external_url" type="text"
                                                               class="form-control max-wd-190 external_url"
                                                               value="{{$goodsGallery->external_url}}" title=""
                                                               data-imgid="" placeholder="图片外部链接地址">
                                                    </div>
                                                </li>
                                            @endforeach
                                        </ul>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="step-top-btn gallery-album clearfix" ectype="gallery_album_list"
                                         data-inid="addAlbumimg" data-act="gallery_album_list"
                                         style="position: relative;">
                                        <a href="javascript:;" class="btn btn-danger mar-all-10 line-hg-30 ft-16"
                                           style="position: relative; z-index: 1;">
                                            <input type="file" multiple id="add-slide-img" class="add-slide-img"
                                                   style="opacity: 0;max-width: 0;height: 0;margin: 0">
                                            <label for="add-slide-img" class="ft-16"><i
                                                        class="glyphicon glyphicon-plus"></i>添加图片</label>
                                        </a>
                                        <a href="javascript:;"
                                           class="btn btn-danger mar-all-10 line-hg-30 ft-16 img-lib-slide"
                                           data-value="图片库选择">
                                            <i class="glyphicon glyphicon-plus"></i>图片库选择
                                        </a>
                                        <div id="addAlbumimg"></div>
                                    </div>
                                </div>

                                <div class="step-pn clearfix">
                                    <div class="goods-btn">
                                        <a href="javascript:;" class="btn btn-default mar-all-10 prev" data-step="3"
                                           data-type="step" ectype="stepSubmit">上一步，填写通用信息</a>
                                        <input class="btn btn-info mar-all-10" type="submit" value="完成,发布商品">
                                    </div>
                                </div>
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
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.config.js')}}"></script>
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.all.min.js')}}"></script>
    <script type="text/javascript"
            src="{{url('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.js')}}"></script>

    <script>

        $(function () {
            var goods_id = $('input[name=goods_id]').val();
            var attrList = [];
            var attrMulti = [];

            $('body').on('click', function () {
                $('.brand-select-container').hide();
            });

            var ue = UE.getEditor('editor', {
                initialFrameHeight: 500,
                scaleEnabled: true
            });
            ue.ready(function () {
                // ue.setHeight(500);
            });

            //选择颜色
            $('#color-picker').colorpicker();
            $('#color-picker').on('change', function () {
                $(this).css('background', $(this).val());
                $(this).css('color', '#fff');
            });

            $('input[name=shop_price]').on('blur', function () {
                $('input[name=market_price]').val((parseFloat($(this).val()) * 1.2).toFixed(2));
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
                    area: ['1100px', '500px'],
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
                var files = $(this)[0].files;
                layer.load();
                var form = new FormData();
                for (var i = 0; i < files.length; i++) {
                    form.append('pic[' + i + ']', files[i]);
                }
                form.append('goods_id', goods_id);
                form.append('_token', '{{csrf_token()}}');
                $.ajax({
                    url: "{{url('admin/goods/upgoodsgallery')}}",
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        var html = '';
                        $.each(data, function (k, v) {
                            $(".goods-img-show").attr("src", v.img_original);
                            $("input[name=goods_gallery_id]").val(v.img_id);
                            html += '<li id="gallery">' +
                                '<div class="img">' +
                                '<img src="' + v.img_original + '" width="160" height="160" id="external_img_url">' +
                                '</div>' +
                                '<div class="info">' +
                                '<span class="zt red">主图</span>' +
                                '<div class="sort">' +
                                '<span>排序：</span>' +
                                '<input type="text" value="100" name="old_img_desc[]"' +
                                'class="stext form-control max-wd-50 hg25" autocomplete="off" maxlength="3">' +
                                '<input type="hidden" value="' + v.img_id + '" name="img_id[]">' +
                                '</div>' +
                                '<a href="javascript:;" data-imgid="' + v.img_id + '" class="delete_img"><i class="glyphicon glyphicon-trash"></i></a>' +
                                '</div>' +
                                '<div class="info">' +
                                '<input name="external_url" type="text" class="form-control max-wd-190 external_url"' +
                                ' value="" title="" data-imgid="' + v.img_id + '" placeholder="图片外部链接地址"></div>' +
                                '</li>';
                        });
                        $('#ul-pics').append(html);
                        layer.closeAll('loading');
                    }
                });
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 10000);
            });

            //上传主图视频
            $("input[name=goods_video]").on('change', function () {
                var file = $(this)[0].files[0];
                var url = getImageURL(file);
                $('.goods-video').attr('src', url);
                $('.goods-video-js').attr('src', url);
                $('.goods-video-div').show();
            });

            //图片库选择图片
            $('.img-lib-main').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['800px', '400px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '图片库选择图片',
                    content: ["{{url('admin/goods/imagelibrary/main/')}}/" + goods_id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //运费模板功能
            $('input[name=freight]').on('click', function () {
                if ($(this).val() == 1) {
                    $('#shipping_fee').show();
                    $('#tid').hide()
                } else {
                    $('#shipping_fee').hide();
                    $('#tid').show()
                }
            });
            $('.transport-add').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['1100px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '运费模板',
                    content: ["{{url('admin/transport/create')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            $('.transport-edit').on('click', function () {
                var tid = $('select[name=tid]').val();
                if (tid > 0) {
                    layer.open({
                        type: 2,
                        area: ['1100px', '500px'],
                        fixed: true, //不固定
                        maxmin: true,
                        title: '运费模板',
                        content: ["{{url('admin/transport/')}}/" + tid + "/edit"],
                        success: function (layero, index) {
                            layer.iframeAuto(index)
                        }
                    });
                } else {
                    layer.msg('请选择运费模板');
                }

            });

            //商品详细信息功能
            $('.tabs').on('click', 'li', function () {
                if ($(this).find('.glyphicon-blackboard').length > 0) {
                    $('#editor').show();
                    $('.web').hide();
                } else {
                    $('#editor').hide();
                    $('.web').show();
                }
            });
            //移动端商品详情图片添加
            $('.web-desc').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['800px', '400px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '图片库选择图片',
                    content: ["{{url('admin/goods/imagelibrary/webdesc/')}}/" + goods_id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            //移动端商品详情图片上移
            $('.section-warp').on('click', '.move-up', function () {
                if (!$(this).hasClass('disabled')) {
                    $(this).parent().parent().prev().before($(this).parent().parent());
                    setDescMobile()
                }
            });
            //移动端商品详情图片下移
            $('.section-warp').on('click', '.move-down', function () {
                if (!$(this).hasClass('disabled')) {
                    $(this).parent().parent().next().after($(this).parent().parent());
                    setDescMobile()
                }
            });
            //移动端商品详情图片删除
            $('.section-warp').on('click', '.move-remove', function () {
                $(this).parent().parent().remove();
                setDescMobile()
            });

            //单选促销
            var optionSet = {
                timePicker: true,
                timePickerIncrement: 1,
                format: 'YYYY-MM-DD hh:mm:ss',
                timePicker24Hour: true,
                timePickerSeconds: true,
                locale: {
                    "separator": " -222 ",
                    "applyLabel": "确定",
                    "cancelLabel": "取消",
                    "fromLabel": "起始",
                    "toLabel": "结束",
                    "customRangeLabel": "自定义",
                    "weekLabel": "W",
                    "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
                    "monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    "firstDay": 1
                }
            };
            $('#promote_date').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm:ss');
                var t = s + '～' + e;
                $('#promote_date').val(t);
            });
            $('input[name=is_promote]').on('click', function () {
                if ($(this).val() == 1) {
                    $('.is-promote').show();
                } else {
                    $('.is-promote').hide();
                }
            });
            //单选限购
            $('#limit_buy_date').daterangepicker(optionSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm:ss');
                var t = s + '～' + e;
                $('#limit_buy_date').val(t);
            });
            $('input[name=is_limit_buy]').on('click', function () {
                if ($(this).val() == 1) {
                    $('.is-limit-buy').show();
                } else {
                    $('.is-limit-buy').hide();
                }
            });

            //单选阶梯价格
            $('input[name=is_volume]').on('click', function () {
                if ($(this).val() == 1) {
                    $('.is-volume-div').show();
                } else {
                    $('.is-volume-div').hide();
                }
            });
            $('.is-volume-div').on('click', '.add-v-p', function () {
                var tbody = $(this).parent().parent().parent();
                var html = '<td><input type="text" name="volume_number[]" value=""' +
                    'class="form-control max-wd-100" autocomplete="off">' +
                    '<input type="hidden" name="volume_id[]" value="0" autocomplete="off"></td>';
                var html1 = '<td><input type="text" name="volume_price[]" value="" class="form-control max-wd-100" autocomplete="off"></td>';
                var html2 = '<td class="text-center"><a href="javascript:;" class="btn btn-info btn-sm del-v-p" data-id="' + ($('.del-v-p').length + 1) + '">删除</a></td>';
                tbody.find('tr').each(function (k, v) {
                    if (k == 0) {
                        $(v).find('td').last().before(html);
                    } else if (k == 1) {
                        $(v).append(html1);
                    } else if (k == 2) {
                        $(v).append(html2);
                    }
                })
            });
            $('.is-volume-div').on('click', '.del-v-p', function () {
                var id = $(this).data('id');
                var vp_id = $(this).data('vp_id');
                var tbody = $(this).parent().parent().parent();
                tbody.find('tr').each(function (k, v) {
                    $(v).find('td').each(function (key, val) {
                        if (id == key) {
                            $(val).remove();
                        }
                    });
                });
                $('.del-v-p').each(function (k, v) {
                    $(v).data('id', (k + 1));
                });
                if (vp_id != undefined) {
                    $.post("{{url('admin/goods/delvolumeprice')}}/" + vp_id, {'_token': "{{csrf_token()}}"}, function (data) {

                    });
                }
            });

            //单选满减价格
            $('input[name=is_fullcut]').on('click', function () {
                if ($(this).val() == 1) {
                    $('.is-fullcut-div').show();
                } else {
                    $('.is-fullcut-div').hide();
                }
            });
            $('.is-fullcut-div').on('click', '.add-f-p', function () {
                var tbody = $(this).parent().parent().parent();
                var html = '<td><input type="text" name="cfull[]" value=""' +
                    'class="form-control max-wd-100" autocomplete="off">' +
                    '<input type="hidden" name="fullcut_id[]" value="0" autocomplete="off"></td>';
                var html1 = '<td><input type="text" name="creduce[]" value="" class="form-control max-wd-100" autocomplete="off"></td>';
                var html2 = '<td class="text-center"><a href="javascript:;" class="btn btn-info btn-sm del-f-p" data-id="' + ($('.del-f-p').length + 1) + '">删除</a></td>';
                tbody.find('tr').each(function (k, v) {
                    if (k == 0) {
                        $(v).find('td').last().before(html);
                    } else if (k == 1) {
                        $(v).append(html1);
                    } else if (k == 2) {
                        $(v).append(html2);
                    }
                })
            });
            $('.is-fullcut-div').on('click', '.del-f-p', function () {
                var id = $(this).data('id');
                var fc_id = $(this).data('fc_id');
                var tbody = $(this).parent().parent().parent();
                tbody.find('tr').each(function (k, v) {
                    $(v).find('td').each(function (key, val) {
                        if (id == key) {
                            $(val).remove();
                        }
                    });
                });
                $('.del-f-p').each(function (k, v) {
                    $(v).data('id', (k + 1));
                });
                if (fc_id != undefined) {
                    $.post("{{url('admin/goods/delfullcut/')}}/" + fc_id, {'_token': "{{csrf_token()}}"}, function (data) {

                    });
                }
            });

            //开关
            $('.switch').on('click', function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }
                $(this).find('input').val(val);
            });

            //添加属性分类弹窗
            $('.add_goods_type_cat_win').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['830px', '300px'],
                    fixed: true,
                    maxmin: true,
                    title: '添加属性分类',
                    content: ["{{url('admin/typecate/typecate/modal')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            //添加商品类型
            $('.add_goods_type').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['830px', '370px'],
                    fixed: true,
                    maxmin: true,
                    title: '添加商品类型',
                    content: ["{{url('admin/goodstype/goodstype/modal')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            //添加属性
            $('.add_attribute').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['900px', '570px'],
                    fixed: true,
                    maxmin: true,
                    title: '添加属性',
                    content: ["{{url('admin/attribute/attribut/modal')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //批量填充
            $('.step-four').on('click', '.glyphicon-edit', function () {
                if ($(this).hasClass('pro_market')) {
                    $('input[name="product_market_price[]"]').val($('input[name="product_market_price[]"]').first().val());
                } else if ($(this).hasClass('pro_shop')) {
                    $('input[name="product_price[]"]').val($('input[name="product_price[]"]').first().val());
                } else if ($(this).hasClass('pro_promote')) {
                    $('input[name="product_promote_price[]"]').val($('input[name="product_promote_price[]"]').first().val());
                } else if ($(this).hasClass('pro_number')) {
                    $('input[name="product_number[]"]').val($('input[name="product_number[]"]').first().val());
                } else if ($(this).hasClass('pro_warning')) {
                    $('input[name="product_warn_number[]"]').val($('input[name="product_warn_number[]"]').first().val());
                }
            });

            //自定义商品规格属性
            $('.step-item-attr-checkbox .step-item-right').on('click', '.attr-custom', function () {
                var attr_id = $(this).data('attr_id');
                layer.open({
                    type: 2,
                    area: ['700px', '250px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '图片库选择图片',
                    content: ["{{url('admin/goods/customattrwin/')}}/" + attr_id + '/' + goods_id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //轮播图
            $('.img-lib-slide').on('click', function () {
                layer.open({
                    type: 2,
                    area: ['800px', '400px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '图片库选择图片',
                    content: ["{{url('admin/goods/imagelibrary/slide/')}}/" + goods_id],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            //上传轮播图片
            $('#add-slide-img').on('change', function () {
                var files = $(this)[0].files;
                layer.load();
                var form = new FormData();
                for (var i = 0; i < files.length; i++) {
                    form.append('pic[' + i + ']', files[i]);
                }
                form.append('goods_id', goods_id);
                form.append('_token', '{{csrf_token()}}');
                $.ajax({
                    url: "{{url('admin/goods/upgoodsgallery')}}",
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        var html = '';
                        $.each(data, function (k, v) {
                            html += '<li id="gallery">' +
                                '<div class="img">' +
                                '<img src="' + v.img_original + '" width="160" height="160" id="external_img_url">' +
                                '</div>' +
                                '<div class="info">' +
                                '<span class="zt red">主图</span>' +
                                '<div class="sort">' +
                                '<span>排序：</span>' +
                                '<input type="text" value="100" name="old_img_desc[]"' +
                                'class="stext form-control max-wd-50 hg25" autocomplete="off" maxlength="3">' +
                                '<input type="hidden" value="' + v.img_id + '" name="img_id[]">' +
                                '</div>' +
                                '<a href="javascript:;" data-imgid="' + v.img_id + '" class="delete_img"><i class="glyphicon glyphicon-trash"></i></a>' +
                                '</div>' +
                                '<div class="info">' +
                                '<input name="external_url" type="text" class="form-control max-wd-190 external_url"' +
                                ' value="" title="" data-imgid="' + v.img_id + '" placeholder="图片外部链接地址"></div>' +
                                '</li>';
                        });
                        $('#ul-pics').append(html);
                        layer.closeAll('loading');
                    }
                });
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 10000);
            });
            //删除轮播图
            $('#ul-pics').on('click', '.delete_img', function () {
                var that = this;
                var imgid = $(this).data('imgid');
                $.post("{{url('admin/goods/delgoodsgallery')}}", {
                    '_token': '{{csrf_token()}}',
                    imgid: imgid
                }, function (data) {
                    if (data.code == 1) {
                        $(that).parent().parent().remove();
                    }
                });
            });
            //替换轮播主图
            $('#ul-pics').on('click', 'li img', function () {
                var img = $(this).parent().parent();
                var curSort = img.find('input[name="old_img_desc[]"]').val();
                var curImgId = img.find('input[name="img_id[]"]').val();
                var mainSort = $('#ul-pics li').first().find('input[name="old_img_desc[]"]').val();
                var mainImgId = $('#ul-pics li').first().find('input[name="img_id[]"]').val();
                $('#ul-pics li').first().find('input[name="old_img_desc[]"]').val(curSort);
                img.find('input[name="old_img_desc[]"]').val(mainSort);
                $('#ul-pics').prepend(img);
                $.post("{{url('admin/goods/changegoodsgallery')}}", {
                    '_token': "{{csrf_token()}}",
                    cur_sort: curSort,
                    cur_img_id: curImgId,
                    main_sort: mainSort,
                    main_img_id: mainImgId,
                }, function (data) {

                })
            });

            ///////////////////////////////////////////////////////////////

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
            $('.prev-step').on('click', function () {
                step($(this).data('step'));
            });

            //填写商品信息下一步
            $('.next-step').on('click', function () {
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
            });
        });

        function getNextCate(parent_id, cat_level) {
            var id = parent_id;
            var level = cat_level + 1;
            if (id > 0 && cat_level < 3) {
                var html = '';
                $.post("{{url('admin/comcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>' +
                            '   </li>';
                        $.each(data.data, function (k, v) {
                            html += '<li data-cat_name="' + v.cat_name + '" data-cat_id="' + v.id + '" data-cat_level="' + level + '" class="">' +
                                '       <a href="javascript:;"><i class="sc-icon"></i>' + v.cat_name + '</a>' +
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
                                $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">' +
                                    '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>' +
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
                        $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>' +
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

        function setDescMobile() {
            $('.section-warp .section').each(function (k, v) {
                $(this).find('.move-up').removeClass('disabled');
                $(this).find('.move-down').removeClass('disabled');
                if (k == 0) {
                    $(this).find('.move-up').addClass('disabled');
                } else if (k == $('.section-warp .section').length - 1) {
                    $(this).find('.move-down').addClass('disabled');
                }
            });

            var imgs = '';
            $('.section-warp .img').each(function () {
                imgs += '<div><input type="hidden" name="desc_url[]" value="' + $(this).find('img').attr('src') + '"><input type="hidden" name="desc_width[]" value="' + $(this).find('img').data('width') + '"><input type="hidden" name="desc_height[]" value="' + $(this).find('img').data('height') + '"></div>';
            });
            $('.desc_mobile').html(imgs);
        }
    </script>
    <script type="text/javascript" src="{{url('styles/admin/js/goods.js?v='.$v)}}"></script>
@endsection
@endsection