@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商品管理 - 评论详情</div>
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
                <form name="brand" action="{{url('admin/comment/'.$id)}}" method="post" class="form-horizontal"
                      enctype="multipart/form-data">
                    {{csrf_field()}}
                    {{method_field('PUT')}}
                    <div class="reply-opt form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">用户名：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="user_name" class="form-control" value="{{$user->user_name}}"
                                       placeholder="用户名" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Email：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="email" class="form-control" value="{{$user->email}}"
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
                            <label class="col-sm-4 control-label"><font class="red">*</font>添加图片：</label>
                            <div class="col-sm-4 n-wd400">
                                <div class="clearfix ">
                                    <input type="file" name="reply-img[]" class="fl">
                                    <div class="add-sub-item fl"><i class="sc-icon sc-icon-add-small cursor"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-4 n-wd400">
                                <input type="submit" class="btn btn-danger btn-edit btn-sm" value="　确定　">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="comments pad-all-30 clearfix">
                    @foreach($comments as $comment)
                        @if($comment->comment_type != 3)
                            <div class="reply-item-wrap clearfix mar-bt-20">
                                <div class="reply-item clearfix mar-bt-5">
                                    <div class="reply-label wd-120 fl text-right">
                                        <a href="javascript:;" class="blue">{{$comment->user_name}}</a>评论
                                        <a href="javascript:;" class="blue">
                                            @if($comment->comment_type ==0)
                                                @if(!empty($comment->goods))
                                                    {{$comment->goods->goods_name}}
                                                @endif
                                            @elseif($comment->comment_type ==1)
                                                @if(!empty($comment->article))
                                                    {{$comment->article->title}}
                                                @endif
                                            @endif
                                        </a> 内容：
                                    </div>
                                    <div class="reply-value fl mar-left-20">
                                        <div class="msg-info wd-550">
                                            <div class="msg-desc pad-all-10">{{$comment->content}}</div>
                                            <ul class="msg-img pad-lr-15 clearfix">
                                                @foreach($comment->commentImg as $commentImg)
                                                    <li class="fl mar-right-10 mar-top-5">
                                                        <a href="{{$commentImg->comment_img}}" target="_blank">
                                                            <img src="{{$commentImg->comment_img}}" alt="" class="img"></a>
                                                    </li>
                                                @endforeach
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="reply-item clearfix mar-top-20">
                                    <div class="reply-label wd-120 fl text-right">评论日期：</div>
                                    <div class="reply-value fl mar-left-20">
                                        <span class="">{{date('Y-m-d H:i:s', $comment->add_time)}}</span>
                                        <span class="mar-left-20 gray">评论等级: {{$comment->comment_rank}}</span>
                                        <span class="mar-left-20 gray">IP地址: {{$comment->ip_address}}</span>
                                    </div>
                                </div>
                            </div>
                        @elseif($comment->comment_type == 3)
                            <div class="reply-item-wrap clearfix mar-bt-20">
                                <div class="reply-item clearfix mar-bt-5">
                                    <div class="reply-label wd-120 fr text-left">：
                                        <a href="javascript:;" class="blue">
                                            {{$comment->user_name}}
                                        </a>回复内容
                                    </div>
                                    <div class="reply-value fr mar-right-20">
                                        <div class="msg-info wd-550">
                                            <div class="msg-desc pad-all-10">{{$comment->content}}</div>
                                            <ul class="msg-img pad-lr-15 clearfix">
                                                @foreach($comment->commentImg as $commentImg)
                                                    <li class="fl mar-right-10 mar-top-5">
                                                        <a href="{{$commentImg->comment_img}}" target="_blank">
                                                            <img src="{{$commentImg->comment_img}}" alt="" class="img"></a>
                                                    </li>
                                                @endforeach
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="reply-item clearfix mar-top-20">
                                    <div class="reply-label wd-120 fr text-left">：回复日期</div>
                                    <div class="reply-value fr mar-right-20">
                                        <span class="mar-right-20 gray">IP地址: {{$comment->ip_address}}</span>
                                        <span class="">{{date('Y-m-d H:i:s', $comment->add_time)}}</span>
                                    </div>
                                </div>
                            </div>
                        @endif
                    @endforeach
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
            $('.add-sub-item').on('click', function () {
                var html = '<div class="clearfix">' +
                    '<input type="file" name="reply-img[]" class="fl">' +
                    '<div class="sub-sub-item fl">' +
                    '<i class="sc-icon sc-icon-sub-small cursor"></i>' +
                    '</div>' +
                    '</div>';
                $(this).parent().parent().append(html)
            });

            $('body').on('click', '.sub-sub-item', function () {
                $(this).parent().remove();
            });
        });
    </script>
@endsection
@endsection