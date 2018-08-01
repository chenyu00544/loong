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
                                        @if(!empty($auths['wechat']))
                                            <a href="{{url('admin/mobileoauth/'.$auths['wechat']->type)}}"
                                               class="btn btn-default btn-sm">编辑</a>
                                            <a href="javascript:;" class="btn btn-danger btn-sm btn-del"
                                               data-id="{{$auths['wechat']->id}}">删除</a>
                                        @else
                                            <a href="{{url('admin/mobileoauth/addauth/wechat')}}"
                                               class="btn btn-default btn-sm">安装插件</a>
                                        @endif
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
                    <li class="item-wrap">
                        <div class="plugin-item" style="clear:both">
                            <div class="plugin-icon">
                                <img src="{{url('styles/admin/images/sns_qq.png')}}" alt="">
                            </div>
                            <div class="plugin-status">
                        	<span class="status-txt">
	                        	<div class="list-div">
	                        		<div class="handle">
                                        @if(!empty($auths['qq']))
                                            <a href="{{url('admin/mobileoauth/'.$auths['qq']->type)}}"
                                               class="btn btn-default btn-sm">编辑</a>
                                            <a href="javascript:;" class="btn btn-danger btn-sm btn-del"
                                               data-id="{{$auths['qq']->id}}">删除</a>
                                        @else
                                            <a href="{{url('admin/mobileoauth/addauth/qq')}}"
                                               class="btn btn-default btn-sm">安装插件</a>
                                        @endif
	                        		</div>
	                        	</div>
                        	</span>
                            </div>
                            <div class="plugin-content">
                                <h3 class="title">QQ</h3>
                                <p class="desc">版本:1.0</p>
                            </div>
                        </div>
                    </li>
                    <li class="item-wrap">
                        <div class="plugin-item" style="clear:both">
                            <div class="plugin-icon">
                                <img src="{{url('styles/admin/images/sns_weibo.png')}}" alt="">
                            </div>
                            <div class="plugin-status">
                        	<span class="status-txt">
	                        	<div class="list-div">
	                        		<div class="handle">
                                        @if(!empty($auths['qq']))
                                            <a href="{{url('admin/mobileoauth/'.$auths['weibo']->type)}}"
                                               class="btn btn-default btn-sm">编辑</a>
                                            <a href="javascript:;" class="btn btn-danger btn-sm btn-del"
                                               data-id="{{$auths['weibo']->id}}">删除</a>
                                        @else
                                            <a href="{{url('admin/mobileoauth/addauth/weibo')}}"
                                               class="btn btn-default btn-sm">安装插件</a>
                                        @endif
	                        		</div>
	                        	</div>
                        	</span>
                            </div>
                            <div class="plugin-content">
                                <h3 class="title">Weibo</h3>
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
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/mobileoauth/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection