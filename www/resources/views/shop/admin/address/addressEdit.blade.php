@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>地址管理 - 编辑地址</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/address/'.$address->address_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>收货人姓名：</label>
                            <div class="col-sm-3">
                                <input type="text" name="consignee" class="form-control" value="{{$address->consignee}}"
                                       placeholder="邮件地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">配送区域：</label>
                            <div class="col-sm-1">
                                <select name="province" class="form-control">
                                    @foreach($regions as $region)
                                        <option value="{{$region['id']}}"
                                                @if($region['id'] == $address->province) selected @endif>{{$region['name']}}</option>
                                    @endforeach
                                </select>
                            </div>
                            <div class="col-sm-1">
                                <select name="city" class="form-control">
                                    @foreach($c_regions as $c_region)
                                        <option value="{{$c_region['id']}}"
                                                @if($c_region['id'] == $address->city) selected @endif>{{$c_region['name']}}</option>
                                    @endforeach
                                </select>
                            </div>
                            <div class="col-sm-1">
                                <select name="district" class="form-control">
                                    @foreach($d_regions as $d_region)
                                        <option value="{{$d_region['id']}}"
                                                @if($d_region['id'] == $address->district) selected @endif>{{$d_region['name']}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">详细地址：</label>
                            <div class="col-sm-3">
                                <input type="text" name="address" class="form-control" value="{{$address->address}}"
                                       placeholder="详细地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">电话：</label>
                            <div class="col-sm-3">
                                <input type="text" name="tel" class="form-control"
                                       value="{{$address->tel}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">手机：</label>
                            <div class="col-sm-3">
                                <input type="text" name="mobile" class="form-control"
                                       value="{{$address->mobile}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">电子邮件：</label>
                            <div class="col-sm-3">
                                <input type="text" name="email" class="form-control"
                                       value="{{$address->email}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮政编码：</label>
                            <div class="col-sm-3">
                                <input type="text" name="zipcode" class="form-control"
                                       value="{{$address->zipcode}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">标志建筑：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sign_building" class="form-control"
                                       value="{{$address->sign_building}}" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">最佳送货时间：</label>
                            <div class="col-sm-3">
                                <input type="text" name="best_time" class="form-control"
                                       value="{{$address->best_time}}" autocomplete="off">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
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
            $('select[name=province]').on('change', function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 2, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('select[name=city]').html($html);
                    }
                    var parent = $('select[name=city]').val();
                    $.post("{{url('api/region/getCountries')}}", {type: 3, parent: parent}, function (data) {
                        if (data.data.length > 0) {
                            $html = '';
                            $.each(data.data, function (k, v) {
                                $html += '<option value="' + v.id + '">' + v.name + '</option>';
                            });
                            $('select[name=district]').html($html);
                        }
                    });
                });
            });
            $('select[name=city]').on('change', function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 3, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('select[name=district]').html($html);
                    }
                });
            })
        });
    </script>
@endsection
@endsection