<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>laravel整合phpSocketIo</title>
</head>
<body>
<h2>实现laravel服务端推送消息到web端</h2>
<h5>效果查看console</h5>
<div><input type="text" name="login">
    <a href="javascript:;" class="login">登录</a></div>
<div><input type="text" name="touid"></div>
<div><input type="text" name="name">
    <a href="javascript:;" class="send">发送</a></div>

<script type="text/javascript" src="{{asset('styles/plugin/jquery/jquery.min.js')}}"></script>
<script type="text/javascript" src='{{asset('styles/plugin/socket/socket.io.js')}}'></script>
<script>
    $(function () {
        var socket = io('http://127.0.0.1:3120');
        var uid = 0;
        var touid = 0;
        // 当连接服务端成功时触发connect默认事件
        socket.on('connect', function () {
            console.log('当连接服务端成功时触发connect默认事件');
        });
        $('.login').click(function () {
            uid = $('input[name=login]').val();
            socket.emit('login', uid);
        });

        // 后端推送来消息时
        socket.on('back_msg', function (msg) {
            console.log('收到消息: ' + msg);
        });

        $('.send').click(function () {
            touid = $('input[name=touid]').val();
            var val = $('input[name=name]').val();
            socket.emit('message', val + '_' + touid);
        });

        // 后端推送来在线数据时
        socket.on('update_online_count', function (online_stat) {
            console.log('即时在线数据: ', online_stat);
        });
    })
</script>
</body>
</html>