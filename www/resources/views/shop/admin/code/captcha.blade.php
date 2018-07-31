@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 验证码设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该功能可以设置开启验证码的场景。</li>
                    <li>可以设置验证码的高度、宽度和大小。</li>
                    <li>请按照每个操作下的提示进行设置。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">验证码设置</th>
                        </tr>
                        </thead>
                        <tbody>
                        <form action="{{url('admin/captcha')}}" class="form-horizontal" method="post">
                            {{csrf_field()}}
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>启用验证码</strong></p>
                                        <p class="blue td">图片验证码可以避免恶意批量评论或提交信息，推荐打开验证码功能。注意:
                                            启用验证码会使得部分操作变得繁琐，建议仅在必需时打开</p>
                                        <p>
                                            <img src="{{url('api/web/captcha/1')}}" alt="captcha"
                                                 class="captcha-img cursor"></p>
                                    </div>
                                    <div class="fl mar-left-20">
                                        <ul>
                                            <li class="wd80">
                                                <a class="cursor sel-all">
                                                    <input type="checkbox" name="captcha_register" id="captcha_register"
                                                           value="1"
                                                           @if($captcha['captcha']->value[0] != 0) checked="true" @endif>
                                                    <label for="captcha_register"
                                                           style="margin: 0"><span>新用户注册</span></label>
                                                </a>
                                            </li>
                                            <li class="wd80">
                                                <a class="cursor sel-all">
                                                    <input type="checkbox" name="captcha_login" id="captcha_login"
                                                           value="2"
                                                           @if($captcha['captcha']->value[1] != 0) checked="true" @endif>
                                                    <label for="captcha_login"
                                                           style="margin: 0"><span>用户登录</span></label>
                                                </a>
                                            </li>
                                            <li class="wd80">
                                                <a class="cursor sel-all">
                                                    <input type="checkbox" name="captcha_comment" id="captcha_comment"
                                                           value="4"
                                                           @if($captcha['captcha']->value[2] != 0) checked="true" @endif>
                                                    <label for="captcha_comment"
                                                           style="margin: 0"><span>发表评论</span></label>
                                                </a>
                                            </li>
                                            <li class="wd80">
                                                <a class="cursor sel-all">
                                                    <input type="checkbox" name="captcha_admin" id="captcha_admin"
                                                           value="8"
                                                           @if($captcha['captcha']->value[3] != 0) checked="true" @endif>
                                                    <label for="captcha_admin"
                                                           style="margin: 0"><span>后台管理员登录</span></label>
                                                </a>
                                            </li>
                                            <li class="wd80">
                                                <a class="cursor sel-all">
                                                    <input type="checkbox" name="captcha_message" id="captcha_message"
                                                           value="16"
                                                           @if($captcha['captcha']->value[4] != 0) checked="true" @endif>
                                                    <label for="captcha_message"
                                                           style="margin: 0"><span>留言板留言</span></label>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>登录失败时显示验证码</strong></p>
                                        <p class="blue td">选择“是”将在用户登录失败 3
                                            次后才显示验证码，选择“否”将始终在登录时显示验证码。注意:只有在启用了用户登录验证码时本设置才有效</p>
                                    </div>
                                    <div class="fl mar-left-20 pad-top-30">
                                        <label class="radio-inline fl">
                                            <input type="radio" name="captcha_login_fail" value="32"
                                                   style="margin-top: 7px"
                                                   @if($captcha['captcha']->value[5] != 0) checked="true" @endif > 是
                                        </label>
                                        <label class="radio-inline fl">
                                            <input type="radio" name="captcha_login_fail" value="0"
                                                   style="margin-top: 7px"
                                                   @if($captcha['captcha']->value[5] == 0) checked="true" @endif> 否
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>验证码图片宽度</strong></p>
                                        <p class="blue td">验证码图片的宽度，范围在 40～145 之间</p>
                                    </div>
                                    <div class="fl mar-left-20 pad-top-13">
                                        <input type="text" name="captcha_width" class="form-control"
                                               value="{{$captcha['captcha_width']->value}}" placeholder="宽度">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>验证码图片高度</strong></p>
                                        <p class="blue td">验证码图片的高度，范围在 15～50 之间</p>
                                    </div>
                                    <div class="fl mar-left-20 pad-top-13">
                                        <input type="text" name="captcha_height" class="form-control"
                                               value="{{$captcha['captcha_height']->value}}" placeholder="高度">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>验证码内容大小</strong></p>
                                        <p class="blue td">(如：font-size:18)</p>
                                    </div>
                                    <div class="fl mar-left-20 pad-top-13">
                                        <input type="text" name="captcha_font_size" class="form-control"
                                               value="{{$captcha['captcha_font_size']->value}}" placeholder="验证码内容大小">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="capt-td">
                                    <div class="fl wd-750">
                                        <p><strong>验证码位数</strong></p>
                                    </div>
                                    <div class="fl mar-left-20">
                                        <input type="text" name="captcha_length" class="form-control"
                                               value="{{$captcha['captcha_length']->value}}" placeholder="验证码位数">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <input type="submit" value="保存设置" class="btn btn-danger clearfix">
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.captcha-img').on('click', function () {
                $(this).attr('src', "{{url('api/web/captcha/')}}/" + Math.random());
            })
        });
    </script>
@endsection
@endsection