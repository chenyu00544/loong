@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">{{$lang['system_set']}} - {{$lang['shop_setup']}}</div>
        <div class="content">
            <div class="tabs">
                <ul class="fl">
                    @foreach($conf as $item)
                        <li class="@if($loop->index == 0) curr @endif fl"><a
                                    href="javascript:void(0);">{{$item->name}}</a>
                        </li>
                    @endforeach
                </ul>
            </div>
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
                    <form enctype="multipart/form-data" name="conf" action="{{url('admin/goodsconf')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        @foreach($conf as $item)
                            <div class="switch-info" @if($loop->index != 0)style="display:none" @endif>
                                @foreach($item->vars as $var)
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label">{{$var['name']}}
                                            ：</label>
                                        <div class="col-sm-4 n-wd400">
                                            {!! $var['html'] !!}
                                        </div>
                                        @if($var['desc'])
                                            <div class="notic col-sm-3">{{nl2br($var['desc'])}}</div>
                                        @endif
                                    </div>

                                @endforeach
                                <div class="item">
                                    <div class="label">&nbsp;</div>
                                    <div class="">
                                        <input type="submit" value="　{{$lang['sure']}}　"
                                               class="btn btn-danger clearfix">
                                    </div>
                                </div>
                            </div>
                        @endforeach
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
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}",{type:1, parent:parent},function(data){
                    if(data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_province').html($html);
                    }
                });
            });

            $('.shop_province').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}",{type:2, parent:parent},function(data){
                    if(data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_city').html($html);
                    }
                });
            })

        });
    </script>
@endsection
@endsection