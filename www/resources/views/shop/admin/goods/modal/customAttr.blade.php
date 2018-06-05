@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="weight-goods-name">
            <div class="form-group clearfix">
                <label class="control-label fl line-hg-35">属性：<i class="glyphicon glyphicon-plus attr-add"></i></label>
                <div class="fl attr-values" style="width: 550px;">
                    <div class="fl mar-all-5">
                        <input type="text" class="form-control max-wd-110 hg25 mar-left-15" name="attr_value">
                    </div>
                </div>
            </div>
        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" class="attr_id" value="{{$id}}">
            <input type="hidden" class="goods_id" value="{{$gid}}">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        getAttr();
        $(function () {
            $('.attr-add').on('click', function () {
                $('.attr-values').append('<div class="fl mar-all-5">' +
                    '<i class="glyphicon glyphicon-minus attr-minus fl" style="margin-top: 3px"></i><input type="text" class="form-control max-wd-110 hg25" name="attr_value"></div>');
                parent.layer.iframeAuto(index);
            });

            $('.attr-values').on('click', '.attr-minus', function () {
                $(this).parent().remove();
                parent.layer.iframeAuto(index);
            });

            $('.btn-sure').on('click', function () {
                var attr_id = $('.attr_id').val();
                var attr_values = [];
                $('input[name=attr_value]').each(function () {
                    if($(this).val() != '' && $.inArray($(this).val(), attr_values) == -1){
                        attr_values.push($(this).val());
                    }
                });
                var line = 0;
                parent.$('.attr-custom').each(function () {
                    var that = this
                    if ($(this).data('attr_id') == attr_id) {
                        parent.$(that).parent().parent().find('label').remove();

                        $.each(attr_values, function (k, v) {
                            var html = '<label class="checkbox-inline">' +
                                '<input type="checkbox" name="attr_value_list1[' + attr_id + '][]"' +
                                ' class="attr_value_list1" data-key="' + k + '" data-k="' + line + '" value="' + v + '" data-attr_id="' + attr_id + '">' + v + '</label>';
                            parent.$(that).parent().before(html);
                        });
                    }
                    line++;
                });
                parent.layer.close(index);
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });

        function getAttr() {
            var attr_id = $('.attr_id').val();
            parent.$('input[name="attr_value_list1[' + attr_id + '][]"]').each(function () {
                $('.attr-values').append('<div class="fl mar-all-5">' +
                    '<i class="glyphicon glyphicon-minus attr-minus fl" style="margin-top: 3px"></i><input type="text" class="form-control max-wd-110 hg25" name="attr_value" value="'+ $(this).val() +'"></div>');
                parent.layer.iframeAuto(index);
            });
        }
    </script>
@endsection
@endsection