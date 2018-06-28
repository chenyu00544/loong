@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">数据管理 - 数据表优化</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>手动清理数据表碎片。</li>
                    <li>定期清理数据表碎片可以提升数据库的查询速度。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="javascript:;"
                       class="btn btn-danger btn-add btn-sm run-optimize">开始进行数据表优化</a>
                    <span>总碎片数： {{$total}}</span>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th width="15%">数据表</th>
                            <th width="15%">数据表类型</th>
                            <th width="10%">记录数</th>
                            <th width="10%">数据</th>
                            <th width="5%">碎片</th>
                            <th width="15%">字符集</th>
                            <th width="5%">状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($tables) > 0)
                            @foreach($tables as $table)
                                <tr>
                                    <td>
                                        {{$table->Name}}
                                    </td>
                                    <td>
                                        {{$table->Engine}}
                                    </td>
                                    <td>
                                        {{$table->Rows}}
                                    </td>
                                    <td>
                                        {{sprintf(" %.2f KB", $table->Data_length / 1024)}}
                                    </td>
                                    <td>
                                        {{$table->Data_free}}
                                    </td>
                                    <td>
                                        {{$table->Collation}}
                                    </td>
                                    <td>
                                        {{$table->status[0]->Msg_text}}
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.run-optimize').on('click', function () {
                layer.load();
                $.post("{{url('admin/database/runoptimize')}}", {'_token': "{{csrf_token()}}"}, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    layer.closeAll('loading');
                    setTimeout(function () {
                        window.location.href = "{{url('admin/database/optimize')}}";
                    }, 3000);
                });
            });
        });
    </script>
@endsection
@endsection