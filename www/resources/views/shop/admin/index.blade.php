@extends('shop.layouts.index')
@section('content')
    @component('shop.components.headernav',['navs'=>$navs])@endcomponent
    <div class="admin-main-right fl">
        <iframe src="{{url('admin/info')}}" id="main" name="main" frameborder="0" width="100%" height="95%" scrolling="yes"></iframe>
    </div>
@endsection
