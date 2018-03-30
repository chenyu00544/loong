
<div class="admin-main">
    <div class="top-border"></div>
    @component('shop.components.leftnav')
        @foreach($navs['index'] as $k => $v)
            <div class="navLeftTab" id="adminNavTabs_{{$k}}" style="display:@if($k == 'home')block @else none @endif;">
                @foreach($navs[$k] as $key => $val)
                    <div class="item @if($loop->index == 0)current @endif">
                        <div class="tit"><a href="javascript:void(0)"><i class="nav_icon icon_00_{{$key}}"></i><h4>{{$val}}</h4>
                            </a></div>
                        <div class="sub-menu" style="display:block;">
                            <ul>
                                @foreach($navs[$key] as $n => $m)
                                    <li class="@if($loop->index == 0)curr @endif"><s></s><a href="{{url($m['url'])}}" target="workspace">{{$m['name']}}</a></li>
                                @endforeach
                            </ul>
                        </div>
                    </div>
                @endforeach
            </div>
@endforeach
@endcomponent