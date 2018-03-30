@extends('shop.layouts.layinfo')

@section('content')
<body class="iframe_body">
<div class="warpper">
    <div class="title">管理中心</div>
    <div class="content start_content">
        <div class="contentWarp">
            <div class="explanation" id="explanation">
                <div class="ex_tit"><h4>系统检测到您有影响商城运营的基本配置信息尚未配置：</h4></div>
                <div class="ex_con">
                    <span>短信配置尚未配置，<a href="sms_setting.php?act=step_up">前往配置</a></span>
                    <span>邮件配置尚未配置，<a href="shop_config.php?act=mail_settings">前往配置</a></span>
                    <span>支付方式尚未配置，<a href="payment.php?act=list">前往配置</a></span>
                    <span>阿里OSS尚未配置，<a href="oss_configure.php?act=list">前往配置</a></span>
                </div>
            </div>
        </div>
        <div class="contentWarp">
            <div class="contentWarp_item clearfix">
                <div class="section_select">
                    <div class="item item_price">
                        <i class="icon"><img src="{{url('styles/admin/images/1.png')}}" width="71" height="74" /></i>
                        <div class="desc">
                            <div class="tit"></div>
                            <span>今日销售总额</span>
                        </div>
                    </div>
                    <div class="item item_order">
                        <i class="icon"><img src="{{url('styles/admin/images/2.png')}}" /></i>
                        <div class="desc">
                            <div class="tit"></div>
                            <span>今日订单总数</span>
                        </div>
                        <i class="icon"></i>
                    </div>
                    <div class="item item_comment">
                        <i class="icon"><img src="{{url('styles/admin/images/3.png')}}" width="90" height="86" /></i>
                        <div class="desc">
                            <div class="tit"></div>
                            <span>今日评论数</span>
                        </div>
                    </div>
                    <div class="item item_flow">
                        <i class="icon"><img src="{{url('styles/admin/images/4.png')}}" width="86" /></i>
                        <div class="desc">
                            <div class="tit"></div>
                            <span>店铺数量</span>
                        </div>
                        <i class="icon"></i>
                    </div>
                </div>
                <div class="section user_section">
                    <div class="sc_title">
                        <i class="sc_icon"></i>
                        <h3>个人会员信息</h3>
                        <cite>（单位：个）</cite>
                    </div>
                    <div class="sc_warp">
                        <div class="user_item user_today_new">
                            <div class="num"></div>
                            <span class="tit">今日新增</span>
                        </div>
                        <div class="user_item user_yest_new">
                            <div class="num"></div>
                            <span class="tit">昨日新增</span>
                        </div>
                        <div class="user_item user_month_new">
                            <div class="num"></div>
                            <span class="tit">本月新增</span>
                        </div>
                        <div class="user_item user_total">
                            <div class="num"></div>
                            <span class="tit">会员总数</span>
                        </div>
                    </div>
                </div>
                <div class="section goods_section">
                    <div class="sc_title">
                        <i class="sc_icon"></i>
                        <h3>商品一览</h3>
                        <cite>（单位：件）</cite>
                    </div>
                    <div class="sc_warp">
                        <div class="goods_item">
                            <div class="tit">自营商品</div>
                            <div class="number">
                                <div class="st">实体：<a href="goods.php?act=list&self=1"></a></div>
                                <div class="xn">虚拟：<a href="goods.php?act=list&extension_code=virtual_card&self=1"></a></div>
                            </div>
                        </div>
                        <div class="goods_item">
                            <div class="tit">商家商品</div>
                            <div class="number">
                                <div class="st">实体：<a href="goods.php?act=list&merchants=1"></a></div>
                                <div class="xn">虚拟：<a href="goods.php?act=list&extension_code=virtual_card&merchants=1"></a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contentWarp_item clearfix">
                <div class="section_order_select">
                    <ul>
                        <li>
                            <a href="order.php?act=list&composite_status={$status.unconfirmed}&source=start">
                                <i class="ice ice_w"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="order.php?act=list&composite_status={$status.await_pay}&source=start">
                                <i class="ice ice_d"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="order.php?act=list&composite_status={$status.await_ship}&source=start">
                                <i class="ice ice_n"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="order.php?act=list&composite_status={$status.finished}&source=start">
                                <i class="ice ice_f"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="goods_booking.php?act=list_all">
                                <i class="ice ice_y"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="order.php?act=list&composite_status={$status.shipped_part}&source=start">
                                <i class="ice ice_q"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                        <li>
                            <a href="user_account.php?act=list&process_type=1&is_paid=0">
                                <i class="ice ice_b"></i>
                                <div class="t"></div>
                                <span class="number"></span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="clear"></div>
                <div class="section section_order_count">
                    <div class="sc_title">
                        <i class="sc_icon"></i>
                        <h3>订单统计</h3>
                        <div class="filter_date">
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'order', 'week')">七天</a>
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'order', 'month')">一月</a>
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'order', 'year')">半年</a>
                        </div>
                    </div>
                    <div class="sc_warp">
                        <div id="order_main" style="height:274px;"></div>
                    </div>
                </div>
                <div class="section section_total_count">
                    <div class="sc_title">
                        <i class="sc_icon"></i>
                        <h3>销售统计</h3>
                        <div class="filter_date">
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'sale', 'week')">七天</a>
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'sale', 'month')">一月</a>
                            <a href="javascript:;" onclick="set_statistical_chart(this, 'sale', 'year')">半年</a>
                        </div>
                    </div>
                    <div class="sc_warp">
                        <div id="total_main" style="height:274px;"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="contentWarp bf100">
            <div class="section col_section">
                <div class="sc_title">
                    <i class="sc_icon"></i>
                    <h3>控制面板</h3>
                </div>
                <div class="sc_warp">
                    <div class="item_section item_section_frist">
                        <div class="section_header">商城管理</div>
                        <div class="section_body">
                            <dl>
                                <dt>商城首页：</dt>
                                <dd><a href="" target="_blank"></a></dd>
                            </dl>
                            <dl>
                                <dt>平台后台：</dt>
                                <dd><a href="" target="_blank"></a></dd>
                            </dl>
                            <dl>
                                <dt>商家后台：</dt>
                                <dd><a href="" target="_blank"></a></dd>
                            </dl>
                            <dl>
                                <dt>门店后台：</dt>
                                <dd><a href="" target="_blank"></a></dd>
                            </dl>
                            <dl>
                                <dt>WAP首页：</dt>
                                <dd><a href="" target="_blank"></a></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="contentWarp">
            <div class="section system_section w190">
                <div class="system_section_con">
                    <div class="sc_title">
                        <i class="sc_icon"></i>
                        <h3>系统信息</h3>
                        <span class="stop stop_jia" title="展开详情"></span>
                    </div>
                    <div class="sc_warp">
                        <table cellpadding="0" cellspacing="0" class="system_table">
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="gray_bg"></td>
                                <td></td>
                                <td class="gray_bg"></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

@component('shop.components.footer')@endcomponent
<script type="text/javascript" src="{{url('styles/admin/js/echarts-all.js')}}"></script>
<script type="text/javascript" src="{{url('styles/admin/js/jquery.purebox.js')}}"></script>
<script type="text/javascript" src="{{url('styles/admin/js/jquery.picTip.js')}}"></script>
<script type="text/javascript">
    if($(".section_order_count").length > 0){
        set_statistical_chart(".section_order_count .filter_date a:first", "order", "week"); //初始设置
    }
    if($(".section_total_count").length > 0){
        set_statistical_chart(".section_total_count .filter_date a:first", "sale", "week"); //初始设置
    }
    function set_statistical_chart(obj, type, date)
    {
        var obj = $(obj);
        obj.addClass("active");
        obj.siblings().removeClass("active");

        $.ajax({
            type:'get',
            url:'index.php',
            data:'act=set_statistical_chart&type='+type+'&date='+date,
            dataType:'json',
            success:function(data){
                if(type == 'order'){
                    var div_id = "order_main";
                }
                if(type == 'sale'){
                    var div_id = "total_main";
                }
                var myChart = echarts.init(document.getElementById(div_id));
                myChart.setOption(data);
            }
        })
    }

    //展开收起系统信息
    $.upDown(".stop",".sc_title",".system_section",73);

    $(function(){
        if($.cookie('adminStartHome') == null){
            var content = $("*[ectype='guide_dialog']").html();
            pb({
                id:"guide_dialog",
                title:"新手向导",
                width:960,
                height:550,
                content:content,
                drag:false,
                foot:false
            });

            $("#guide_dialog .guide_list").perfectScrollbar("destroy");
            $("#guide_dialog .guide_list").perfectScrollbar();

            $("*[ectype='btnNext']").on("click",function(){
                if(!$(this).hasClass("btn_disabled")){
                    var type = $(this).parents(".guide_btn").data("type");
                    var g_con = $(this).parents("*[ectype='guide_content']");

                    g_con.find(".guide_step .item").eq(type+1).addClass("current").siblings().removeClass("current");
                    g_con.find(".guide_list .guide_item").eq(type+1).show().siblings().hide();

                    $(this).parents(".guide_btn").data("type",type+1);
                    $(this).siblings("*[ectype='btnPrev']").removeClass("btn_disabled");

                    if(type == 2){
                        $(this).addClass("btn_disabled");
                        $(this).html("好的，我知道了");
                    }else{
                        $(this).removeClass("btn_disabled");
                        $(this).html("了解了，下一步");
                    }

                    $("#guide_dialog .guide_list").perfectScrollbar("destroy");
                    $("#guide_dialog .guide_list").perfectScrollbar();
                }else{
                    $("#guide_dialog,#pb-mask").remove();

                }
            });

            $("*[ectype='btnPrev']").on("click",function(){
                if(!$(this).hasClass("btn_disabled")){
                    var type = $(this).parents(".guide_btn").data("type");
                    var g_con = $(this).parents("*[ectype='guide_content']");

                    g_con.find(".guide_step .item").eq(type-1).addClass("current").siblings().removeClass("current");
                    g_con.find(".guide_list .guide_item").eq(type-1).show().siblings().hide();

                    $(this).parents(".guide_btn").data("type",type-1);
                    $(this).siblings("*[ectype='btnNext']").removeClass("btn_disabled");
                    $(this).siblings("*[ectype='btnNext']").html("了解了，下一步");

                    if(type == 1){
                        $(this).addClass("btn_disabled");
                    }else{
                        $(this).removeClass("btn_disabled");
                    }

                    $("#guide_dialog .guide_list").perfectScrollbar("destroy");
                    $("#guide_dialog .guide_list").perfectScrollbar();
                }
            });

            //生成cookie
            $.cookie('adminStartHome','cookieValue', { expires: 1 ,path:'/'});
        };
    });
</script>
</body>
@endsection
