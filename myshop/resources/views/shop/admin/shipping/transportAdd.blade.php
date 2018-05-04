@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">地区设置 - 添加编辑运费</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商店相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/transport/')}}" class="form-horizontal" method="post">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">模板类型：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="freight_type" value="0"> 自定义
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="freight_type" value="1"> 快递模板
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><em>*</em>模板名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="title" class="form-control" value=""
                                       placeholder="模板名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">运费标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="shipping_title" class="form-control" value=""
                                       placeholder="运费标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">计算方式：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="0"> 不计重量和件数
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="1"> 按商品件数
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="3"> 按重量
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">免费额度：</label>
                            <div class="col-sm-2">
                                <input type="text" name="free_money" class="form-control" value=""
                                       placeholder="免费额度">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">配送地区：</label>
                            <div class="col-sm-2">
                                <a type="button" data-id="21" class="btn btn-primary btn-add-area ">添加地区</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">快递方式：</label>
                            <div class="col-sm-2">
                                <a type="button" data-id="21" class="btn btn-primary btn-add-express ">添加快递</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
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