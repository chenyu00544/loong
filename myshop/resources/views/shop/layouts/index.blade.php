<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/static/bootstrap.min.css')}}">
<<<<<<< HEAD
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/switch/bootstrap-switch.min.css')}}">
    <link rel="stylesheet" href="{{asset('styles/plugin/nyroModal/styles/nyroModal.css')}}">
=======
>>>>>>> 3446ec3a04598a6da640fcdbe28208fa77238de4
    <link rel="stylesheet" href="{{asset('styles/admin/css/main.css').'?v='.$v}}">
    <title>{{Config::get('config.admin_name')}}</title>
    <script>
        var weburl = '{{Config::get('config.location')}}';
    </script>
</head>
<body>
@yield('content')
<script src="{{asset('styles/plugin/jquery/jquery.min.js')}}"></script>
<script src="{{asset('styles/plugin/bootstrap/static/bootstrap.min.js')}}"></script>
<script src="{{asset('styles/plugin/bootstrap/switch/bootstrap-switch.min.js')}}"></script>
<script src="{{asset('styles/plugin/nyroModal/js/jquery.nyroModal.custom.js')}}"></script>
<script src="{{asset('styles/plugin/layer/layer.js')}}"></script>
<!--[if IE 6]>
<script src="{{asset('styles/plugin/nyroModal/js/jquery.nyroModal-ie6.js')}}"></script>
<![endif]-->
<script src="{{asset('styles/admin/js/main.js')}}"></script>
@yield('script')
</body>
</html>