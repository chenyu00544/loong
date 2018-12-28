@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 会员消费分析</div>
        <div class="content">
            <div class="tabs mar-top-5">
                @component('shop.components.userSatistics',['usernav'=>$usernav])@endcomponent
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>默认会员消费排行按订单总金额降序排列。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <div id="user-rank"></div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/charts/echarts.min.js')}}"></script>
    <script>
        $(function () {
            //图表订单数量
            var userConsumptionChart = echarts.init(document.getElementById('user-rank'));
            var userConsumptionOption = {
                title: {
                    text: '会员消费前十分析',
                    subtext: '消费(元)',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: [
                        @foreach($userconsumptionsat['data'] as $consumption)
                            "{{$consumption->User->user_name}}",
                        @endforeach
                    ]
                },
                series: [
                    {
                        name: '会员消费',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        selectedMode: 'single',
                        data: [
                                @foreach($userconsumptionsat['data'] as $consumption)
                            {name:"{{$consumption->User->user_name}}",value:"{{$consumption->total}}"},
                            @endforeach
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 100,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.3)'
                            }
                        }
                    }
                ]
            };
            userConsumptionChart.setOption(userConsumptionOption);
        });
    </script>
@endsection
@endsection