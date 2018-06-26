@extends('shop.layouts.home')
@section('content')
    <body class="iframe_body" style="overflow-y: scroll;background-color: #f7f7f7;">
    <form action="{{url('test')}}" method="post" class="form-horizontal" enctype="multipart/form-data">
        {{csrf_field()}}
        <input type="file" name="file" value="">
        <input type="submit" value="提交">
    </form>
    </body>
@endsection
