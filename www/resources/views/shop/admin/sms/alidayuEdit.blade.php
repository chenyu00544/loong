@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>短信管理 - 大于短信</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>填写短信签名名称和模板ID请与阿里大于申请的保持一致。</li>
                    <li>编辑短信内容时请根据提供的模板进行修改，模板内的每个变量是固定的，且不可改变位置。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="{{url('admin/alidayu/'.$alidayu->id)}}" method="post"
                          class="form-horizontal" enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>短信签名：</label>
                            <div class="col-sm-4">
                                <input type="text" name="set_sign" class="form-control" value="{{$alidayu->set_sign}}"
                                       placeholder="与阿里大于内短信签名保持一致">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>短信模板：</label>
                            <div class="col-sm-4">
                                <input type="text" name="temp_id" class="form-control" value="{{$alidayu->temp_id}}"
                                       placeholder="与阿里大于内短信模板code保持一致">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发送短信的内容：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="temp_content" class="form-control ww" row="5"
                                          placeholder="与阿里大于内短信签名保持一致"
                                          style="min-height:100px;">{{$alidayu->temp_content}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发送时机：</label>
                            <div class="col-sm-4">
                                <select name="send_time" class="form-control select">
                                    <option value="0">请选择...</option>
                                    @foreach($sendTime as $key => $val)
                                        <option value="{{$key}}"
                                                @if($key == $alidayu->send_time) selected @endif>{{$val}}</option>
                                    @endforeach
                                </select>
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
            $('select[name=send_time]').change(function () {
                var temp = $(this).val();
                $.post(
                    "{{url('admin/alidayu/temp')}}",
                    {'_token': '{{csrf_token()}}', temp: temp},
                    function (data) {
                        $('textarea[name=temp_content]').html(data);
                    }
                );
            });
        });
    </script>
@endsection
@endsection