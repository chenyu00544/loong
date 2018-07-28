@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">报表 - 新增会员</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($usernav == 'user') curr @endif fl">
                        <a href="{{url('admin/satistics/user')}}">新增会员</a>
                    </li>
                    <li class="@if($usernav == 'area') curr @endif fl">
                        <a href="{{url('admin/satistics/area')}}">会员区域分析</a>
                    </li>
                    <li class="@if($usernav == 'rank') curr @endif fl">
                        <a href="{{url('admin/satistics/rank')}}">会员等级分析</a>
                    </li>
                    <li class="@if($usernav == 'consumption') curr @endif fl">
                        <a href="{{url('admin/satistics/consumption')}}">会员消费排行</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>统计图可根据错选时间段展示新增会员数量走势。</li>
                </ul>
            </div>

            <div class="fromlist clearfix">
                <div class="clearfix">
                    <form class="form-horizontal">
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
                    </form>
                </div>
                <div class="main-info">
                    <div id="user-num"></div>
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

            //图表订单数量
            var userNumChart = echarts.init(document.getElementById('user-num'));
            //指定图表的配置项和数据
            var userNumOption = {
                itemStyle: {
                    color: '#37a2da'
                },
                title: {
                    text: '新增会员'
                },
                tooltip: {},
                legend: {
                    data: ['会员数量']
                },
                xAxis: {
                    data: [
                        @foreach($usersat['data'] as $val)
                        "{{$val['name']}}",
                        @endforeach
                    ]
                },
                yAxis: {},
                series: [{
                    name: '会员数量',
                    type: 'bar',
                    data: [@foreach($usersat['data'] as $val)
                        "{{$val['value']}}",
                        @endforeach]
                }]
            };
            userNumChart.setOption(userNumOption);

            $('.search_1').on('click', function () {
                var range = $('input[name=start_end_date]').val();
                $.post("{{url('admin/satistics/getsat')}}", {
                    '_token': '{{csrf_token()}}',
                    type: 'user',
                    range: range,
                    opt: 'range'
                }, function (data) {
                    userNumOption.xAxis.data = [];
                    userNumOption.series[0].data = [];
                    for (i in data.data) {
                        userNumOption.xAxis.data[i] = data.data[i].name;
                        userNumOption.series[0].data[i] = data.data[i].value;
                    }
                    // 使用刚指定的配置项和数据显示图表。
                    userNumChart.setOption(userNumOption);
                });
            });
        });
    </script>
@endsection
@endsection