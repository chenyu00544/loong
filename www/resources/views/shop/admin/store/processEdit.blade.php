@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">商家 - 编辑流程步骤</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>需先选择所属流程，请合理设定流程信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="{{url('admin/msp/'.$msp->id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">所属流程：</label>
                            <div class="col-sm-4">
                                <select name="process_steps" class="form-control select">
                                    <option value="1" @if() @endif>入驻须知</option>
                                    <option value="2">公司信息认证</option>
                                    <option value="3">店铺信息认证</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>流程信息标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="process_title" class="form-control" value=""
                                       placeholder="流程信息标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>文章ID：</label>
                            <div class="col-sm-3">
                                <input type="text" name="process_article" class="form-control" value=""
                                       placeholder="文章ID">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="steps_sort" class="form-control" value="100"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>下一步标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="fields_next" class="form-control" value=""
                                       placeholder="下一步标题">
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