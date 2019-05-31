@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 注册项设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>注册项相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/regfields/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">会员注册项名称</th>
                            <th class="col-sm-1">排序权值</th>
                            <th class="col-sm-3 text-center">是否显示</th>
                            <th class="col-sm-3 text-center">是否必填</th>
                            <th class="col-sm-3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($regfields) > 0)
                            @foreach($regfields as $regfield)
                                <tr>
                                    <th>{{$regfield->reg_field_name}}</th>
                                    <td>
                                        <input class="form-control input-sm chang-order" type="text" autocomplete="off"
                                               data-id="{{$regfield->id}}"
                                               name="discount" value="{{$regfield->dis_order}}">
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix regfield">
                                            <div class="switch @if($regfield->display) active @endif mar-lr-auto"
                                                 data-type="display"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$regfield->id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="switch-wrap clearfix regfield">
                                            <div class="switch @if($regfield->is_need) active @endif mar-lr-auto"
                                                 data-type="is_need"
                                                 title="是">
                                                <div class="circle"></div>
                                                <input type="hidden" value="{{$regfield->id}}">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <a type="button" href="{{url('admin/regfields/'.$regfield->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm">编辑</a>
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
                    '{{url("admin/regfields/changes")}}',
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

            $('.chang-order').change(function () {
                $.post(
                    '{{url("admin/regfields/changes")}}',
                    {
                        id: $(this).data('id'),
                        val: $(this).val(),
                        type: 'order',
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });
        });
    </script>
@endsection
@endsection