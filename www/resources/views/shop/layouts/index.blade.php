<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="{{url('styles/admin/images/favicon.gif')}}" type="image/gif">
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/static/bootstrap.min.css')}}">
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/switch/bootstrap-switch.min.css')}}">
    <link rel="stylesheet" href="{{asset('styles/plugin/nyroModal/styles/nyroModal.css')}}">
    <link rel="stylesheet" href="{{asset('styles/iconfont/iconfont.css')}}">
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/datepicker/daterangepicker.css')}}"/>
    <link rel="stylesheet" href="{{asset('styles/admin/css/main.css').'?v='.$v}}">
    @yield('css')
    <title>{{Config::get('config.admin_name')}}</title>
    <script>
        var weburl = '{{Config::get('config.location')}}';
    </script>
</head>
<body>
@yield('content')
<script type="text/javascript" src="{{asset('styles/plugin/jquery/jquery.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/bootstrap/static/bootstrap.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/bootstrap/switch/bootstrap-switch.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/nyroModal/js/jquery.nyroModal.custom.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/layer/layer.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/vue/vue.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/bootstrap/datepicker/moment.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/bootstrap/datepicker/daterangepicker.js')}}"></script>
{{--<script type="text/javascript" src="{{asset('styles/plugin/vue/vue.js')}}"></script>--}}
<!--[if IE 6]>
<script type="text/javascript" src="{{asset('styles/plugin/nyroModal/js/jquery.nyroModal-ie6.js')}}"></script>
<![endif]-->
<script type="text/javascript" src="{{asset('styles/admin/js/main.js')}}"></script>
@yield('script')
</body>
</html>