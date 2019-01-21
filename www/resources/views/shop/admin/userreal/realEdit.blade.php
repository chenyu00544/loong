@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>会员管理 - 实名详情</div>
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
                    <form name="conf" action="{{url('admin/usersreal/'.$userReal->real_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>会员名称：</label>
                            <div class="col-sm-4">
                                <div class="pad-top-7">{{$userReal->user_name}}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>真实姓名：</label>
                            <div class="col-sm-4">
                                <input type="text" name="real_name" class="form-control"
                                       value="{{$userReal->real_name}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>身份证号：</label>
                            <div class="col-sm-4">
                                <input type="text" name="self_num" class="form-control"
                                       value="{{$userReal->self_num}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">银行名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="bank_name" class="form-control"
                                       value="{{$userReal->bank_name}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">银行卡号：</label>
                            <div class="col-sm-4">
                                <input type="text" name="bank_card" class="form-control"
                                       value="{{$userReal->bank_card}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">手机号码：</label>
                            <div class="col-sm-4">
                                <input type="text" name="bank_mobile" class="form-control"
                                       value="{{$userReal->bank_mobile}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">身份证正面：</label>
                            <div class="col-sm-2 pad-top-4">
                                <a href="{{url($userReal->front_of_id_card)}}" class="nyroModal">
                                    <i class="glyphicon glyphicon-picture top2"
                                       data-tooltipimg="{{url($userReal->front_of_id_card)}}" ctype="tooltip"
                                       title="tooltip"></i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">身份证反面：</label>
                            <div class="col-sm-2 pad-top-4">
                                <a href="{{url($userReal->reverse_of_id_card)}}" class="nyroModal">
                                    <i class="glyphicon glyphicon-picture top2"
                                       data-tooltipimg="{{url($userReal->reverse_of_id_card)}}" ctype="tooltip"
                                       title="tooltip"></i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">审核：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="0"
                                           @if($userReal->review_status == 0) checked @endif> 未审核
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="1"
                                           @if($userReal->review_status == 1) checked @endif> 通过
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="review_status" value="2"
                                           @if($userReal->review_status == 2) checked @endif> 未通过
                                </label>
                            </div>
                        </div>
                        <div class="form-group examine" style="@if($userReal->review_status != 2) display:none; @endif">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-4">
                                <textarea name="review_content" class="form-control" cols="30"
                                          rows="5">{{$userReal->review_content}}</textarea>
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
            $('.nyroModal').nyroModal();

            $('input[name=review_status]').click(function () {
                if($(this).val() == 2){
                    $('.examine').show();
                }else{
                    $('.examine').hide();
                }
            });
        });
    </script>
@endsection
@endsection