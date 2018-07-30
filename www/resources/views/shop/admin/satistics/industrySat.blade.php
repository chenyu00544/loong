@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 行业统计</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>总下单量：所选时间范围内所有状态下订单总数。</li>
                    <li>商品总数：所选商品分类下商品总数。</li>
                </ul>
            </div>

            <div class="fromlist clearfix">
                <div class="main-info">
                    <div id="industry-num"></div>
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
            //图表订单数量
            var industryNumChart = echarts.init(document.getElementById('industry-num'));
            //指定图表的配置项和数据
            var industryNumOption = {
                itemStyle: {
                    color: '#ff4500'
                },
                title: {
                    text: '行业分析概况'
                },
                tooltip: {},
                legend: {
                    data: ['订单金额']
                },
                xAxis: {
                    data: [
                        @foreach($industrysat['data'] as $val)
                        "{{$val['name']}}",
                        @endforeach
                    ]
                },
                yAxis: {},
                series: [{
                    name: '订单金额',
                    type: 'bar',
                    data: [@foreach($industrysat['data'] as $val)
                        "{{$val['value']}}",
                        @endforeach]
                }]
            };
            industryNumChart.setOption(industryNumOption);

            $('.search').on('click', function () {
                $.post("{{url('admin/satistics/getsat')}}", {
                    '_token': '{{csrf_token()}}',
                    type: 'industry',
                    range: range,
                    opt: 'industry'
                }, function (data) {
                    for (i in data.data) {
                    }
                });
            });
        });
    </script>
@endsection
@endsection