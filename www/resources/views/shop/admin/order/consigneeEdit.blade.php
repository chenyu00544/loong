@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">订单设置 - 编辑订单</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识“<font class="red">*</font>”的选项为必填项，其余为选填项</li>
                    <li>添加订单流程为：选择商城已有会员-选择商品加入订单-确认订单金额-填写收货信息-添加配送方式-选择支付方式-添加发票-查看费用信息-完成。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="#" method="post" class="form-horizontal"
                          enctype="multipart/form-data">

                        <input type="hidden" name="order_id" value="{{$order->order_id}}">

                        <div class="form-group">
                            <label class="col-sm-4 control-label">从已有收货地址中选择：</label>
                            <div class="col-sm-3">
                                <select name="addresslist" id="" class="form-control">
                                    <option value="0">请选择</option>
                                    @foreach($addresses as $address)
                                        <option value="{{$address->address_id}}">{{$address->$address}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>收货人：</label>
                            <div class="col-sm-3">
                                <input type="text" name="consignee" class="form-control" value="{{$order->consignee}}"
                                       placeholder="收货人" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>所在地区：</label>
                            <div class="col-sm-6">
                                @if($order->country)
                                    <div class="gallery-option fl">
                                        <select class="form-control select" name="country">
                                            <option value="0">国家</option>
                                            @foreach($regions as $region)
                                                <option value="{{$region['id']}}"
                                                        @if($order->country == $region['id']) selected @endif>{{$region['name']}}</option>
                                            @endforeach
                                        </select>
                                    </div>
                                @endif
                                @if($order->province)
                                    <div class="gallery-option fl mar-left-20">
                                        <select class="form-control select" name="province">
                                            <option value="0">省/直辖市</option>
                                            @foreach($provinces as $province)
                                                <option value="{{$province['id']}}"
                                                        @if($order->province == $province['id']) selected @endif>{{$province['name']}}</option>
                                            @endforeach
                                        </select></div>
                                @endif
                                @if($order->city)
                                    <div class="gallery-option fl mar-left-20">
                                        <select class="form-control select" name="city">
                                            <option value="0">市</option>
                                            @foreach($citys as $city)
                                                <option value="{{$city['id']}}"
                                                        @if($order->city == $city['id']) selected @endif>{{$city['name']}}</option>
                                            @endforeach
                                        </select></div>
                                @endif
                                @if($order->district)
                                    <div class="gallery-option fl mar-left-20">
                                        <select class="form-control select" name="district">
                                            <option value="0">区/县</option>
                                            @foreach($districts as $district)
                                                <option value="{{$district['id']}}"
                                                        @if($order->district == $district['id']) selected @endif>{{$district['name']}}</option>
                                            @endforeach
                                        </select></div>
                                @endif
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">电子邮件：</label>
                            <div class="col-sm-3">
                                <input type="text" name="email" class="form-control" value="{{$order->email}}"
                                       placeholder="电子邮件" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>收货地址：</label>
                            <div class="col-sm-3">
                                <input type="text" name="address" class="form-control" value="{{$order->address}}"
                                       placeholder="收货地址" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮政编码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="zipcode" class="form-control" value="{{$order->zipcode}}"
                                       placeholder="邮政编码" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">电话号码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="tel" class="form-control" value="{{$order->tel}}"
                                       placeholder="电话号码" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>手机号码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="mobile" class="form-control" value="{{$order->mobile}}"
                                       placeholder="手机号码" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">地址别名：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sign_building" class="form-control"
                                       value="{{$order->sign_building}}"
                                       placeholder="地址别名" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">送货时间：</label>
                            <div class="col-sm-3">
                                <input type="text" name="best_time" class="form-control" value="{{$order->best_time}}"
                                       placeholder="送货时间" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <a type="button" class="btn btn-danger btn-sure"> 确定 </a>
                                <a type="button" class="btn btn-default mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
                            </div>
                        </div>
                    </form>
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
            $('.btn-sure').on('click', function () {
                var order_id = $('input[name=order_id]').val();
                var data = $('.form-horizontal').serializeArray();
                $.post(
                    '{{url("admin/order/change")}}',
                    {
                        id: order_id,
                        type: 'consignee',
                        address: data,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        setTimeout(function () {
                            location.href = "{{url('admin/order/')}}/" + order_id + "/edit";
                        }, 2000);
                    }
                );
            });

            $('select[name=country]').on('change', function () {
                var parent = $(this).val();
                var provinceHtml = '<option value="0">省/直辖市</option>', cityHtml = '<option value="0">市</option>',
                    districtHtml = '<option value="0">区/县</option>';
                $.post("{{url('admin/regions/nextall')}}", {
                    type: 1,
                    parent: parent,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            for(var j = 0; j < data[i].length; j++){
                                provinceHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        } else if (i == 1) {
                            for(var j = 0; j < data[i].length; j++){
                                cityHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        } else if (i == 2) {
                            for(var j = 0; j < data[i].length; j++){
                                districtHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        }
                    }
                    $('select[name=province]').html(provinceHtml);
                    $('select[name=city]').html(cityHtml);
                    $('select[name=district]').html(districtHtml);
                });
            });
            $('select[name=province]').on('change', function () {
                var parent = $(this).val();
                var cityHtml = '<option value="0">市</option>',
                    districtHtml = '<option value="0">区/县</option>';
                $.post("{{url('admin/regions/nextall')}}", {
                    type: 2,
                    parent: parent,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            for(var j = 0; j < data[i].length; j++){
                                cityHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        } else if (i == 1) {
                            for(var j = 0; j < data[i].length; j++){
                                districtHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        }
                    }
                    $('select[name=city]').html(cityHtml);
                    $('select[name=district]').html(districtHtml);
                });
            });
            $('select[name=city]').on('change', function () {
                var parent = $(this).val();
                var districtHtml = '<option value="0">区/县</option>';
                $.post("{{url('admin/regions/nextall')}}", {
                    type: 3,
                    parent: parent,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            for(var j = 0; j < data[i].length; j++){
                                districtHtml += '<option value="' + data[i][j].region_id + '">' + data[i][j].region_name + '</option>';
                            }
                        }
                    }
                    $('select[name=district]').html(districtHtml);
                });
            });
        });
    </script>
@endsection
@endsection