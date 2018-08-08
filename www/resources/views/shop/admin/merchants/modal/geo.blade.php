@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div id="geo-container" tabindex="0" class="fl"></div>
        <div class="fl wd250 mar-left-20">
            <input type="text" name="keywords" value="" id="keywords"
                   class="form-control input-sm max-wd-190" placeholder="名称" autocomplete="off">
            <input type="submit" class="btn btn-primary btn-sm mar-left-10 fr search" value="查询">
        </div>
        <div class="form-group fl mar-top-20">
            <label class="col-sm-3 control-label lh30">经度：</label>
            <div class="col-sm-8">
                <input type="text" name="lngx" class="form-control input-sm"
                       value=""
                       placeholder="经度"/>
            </div>
        </div>
        <div class="form-group fl mar-top-20">
            <label class="col-sm-3 control-label lh30">纬度：</label>
            <div class="col-sm-8">
                <input type="text" name="laty" class="form-control input-sm"
                       value=""
                       placeholder="纬度"/>
            </div>
        </div>

        <div class="form-group fl mar-top-200 text-center wd250">
            <a type="button" class="btn btn-danger btn-sure"
               href="javascript:;">　确定　</a>
            <a type="button" class="btn btn-default clearfix mar-left-20 btn-close"
               href="javascript:;">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=2761558037cb710a1cebefe5ec5faacd&plugin=AMap.Autocomplete"></script>
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        var longitude;
        var latitude;
        var marker;
        var map;

        $(function () {
            get_lngxlaty(function (x, y) {
                longitude = x;
                latitude = y;
                map = new AMap.Map('geo-container', {
                    resizeEnable: true,
                    center: [longitude, latitude],
                    icon: '{{url("styles/admin/images/mark_b.png")}}',
                    zoom: 11
                });

                marker = new AMap.Marker({ //添加自定义点标记
                    map: map,
                    position: [longitude, latitude], //基点位置
                    offset: new AMap.Pixel(-10, -42), //相对于基点的偏移位置
                    draggable: false,  //是否可拖动
                    content: '<img src="{{url("styles/admin/images/mark_b.png")}}">'
                });

                //为地图注册click事件获取鼠标点击出的经纬度坐标
                var clickEventListener = map.on('click', function (e) {
                    $("input[name=lngx]").val(e.lnglat.getLng());
                    $("input[name=laty]").val(e.lnglat.getLat());
                });

                $(".search").click(function () {
                    var keywords = $("input[name=keywords]").val();
                    var auto = new AMap.Autocomplete({
                        input: "keywords"
                    });
                    //查询成功时返回查询结果
                    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
                    auto.search(keywords);
                });
                var auto = new AMap.Autocomplete({
                    input: "keywords"
                });
                AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
            });

            //给父页面传值
            $('.btn-sure').on('click', function () {
                var lngx = $("input[name=lngx]").val();
                var laty = $("input[name=laty]").val();
                parent.$("input[name=longitude]").val(lngx);
                parent.$("input[name=latitude]").val(laty);
                parent.layer.close(index);
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });

        /* 加载获取地区获取坐标 */
        function get_lngxlaty(callback) {
            var province = parent.$("select[name=province]").find("option:selected").text();
            var city = parent.$("select[name=city]").find("option:selected").text();
            var district = parent.$("select[name=district]").find("option:selected").text();
            var address = province + city + district + parent.$("input[name=shop_address]").val();
            address = address ? address : "中国";

            var mapObj = new AMap.Map('iCenter');
            mapObj.plugin(["AMap.Geocoder"], function () {     //加载地理编码插件
                MGeocoder = new AMap.Geocoder({
                    city: "中国", //城市，默认：“中国”
                    radius: 500 //范围，默认：500
                });
                //返回地理编码结果
                AMap.event.addListener(MGeocoder, "complete", function (data) {
                    var geocode = data.geocodes;
                    var lngX = geocode[0].location.getLng();
                    var latY = geocode[0].location.getLat();
                    mapObj.setCenter(new AMap.LngLat(lngX, latY));

                    $("input[name=lngx]").val(lngX);
                    $("input[name=laty]").val(latY);

                    callback(lngX, latY);
                });

                MGeocoder.getLocation(address);  //地理编码
            });
        }

        // 实例化点标记
        function addMarker(lat, lng) {
            var marker = new AMap.Marker({
                icon: '{{url("styles/admin/images/mark_b.png")}}',
                position: [lng, lat]
            });
            marker.setMap(map);
        }

        function select(e) {
            if (e.poi && e.poi.location) {
                map.setZoom(15);
                map.setCenter(e.poi.location);
                addMarker(e.poi.location.lat, e.poi.location.lng);
                $("input[name=lngx]").val(e.poi.location.lng);
                $("input[name=laty]").val(e.poi.location.lat);
            }
        }
    </script>
@endsection
@endsection