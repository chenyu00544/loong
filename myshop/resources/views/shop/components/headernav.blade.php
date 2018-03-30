<div class="admin-header clearfix" style="min-width:1280px;">
    <div class="bgSelector"></div>
    <div class="admin-logo">
        <a href="javascript:void(0);" data-param="home" target="workspace">
            <img src="{{asset('styles/images/logo.png')}}" />
        </a>
        <div class="foldsider"><i class="icon icon-indent-left"></i></div>
    </div>
    <div class="module-menu">
        <ul>
            @foreach($navs['index'] as $k => $v)
                <li data-param="{{$k}}"><a href="javascript:void(0);">{{$v}}</a></li>
            @endforeach
        </ul>
    </div>
    <div class="admin-header-right">
        <div class="manager">
            <dl>
                <dt class="name">xxxx</dt>
                <dd class="group">超级管理员</dd>
            </dl>
            <span class="avatar">
				<form action="index.php" id="fileForm" method="post"  enctype="multipart/form-data"  runat="server">
					<input name="img" type="file" class="admin-avatar-file" id="_pic" title="设置管理员头像">
				</form>
				<img nctype="admin_avatar" src="" />
			</span>
            <div id="admin-manager-btn" class="admin-manager-btn"><i class="arrow"></i></div>
            <div class="manager-menu">
                <div class="title">
                    <h4>最后登录</h4>
                    <a href="privilege.php?act=edit&id=" target="workspace" class="edit_pwd">修改密码</a>
                </div>
                <div class="login-date">
                    <strong>{$admin_info.last_login}</strong>
                    <span>(IP:{$admin_info.last_ip})</span>
                </div>
                <div class="title mt10">
                    <h4>常用操作</h4>
                    <a href="javascript:;" class="add_nav">添加菜单</a>
                </div>
                <div class="quick_link">
                    <ul>
                        <li class="tl"><a href="xxxxxxxxxxx" target="workspace">xxxxxxxxxx</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="operate">
            <li style="position: relative;" ectype="oper_msg">
                <a href="javascript:void(0);" class="msg" title="查看消息">&nbsp;</a>
                <div id="msg_Container">
                    <div class="item">
                        <h3 class="order_msg" ectype="msg_tit">订单提示<em class="iconfont icon-up"></em></h3>
                        <div class="msg_content" ectype="orderMsg" style="display:block;"></div>
                    </div>

                    <div class="item">
                        <h3 class="goods_msg" ectype="msg_tit">商品提示<em class="iconfont icon-down"></em></h3>
                        <div class="msg_content" ectype="goodMsg"></div>
                    </div>

                    <div class="item">
                        <h3 class="shop_msg" ectype="msg_tit">商家审核提示<em class="iconfont icon-down"></em></h3>
                        <div class="msg_content" ectype="sellerMsg"></div>
                    </div>

                    <div class="item">
                        <h3 class="ad_msg" ectype="msg_tit">广告位提示<em class="iconfont icon-down"></em></h3>
                        <div class="msg_content" ectype="advMsg"></div>
                    </div>

                    <div class="item">
                        <h3 class="user_msg" ectype="msg_tit">会员提醒<em class="iconfont icon-down"></em></h3>
                        <div class="msg_content" ectype="userMsg"></div>
                    </div>

                    <div class="item">
                        <h3 class="campaign_msg" ectype="msg_tit">活动提醒<em class="iconfont icon-down"></em></h3>
                        <div class="msg_content" ectype="promotionMsg"></div>
                    </div>
                </div>
            </li>
            <i></i>
            <li><a href="../" target="_blank" class="home" title="新窗口打开商城首页">&nbsp;</a></li>
            <i></i>
            <li><a href="javascript:void(0);" class="sitemap" title="查看全部管理菜单">&nbsp;</a></li>
            <i></i>
            <li><a href="javascript:void(0);" id="trace_show" class="style-color" title="给管理中心换个颜色">&nbsp;</a></li>
            <i></i>
            <li><a href="index.php?act=clear_cache" class="clear" target="workspace" title="清除缓存">&nbsp;</a></li>
            <i></i>
            <li><a href="privilege.php?act=logout" class="prompt" title="安全退出管理中心">&nbsp;</a></li>
        </div>
    </div>
</div>

<div id="allMenu" style="display: none;">
    <div class="admincp-map ui-widget-content ui-draggable" nctype="map_nav" id="draggable">
        <div class="title ui-widget-header ui-draggable-handle" style="border:none; background:#fff;">
            <h3>管理中心全部菜单</h3>
            <h5>切换显示全部管理菜单，通过点击勾选可添加菜单为管理常用操作项，最多添加10个</h5>
            <span><a nctype="map_off" onclick="$('#allMenu').hide();" href="JavaScript:void(0);">X</a></span>
        </div>
        <div class="content">
            <ul class="admincp-map-nav">
                <li class=""><a href="javascript:void(0);" data-param="map-system">平台</a></li>
                <li class="selected"><a href="javascript:void(0);" data-param="map-shop">商城</a></li>
                <li class=""><a href="javascript:void(0);" data-param="map-mobile">手机端</a></li>
                <!--<li class=""><a href="javascript:void(0);" data-param="map-cms">APP</a></li>-->
                <li class=""><a href="javascript:void(0);" data-param="map-cms">资源</a></li>
            </ul>
            <div class="admincp-map-div" data-param="map-system" style="display: none;">
                <dl>
                    <dt>xxxxx</dt>
                    <dd class="selected"><a href="xxxxxxxxxx" data-param="" target="workspace">xxxxxx</a><i class="fa fa-check-square-o"></i></dd>
                </dl>
            </div>
            <div class="admincp-map-div" data-param="map-shop" style="display: block;">
                <dl>
                    <dt>xxxxxx</dt>
                    <dd class="selected"><a href="xxxxxx" data-param="" target="workspace">xxxxxx</a><i class="fa fa-check-square-o"></i></dd>
                </dl>
            </div>
            <div class="admincp-map-div" data-param="map-mobile" style="display: none;">
                <dl>
                    <dt>{$vo.label}</dt>
                    <dd class="selected"><a href="xxx" data-param="" target="workspace">xxx</a><i class="fa fa-check-square-o"></i></dd>
                </dl>
            </div>
            <div class="admincp-map-div" data-param="map-cms" style="display: none;">
                <dl>
                    <dt>xxxx</dt>
                    <dd class="selected"><a href="xxx" data-param="" target="workspace">xxxx</a><i class="fa fa-check-square-o"></i></dd>
                </dl>
            </div>
        </div>
    </div>
</div>