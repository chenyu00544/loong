@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 评论详情</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商城相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="comments pad-all-30 clearfix">
                    <div class="reply-item-wrap clearfix mar-bt-20">
                        <div class="reply-item clearfix mar-bt-5">
                            <div class="reply-label wd-120 fl text-right"><a href="javascript:;" class="blue">wx31cc81</a>
                                评论 <a href="javascript:;" class="blue">ces</a> 内容：
                            </div>
                            <div class="reply-value fl mar-left-20">
                                <div class="msg-info wd-550">
                                    <div class="msg-desc pad-all-10">宝贝收到了，真快！质量也好</div>
                                    <ul class="msg-img pad-lr-15">
                                        <li><img src="" alt=""></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="reply-item clearfix mar-top-20">
                            <div class="reply-label wd-120 fl text-right">评论日期：</div>
                            <div class="reply-value fl mar-left-20">
                                <span class="">2018-08-14 15:26:57</span>
                                <span class="mar-left-20 gray">评论等级:  5</span>
                                <span class="mar-left-20 gray">IP地址:  60.181.171.79</span>
                            </div>
                        </div>
                    </div>
                    <div class="reply-item-wrap clearfix mar-bt-20">
                        <div class="reply-item clearfix mar-bt-5">
                            <div class="reply-label wd-120 fr text-left">：<a href="javascript:;" class="blue">ces</a>
                                回复 <a href="javascript:;" class="blue">wx31cc81</a> 内容
                            </div>
                            <div class="reply-value fr mar-right-20">
                                <div class="msg-info wd-550">
                                    <div class="msg-desc pad-all-10">宝贝收到了，真快！质量也好</div>
                                    <ul class="msg-img pad-lr-15">
                                        <li><img src="" alt=""></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="reply-item clearfix mar-top-20">
                            <div class="reply-label wd-120 fr text-left">：回复日期</div>
                            <div class="reply-value fr mar-right-20">
                                <span class="">2018-08-14 15:26:57</span>
                                <span class="mar-left-20 gray">评论等级:  5</span>
                                <span class="mar-left-20 gray">IP地址:  60.181.171.79</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="reply-opt form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">用户名：</label>
                        <div class="col-sm-4 n-wd400">
                            <input type="text" name="user_name" class="form-control" value=""
                                   placeholder="用户名" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Email：</label>
                        <div class="col-sm-4 n-wd400">
                            <input type="text" name="email" class="form-control" value=""
                                   placeholder="Email" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><font class="red">*</font>回复内容：</label>
                        <div class="col-sm-4 n-wd400">
                            <textarea name="content" cols="50" rows="4" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label"></label>
                        <div class="col-sm-4 n-wd400">
                            <a type="button" href="javascript:;"
                               class="btn btn-danger btn-edit btn-sm">　确定　</a>
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