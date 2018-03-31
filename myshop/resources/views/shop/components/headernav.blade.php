<div class="admin-header clearfix">
    <div class="admin-logo fl">
        <a href="javascript:void(0);" data-param="home" target="workspace">
            <img src="{{asset('styles/images/logo.png')}}" />
        </a>
        <div class="foldsider"><i class="icon icon-indent-left"></i></div>
    </div>
    <div class="fl admin-menu">
        <ul>
            @foreach($navs['index'] as $k => $v)
                <li data-param="{{$k}}" class="col-xs-1 wd80 text-center lh60"><a href="javascript:void(0);">{{$v}}</a></li>
            @endforeach
        </ul>
    </div>
</div>
<div class="top-border"></div>
<div class="nav-left">


</div>
