@extends('shop.layouts.index')
@section('content')
    @component('shop.components.headernav',['navs'=>$navs,'user'=>$user])@endcomponent
    <div class="admin-main-right fl">
        <iframe src="{{url('admin/info')}}" id="main" name="main" frameborder="0" scrolling="yes"></iframe>
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
            })
        });
    </script>
@endsection
@endsection
