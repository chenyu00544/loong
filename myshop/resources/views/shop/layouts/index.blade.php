<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/static/bootstrap.min.css')}}">
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
<script src="{{asset('styles/admin/js/main.js')}}"></script>
</body>
</html>