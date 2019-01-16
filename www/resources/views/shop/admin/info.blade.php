@extends('shop.layouts.index')
@section('css')
    @if($debug)
        <link rel="stylesheet" href="{{asset('styles/admin/css/info.css').'?v='.$v}}">
    @else
        <link rel="stylesheet" href="{{asset('styles/admin/css/info.min.css')}}">
    @endif
@endsection
@section('content')
    <body class="iframe_body" style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper">
        <div class="title">管理中心</div>
        <div class="content start_content">
            @if(empty($conf['sms']['sms_shop_mobile']) || empty($conf['smtp']['smtp_user']) || (empty($conf['alipay']['alipay_key']) && empty($conf['wxpay']['wxpay_mchid'])) || empty($conf['oss']['keyid']))
                <div class="contentWarp">
                    <div class="explanation" id="explanation">
                        <div class="ex_tit"><h4 class="lh30">系统检测到您有影响商城运营的基本配置信息尚未配置：</h4></div>
                        <div class="ex_con">
                            @if(empty($conf['sms']['sms_shop_mobile']))
                                <span class="mar-right-20">短信配置尚未配置，<a href="{{url('admin/sms')}}">前往配置</a></span>
                            @endif
                            @if(empty($conf['smtp']['smtp_user']))
                                <span class="mar-right-20">邮件配置尚未配置，<a href="{{url('admin/email')}}">前往配置</a></span>
                            @endif
                            @if(empty($conf['alipay']['alipay_key']) && empty($conf['wxpay']['wxpay_mchid']))
                                <span class="mar-right-20">支付方式尚未配置，<a href="{{url('admin/pay')}}">前往配置</a></span>
                            @endif
                            @if(empty($conf['oss']['keyid']))
                                <span class="mar-right-20">阿里OSS尚未配置，<a href="{{url('admin/oss')}}">前往配置</a></span>
                            @endif
                        </div>
                    </div>
                </div>
            @endif

            <div class="contentWarp">
                <div class="contentWarp_item clearfix">
                    <div class="section_select clearfix">
                        <div class="item item_price">
                            <i class="icon"><img src="{{asset('styles/admin/images/1.png')}}" width="71"
                                                 height="74"/></i>
                            <div class="desc">
                                <div class="tit">￥{{$info['order_money_total']}}</div>
                                <span class="fs-14">今日销售总额</span>
                            </div>
                        </div>
                        <div class="item item_order">
                            <i class="icon"><img src="{{asset('styles/admin/images/2.png')}}"/></i>
                            <div class="desc">
                                <div class="tit">{{$info['order_sum']}}</div>
                                <span class="fs-14">今日订单总数</span>
                            </div>
                            <i class="icon"></i>
                        </div>
                        <div class="item item_comment">
                            <i class="icon"><img src="{{asset('styles/admin/images/3.png')}}" width="90"
                                                 height="86"/></i>
                            <div class="desc">
                                <div class="tit">{{$info['comment_sum']}}</div>
                                <span class="fs-14">今日评论数</span>
                            </div>
                        </div>
                        <div class="item item_flow">
                            <i class="icon"><img src="{{asset('styles/admin/images/4.png')}}" width="86"/></i>
                            <div class="desc">
                                <div class="tit">{{$info['seller_sum']}}</div>
                                <span class="fs-14">店铺数量</span>
                            </div>
                            <i class="icon"></i>
                        </div>
                    </div>
                    <div class="section user_section">
                        <div class="sc_title">
                            <i class="sc-icon"></i>
                            <h3>个人会员信息</h3>
                            <cite>（单位：个）</cite>
                        </div>
                        <div class="sc_warp">
                            <div class="user_item user_today_new">
                                <div class="num">{{$info['user_now_sum']}}</div>
                                <span class="tit">今日新增</span>
                            </div>
                            <div class="user_item user_yest_new">
                                <div class="num">{{$info['user_yester_sum']}}</div>
                                <span class="tit">昨日新增</span>
                            </div>
                            <div class="user_item user_month_new">
                                <div class="num">{{$info['user_now_month_sum']}}</div>
                                <span class="tit">本月新增</span>
                            </div>
                            <div class="user_item user_total">
                                <div class="num">{{$info['user_total_sum']}}</div>
                                <span class="tit">会员总数</span>
                            </div>
                        </div>
                    </div>
                    <div class="section goods_section">
                        <div class="sc_title">
                            <i class="sc-icon"></i>
                            <h3>商品一览</h3>
                            <cite>（单位：件）</cite>
                        </div>
                        <div class="sc_warp">
                            <div class="goods_item">
                                <div class="tit">自营商品</div>
                                <div class="number">
                                    <div class="st">实体：<a
                                                href="{{url('admin/goods/selfsale')}}">{{$info['goods_entity_num']}}</a></div>
                                    <div class="xn">虚拟：<a
                                                href="{{url('admin/goods/selfsale')}}">{{$info['goods_fictitious_num']}}</a>
                                    </div>
                                </div>
                            </div>
                            <div class="goods_item">
                                <div class="tit">商家商品</div>
                                <div class="number">
                                    <div class="st">实体：<a
                                                href="{{url('admin/goods/seller')}}">{{$info['goods_seller_entity_num']}}</a>
                                    </div>
                                    <div class="xn">虚拟：<a
                                                href="{{url('admin/goods/seller')}}">{{$info['goods_seller_fictitious_num']}}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="contentWarp_item clearfix">
                    <div class="section_order_select">
                        <ul>
                            <li>
                                <a href="{{url('admin/order/1?seller=selfsale')}}">
                                    <i class="ice ice_w"></i>
                                    <div class="t">未确认订单</div>
                                    <span class="number">{{$info['order_no_confirm']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="{{url('admin/order/2?seller=selfsale')}}">
                                    <i class="ice ice_d"></i>
                                    <div class="t">待支付订单</div>
                                    <span class="number">{{$info['order_no_pay']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="{{url('admin/order/3?seller=selfsale')}}">
                                    <i class="ice ice_n"></i>
                                    <div class="t">待发货订单</div>
                                    <span class="number">{{$info['order_no_shipping']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="{{url('admin/order/5?seller=selfsale')}}">
                                    <i class="ice ice_f"></i>
                                    <div class="t">已成交订单数</div>
                                    <span class="number">{{$info['order_over_transaction']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="ice ice_y"></i>
                                    <div class="t">缺货登记</div>
                                    <span class="number">{{$info['shortage_registration']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="{{url('admin/order/3?seller=selfsale')}}">
                                    <i class="ice ice_q"></i>
                                    <div class="t">部分发货订单</div>
                                    <span class="number">{{$info['order_part_shipping']}}</span>
                                </a>
                            </li>
                            <li>
                                <a href="{{url('admin/uaccount')}}">
                                    <i class="ice ice_b"></i>
                                    <div class="t">用户提现申请</div>
                                    <span class="number">{{$info['user_cash_withdrawal']}}</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="clear"></div>
                    <div class="section section_order_count">
                        <div class="text-right filter_date order_total">
                            <a href="javascript:;" class="focus" data-date="week">一周</a>
                            <a href="javascript:;" data-date="month">一月</a>
                            <a href="javascript:;" data-date="year">一年</a>
                        </div>
                        <div id="order-count"></div>
                    </div>
                    <div class="section section_total_count">
                        <div class="text-right filter_date amount_total">
                            <a href="javascript:;" class="focus" data-date="week">一周</a>
                            <a href="javascript:;" data-date="month">一月</a>
                            <a href="javascript:;" data-date="year">一年</a>
                        </div>
                        <div id="sale-count"></div>
                    </div>
                </div>
            </div>
            <div class="contentWarp bf100">
                <div class="section col_section">
                    <div class="sc_title">
                        <i class="sc-icon"></i>
                        <h3>控制面板</h3>
                    </div>
                    <div class="sc_warp">
                        <div class="item_section item_section_frist">
                            <div class="section_header">商城管理</div>
                            <div class="section_body">
                                <dl>
                                    <dt>商城首页：</dt>
                                    <dd><a href="{{$sys_info['home']}}" target="_blank">{{$sys_info['home']}}</a></dd>
                                </dl>
                                <dl>
                                    <dt>平台后台：</dt>
                                    <dd><a href="{{$sys_info['admin']}}" target="_blank">{{$sys_info['admin']}}</a></dd>
                                </dl>
                                <dl>
                                    <dt>商家后台：</dt>
                                    <dd><a href="{{$sys_info['seller']}}" target="_blank">{{$sys_info['seller']}}</a>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>门店后台：</dt>
                                    <dd><a href="{{$sys_info['stores']}}" target="_blank">{{$sys_info['stores']}}</a>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>WAP首页：</dt>
                                    <dd><a href="{{$sys_info['web']}}" target="_blank">{{$sys_info['web']}}</a></dd>
                                </dl>
                            </div>
                        </div>
                        <div class="item_section item_section_frist">
                            <div class="section_header">客户服务</div>
                            <div class="section_body">
                                <dl>
                                    <dt>客服电话：</dt>
                                    <dd>{{$sys_info['service_phone']}}</dd>
                                </dl>
                                <dl>
                                    <dt>客服QQ号码：</dt>
                                    <dd>
                                        @foreach($sys_info['service_qq'] as $service_qq)
                                            <a href="http://crm2.qq.com/page/portalpage/wpa.php?uin={{explode('|', $service_qq)[1]}}&aty=0&a=0&curl=&ty=1"
                                               target="_blank">{{$service_qq}}</a>
                                        @endforeach
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>问答社区：</dt>
                                    <dd><a href="{{$sys_info['qa']}}" target="_blank">{{$sys_info['qa']}}</a>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>官网：</dt>
                                    <dd><a href="{{$sys_info['www']}}" target="_blank">{{$sys_info['www']}}</a>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>交流群：</dt>
                                    <dd>{{$sys_info['qqs']}}</dd>
                                </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="contentWarp">
                <div class="section system_section">
                    <div class="system_section_con">
                        <div class="sc_title" style="border-bottom: 1px solid #e4eaec;">
                            <i class="sc-icon"></i>
                            <h3>系统信息</h3>
                        </div>
                        <div class="sc_warp">
                            <table class="table table-bordered">
                                <tr>
                                    <td class="gray_bg">服务器操作系统:</td>
                                    <td>{{$sys_info['system']}}</td>
                                    <td class="gray_bg">Web 服务器:</td>
                                    <td>{{$sys_info['server']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">PHP 版本:</td>
                                    <td>{{$sys_info['php']}}</td>
                                    <td class="gray_bg">MySQL 版本:</td>
                                    <td>{{$sys_info['mysql']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">安全模式:</td>
                                    <td>{{$sys_info['safe_mode']}}</td>
                                    <td class="gray_bg">安全模式GID:</td>
                                    <td>{{$sys_info['safe_mode_gid']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">Socket 支持:</td>
                                    <td>{{$sys_info['socket']}}</td>
                                    <td class="gray_bg">时区设置:</td>
                                    <td>{{$sys_info['timezone']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">GD 版本:</td>
                                    <td>{{$sys_info['gd']}}</td>
                                    <td class="gray_bg">Zlib 支持:</td>
                                    <td>{{$sys_info['zlib']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">编码:</td>
                                    <td>{{$sys_info['charset']}}</td>
                                    <td class="gray_bg">文件上传的最大大小:</td>
                                    <td>{{$sys_info['upload_max']}}</td>
                                </tr>
                                <tr>
                                    <td class="gray_bg">程序版本:</td>
                                    <td>{{$sys_info['program_ver']}}</td>
                                    <td class="gray_bg">安装日期:</td>
                                    <td>{{$sys_info['install_date']}}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/charts/echarts.min.js')}}"></script>
    <script src="{{url('styles/plugin/charts/macarons.js')}}"></script>
    <script>
        var orderNumOption = {
            title: {
                text: '订单统计',
                subtext: '统计'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['订单统计']
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}单'
                    }
                }
            ],
            series: [
                {
                    name: '订单统计',
                    type: 'line',
                    data: [],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                },
            ]
        };
        var saleNumOption = {
            title: {
                text: '销量统计',
                subtext: '统计'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['销量统计']
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value}元'
                    }
                }
            ],
            series: [
                {
                    name: '销量统计',
                    type: 'line',
                    data: [],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                },
            ]
        };
        var orderNumChart = echarts.init(document.getElementById('order-count'), 'macarons');
        var saleNumChart = echarts.init(document.getElementById('sale-count'), 'macarons');
        window.onresize = function () {
            changeWidth();
        };

        function changeWidth() {
            orderNumChart.resize();
            saleNumChart.resize()
        }
        function setOrderNumOption(data) {
            orderNumOption.xAxis[0].data = [];
            orderNumOption.series[0].data = [];
            $.each(data, function (k, v) {
                orderNumOption.xAxis[0].data.push(v.date);
                orderNumOption.series[0].data.push(v.count);
            });
            orderNumChart.setOption(orderNumOption, true);
        }
        function setSaleNumOption(data) {
            saleNumOption.xAxis[0].data = [];
            saleNumOption.series[0].data = [];
            $.each(data, function (k, v) {
                saleNumOption.xAxis[0].data.push(v.date);
                saleNumOption.series[0].data.push(v.count);
            });
            saleNumChart.setOption(saleNumOption, true);
        }
        function initChart() {
            $.post("{{url('admin/satistics/order/total')}}", {
                date: 'week',
                _token: '{{csrf_token()}}'
            }, function (data) {
                setOrderNumOption(data);
            });
            $.post("{{url('admin/satistics/amount/total')}}", {
                date: 'week',
                _token: '{{csrf_token()}}'
            }, function (data) {
                setSaleNumOption(data)
            });
        }

        $(function () {
            initChart();
            $('.order_total a').click(function () {
                $('.order_total a').each(function (k, v) {
                    $(v).removeClass('focus');
                });
                $(this).addClass('focus');
                var date = $(this).data('date');
                $.post("{{url('admin/satistics/order/total')}}", {
                    date: date,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    setOrderNumOption(data);
                });
            });
            $('.amount_total a').click(function () {
                $('.amount_total a').each(function (k, v) {
                    $(v).removeClass('focus');
                });
                $(this).addClass('focus');
                var date = $(this).data('date');
                $.post("{{url('admin/satistics/amount/total')}}", {
                    date: date,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    setSaleNumOption(data)
                });
            });
        });
    </script>
@endsection
@endsection
