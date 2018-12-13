<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="{{asset('styles/home/css/web.css').'?v='.$v}}">
    @yield('css')
    @component('shop.components.headerSeo',['seo'=>$seo])@endcomponent
    <script>
        var weburl = '{{Config::get('config.location')}}';
    </script>
</head>
<body>
@yield('content')
<script type="text/javascript" src="{{asset('styles/plugin/jquery/jquery.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/layer/layer.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/plugin/vue/vue.min.js')}}"></script>
<script type="text/javascript" src="{{asset('styles/home/js/web.js')}}"></script>
@yield('script')
</body>
</html>