@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <input type="hidden" name="openid" value="{{$id}}">
        <div class="weight-goods-name">
            <div class="form-group clearfix">
                <label class="control-label fl">消息类型：</label>
                <div class="fl">
                    <label class="radio-inline fl">
                        <input type="radio" name="msg_type" class="examine" value="1"
                                style="margin-top:0;" checked> 文本消息
                    </label>
                    <label class="radio-inline fl">
                        <input type="radio" name="msg_type" class="examine" value="2"
                                style="margin-top:0;"> 图片消息
                    </label>
                    <label class="radio-inline fl">
                        <input type="radio" name="msg_type" class="examine" value="3"
                                style="margin-top: 0;"> 小程序卡片消息
                    </label>
                </div>
            </div>
        </div>
        <div class="weight-goods-name text-msg">

        </div>

        <div class="weight-goods-name img-msg hidden">

        </div>

        <div class="weight-goods-name wxapp-msg hidden">

        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" class="goods_id" value="">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        $(function () {
            $('.noexamined').on('click', function () {
                $('.text').show();
                parent.layer.iframeAuto(index);
            });

            $('.examine').on('click', function () {
                $('.text').hide();
                parent.layer.iframeAuto(index);
            });
            $('.noexamine').on('click', function () {
                $('.text').hide();
                parent.layer.iframeAuto(index);
            });

            //给父页面传值
            $('.btn-sure').on('click', function () {
                var examines = $('input[name=examine]');
                examines.each(function (k, v) {
                    if ($(v).prop('checked')) {
                        var goods_id = $('.goods_id').val();
                        var examine_id = parent.$('#examine_' + goods_id);
                        changeExamine(goods_id, $(v).val());
                        examine_id.removeClass();
                        switch ($(v).val()) {
                            case '1':
                                examine_id.text('未审核');
                                examine_id.addClass('oranges');
                                break;
                            case '2':
                                examine_id.text('审核未通过');
                                examine_id.addClass('red');
                                break;
                            case '3':
                                examine_id.text('审核已通过');
                                examine_id.addClass('blue');
                                break;
                            default:
                                break;
                        }
                    }
                });
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });

        function changeExamine(id, exa) {
            var review_content = $('.review_content').val();
            $.post(
                '{{url("admin/goods/change")}}',
                {
                    id: id,
                    type: 'is_examine',
                    val: exa,
                    review_content: review_content,
                    _token: '{{csrf_token()}}'
                },
                function (data) {
                    parent.layer.close(index);
                }
            );
        }
    </script>
@endsection
@endsection