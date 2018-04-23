@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 添加编辑导航栏</div>
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
                    <form name="conf" action="{{url('admin/navsetup/'.$field->id)}}" class="form-horizontal" method="post">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">顶级导航：</label>
                            <div class="col-sm-3">
                                <select name="cid" class="form-control">
                                    <option value="0">==顶级导航==</option>
                                    @foreach($navsTop as $v)
                                        <option value="{{$v->id}}"
                                                @if($v->id == $field->cid) selected @endif
                                        >{{$v->name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">导航名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="name" class="form-control" value="{{$field->name}}"
                                       placeholder="导航名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">链接地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="url" class="form-control" value="{{$field->url}}"
                                       placeholder="地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-1">
                                <input type="text" name="vieworder" class="form-control" value="{{$field->vieworder}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="ifshow" value="1" @if($field->ifshow ==1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="ifshow" value="0" @if($field->ifshow ==0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否新窗口：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="opennew" value="1" @if($field->opennew ==1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="opennew" value="0" @if($field->opennew ==0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">位置：</label>
                            <div class="col-sm-2">
                                <select name="position" class="form-control">
                                    <option value="home_head" @if($field->position == 'home_head') selected @endif>首页顶部</option>
                                    <option value="home_nav" @if($field->position == 'home_nav') selected @endif>首页导航</option>
                                    <option value="bottom" @if($field->position == 'bottom') selected @endif>底部导航</option>
                                </select>
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