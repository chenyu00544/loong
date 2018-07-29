<ul class="fl">
    <li class="@if($usernav == 'user') curr @endif fl">
        <a href="{{url('admin/satistics/user')}}">新增会员</a>
    </li>
    <li class="@if($usernav == 'area') curr @endif fl">
        <a href="{{url('admin/satistics/user/area')}}">会员区域分析</a>
    </li>
    <li class="@if($usernav == 'rank') curr @endif fl">
        <a href="{{url('admin/satistics/user/rank')}}">会员等级分析</a>
    </li>
    <li class="@if($usernav == 'consumption') curr @endif fl">
        <a href="{{url('admin/satistics/user/consumption')}}">会员消费排行</a>
    </li>
</ul>