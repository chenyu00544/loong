@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="weight-goods-name clearfix">
            <span class="fl">商品名称：</span>
            <span class="title fl" style="white-space: nowrap;width: 350px;">{{$goods->goods_name}}</span>
        </div>
        <div class="weight-goods-name">
            <div class="form-group clearfix">
                <label class="control-label fl">商品审核：</label>
                <div class="fl">
                    <label class="radio-inline fl">
                        <input type="radio" name="examine" class="noexamine" value="1"
                               @if($goods->review_status == 1) checked="true" @endif style="margin-top:0;"> 未审核
                    </label>
                    <label class="radio-inline fl">
                        <input type="radio" name="examine" class="examine" value="3"
                               @if($goods->review_status == 3 || $goods->review_status == 4) checked="true"
                               @endif style="margin-top:0;"> 审核通过
                    </label>
                    <label class="radio-inline fl">
                        <input type="radio" name="examine" class="noexamined" value="2"
                               @if($goods->review_status == 2) checked="true" @endif style="margin-top: 0;"> 审核未通过
                    </label>
                </div>
            </div>
        </div>
        <div class="weight-goods-name text" style="@if($goods->review_status != 2) display:none @endif">
            <textarea rows="5" name="review_content" style="width: 100%" class="form-control review_content">{{$goods->review_content}}</textarea>
        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" class="goods_id" value="{{$goods->goods_id}}">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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