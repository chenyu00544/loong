@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员管理 - 实名认证</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>实名认证相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    @foreach($usersNav as $nav)
                        <li class="@if($navType == $nav['navType']) curr @endif fl">
                            <a href="{{url('admin/'.$nav['navType'])}}">{{$nav['title']}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix pad-bt-15">
                    <div class="fr wd360">
                        <form action="{{url('admin/usersreal/')}}" method="get">
                            {{csrf_field()}}
                            <select name="review_status" class="form-control input-sm max-wd-100 fl">
                                <option value="-1">全部</option>
                                <option value="0">未审核</option>
                                <option value="1">审核通过</option>
                                <option value="2">审核未通过</option>
                            </select>

                            <input type="text" name="keywords" value=""
                                   class="form-control input-sm max-wd-190 fl mar-left-10" placeholder="名称">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 lh22 fl" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="5%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="text-center" width="5%"><a>编号</a></th>
                            <th width="15%"><a>会员名称</a></th>
                            <th width="10%">真实姓名</th>
                            <th width="15%">手机号码</th>
                            <th width="25%">身份证号</th>
                            <th width="10%">审核状态</th>
                            <th class="text-center" width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($userReals) > 0)
                            @foreach($userReals as $userReal)
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkboxes" value="{{$userReal->real_id}}"
                                               class="checkbox check-all"
                                               id="checkbox_{{$userReal->real_id}}">
                                    </td>
                                    <td class="text-center">
                                        {{$userReal->real_id}}
                                    </td>
                                    <td>
                                        {{$userReal->user_name}}
                                    </td>
                                    <td>
                                        {{$userReal->real_name}}
                                    </td>
                                    <td>
                                        {{$userReal->bank_mobile}}
                                    </td>
                                    <td>
                                        {{$userReal->self_num}}
                                    </td>
                                    <td>
                                        @if($userReal->review_status == 0) 未审核
                                        @elseif($userReal->review_status==1) 审核通过
                                        @elseif($userReal->review_status==2) 审核不通过 @endif
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/usersreal/'.$userReal->real_id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$userReal->real_id}}">删除</a>
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
                                       class="checkbox check-all fl">全选</label>
                        </div>
                        <div class="fl mar-all-5">
                            <select name="select_type" class="form-control col-md-2 input-sm">
                                <option value="0">请选择</option>
                                <option value="is_best_on">删除</option>
                                <option value="is_best_off">审核</option>
                            </select>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-8">确定</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$userReals->links()}}
                    </div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
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

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/usersreal/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                $(that).parent().parent().remove();
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