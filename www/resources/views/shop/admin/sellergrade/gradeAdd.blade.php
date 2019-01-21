@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商家 - 添加商家等级</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>平台区分商家等级有助于更好的管理商城。</li>
                    <li>对于不同等级的商家可提供不同权限的服务。</li>
                    <li>标识“*”的选项为必填项，其余为选填项。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/sellergrade')}}" method="post" class="form-horizontal" enctype="multipart/form-data" >
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>等级名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="grade_name" class="form-control input-sm" value=""
                                       placeholder="等级名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发布商品数量：</label>
                            <div class="col-sm-4">
                                <input type="text" name="goods_sun" class="form-control input-sm" value="-1"
                                       placeholder="如果为‘-1’，将不做限制">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">模板数量：</label>
                            <div class="col-sm-4">
                                <input type="text" name="seller_temp" class="form-control input-sm" value="-1"
                                       placeholder="如果为‘-1’，将不做限制">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">赠送消费积分比例：</label>
                            <div class="col-sm-4">
                                <input type="text" name="give_integral" class="form-control input-sm" value=""
                                       placeholder="">
                            </div>%
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">赠送等级积分比例：</label>
                            <div class="col-sm-4">
                                <input type="text" name="rank_integral" class="form-control input-sm" value=""
                                       placeholder="0">
                            </div>%
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">积分购买金额比例：</label>
                            <div class="col-sm-4">
                                <input type="text" name="pay_integral" class="form-control input-sm" value=""
                                       placeholder="0">
                            </div>%
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否开启白条支付：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="white_bar" value="1"> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="white_bar" value="0" checked> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">等级标志：</label>
                            <div class="col-sm-4">
                                <input type="file" name="file" class="fl">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">或地址：</label>
                            <div class="col-sm-4">
                                <input type="text" name="file_url" class="form-control" value=""
                                       placeholder="或地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">等级介绍：</label>
                            <div class="col-sm-4">
                                <textarea name="grade_introduce" class="form-control" row="5"
                                          placeholder="等级介绍"
                                          style="min-height:100px;"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">加入标准：</label>
                            <div class="col-sm-8">
                                <div class="checkbox">
                                    @foreach($ecs as $ec)
                                        <label class="mar-right-10">
                                            <input type="checkbox" name="criteria_name[]"
                                                   value="{{$ec->id}}">{{$ec->criteria_name}}
                                        </label>
                                    @endforeach
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否开启：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_open" value="1"> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_open" value="0" checked> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否默认：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_default" value="1"> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_default" value="0" checked> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20" href="javascript:history.go(-1)" >返回</a>
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
        });
    </script>
@endsection
@endsection