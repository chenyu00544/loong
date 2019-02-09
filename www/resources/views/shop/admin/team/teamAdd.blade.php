@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 拼团活动</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>拼团活动相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/team')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">搜索商品：</label>
                            <div class="col-sm-8">
                                <input type="text" class="keyword form-control wd-220 input-sm fl" placeholder="关键字">
                                <a href="javascript:;"
                                   class="btn btn-info input-sm btn-search fl mar-left-10"
                                   style="padding: 4px 10px;">搜索</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>拼团商品：</label>
                            <div class="col-sm-3">
                                <select name="goods_id" class="form-control input-sm">
                                    <option value="0">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>拼团名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_name" class="form-control input-sm" value=""
                                       placeholder="拼团名称" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">拼团购买时的商品价格</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">拼团价格：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_price" class="form-control input-sm" value=""
                                       placeholder="拼团价格" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">拼团购买时的商品价格</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">几人团：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_num" class="form-control input-sm" value=""
                                       placeholder="几人团" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">此商品的成团人数</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">开团有效期：</label>
                            <div class="col-sm-3">
                                <input type="text" name="validity_time" class="form-control input-sm" value=""
                                       placeholder="开团有效期" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">团长开团成功后开始倒计时(时间格式：小时，输入1~24 数字)</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">限购数量：</label>
                            <div class="col-sm-3">
                                <input type="text" name="astrict_num" class="form-control input-sm shop_price" value=""
                                       placeholder="限购数量" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">每位参团人员（包括团长）的限购数量</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">频道：</label>
                            <div class="col-sm-3">
                                <select name="tc_id" class="form-control input-sm">
                                    <option value="0">请选择</option>
                                    @foreach($teamCates as $teamCate)
                                        <option value="{{$teamCate->id}}">{{$teamCate->name}}</option>
                                        @foreach($teamCate->subCates as $subCate)
                                            <option value="{{$subCate->id}}">{{$subCate->name}}</option>
                                        @endforeach
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">已参团人数(添加虚拟数量)：</label>
                            <div class="col-sm-3">
                                <input type="text" name="limit_num" class="form-control input-sm" value="0"
                                       placeholder="已参团人数" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">拼团介绍：</div>
                            <div class="col-sm-3">
                                <textarea class="form-control fl" rows="5"
                                          name="team_desc" placeholder="拼团介绍"></textarea>
                                <div class="form-prompt"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort_order" class="form-control input-sm" value="50"
                                       placeholder="排序" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_team" value="1" checked> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_team" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">审核：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="0" checked> 未审核
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="2"> 审核通过
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="1"> 审核未通过
                                </label>
                            </div>
                        </div>
                        <div class="form-group isnot_aduit_reason" style="display: none;">
                            <div class="col-sm-4 control-label">审核未通过理由：</div>
                            <div class="col-sm-3">
                                <textarea class="form-control fl" rows="5" cols="10"
                                          name="isnot_aduit_reason" placeholder="审核未通过理由"></textarea>
                                <div class="form-prompt"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="col-sm-4">
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
                                html += '<option value="' + data.data[i].id + '" data-shop_price="' + data.data[i].shop_price + '">' + data.data[i].name +  '- 价格：' + data.data[i].shop_price + '</option>'
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

            $('input[name=is_audit]').click(function () {
                if ($(this).val() == 1) {
                    $('.isnot_aduit_reason').show();
                } else {
                    $('.isnot_aduit_reason').hide();
                }
            });
        });
    </script>
@endsection
@endsection