@extends('shop.layouts.index')
@section('content')
    <div id="mainBody" class="body">
        <div id="cloud1" class="cloud" style="background-position: -720px 100px;"></div>
        <div id="cloud2" class="cloud" style="background-position: 0px 460px;"></div>
    </div>

    <form class="form-horizontal login" method="post" action="{{url('admin/login')}}">
        {{csrf_field()}}
        <div class="container-fluid">
            <div class="form-group">
                <div class="error text-center">
                    @if(count($errors) > 0)
                        @if(is_object($errors))
                            <p style="color:red">{{$errors->all()[0]}}</p>
                        @else
                            <p style="color:red">{{$errors}}</p>
                        @endif
                    @endif
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">{{$lang['username']}}</label>
                <div class="col-sm-7">
                    <input type="text" name="username" class="form-control"
                           placeholder="{{$lang['username']}}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">{{$lang['password']}}</label>
                <div class="col-sm-7">
                    <input type="password" name="password" class="form-control"
                           placeholder="{{$lang['password']}}">
                </div>
            </div>
            @if($captcha->value[3] != 0)
                <div class="form-group">
                    <label class="col-sm-3 control-label">{{$lang['captcha']}}</label>
                    <div class="col-sm-7">
                        <input type="text" name="captcha" class="form-control fl wd-110"
                               placeholder="{{$lang['captcha']}}">
                        <img src="{{url('api/web/captcha/1')}}" alt=""
                             class="fl captcha-login cursor">
                    </div>
                </div>
            @endif
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-9">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> {{$lang['remember']}}
                        </label>
                        <label class="col-sm-offset-4">
                            <a class="link-home cl-link-blue" href="../">{{$lang['back_home']}}</a>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4 center clear">
                    <input type="submit" class="btn btn-primary n-wd110" value="{{$lang['login']}}">
                </div>
            </div>
            @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
        </div>
    </form>
@section('script')
    <script>
        $(function () {
            $('.captcha-login').on('click', function () {
                $(this).attr('src', "{{url('api/web/captcha/')}}/" + Math.random());
            });
            var p1 = -720.0;
            var p2 = 0.0;
            setInterval(function () {
                if (p1 > 1000) {
                    p1 = -720;
                }
                if (p2 > 1000) {
                    p2 = -720;
                }
                p1 += 0.1;
                p2 += 0.1;
                $('#cloud1').css('background-position', p1 + 'px 100px');
                $('#cloud2').css('background-position', p2 + 'px 460px');
            }, 10);
        });
    </script>
@endsection
@endsection