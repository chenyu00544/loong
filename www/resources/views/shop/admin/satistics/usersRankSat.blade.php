@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 会员等级分析</div>
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
                    <li>统计图展示了平台所有会员等级的分布占比。</li>
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
    <script type="text/javascript" src="{{url('styles/plugin/charts/echarts.js')}}"></script>
    <script>
        $(function () {
            //图表订单数量
            var userRankChart = echarts.init(document.getElementById('user-rank'));
            var userRankOption = {
                title: {
                    text: '会员等级分析',
                    subtext: '按积分',
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
                        @foreach($userranksat['data'] as $rank)
                            "{{$rank->rank_name}}",
                        @endforeach
                    ]
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        selectedMode: 'single',
                        data: [
                            @foreach($userranksat['data'] as $rank)
                            {name:"{{$rank->rank_name}}",value:"{{$rank->user_count}}"},
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
            userRankChart.setOption(userRankOption);
        });
    </script>
@endsection
@endsection