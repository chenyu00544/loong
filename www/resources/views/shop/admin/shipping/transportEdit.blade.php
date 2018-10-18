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
                    <form name="conf" action="{{url('admin/transport/'.$transportInfo['transport']->tid)}}" class="form-horizontal" method="post">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">模板类型：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="freight_type" value="0" @if($transportInfo['transport']->freight_type == 0) checked @endif> 自定义
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="freight_type" value="1" disabled @if($transportInfo['transport']->freight_type == 1) checked @endif> 快递模板
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><em>*</em>模板名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="title" class="form-control" value="{{$transportInfo['transport']->title}}"
                                       placeholder="模板名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">运费标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="shipping_title" class="form-control" value="{{$transportInfo['transport']->shipping_title}}"
                                       placeholder="运费标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">计算方式：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="0" @if($transportInfo['transport']->type == 0) checked @endif > 不计重量和件数
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="1" @if($transportInfo['transport']->type == 1) checked @endif> 按商品件数
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="2" @if($transportInfo['transport']->type == 2) checked @endif> 按重量
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">免费额度：</label>
                            <div class="col-sm-2">
                                <input type="text" name="free_money" class="form-control" value="{{$transportInfo['transport']->free_money}}"
                                       placeholder="免费额度">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">配送地区：</label>
                            <div class="col-sm-7">
                                <div class="area-wrap"
                                     style="display: @if(count($transportInfo['extend']) == 0) none @else block @endif ;">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th class="col-sm-7">运送到</th>
                                            <th class="col-sm-3">运费（元）</th>
                                            <th class="col-sm-2">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="area-info-wrap">
                                        @foreach($transportInfo['extend'] as $val)
                                            <tr class="area-line area-line_{{$val->id}}">
                                                <td>
                                                    <div class="trans-info trans-info-{{$val->id}}">
                                                        @if($val->area_html)
                                                            {!! $val->area_html !!}
                                                        @else
                                                            <p>未指定地区</p>
                                                        @endif
                                                    </div>
                                                </td>
                                                <td>
                                                    <input type="text" name="sprice[]" class="form-control"
                                                           value="{{$val->sprice}}">
                                                </td>
                                                <td>
                                                    <input type="hidden" name="areaid[]" class="areaid"
                                                           value="{{$val->id}}">
                                                    <a href="javascript:;"
                                                       class="btn btn-info btn-edit-area btn-sm">编辑</a>
                                                    <a href="javascript:;"
                                                       class="btn btn-info btn-del-area btn-sm">删除</a>
                                                </td>
                                            </tr>
                                        @endforeach
                                        </tbody>
                                    </table>
                                </div>
                                <a type="button" class="btn btn-info btn-add-area">添加地区</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">快递方式：</label>
                            <div class="col-sm-7">
                                <div class="express-wrap"
                                     style="display: @if(count($transportInfo['express']) == 0) none @else block @endif ;">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th class="col-sm-7">快递名称</th>
                                            <th class="col-sm-3">额外运费（元）</th>
                                            <th class="col-sm-2">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="express-info-wrap">
                                        @foreach($transportInfo['express'] as $value)
                                            <tr class="express-line express-line_{{$value->id}}">
                                                <td>
                                                    <div class="express-info express-info-{{$value->id}}">
                                                        @if($value->ship_html)
                                                            {!! $value->ship_html !!}
                                                        @else
                                                            <p>未指定快递</p>
                                                        @endif
                                                    </div>
                                                </td>
                                                <td>
                                                    <input type="text" name="shipping_fee[]" class="form-control"
                                                           value="{{$value->shipping_fee}}">
                                                </td>
                                                <td>
                                                    <input type="hidden" name="expressid[]" class="expressid"
                                                           value="{{$value->id}}">
                                                    <a href="javascript:;" class="btn btn-info btn-edit-express btn-sm">编辑</a>
                                                    <a href="javascript:;" class="btn btn-info btn-del-express btn-sm">删除</a>
                                                </td>
                                            </tr>
                                        @endforeach
                                        </tbody>
                                    </table>
                                </div>
                                <a type="button" class="btn btn-info btn-add-express">添加快递</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">备注：</label>
                            <div class="col-sm-4">
                                <input type="text" name="remarks" class="form-control" value="{{$transportInfo['transport']->remarks}}"
                                       placeholder="备注">
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
            $('body').on('click', '.btn-add-area', function () {
                $.post('{{url("admin/transport/changes")}}', {
                    _token: '{{csrf_token()}}',
                    type: 'area_add'
                }, function (data) {
                    var html = '<tr class="area-line area-line_' + data.id + '">\n' +
                        '           <td>\n' +
                        '               <div class="trans-info trans-info-' + data.id + '">\n' +
                        '                    <p>未指定地区</p>\n' +
                        '               </div>\n' +
                        '           </td>\n' +
                        '           <td>\n' +
                        '               <input type="text" name="sprice[]" class="form-control" value="0.00">\n' +
                        '           </td>\n' +
                        '           <td>\n' +
                        '               <input type="hidden" name="areaid[]" class="areaid"  value="' + data.id + '">\n' +
                        '               <a href="javascript:;" class="btn btn-info btn-edit-area btn-sm">编辑</a>\n' +
                        '               <a href="javascript:;" class="btn btn-info btn-del-area btn-sm">删除</a>\n' +
                        '           </td>\n' +
                        '        </tr>';
                    $('.area-info-wrap').append(html);
                    $('.area-wrap').show();
                })
            });

            $('body').on('click', '.btn-del-area', function () {
                var id = $(this).parent().find('.areaid').val();
                $.post('{{url("admin/transport/changes")}}', {
                    _token: '{{csrf_token()}}',
                    type: 'area_del',
                    id: id
                }, function (data) {
                    // $(this).parent().parent().remove();
                    $('.area-line_' + id).remove();
                    if ($('.area-line').length <= 0) {
                        $('.area-wrap').hide();
                    }
                })
            });

            $('body').on('click', '.btn-edit-area', function () {

                var id = $(this).parent().find('.areaid').val();

                layer.open({
                    type: 2,
                    area: ['700px', '410px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '地区',
                    content: ["{{url('admin/transport/regions/')}}" + "/" + id + "/0", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            $('body').on('click', '.btn-add-express', function () {
                $.post('{{url("admin/transport/changes")}}', {
                    _token: '{{csrf_token()}}',
                    type: 'express_add'
                }, function (data) {
                    var html = '<tr class="express-line express-line_' + data.id + '">\n' +
                        '           <td>\n' +
                        '               <div class="express-info express-info-' + data.id + '">\n' +
                        '                    <p>未指定快递</p>\n' +
                        '               </div>\n' +
                        '           </td>\n' +
                        '           <td>\n' +
                        '               <input type="text" name="shipping_fee[]" class="form-control" value="0.00">\n' +
                        '           </td>\n' +
                        '           <td>\n' +
                        '               <input type="hidden" name="expressid[]" class="expressid" value="' + data.id + '">\n' +
                        '               <a href="javascript:;" class="btn btn-info btn-edit-express btn-sm">编辑</a>\n' +
                        '               <a href="javascript:;" class="btn btn-info btn-del-express btn-sm">删除</a>\n' +
                        '           </td>\n' +
                        '        </tr>';
                    $('.express-info-wrap').append(html);
                    $('.express-wrap').show();
                })
            });

            $('body').on('click', '.btn-del-express', function () {
                var id = $(this).parent().find('.expressid').val();
                $.post('{{url("admin/transport/changes")}}', {
                    _token: '{{csrf_token()}}',
                    type: 'express_del',
                    id: id
                }, function (data) {
                    // $(this).parent().parent().remove();
                    $('.express-line_' + id).remove();
                    if ($('.express-line').length <= 0) {
                        $('.express-wrap').hide();
                    }
                })
            });

            $('body').on('click', '.btn-edit-express', function () {
                var id = $(this).parent().find('.expressid').val();
                layer.open({
                    type: 2,
                    area: ['700px', '270px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '快递',
                    content: ["{{url('admin/transport/express/')}}" + "/" + id + "/0", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
        });
    </script>
@endsection
@endsection