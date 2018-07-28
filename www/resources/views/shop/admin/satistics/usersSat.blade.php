@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 订单统计</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>统计商城所有的订单，销售总额、有效金额、总点击数、每日点击数、每日点击购买额。</li>
                    <li>选择不同月份统计出现几个月的订单概括、配送方式、支付方式统计图。</li>
                </ul>
            </div>

            <div class="fromlist clearfix">
                <div class="clearfix">
                    <form class="form-horizontal">
                        <div class="form-group mar-top-20">
                            <label class="col-sm-2 control-label wd-120">总览：</label>
                            <div class="col-sm-10">
                                <div class="fl pad-sat"><span class="fs-14 pad-all-5">订单数量:</span><span
                                            class="red fs-14 pad-all-5">{{$countOrder}}</span></div>
                                <div class="fl b-l-t-e pad-sat"><span class="fs-14 pad-all-5">销售额:</span><span
                                            class="red fs-14 pad-all-5">￥{{$sumAmount}}</span></div>
                            </div>
                        </div>
                        <div class="form-group mar-top-20">
                            <label class="col-sm-2 control-label wd-120">起止日期：</label>
                            <div class="col-sm-6">
                                <input type="text" style="width: 350px" name="start_end_date"
                                       id="start_end_date" class="form-control input-sm fl"
                                       value="{{$now_date}}">
                                <a type="button" href="javascript:;"
                                   class="btn btn-info btn-sm search_1 mar-left-20" style="padding: 5px 20px;">查询</a>
                            </div>
                        </div>
                        <div class="form-group mar-top-20">
                            <label class="col-sm-2 control-label wd-120">查询年月：</label>
                            <div class="col-sm-10">
                                @foreach($date as $k => $v)
                                    <input type="text" style="width: 145px" name="date"
                                           id="date_{{$k}}"
                                           class="form-control input-sm fl @if($k!=0) mar-left-15 @endif"
                                           value="{{$v}}">
                                @endforeach
                                <a type="button" href="javascript:;"
                                   class="btn btn-info btn-sm search_2 mar-left-20" style="padding: 5px 20px;">查询</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="main-info">
                    <div id="order-num"></div>
                    <div id="sale-total" class="mar-top-20"></div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/charts/echarts.js')}}"></script>
    <script>
        $(function () {
            $('#start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD') + ' 08:00:00';
                var e = end.format('YYYY-MM-DD') + ' 08:00:00';
                var t = s + '～' + e;
                $('#start_end_date').val(t);
            });
            $('#date_0').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM');
                $('#date_0').val(s);
            });
            $('#date_1').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM');
                $('#date_1').val(s);
            });
            $('#date_2').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM');
                $('#date_2').val(s);
            });
            $('#date_3').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM');
                $('#date_3').val(s);
            });
            $('#date_4').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM');
                $('#date_4').val(s);
            });

            //图表订单数量
            var orderNumChart = echarts.init(document.getElementById('order-num'));
            //指定图表的配置项和数据
            var orderNumOption = {
                itemStyle: {
                    color: '#37a2da'
                },
                title: {
                    text: '订单数量'
                },
                tooltip: {},
                legend: {
                    data: ['订单数量']
                },
                xAxis: {
                    data: [
                        @foreach($ordersat['data'] as $val)
                        "{{$val['name']}}",
                        @endforeach
                    ]
                },
                yAxis: {},
                series: [{
                    name: '订单数量',
                    type: 'bar',
                    data: [@foreach($ordersat['data'] as $val)
                        "{{$val['value_on']}}",
                        @endforeach]
                }]
            };
            orderNumChart.setOption(orderNumOption);

            //销售总额
            var saleTotalChart = echarts.init(document.getElementById('sale-total'));
            // 指定图表的配置项和数据
            var saleTotalOption = {
                itemStyle: {
                    color: '#37a2da'
                },
                title: {
                    text: '销售额'
                },
                tooltip: {},
                legend: {
                    data: ['销售额']
                },
                xAxis: {
                    data: [
                        @foreach($ordersat['data'] as $val)
                            "{{$val['name']}}",
                        @endforeach
                    ]
                },
                yAxis: {},
                series: [{
                    name: '销售额',
                    type: 'bar',
                    data: [
                        @foreach($ordersat['data'] as $val)
                            "{{$val['value_st']}}",
                        @endforeach
                    ]
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            saleTotalChart.setOption(saleTotalOption);

            $('.search_1').on('click', function () {
                var range = $('input[name=start_end_date]').val();
                $.post("{{url('admin/satistics/getsat')}}", {
                    '_token': '{{csrf_token()}}',
                    type: 'order',
                    range: range,
                    opt: 'range'
                }, function (data) {
                    orderNumOption.xAxis.data = [];
                    orderNumOption.series[0].data = [];
                    saleTotalOption.xAxis.data = [];
                    saleTotalOption.series[0].data = [];
                    for (i in data.data) {
                        orderNumOption.xAxis.data[i] = data.data[i].name;
                        orderNumOption.series[0].data[i] = data.data[i].value_on;
                        saleTotalOption.xAxis.data[i] = data.data[i].name;
                        saleTotalOption.series[0].data[i] = data.data[i].value_st;
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    orderNumChart.setOption(orderNumOption);
                    saleTotalChart.setOption(saleTotalOption);
                });
            });

            $('.search_2').on('click', function () {
                var range = [];
                $('input[name=date]').each(function () {
                    range.push($(this).val());
                });
                $.post("{{url('admin/satistics/getsat')}}", {
                    '_token': '{{csrf_token()}}',
                    type: 'order',
                    range: range,
                    opt: 'part'
                }, function (data) {
                    orderNumOption.xAxis.data = [];
                    orderNumOption.series[0].data = [];
                    saleTotalOption.xAxis.data = [];
                    saleTotalOption.series[0].data = [];
                    for (i in data.data) {
                        orderNumOption.xAxis.data[i] = data.data[i].name;
                        orderNumOption.series[0].data[i] = data.data[i].value_on;
                        saleTotalOption.xAxis.data[i] = data.data[i].name;
                        saleTotalOption.series[0].data[i] = data.data[i].value_st;
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    orderNumChart.setOption(orderNumOption);
                    saleTotalChart.setOption(saleTotalOption);
                });
            });
        });
    </script>
@endsection
@endsection