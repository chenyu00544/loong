@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;">
    <div class="pad-all-15">
        <div class="clearfix">
            <div class="search-header ">
                <div class="brand fl">
                    <div class="selection">
                        <input type="hidden" name="ids" value="{{$id}}">
                        <input type="text" id="brand_name"
                               class="form-control max-wd-190 hg30 fl" data-filter="brand_name"
                               autocomplete="off" value="请选择品牌" readonly="">
                        <input type="hidden" name="brand_id" id="brand_id" value="0">
                        <div class="form_prompt"></div>
                    </div>
                    <div class="brand-select-container" style="display: none;">
                        <div class="brand-top">
                            <div class="letter">
                                <ul>
                                    <li>
                                        <a href="javascript:;" data-letter="">全部品牌</a>
                                    </li>
                                    @for($letter = 65; $letter<=90;$letter++)
                                        <li>
                                            <a href="javascript:;"
                                               data-letter="{{chr($letter)}}">{{chr($letter)}}</a>
                                        </li>
                                    @endfor
                                    <li>
                                        <a href="javascript:;" data-letter="QT">其他</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="b-search">
                                <input id="search_brand_keyword" type="text"
                                       class="form-control max-wd-190 hg30 fl"
                                       autocomplete="off" placeholder="品牌名">
                                <a href="javascript:;" class="btn-mini"><i
                                            class="glyphicon glyphicon-search"></i></a>
                            </div>
                        </div>
                        <div class="brand-list ps-container ps-active-y">
                            <ul>
                                <li data-id="0" data-name="请选择品牌" class="blue cursor">取消选择</li>
                                @foreach($brands as $bVal)
                                    <li data-id="{{$bVal['id']}}"
                                        data-name="{{$bVal['brand_name']}}">
                                        <em>{{$bVal['brand_first_char']}}</em>{{$bVal['brand_name']}}
                                    </li>

                                @endforeach
                            </ul>
                            <div class="brand-not" style="display: none;">没有符合"<strong
                                        class="red"></strong>"条件的品牌
                            </div>
                        </div>
                    </div>
                </div>
                <div class="search fl mar-left-10">
                    <input type="text" name="keywords" class="form-control input-sm" value="" placeholder="名称">
                </div>
                <div><a href="javascript:;" class="btn btn-primary btn-search btn-sm mar-left-10">搜索</a></div>
            </div>
            <div class="goods-list mar-top-10">
                <ul>
                </ul>
            </div>
            <div class="text-center mar-top-10">
                <a type="button" href="javascript:;" class="btn btn-danger">确定</a>
                <a type="button" href="javascript:;" class="btn btn-default mar-left-20">取消</a>
            </div>
        </div>
    </div>
    </body>
@section('script')
    <script>
        $(function () {
            $('body').on('click', function () {
                $('.brand-select-container').hide();
            });
            //选择品牌
            $('#brand_name').on('click', function (event) {
                $('.brand-select-container').show();
                event.stopPropagation();
            });
            $('.brand-select-container').on('click', function (event) {
                event.stopPropagation();
            });
            $('.brand-top .letter ul li a').on('click', function () {
                var letter = $(this).data('letter');
                var param = {
                    '_token': '{{csrf_token()}}',
                    letter: letter
                };
                searchBrand(param, letter);
            });
            $('.btn-mini').on('click', function () {
                var keywords = $('#search_brand_keyword').val();
                if (keywords != '') {
                    var param = {
                        '_token': '{{csrf_token()}}',
                        keywords: keywords
                    };
                    searchBrand(param, keywords);
                }
            });
            $('.brand-list ul').on('click', 'li', function () {
                $('.brand-select-container').hide();
                $('#brand_name').val($(this).data('name'));
                $('#brand_id').val($(this).data('id'));
            });

            //添加品牌
            $(".brand-add").on('click', function () {
                layer.open({
                    type: 2,
                    area: ['1100px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加品牌',
                    content: ["{{url('admin/brand/create')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            $('.goods-list').on('click', '.choose a', function () {
                if ($(this).hasClass('on')) {
                    $(this).removeClass('on');
                    $(this).find('i').removeClass('glyphicon-ok');
                    $(this).find('i').addClass('glyphicon-plus');
                    $(this).find('span').html('选择');
                } else {
                    $(this).addClass('on');
                    $(this).find('i').removeClass('glyphicon-plus');
                    $(this).find('i').addClass('glyphicon-ok');
                    $(this).find('span').html('已选择');
                }
            });

            $('.btn-search').on('click', function () {
                var brand_id = $('input[name=brand_id]').val();
                var keywords = $('input[name=keywords]').val();
                var ids = $('input[name=ids]').val();
                $.post("{{url('admin/search/dialog')}}", {
                    _token: '{{csrf_token()}}',
                    brand_id: brand_id,
                    keywords: keywords
                }, function (data) {
                    if(data.code === 1){log(1231);
                        var html = '';
                        $.each(data.data, function (k, v) {
                            html += '<li>' +
                                '<div class="img">' +
                                '<img src="' + v.original_img + '" alt="">' +
                                '</div>' +
                                '<div class="name">' + v.goods_name + '</div>' +
                                '<div class="price">￥' + v.shop_price + '</div>' +
                                '<div class="choose mar-top-5">';
                            if (ids.indexOf('' + v.goods_id) > 0) {
                                html += '<a href="javascript:;" class="on"><i class="glyphicon glyphicon-ok"></i><span>已选择</span></a></div></li>';
                            } else {
                                html += '<a href="javascript:;" class=""><i class="glyphicon glyphicon-plus"></i><span>选择</span></a></div></li>';
                            }
                        });
                        $('.goods-list ul').html(html);
                    }
                })
            });
        });

        //品牌搜索
        function searchBrand(param, keywords) {
            $.post("{{url('admin/brand/search')}}", param, function (data) {
                if (data.length > 0) {
                    $('.brand-not').hide();
                    var html = '<li data-id="0" data-name="请选择品牌" class="blue cursor">取消选择</li>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<li data-id="' + data[i].id + '" data-name="' + data[i].brand_name + '"><em>' + data[i].brand_first_char + '</em>' + data[i].brand_name + '</li>'
                    }
                    $('.brand-list ul').html(html);
                } else {
                    $('.brand-list ul').html("");
                    $('.brand-not').show();
                    $('.brand-not strong').html(keywords);
                }
            });
        }
    </script>
@endsection
@endsection