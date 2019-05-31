@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会话管理 - 会员列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <form action="{{url('admin/wxappsession')}}" method="get" class="form-horizontal">
                        {{csrf_field()}}
                        <div class="form-group fl  mar-top-10">
                            <label class="col-sm-3 control-label">页数：</label>
                            <div class="col-sm-4">
                                <input type="text" name="keywords" value="{{$search['page']}}"
                                       class="form-control input-sm" placeholder="页数">
                            </div>
                        </div>
                        <div class="fr wd250 mar-top-10">
                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </div>
                    </form>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="text-center" width="5%"><a>编号</a></th>
                            <th width="10%">昵称</th>
                            <th width="10%">用户名</th>
                            <th width="20%">OPENID</th>
                            <th width="8%">更新日期</th>
                            <th width="8%">会话结束时间</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($wxappes) > 0)
                            @foreach($wxappes as $wxapp)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$wxapp->id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$wxapp->id}}">
                                    </td>
                                    <td class="text-center">
                                        {{$wxapp->id}}
                                    </td>
                                    <td>
                                        @if($wxapp->nick_name)
                                            {{$wxapp->nick_name}}
                                        @endif
                                    </td>
                                    <td>
                                        @if($wxapp->user_name)
                                            {{$wxapp->user_name}}
                                        @endif
                                    </td>
                                    <td>
                                        {{$wxapp->openid}}
                                    </td>
                                    <td>
                                        {{date('Y-m-d H:i:s', $wxapp->update_time)}}
                                    </td>
                                    <td>
                                        {{floor((48*3600 - time() + $wxapp->update_time)/3600)}}
                                        小时{{floor((48*3600 - time() + $wxapp->update_time)%3600/60)}}分
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="javascript:;" data-id="{{$wxapp->id}}"
                                           class="btn btn-info btn-send btn-sm mar-all-5">发送消息</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$wxapp->id}}">删除</a>
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
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-5">群发</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$wxappes->links()}}
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
            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });

            //发送客服消息
            $('.btn-send').click(function () {
                var id = $(this).data('id');
                layer.open({
                    type: 2,
                    area: ['650px', '400px'],
                    fixed: true, //固定
                    maxmin: true,
                    title: '发送客户消息',
                    content: ["<?php echo e(url('admin/wxappsession')); ?>/" + id + "/edit", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/wxappsession/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
                            }
                        }
                    );
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection