@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 设置秒杀商品</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>设置秒杀商品信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="30%">秒杀时段名称</th>
                            <th width="25%">每日开始时间</th>
                            <th width="25%">每日结束时间</th>
                            <th width="15%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($seckilltimes->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($seckilltimes as $seckilltime)
                                <tr class="">
                                    <td>{{$seckilltime->id}}</td>
                                    <td class="wsn">
                                        {{$seckilltime->title}}
                                    </td>
                                    <td>{{$seckilltime->begin_time}}</td>
                                    <td>{{$seckilltime->end_time}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/seckillgoods/'.$id.'/'.$seckilltime->id)}}"
                                           class="btn btn-info btn-edit btn-sm">添加商品</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
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
        });
    </script>
@endsection
@endsection