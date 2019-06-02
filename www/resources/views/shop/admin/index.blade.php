@extends('shop.layouts.index')
@section('content')
    @component('shop.components.headernav',['navs'=>$navs,'user'=>$user])@endcomponent
    <div class="admin-main-right fl">
        <iframe src="" id="main" name="main" frameborder="0" scrolling="yes"></iframe>
    </div>
@section('script')
    <script>
        $(function () {
            $('.admin-avatar-file').on('change', function () {
                layer.load();
                var files = $(this)[0].files;
                var form = new FormData();
                for (var i = 0; i < files.length; i++) {
                    form.append('pic', files[i]);
                }
                form.append('_token', '{{csrf_token()}}');
                $.ajax({
                    url: "{{url('admin/adminuser/change/logo')}}",
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        layer.closeAll('loading');
                    }
                });
            });

            //初始化导致状态
            initNav();
            $('a').click(function () {
                var src = $(this).attr('href');
                if (src != 'javascript:;' && src.indexOf("logout") < 0 && src != 'javascript:history.go(-1);') {
                    $.cookie('href', src);
                }
            });
            var iframe = document.getElementById("main");
            // if (iframe.attachEvent) {
            //     // iframe.attachEvent("onload", function () {
            //     //     var _iframe = document.getElementById('main').contentWindow;
            //     //     $(_iframe.document).find('a').click(function () {
            //     //         log($(this).attr('href'));
            //     //     });
            //     // });
            // } else {
                iframe.onload = function () {
                    var _iframe = iframe.contentWindow;
                    $(_iframe.document).find('a').click(function () {
                        var src = $(this).attr('href');
                        if (src != 'javascript:;' && src.indexOf("logout") < 0 && src != 'javascript:history.go(-1);') {
                            $.cookie('href', src);
                        }
                    });
                };
            // }
        });
    </script>
@endsection
@endsection
