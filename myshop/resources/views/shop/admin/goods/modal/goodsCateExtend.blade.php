@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #fff;padding: 0 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="pb-bd">
            <div class="pb-ct">
                <div class="extension-category clearfix">
                    <div class="filter"></div>
                    <div class="cate-info clearfix">
                        <div class="cate-list cate-list-one">
                            <div class="cate-list-warp">
                                <div class="category-list ps-container ps-active-y">
                                    <ul ectype="category" data-cat_level="1">
                                        <li data-cat_name="" data-cat_id="0" data-cat_level="1" class="current">
                                            <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                        </li>
                                        @foreach($comCates as $comCate)
                                            <li data-cat_id="{{$comCate->id}}"
                                                data-cat_name="{{$comCate->cat_name}}" data-cat_level="1"
                                                class="">
                                                <a href="javascript:;"><i
                                                            class="sc-icon"></i>{{$comCate->cat_name}}</a>
                                            </li>
                                        @endforeach
                                    </ul>
                                </div>
                                <div class="sort-point"></div>
                            </div>
                        </div>
                        <div class="cate-list cate-list-one">
                            <div class="cate-list-warp">
                                <div class="category-list ps-container ps-active-y">
                                    <ul ectype="category" data-cat_level="2">
                                        <li data-cat_name="" data-cat_id="0" data-cat_level="2">
                                            <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="sort-point"></div>
                        </div>
                        <div class="cate-list">
                            <div class="cate-list-warp">
                                <div class="category-list ps-container ps-active-y">
                                    <ul ectype="category" data-cat_level="3">
                                        <li data-cat_name="" data-cat_id="0" data-cat_level="3">
                                            <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="ext_cat_id" value="0">
                    </div>
                    <a href="javascript:;" class="btn btn-primary fr btn-sm btn-add">添加</a>
                </div>
            </div>
            <div class="pb-ft">
                <input type="hidden" name="goods_id" value="{{$id}}">
                <a class="btn btn-danger mar-all-10 btn-sure">确定</a>
                <a class="btn btn-default mar-all-10 btn-close">取消</a>
            </div>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        $(function () {

            //选择商品分类
            $('.category-list').on('click', 'li', function () {
                $(this).parent().find('li').removeClass('current');
                $(this).addClass('current');
                var cate_id = $(this).data('cat_id');
                var cate_level = $(this).data('cat_level');
                getNextCate(cate_id, cate_level);
                var c_id = 0;
                $('.current').each(function () {
                    if (cate_id == 0 && cate_level > 1) {
                        if ($(this).data('cat_id') != 0) {
                            c_id = $(this).data('cat_id');
                        } else {
                            cate_id = c_id;
                        }
                    }
                });
                $('input[name=ext_cat_id]').val(cate_id);
            });

            //添加扩展分类
            $('.btn-add').on('click', function () {
                var bool = true;
                var cat_id = $('input[name=ext_cat_id]').val();
                var goods_id = $('input[name=goods_id]').val();
                $('input[name^=other_cat]').each(function (k, v) {
                    if ($(v).val() == cat_id) {
                        bool = false;
                    }
                });
                if (bool && cat_id > 0) {
                    $.post(
                        "{{url('admin/goods/addcateext')}}",
                        {
                            '_token': '{{csrf_token()}}',
                            goods_id: goods_id,
                            cat_id: cat_id
                        },
                        function (data) {
                            if(data.code == 1){
                                var str = '';
                                $('.current').each(function () {
                                    str += $(this).data('cat_name') + '>';
                                });
                                str = str.substr(0, str.length - 1);
                                var html = '<span class="filter-item">' +
                                    '           <span>' + str + '</span>' +
                                    '           <a herf="javascript:void(0);" class="delete"></a>' +
                                    '           <input type="hidden" name="other_cat[]" value="' + cat_id + '">' +
                                    '       </span>';
                                $('.filter').append(html);
                                parent.layer.iframeAuto(index);
                            }
                        });
                } else {
                    if ($('.red_notic').length == 0) {
                        var topStr = '';
                        if (cat_id == 0) {
                            topStr = '请选择分类';
                        } else {
                            topStr = '不能添加重复分类';
                        }
                        $(this).before('<div class="red_notic red">' + topStr + '</div>');
                        setTimeout(function () {
                            $('.red_notic').remove();
                        }, 3000);
                    }
                }
                parent.layer.iframeAuto(index);
            });

            //删除扩展分类
            $('.filter').on('click', '.delete', function () {
                $(this).parent().remove();
                parent.layer.iframeAuto(index);
            });

            //给父页面传值
            $('.btn-sure').on('click', function () {
                parent.layer.close(index);
            });

            //关闭iframe
            $('.btn-close').on('click', function () {
                parent.layer.close(index);
            });
        });

        function getNextCate(parent_id, cat_level) {
            var id = parent_id;
            var level = cat_level + 1;
            if (id > 0 && cat_level < 3) {
                var html = '';
                $.post("{{url('admin/comcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                            '   </li>';
                        $.each(data.data, function (k, v) {
                            html += '<li data-cat_name="' + v.cat_name + '" data-cat_id="' + v.id + '" data-cat_level="' + level + '" class="">\n' +
                                '       <a href="javascript:;"><i class="sc-icon"></i>' + v.cat_name + '</a>\n' +
                                '   </li>';
                        });
                        $('.category-list ul').each(function () {
                            if ($(this).data('cat_level') == level) {
                                $(this).html(html);
                            }
                        })
                    } else {
                        $('.category-list ul').each(function () {
                            if ($(this).data('cat_level') >= level) {
                                $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                                    '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                                    '   </li>');
                            }
                        })
                    }
                })
            } else {
                $('.category-list ul').each(function () {
                    if ($(this).data('cat_level') >= level) {
                        $(this).html('<li data-cat_name="" data-cat_id="0" data-cat_level="' + level + '" class="">\n' +
                            '       <a href="javascript:;"><i class="sc-icon"></i>请选择分类</a>\n' +
                            '   </li>');
                    }
                });
            }
        }
    </script>
@endsection
@endsection