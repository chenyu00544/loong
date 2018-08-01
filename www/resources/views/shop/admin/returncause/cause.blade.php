@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">订单设置 - 退货原因列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>商城退货原因信息列表管理。</li>
                    <li>可进行删除或修改退换货原因。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/returncause/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">退换原因</th>
                            <th class="col-sm-3">是否显示</th>
                            <th class="col-sm-3">排序</th>
                            <th class="col-sm-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($causes as $cause)
                            <tr>
                                <td>
                                    {{$cause->cause_name}}
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($cause->is_show) active @endif" data-type="is_show"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$cause->cause_id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <input class="form-control input-sm wd-80" type="text"
                                           data-id="{{$cause->cause_id}}"
                                           name="sort_order"
                                           value="{{$cause->sort_order}}">
                                </td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/returncause/'.$cause->cause_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm"
                                       data-id="{{$cause->cause_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$causes->links()}}
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
                    '{{url("admin/returncause/change")}}',
                    {
                        id: id,
                        type: tag,
                        value: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            $('input[name=sort_order]').change(function () {
                $.post(
                    '{{url("admin/returncause/change")}}',
                    {
                        id: $(this).data('id'),
                        value: $(this).val(),
                        type: 'sort',
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                    }
                );
            });

            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/returncause/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                $(that).parent().parent().remove();
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection