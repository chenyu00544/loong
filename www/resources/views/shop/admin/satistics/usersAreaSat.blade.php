@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 会员区域分析</div>
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
                    <li>各地区会员总数、有效订单下单总金额和有效订单下单量。</li>
                </ul>
            </div>

            <div class="fromlist clearfix">
                <div class="mar-bt-20">
                    <a type="button" href="javascript:;"
                       class="btn btn-info btn-sm search mar-left-20" data-val="user">按用户数</a>
                    <a type="button" href="javascript:;"
                       class="btn btn-info btn-sm search mar-left-20" data-val="sale">按销售额</a>
                    <a type="button" href="javascript:;"
                       class="btn btn-info btn-sm search mar-left-20" data-val="order">按订单数</a>
                </div>
                <div class="main-info">
                    <div id="user-area-map"></div>
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
            var userAreaChart = echarts.init(document.getElementById('user-area-map'));
            var areaOption = {
                title: {
                    text: '会员区域分析',
                    left: 'right'
                },
                tooltip: {

                },
                visualMap: {
                    left: 'right',
                    min: 0,
                    max: 10000000,
                    inRange: {
                        color: ['#e0f3f8','#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                    },
                    text: ['High', 'Low'],
                    calculable: true
                },
                toolbox: {
                    show: true,
                    //orient: 'vertical',
                    left: 'left',
                    top: 'top',
                    feature: {
                        dataView: {readOnly: false},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                series: [
                    {
                        name: '用户数量（个）',
                        type: 'map',
                        roam: true,
                        map: 'china',
                        itemStyle: {
                            emphasis: {label: {show: true}}
                        },
                        // 文本位置修正
                        textFixed: {
                            Alaska: [20, -20]
                        },
                        data: [
                                @foreach($userareasat['data'] as $area)
                            {
                                name: '{{$area->region_name}}', value: '{{$area->users_count}}'
                            },
                            @endforeach
                        ]
                    }
                ]
            };
            $.post("{{url('admin/satistics/geojson')}}", {
                '_token': '{{csrf_token()}}',
                type: 'user',
            }, function (data) {
                echarts.registerMap('china', data);
                userAreaChart.setOption(areaOption);
            });

            $('.search').on('click', function () {
                userAreaChart.showLoading();
                var opt = $(this).data('val');
                $.post("{{url('admin/satistics/getsat')}}", {
                    '_token': '{{csrf_token()}}',
                    type: 'userarea',
                    opt: opt
                }, function (data) {
                    userAreaChart.hideLoading();
                    areaOption.series[0].data = [];
                    areaOption.series[0].name = data.msg;
                    for (i in data.data) {
                        var d = {name: '', value: ''};
                        d.name = data.data[i].region_name;
                        if (opt == 'user') {
                            d.value = data.data[i].users_count;
                        } else if (opt == 'sale') {
                            d.value = data.data[i].sale_count;
                        } else if (opt == 'order') {
                            d.value = data.data[i].order_count;
                        }
                        areaOption.series[0].data.push(d);
                    }
                    userAreaChart.setOption(areaOption);
                });
            });
        });
    </script>
@endsection
@endsection