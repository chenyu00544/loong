<div class="admin-header clearfix">
    <div class="admin-logo fl">
        <a href="javascript:void(0);" data-param="home" target="workspace">
            <img src="{{asset('styles/images/admin_logo.png')}}"/>
        </a>
        <div class="foldsider"><i class="icon icon-indent-left"></i></div>
    </div>
    <div class="fl admin-menu">
        <ul>
            @foreach($navs['index'] as $k => $v)
                <li data-param="{{$k}}" class="col-xs-1 wd80 text-center lh48 @if($loop->index == 0) active @endif"><a href="{{url($navs[array_keys($navs[$k])[0]][array_keys($navs[array_keys($navs[$k])[0]])[0]]['url'])}}" target="main">{{$v}}</a>
                </li>
            @endforeach
        </ul>
    </div>
    <div class="admin-fun-right fr">
        <div class="manager"></div>
        <div class="operate">
            <li><a href="{{url('admin/clearcache')}}" class="clear sc-icon" title="清除缓存">&nbsp;</a></li>
            <i class="sc-icon"></i>
            <li><a href="{{url('admin/logout')}}" class="prompt sc-icon" title="安全退出">&nbsp;</a></li>
        </div>
    </div>
</div>
<div class="top-border"></div>
<div class="nav-left">
    @foreach($navs['index'] as $k => $v)
        <div class="sub-nav sub_{{$k}}" style="display:@if($k == 'home')block @else none @endif">
            @foreach($navs[$k] as $key => $val)
                <div class="item fl @if($loop->index == 0)current @endif">
                    <div class="title fl">
                        <a href="{{url($navs[$key][array_keys($navs[$key])[0]]['url'])}}" target="main">
                            <i class="nav_icon icon_{{$key}}"></i>
                            <h6>{{$val}}</h6>
                        </a>
                    </div>
                    <div class="sub-item fl" style="display:@if($loop->index == 0)block @else none @endif">
                        <ul>
                            @foreach($navs[$key] as $n => $m)
                                <li class="@if($loop->index == 0) curr @endif">
                                    <s></s>
                                    <a href="{{url($m['url'])}}" target="main">{{$m['name']}}</a>
                                </li>
                            @endforeach
                        </ul>
                    </div>
                </div>
            @endforeach
        </div>
    @endforeach
</div>
