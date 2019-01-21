@extends('shop.layouts.index')
@section('css')
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.css')}}">
@endsection
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 团购活动</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>团购活动相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/groupbuy/'.$group->act_id)}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>商品名称：</label>
                            <div class="col-sm-4">
                                <select class="form-control input-sm">
                                    <option value="0">请选择</option>
                                    <option value="{{$group->act_name}}" selected>{{$group->act_name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>起止日期：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="use_start_end_date"
                                       id="use_start_end_date" class="form-control input-sm"
                                       value="{{$group->date_format}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">保证金：</label>
                            <div class="col-sm-2">
                                <input type="text" name="deposit" class="form-control input-sm"
                                       value="{{$group->ext_info['deposit']}}"
                                       placeholder="保证金" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-10">购买该商品时赠送消费积分数,-1表示按商品价格赠送</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">限购数量：</label>
                            <div class="col-sm-2">
                                <input type="text" name="restrict_amount" class="form-control input-sm"
                                       value="{{$group->ext_info['restrict_amount']}}"
                                       placeholder="限购数量" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">赠送积分数：</label>
                            <div class="col-sm-2">
                                <input type="text" name="gift_integral" class="form-control input-sm"
                                       value="{{$group->ext_info['gift_integral']}}"
                                       placeholder="限购数量" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">市场售价：</label>
                            <div class="col-sm-1">
                                <input type="text" class="form-control input-sm shop_price"
                                       value="{{$group->goods->shop_price}}" disabled>
                                <input type="hidden" name="goods_name" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label"><b>*</b>阶梯价格：</div>
                            <div class="col-sm-8">
                                <div class="is-volume-div">
                                    <table class="table table-bordered volume-price" style="width: auto;">
                                        <tbody>
                                        <tr class="first-tr">
                                            <td class="text-center">开团数量</td>
                                            @foreach($group->ext_info['price_ladder'] as $price_ladder)
                                                <td>
                                                    <input type="text" name="volume_number[]"
                                                           value="{{$price_ladder['amount']}}"
                                                           class="form-control max-wd-100" autocomplete="off">
                                                </td>
                                            @endforeach
                                            <td class="" rowspan="2">
                                                <a href="javascript:;" class="add-v-p"></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="text-center">享受价格</td>
                                            @foreach($group->ext_info['price_ladder'] as $price_ladder)
                                                <td>
                                                    <input type="text" name="volume_price[]"
                                                           value="{{$price_ladder['price']}}"
                                                           class="form-control max-wd-100"
                                                           autocomplete="off">
                                                </td>
                                            @endforeach
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">活动说明：</div>
                            <div class="col-sm-4">
                                            <textarea class="form-control max-wd-350 fl" rows="5"
                                                      name="seller_note">{{$group->act_desc}}</textarea>
                                <div class="form-prompt"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">新品：</div>
                            <div class="col-sm-4">
                                <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                    <div class="switch @if($group->is_new) active @endif" data-type="is_on_sale"
                                         title="是">
                                        <div class="circle"></div>
                                        <input type="hidden" value="{{$group->is_new}}" name="is_new">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">热销：</div>
                            <div class="col-sm-4">
                                <div class="switch-wrap clearfix fl" style="margin: 5px 0;">
                                    <div class="switch @if($group->is_hot) active @endif" data-type="is_on_sale"
                                         title="是">
                                        <div class="circle"></div>
                                        <input type="hidden" value="{{$group->is_hot}}" name="is_hot">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="col-sm-5">
                                <input type="submit" value="　确定　" class="btn btn-danger">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
                                @if($group->end_time > time())
                                    <div class="mar-right-20 fr">活动还未结束，要马上<a type="button" data-id="{{$group->act_id}}"
                                                                              class="btn btn-danger over_faat"
                                                                              href="javascript:;">结束活动</a>吗?
                                    </div>
                                @endif
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

            $('#use_start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm');
                var t = s + '～' + e + ':59';
                $('#use_start_end_date').val(t);
            });

            $('.btn-search').on('click', function () {
                var keywords = $('.keyword').val();
                if (keywords != '') {
                    $.post("{{url('admin/search')}}", {
                        keywords: keywords,
                        type: 3,
                        '_token': '{{csrf_token()}}'
                    }, function (data) {
                        var html = '<option value="0">请选择</option>';
                        if (data.code == 1) {
                            for (var i in data.data) {
                                html += '<option value="' + data.data[i].id + '" data-shop_price="' + data.data[i].shop_price + '">' + data.data[i].name + '</option>'
                            }
                        } else {
                            layer.msg(data.msg, {icon: data.code});
                            html += '<option value="0" selected>没有搜索到相关记录，请重新搜索</option>'
                        }
                        $('select[name=goods_id]').html(html)
                    })
                } else {
                    layer.msg('请输入关键字', {icon: 5});
                }
            });

            $('select[name=goods_id]').change(function () {
                $('.shop_price').val($(this).find('option:selected').data('shop_price'));
                $('input[name=goods_name]').val($(this).find('option:selected').html());
            });

            $('.is-volume-div').on('click', '.add-v-p', function () {
                var tbody = $(this).parent().parent().parent();
                var html = '<td><input type="text" name="volume_number[]" value="0"' +
                    'class="form-control max-wd-100" autocomplete="off" placeholder="数量"></td>';
                var html1 = '<td><input type="text" name="volume_price[]" value="0" class="form-control max-wd-100" autocomplete="off"  placeholder="价格"></td>';
                tbody.find('tr').each(function (k, v) {
                    if (k == 0) {
                        $(v).find('td').last().before(html);
                    } else if (k == 1) {
                        $(v).append(html1);
                    }
                })
            });

            //开关
            $('.switch').on('click', function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }
                $(this).find('input').val(val);
            });

            $('.over_faat').click(function () {
                var id = $(this).data('id');
                $.post("{{url('admin/groupbuy/change')}}", {
                    _token: '{{csrf_token()}}',
                    id: id,
                    type: "is_finish"
                }, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                })
            });
        });
    </script>
@endsection
@endsection