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
                            <div class="col-sm-4">
                                <input type="text" name="cron_name" class="form-control" value="{{$cron->cron_name}}"
                                       placeholder="计划任务名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">计划任务描述：</label>
                            <div class="col-sm-4">
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
                            <div class="col-sm-4">
                                <div class="clearfix hg40 pad-top-4">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="day"> 每月
                                    </label>
                                    <div class="fl wd-180">
                                        <select name="day" id="" class="form-control wd-120 input-sm fl">
                                            @for($i=1;$i<32;$i++)
                                                <option value="{{$i}}">{{$i}}</option>
                                            @endfor
                                        </select>
                                        <span class="line-hg-30 mar-left-5">天</span>
                                    </div>
                                </div>
                                <div class="clearfix hg40 pad-top-4">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="week"> 每周
                                    </label>
                                    <div class="fl wd-180">
                                        <select name="week" id="" class="form-control wd-120 input-sm fl">
                                            @foreach($week as $k => $w)
                                                <option value="{{$k+1}}">{{$w}}</option>
                                            @endforeach
                                        </select>
                                        <span class="line-hg-30 mar-left-5">星期</span>
                                    </div>
                                </div>
                                <div class="clearfix hg40 pad-top-4">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="ttype" value="hour" checked> 每日
                                    </label>
                                    <div class="fl wd-180">
                                        <input type="text" name="hour" class="form-control input-sm fl"
                                               value="{{$cron->hour}}"
                                               placeholder="请用半角逗号分隔多个小时">
                                    </div>
                                    <span class="line-hg-30 mar-left-5">小时</span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">分钟：</label>
                            <div class="col-sm-4">
                                <input type="text" name="minute" class="form-control"
                                       value="{{$cron->minute}}"
                                       placeholder="请用半角逗号分隔多个分钟">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">执行后关闭：</label>
                            <div class="col-sm-4">
                                <div class="clearfix checkbox-items">
                                    <div class="checkbox-item fl mar-right-20">
                                        <input type="checkbox" name="cron_run_once" class="ui-checkbox" value="1"
                                               id="cron_run_once">
                                        <label class="ui-label mar-left-5 mar-top-7" for="cron_run_once">关闭</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">允许执行的服务器ip：</label>
                            <div class="col-sm-4">
                                <input type="text" name="allow_ip" class="form-control"
                                       value="{{$cron->allow_ip}}"
                                       placeholder="允许运行服务器的IP，请用半角逗号分隔多个IP">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">执行的任务：</label>
                            <div class="col-sm-4 checkbox-items">
                                <select name="alow_files" id="" class="form-control wd-120 input-sm fl">
                                    <option value="1">1</option>
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
        });
    </script>
@endsection
@endsection