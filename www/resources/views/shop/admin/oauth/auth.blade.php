@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">手机设置 - 第三方登录插件</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>列表页展示了所有手机端支持的第三方登录插件。</li>
                    <li>安装插件时需先到各第三方应用中申请ID和KEY，其中微信登录需要申请微信公众号并在微信客户端中方可使用。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <ul class="items-box">
                    <li class="item-wrap">
                        <div class="plugin-item" style="clear:both">
                            <div class="plugin-icon">
                                <img src="{{url('styles/admin/images/sns_wechat.png')}}" alt="">
                            </div>
                            <div class="plugin-status">
                        	<span class="status-txt">
	                        	<div class="list-div">
	                        		<div class="handle">
	                        			<a href="" class="btn btn-default btn-sm">安装插件</a>
                                        <a href="" class="btn btn-default btn-sm">安装插件</a>
	                        		</div>
	                        	</div>
                        	</span>
                            </div>
                            <div class="plugin-content">
                                <h3 class="title">WeChat</h3>
                                <p class="desc">版本:1.0</p>
                            </div>
                        </div>
                    </li>
                </ul>
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