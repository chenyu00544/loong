@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 编辑此计划任务</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>对于已安装的计划任务可进行编辑，编辑计划任务名称、内容、执行时间等信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/cron/'.$cron->cron_id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">计划任务名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="cron_name" class="form-control" value="{{$cron->cron_name}}"
                                       placeholder="计划任务名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">计划任务描述：</label>
                            <div class="col-sm-3">
                                <textarea name="cron_desc" id="" cols="15" rows="5" class="form-control"
                                          placeholder="计划任务描述">{{$cron->cron_desc}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">每次处理记录个数：</label>
                            <div class="col-sm-2">
                                <input type="text" name="cron_num" class="form-control" value="{{$cron->cron_num}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">计划任务执行时间：</label>
                            <div class="col-sm-3">
                                <div class="clearfix hg34">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="day"> 每月
                                    </label>
                                    <div class="fl wd-180">
                                        <select name="" id="" class="form-control wd-120 input-sm fl">
                                            <option value="1">1</option>
                                        </select>
                                        <span class="line-hg-30 mar-left-5">天</span>
                                    </div>
                                </div>
                                <div class="clearfix hg34">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="week"> 每周
                                    </label>
                                    <div class="fl wd-180">
                                        <select name="" id="" class="form-control wd-120 input-sm fl">
                                            <option value="1">1</option>
                                        </select>
                                        <span class="line-hg-30 mar-left-5">星期</span>
                                    </div>
                                </div>
                                <div class="clearfix hg34">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="hour" checked> 每日
                                    </label>
                                    <div class="fl wd-180">
                                        <select name="" id="" class="form-control wd-120 input-sm fl">
                                            <option value="1">1</option>
                                        </select>
                                        <span class="line-hg-30 mar-left-5">小时</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">或LOGO地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="link_logo_url" class="form-control"
                                       value="{{$cron->link_logo}}"
                                       placeholder="或LOGO地址">
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