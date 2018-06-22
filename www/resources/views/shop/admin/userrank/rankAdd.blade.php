@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">会员管理 - 添加会员等级</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>会员等级相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/userrank')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>会员等级名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="rank_name" class="form-control" value=""
                                       placeholder="会员等级名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font> 积分下限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="min_points" class="form-control" value=""
                                       placeholder="积分下限">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>积分上限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="max_points" class="form-control" value=""
                                       placeholder="积分上限">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"> </label>
                            <div class="col-sm-4">
                                <label class="label-tip">
                                    <input type="checkbox" name="show_price" value="1"
                                           class="checkbox check-all fl" checked><span
                                            class="lh26 mar-left-5">在商品详情页显示该会员等级的商品价格</span></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"> </label>
                            <div class="col-sm-4">
                                <label class="label-tip">
                                    <input type="checkbox" name="special_rank" value="1"
                                           class="checkbox check-all fl "><span
                                            class="lh26 mar-left-5">特殊会员组<font
                                                class="red">特殊会员组的会员不会随着积分的变化而变化</font></span></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">初始折扣率：</label>
                            <div class="col-sm-3">
                                <input type="text" name="discount" class="form-control" value="100"
                                       placeholder="初始折扣率">
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
        });
    </script>
@endsection
@endsection