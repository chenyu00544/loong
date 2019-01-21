@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 添加秒杀时间段</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>秒杀时间段相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/seckilltime')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>秒杀时段名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="title" class="form-control input-sm" placeholder="秒杀时段名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>每日开始时间：</label>
                            <div class="col-sm-4">
                                <input type="text" name="begin_hour" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="时">
                                <span class="fl line-hg-30">：</span>
                                <input type="text" name="begin_minute" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="分">
                                <span class="fl line-hg-30">：</span>
                                <input type="text" name="begin_second" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="秒">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>每日结束时间：</label>
                            <div class="col-sm-4">
                                <input type="text" name="end_hour" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="时">
                                <span class="fl line-hg-30">：</span>
                                <input type="text" name="end_minute" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="分">
                                <span class="fl line-hg-30">：</span>
                                <input type="text" name="end_second" class="form-control input-sm wd-80 fl" maxlength="2" placeholder="秒">
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
        });
    </script>
@endsection
@endsection