@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 发放红包</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>发放红包相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <div class="step">
                        <div class="step-near clearfix" style="margin-left:1%;">
                            <input name="keywords" value="" class="form-control fl max-wd-350 input-sm"
                                   placeholder="关键字">
                            <a class="btn btn-primary btn-search mar-left-10 fl input-sm"
                               style="padding: 4px 10px;">搜索</a>
                        </div>

                        <div class="sort-info clearfix">
                            <div id="cate-add" class="clearfix">
                                <div class="clearfix">
                                    <h4 class="fl goods-s">可选商品</h4><h4 class="fl goods-s">发放此类型红包的商品</h4>
                                </div>
                                <div class="sort-list sort-list-one" style="width: 45%;margin-left:1%;margin-right: 8%">
                                    <div class="sort-list-warp">
                                        <div class="category-list ps-container ps-active-y">
                                            <ul>
                                                <li class=""><i class="sc-icon sc_icon_ok"></i>
                                                    <a href="javascript:;" data-value="787" class="s-goods-name">YOHO有货潮牌LAL/数字贴布连帽套头卫衣男女通用 吴亦凡亲着同款 春夏焕新季，3.21日00:00开始抢购</a>
                                                    <input type="hidden" name="user_search[]" value="787">
                                                </li>
                                                <li class="current"><i class="sc-icon sc_icon_ok"></i>
                                                    <a href="javascript:;" data-value="788" class="s-goods-name">HLA/海澜之家休闲西服2017春季新品平驳领时尚西装男单西外套 平驳领型 胸针装饰 青春休闲 舒适手感</a>
                                                    <input type="hidden" name="user_search[]" value="788">
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="sort-point"></div>
                                    </div>
                                </div>
                                <div class="sort-list sort-list-one" style="width: 45%;margin-right: 0%">
                                    <div class="sort-list-warp">
                                        <div class="category-list ps-container ps-active-y">
                                            <ul>
                                                <li class="current"><i class="sc-icon sc_icon_no"></i><a href="javascript:;" data-value="788" class="s-goods-name">HLA/海澜之家休闲西服2017春季新品平驳领时尚西装男单西外套 平驳领型 胸针装饰 青春休闲 舒适手感</a></li>
                                                <li class=""><i class="sc-icon sc_icon_no"></i><a href="javascript:;" data-value="788" class="s-goods-name">HLA/海澜之家休闲西服2017春季新品平驳领时尚西装男单西外套 平驳领型 胸针装饰 青春休闲 舒适手感</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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