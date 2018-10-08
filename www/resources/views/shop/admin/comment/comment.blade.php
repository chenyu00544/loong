@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商品管理 - 用户评论</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($search['seller'] == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/comment/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($search['seller'] == 'seller') curr @endif fl">
                        <a href="{{url('admin/comment/seller')}}">店铺</a>
                    </li>
                </ul>
            </div>
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
                <div>
                    <div class="fr wd250">
                        <form action="{{url('admin/comment')}}" method="get">
                            {{csrf_field()}}
                            <input type="hidden" name="seller" value="{{$search['seller']}}">
                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="评论内容">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th style="width: 3%"><input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="text-center" style="width: 5%"><a>编号</a></th>
                            <th style="width: 10%"><a>用户名</a></th>
                            <th style="width: 10%">商家名称</th>
                            <th style="width: 5%">类型</th>
                            <th style="width: 25%">评论对象</th>
                            <th style="width: 10%">IP地址</th>
                            <th style="width: 10%">评论时间</th>
                            <th style="width: 7%">是否显示</th>
                            <th class="text-center" style="width: 15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($comments) > 0)
                            @foreach($comments as $comment)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$comment->comment_id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$comment->comment_id}}">
                                    </td>
                                    <td class="text-center">
                                        {{$comment->comment_id}}
                                    </td>
                                    <td>{{$comment->user_name}}</td>
                                    <td>
                                        @if(!empty($comment->shop))
                                            <font class="@if($comment->ru_id != 0) blue @else red @endif">{{$comment->shop->shop_name}}</font>
                                        @else
                                            暂无
                                        @endif
                                    </td>
                                    <td>
                                        @if($comment->comment_type == 0)
                                            商品
                                        @elseif($comment->comment_type == 1)
                                            文章
                                        @elseif($comment->comment_type == 3)
                                            管理员回复
                                        @endif
                                    </td>
                                    <td>
                                        @if($comment->comment_type == 0)
                                            @if(empty($comment->goods))
                                                无
                                            @else
                                                <a href="{{url('goods/'.$comment->goods->goods_id)}}">{{$comment->goods->goods_name}}</a>
                                            @endif
                                        @else
                                            @if(empty($comment->article))
                                                无
                                            @else
                                                <a href="{{url('article/'.$comment->article->article_id)}}">{{$comment->article->title}}</a>
                                            @endif
                                        @endif
                                    </td>
                                    <td>
                                        {{$comment->ip_address}}
                                    </td>
                                    <td>
                                        {{date('Y-m-d H:i:s', $comment->add_time)}}
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix">
                                            <div class="switch @if($comment->status) active @endif" data-type="status"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$comment->comment_id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/comment/'.$comment->comment_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">查看</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$comment->comment_id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                        @else
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        </tbody>
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl mar-all-5 checkwrap">
                            <label class="label-tip">
                                <input type="checkbox" name="all_list" value=""
                                       class="checkbox check-all fl ">全选</label>
                        </div>
                        <div class="fl mar-all-5">
                            <select name="select_type" class="form-control col-md-2 input-sm">
                                <option value="0">请选择</option>
                                <option value="remove">删除评论</option>
                                <option value="allow">允许显示</option>
                                <option value="deny">禁止显示</option>
                            </select>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-8">确定</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$comments->links()}}
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
            //开关
            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0
                    $(this).removeClass('active');
                } else {
                    val = 1
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/comment/change")}}',
                    {
                        id: id,
                        type: tag,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            //批量修改
            $('.btn-sure').click(function () {

                var goods_ids = $("input[name=checkboxes]");

                var ids = [];
                $.each(goods_ids, function (k, v) {
                    if ($(v).is(':checked')) {
                        ids.push($(v).val());
                    }
                });
                var select_type = $("select[name=select_type]").val();

                if (ids.length > 0 && select_type != 0) {
                    $.post(
                        '{{url("admin/comment/changes")}}',
                        {
                            ids: ids,
                            type: select_type,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    );
                }
            });

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/comment/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                setTimeout(function () {
                                    $(that).parent().parent().remove();
                                }, 1000);
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection