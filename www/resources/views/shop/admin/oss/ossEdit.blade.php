@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>文件管理 - 阿里云OSS配置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>填写在阿里云创建的OSS信息。</li>
                    <li>需要在系统设置->商品设置->扩展信息中开启该功能即可使用。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="{{url('admin/oss/'.$alioss->id)}}" method="post"
                          class="form-horizontal" enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>Bucket名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="bucket" class="form-control" value="{{$alioss->bucket}}"
                                       placeholder="与阿里云OSS开通对象名称一致">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>AccessKeyID：</label>
                            <div class="col-sm-4">
                                <input type="text" name="keyid" class="form-control" value="{{$alioss->keyid}}"
                                       placeholder="填写阿里云Access Key管理的(ID)">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>AccessKeySecret：</label>
                            <div class="col-sm-4">
                                <input type="text" name="keysecret" class="form-control" value="{{$alioss->keysecret}}"
                                       placeholder="填写阿里云Access Key管理的(Secret)">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">域名绑定：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cname" value="0" @if($alioss->is_cname == 0) checked @endif> 关闭
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cname" value="1" @if($alioss->is_cname == 1) checked @endif> 开启
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-4">
                                <div class="notic">默认选关闭，官方建议开启绑定域名，域名格式：http://xx.xxxx.com/（不可绑定当前网站域名，建议新开二级域名）</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>EndPoint：</label>
                            <div class="col-sm-4">
                                <input type="text" name="endpoint" class="form-control" value="{{$alioss->endpoint}}"
                                       placeholder="http://xx.xxxx.com/">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Bucket地域：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="shanghai" @if($alioss->regional == 'shanghai') checked @endif> 中国（上海：华东 2）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="hangzhou" @if($alioss->regional == 'hangzhou') checked @endif> 中国（杭州：华东 1）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="shenzhen" @if($alioss->regional == 'shenzhen') checked @endif> 中国（深圳：华南 1）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="beijing" @if($alioss->regional == 'beijing') checked @endif> 中国（北京：华北 2）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="qingdao" @if($alioss->regional == 'qingdao') checked @endif> 中国（青岛：华北 1）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="hongkong" @if($alioss->regional == 'hongkong') checked @endif> 中国（香港）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="us-west-1" @if($alioss->regional == 'us-west-1') checked @endif> 美国西部 1 (硅谷)
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="us-east-1" @if($alioss->regional == 'us-east-1') checked @endif> 国东部 1 (弗吉尼亚)
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="ap-southeast-1" @if($alioss->regional == 'ap-southeast-1') checked @endif> 亚太东南 1 (新加坡)
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="regional" value="ap-northeast-1" @if($alioss->regional == 'ap-northeast-1') checked @endif> 亚太东北 1 (日本)
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否使用：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_use" value="0" @if($alioss->is_use == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_use" value="1" @if($alioss->is_use == 1) checked @endif> 是
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否删除图片：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_delimg" value="0" @if($alioss->is_delimg == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_delimg" value="1" @if($alioss->is_delimg == 1) checked @endif> 是
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