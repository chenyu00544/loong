@extends('shop.layouts.index')

@section('content')

    <div style="background: #ffffff;height: 100%; text-align: center">
        <img src="{{url('styles/images/success.png')}}" alt="" class="suc-tip">
        <h5 class="suc-title">修改成功</h5>
        <div class="suc-back"><a href="javascript:history.go(-1)">返回上一页</a></div>
    </div>
    
@endsection
