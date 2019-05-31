@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
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
                    <form name="brand" action="{{url('admin/team/'.$team->id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>拼团商品：</label>
                            <div class="col-sm-3">
                                <select name="goods_id" class="form-control input-sm">
                                    <option value="{{$team->goods_id}}" selected>{{$team->goods->goods_name}} - 价格：{{$team->goods->shop_price}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>拼团名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_name" class="form-control input-sm"
                                       value="{{$team->team_name}}"
                                       placeholder="拼团名称" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">拼团购买时的商品价格</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">拼团价格：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_price" class="form-control input-sm"
                                       value="{{$team->team_price}}"
                                       placeholder="拼团价格" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">拼团购买时的商品价格</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">几人团：</label>
                            <div class="col-sm-3">
                                <input type="text" name="team_num" class="form-control input-sm"
                                       value="{{$team->team_num}}"
                                       placeholder="几人团" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">此商品的成团人数</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">开团有效期：</label>
                            <div class="col-sm-3">
                                <input type="text" name="validity_time" class="form-control input-sm"
                                       value="{{$team->validity_time}}"
                                       placeholder="开团有效期" autocomplete="off">
                            </div>
                            <div class="notic fl mar-left-5">团长开团成功后开始倒计时(时间格式：小时，输入1~24 数字)</div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">限购数量：</label>
                            <div class="col-sm-3">
                                <input type="text" name="astrict_num" class="form-control input-sm shop_price"
                                       value="{{$team->astrict_num}}"
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
                                        <option value="{{$teamCate->id}}" @if($teamCate->id == $team->tc_id) selected @endif>{{$teamCate->name}}</option>
                                        @foreach($teamCate->subCates as $subCate)
                                            <option value="{{$subCate->id}}"
                                                    @if($subCate->id == $team->tc_id) selected @endif>{{$subCate->name}}</option>
                                        @endforeach
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">已参团人数(添加虚拟数量)：</label>
                            <div class="col-sm-3">
                                <input type="text" name="limit_num" class="form-control input-sm"
                                       value="{{$team->limit_num}}"
                                       placeholder="已参团人数" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">拼团介绍：</div>
                            <div class="col-sm-3">
                                <textarea class="form-control fl" rows="5"
                                          name="team_desc" placeholder="拼团介绍">{{$team->team_desc}}</textarea>
                                <div class="form-prompt"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort_order" class="form-control input-sm"
                                       value="{{$team->sort_order}}"
                                       placeholder="排序" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_team" value="1"
                                           @if($team->is_team == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_team" value="0"
                                           @if($team->is_team == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">审核：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="0"
                                           @if($team->is_audit == 0) checked @endif> 未审核
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="2"
                                           @if($team->is_audit == 2) checked @endif> 审核通过
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_audit" value="1"
                                           @if($team->is_audit == 1) checked @endif> 审核未通过
                                </label>
                            </div>
                        </div>
                        <div class="form-group isnot_aduit_reason" style="display: none;">
                            <div class="col-sm-4 control-label">审核未通过理由：</div>
                            <div class="col-sm-3">
                                <textarea class="form-control fl" rows="5" cols="10"
                                          name="isnot_aduit_reason"
                                          placeholder="审核未通过理由">{{$team->isnot_aduit_reason}}</textarea>
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