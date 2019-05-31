@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 添加频道</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>拼团频道相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/teamcate/'.$teamCate->id)}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>频道名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="name" class="form-control input-sm" value="{{$teamCate->name}}"
                                       placeholder="频道名称" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上级频道：</label>
                            <div class="col-sm-3">
                                <select name="parent_id" class="form-control input-sm">
                                    <option value="0">顶级分类</option>
                                    @foreach($pCates as $pCate)
                                        <option value="{{$pCate->id}}"
                                                @if($teamCate->parent_id == $pCate->id) selected @endif>{{$pCate->name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">子级频道小图标：</label>
                            <div class="col-sm-3">
                                <input type="file" name="tc_img" class="fl">
                                <input type="hidden" name="old_tc_img" value="{{$teamCate->tc_img}}">
                                <a href="{{$teamCate->tc_img_oss}}" target="_blank" class="nyroModal">
                                    <i class="glyphicon glyphicon-picture top5"
                                       data-tooltipimg="{{$teamCate->tc_img_oss}}" ectype="tooltip"
                                       data-toggle="tooltip" title="tooltip"></i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">描述：</label>
                            <div class="col-sm-3">
                                <textarea class="form-control" rows="5" name="content"
                                          placeholder="描述">{{$teamCate->content}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort_order" class="form-control input-sm"
                                       value="{{$teamCate->sort_order}}"
                                       placeholder="排序" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="1" @if($teamCate->status ==1) checked @endif>
                                    是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="0" @if($teamCate->status ==0) checked @endif> 否
                                </label>
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
            $('.nyroModal').nyroModal();
        });
    </script>
@endsection
@endsection