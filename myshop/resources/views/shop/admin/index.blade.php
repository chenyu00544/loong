@extends('shop.layouts.index')

@section('content')
    @component('shop.components.headernav',['navs'=>$navs])@endcomponent

    <div class="admin-main-right">
        {{--<iframe src="{{url('admin/info')}}" id="workspace" name="workspace" frameborder="0" width="100%" height="95%" scrolling="yes"></iframe>--}}
    </div>
@endsection
