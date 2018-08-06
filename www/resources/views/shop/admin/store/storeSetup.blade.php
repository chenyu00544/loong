@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">店铺管理 - 店铺设置</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($nav == 'store') curr @endif fl">
                        <a href="{{url('admin/store')}}">店铺设置</a>
                    </li>
                    <li class="@if($nav == 'process') curr @endif fl">
                        <a href="{{url('admin/msp')}}">入驻流程</a>
                    </li>
                    <li class="@if($nav == 'privilege') curr @endif fl">
                        <a href="{{url('admin/store/privilege')}}">入驻初始化权限</a>
                    </li>
                    <li class="@if($nav == 'grade') curr @endif fl">
                        <a href="{{url('admin/sellergrade')}}">店铺等级</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>店铺相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/store')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}

                        @foreach($conf as $item)
                            <div class="form-group">
                                <label class="col-sm-4 control-label">{{$item->name}}：</label>
                                <div class="col-sm-3 n-wd400">
                                    {!! $item->html !!}
                                </div>
                                @if($item->desc)
                                    <div class="notic col-sm-3">{{nl2br($item->desc)}}</div>@endif
                            </div>
                        @endforeach
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