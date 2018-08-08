@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">自营 - 基本信息设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>其中部分店铺信息需要其他地方先配置，比如配送方式等。</li>
                    <li>请准确无误的设置店铺信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" name="conf" action="{{url('admin/shopconf/'.$self->id)}}"
                          method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">店铺名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="shop_name" class="form-control input-sm"
                                       value="{{$self->shop_name}}"
                                       placeholder="店铺名称"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">商店标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="shop_title" class="form-control input-sm"
                                       value="{{$self->shop_title}}"
                                       placeholder="商店标题"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">店铺关键字：</label>
                            <div class="col-sm-4">
                                <input type="text" name="shop_keyword" class="form-control input-sm"
                                       value="{{$self->shop_keyword}}"
                                       placeholder="店铺关键字"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">店铺二级域名：</label>
                            <div class="col-sm-4">
                                <input type="text" name="domain_name" class="form-control input-sm"
                                       value="{{!empty($info->domain_name)?$info->domain_name->domain_name:''}}"
                                       placeholder="店铺二级域名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">所在国家：</label>
                            <div class="col-sm-4">
                                <select name="country" class="form-control input-sm shop_country">
                                    <option value="0">国家</option>
                                    @foreach($self->shop_country as $country)
                                        <option value="{{$country->region_id}}"
                                                @if($self->country == $country->region_id) selected @endif>{{$country->region_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">所在省份：</label>
                            <div class="col-sm-4">
                                <select name="province" class="form-control input-sm shop_province">
                                    <option value="0">省/直辖市</option>
                                    @foreach($self->shop_province as $province)
                                        <option value="{{$province->region_id}}"
                                                @if($self->province == $province->region_id) selected @endif>{{$province->region_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">所在城市：</label>
                            <div class="col-sm-4">
                                <select name="city" class="form-control input-sm shop_city">
                                    <option value="0">市</option>
                                    @foreach($self->shop_city as $city)
                                        <option value="{{$city->region_id}}"
                                                @if($self->city == $city->region_id) selected @endif>{{$city->region_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">所在区域：</label>
                            <div class="col-sm-4">
                                <select name="district" class="form-control input-sm shop_district">
                                    <option value="0">区/县</option>
                                    @foreach($self->shop_district as $district)
                                        <option value="{{$district->region_id}}"
                                                @if($self->district == $district->region_id) selected @endif>{{$district->region_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">详细地址：</label>
                            <div class="col-sm-4">
                                <input type="text" name="shop_address" class="form-control input-sm"
                                       value="{{$self->shop_address}}" placeholder="详细地址"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">配送方式：</label>
                            <div class="col-sm-4">
                                <select name="shipping_id" class="form-control input-sm">
                                    <option value="0">请选择</option>
                                    @foreach($self->shippings as $shipping)
                                        <option value="{{$shipping->shipping_id}}"
                                                @if($self->shipping_id == $shipping->shipping_id) selected @endif>{{$shipping->shipping_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服手机号码：</label>
                            <div class="col-sm-4">
                                <input type="text" name="mobile" class="form-control input-sm"
                                       value="{{$self->mobile}}" placeholder="客服手机号码"/>
                            </div>
                            <div class="notic col-sm-3">店铺接受平台所发送的短信信息手机号，比如：客户下订单时给商家发短信</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服邮件地址：</label>
                            <div class="col-sm-4">
                                <input type="text" name="seller_email" class="form-control input-sm"
                                       value="{{$self->seller_email}}" placeholder="客服邮件地址"/>
                            </div>
                            <div class="notic col-sm-3">店铺接受平台所发送的邮箱信息，比如：客户下单时给商家发邮件</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服QQ号码：</label>
                            <div class="col-sm-4">
                                <textarea name="kf_qq" class="form-control" row="5"
                                          placeholder="客服QQ号码">{{$self->kf_qq}}</textarea>
                            </div>
                            <div class="notic col-sm-3">QQ客服名称和号码请用“|”隔开（如：客服1|123456），如果您有多个客服的QQ号码，请换行。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服淘宝旺旺：</label>
                            <div class="col-sm-4">
                                <textarea name="kf_ww" class="form-control" row="5"
                                          placeholder="客服淘宝旺旺">{{$self->kf_ww}}</textarea>
                            </div>
                            <div class="notic col-sm-3">旺旺客服名称和号码请用“|”隔开（如：客服2|654321），如果您有多个客服的旺旺号码，请换行。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否开启IM在线客服：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="kf_im_switch" value="0"
                                           @if($self->kf_im_switch == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="kf_im_switch" value="1"
                                           @if($self->kf_im_switch == 1) checked @endif> 是
                                </label>
                            </div>
                            <div class="notic col-sm-3">开启后即可使用IM在线客服,否则只能使用默认的 (平台)</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">在线客服账号：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_touid" class="form-control input-sm"
                                       value="{{$self->kf_touid}}" placeholder="在线客服账号"/>
                            </div>
                            <div class="notic col-sm-3">在<a target="_blank"
                                                            href="http://my.open.taobao.com/app/app_list.htm">
                                    淘宝开放平台 </a>已开通云旺客服的账号。
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">在线客服appkey：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_appkey" class="form-control input-sm"
                                       value="{{$self->kf_appkey}}" placeholder="在线客服appkey"/>
                            </div>
                            <div class="notic col-sm-3">在淘宝开放平台创建一个应用(百川无线)即可获得appkey</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">在线客服secretkey：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_secretkey" class="form-control input-sm"
                                       value="{{$self->kf_secretkey}}" placeholder="在线客服secretkey"/>
                            </div>
                            <div class="notic col-sm-3">在淘宝开放平台创建一个应用(百川无线)即可获得secretkey</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">在线客服头像LOGO：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_logo" class="form-control input-sm"
                                       value="{{$self->kf_logo}}" placeholder="在线客服头像LOGO"/>
                            </div>
                            <div class="notic col-sm-3">直接黏贴图片网址(推荐40 x 40),不填即使用默认头像。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">在线客服欢迎信息：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_welcomeMsg" class="form-control input-sm"
                                       value="{{$self->kf_welcomeMsg}}" placeholder="在线客服欢迎信息"/>
                            </div>
                            <div class="notic col-sm-3">向用户发送的一条欢迎信息。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">美洽客服：</label>
                            <div class="col-sm-4">
                                <input type="text" name="meiqia" class="form-control input-sm"
                                       value="{{$self->meiqia}}" placeholder="美洽客服"/>
                            </div>
                            <div class="notic col-sm-3">此功能仅手机端（wap）使用。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服电话：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kf_tel" class="form-control input-sm"
                                       value="{{$self->kf_tel}}" placeholder="客服电话"/>
                            </div>
                            <div class="notic col-sm-3">店铺联系客服号码。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">客服样式：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="kf_type" value="0"
                                           @if($self->kf_type == 0) checked @endif> QQ客服
                                </label><label class="radio-inline fl">
                                    <input type="radio" name="kf_type" value="1"
                                           @if($self->kf_type == 1) checked @endif> 旺旺客服
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">扫码appkey（极速数据）：</label>
                            <div class="col-sm-4">
                                <input type="text" name="js_appkey" class="form-control input-sm"
                                       value="{{$self->js_appkey}}" placeholder="扫码appkey"/>
                            </div>
                            <div class="notic col-sm-3">在<a target="_blank" href="http://www.jisuapi.com/api/barcode2/">
                                    极速数据 </a>申请账号。
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">扫码appsecret（极速数据）：</label>
                            <div class="col-sm-4">
                                <input type="text" name="js_appsecret" class="form-control input-sm"
                                       value="{{$self->js_appsecret}}" placeholder="扫码appsecret"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">快递单打印方式：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="print_type" value="0"
                                           @if($self->print_type == 0) checked @endif> 系统默认
                                </label><label class="radio-inline fl">
                                    <input type="radio" name="print_type" value="1"
                                           @if($self->print_type == 1) checked @endif> 快递鸟
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">快递单打印机（快递鸟）：</label>
                            <div class="col-sm-4">
                                <input type="text" name="kdniao_printer" class="form-control input-sm"
                                       value="{{$self->kdniao_printer}}" placeholder="快递单打印机"/>
                            </div>
                            <div class="notic col-sm-3">仅在设置打印方式为“快递鸟”时需要设置该项。</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">店铺公告：</label>
                            <div class="col-sm-4">
                                    <textarea name="notice" class="form-control" row="5"
                                              placeholder="店铺公告">{{$self->notice}}</textarea>
                            </div>
                        </div>

                        <div class="item">
                            <div class="label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　"
                                       class="btn btn-danger clearfix">
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
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 1, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_province').html($html);
                    }
                });
            });

            $('.shop_province').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 2, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_city').html($html);
                    }
                });
            });

            $('.shop_city').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 3, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_district').html($html);
                    }
                });
            })

        });
    </script>
@endsection
@endsection