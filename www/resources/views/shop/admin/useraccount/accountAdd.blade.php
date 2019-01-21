@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>会员管理 - 添加申请</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>资金信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/uaccount')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>会员名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="username" class="form-control" value=""
                                       placeholder="会员名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>金额：</label>
                            <div class="col-sm-3">
                                <input type="text" name="amount" class="form-control" value=""
                                       placeholder="金额">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">类型：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="process_type" value="0" checked> 充值
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="process_type" value="1"> 提现
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">支付方式：</label>
                            <div class="col-sm-2">
                                <select name="pay_id" class="form-control">
                                    <option value="0">请选择</option>
                                    @foreach($payments as $payment)
                                        <option value="{{$payment['pay_id']}}">{{$payment['pay_name']}}
                                            -费率{{$payment['pay_fee']}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">管理员备注：</label>
                            <div class="col-sm-4">
                                <textarea name="admin_note" id="" class="form-control" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">会员描述：</label>
                            <div class="col-sm-4">
                                <textarea name="user_note" id="" class="form-control" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">到款状态：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_paid" value="0" checked> 未确认
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_paid" value="1"> 已完成
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_paid" value="2"> 取消
                                </label>
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
        });
    </script>
@endsection
@endsection