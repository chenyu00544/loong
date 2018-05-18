@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;padding: 20px;">
    <div class="content-wrap">
        <div class="top">
            <select class="form-control hg30 max-wd-190 mar-all-10 ft-12 fl">
                <option value="1">请选择</option>
            </select>
            <a href="javascript:;" class="btn btn-danger btn-sm mar-all-10 line-hg-20 lib-add fl">添加图库</a>
            <a href="javascript:;" class="btn btn-danger btn-sm mar-all-10 line-hg-20 upload-img fl">上传图片</a>
        </div>

        @foreach($files as $file)

        @endforeach
    </div>
    </body>
@section('script')
    <script>
        $(function () {

        });
    </script>
@endsection
@endsection