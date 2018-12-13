@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">文章管理 - 添加文章</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="curr fl">
                        <a href="javascript:;">通用信息</a>
                    </li>
                    <li class="fl">
                        <a href="javascript:;">文章内容</a>
                    </li>
                    <li class="fl">
                        <a href="javascript:;">关联商品</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商店相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/article')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="switch-info">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">文章标题：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" class="form-control" value=""
                                           placeholder="文章标题">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">文章分类：</label>
                                <input type="hidden" name="parent_id" value="1">
                                <div class="col-sm-8 pre-cate">
                                    <div class="cate-option fl">
                                        <select class="form-control select"
                                                onchange="setNextCate(this)" data-parent="0">
                                            @foreach($cates as $cate)
                                                <option value="{{$cate->cat_id}}">{{$cate->cat_name}}</option>
                                            @endforeach
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">是否显示：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl ml10">
                                        <input type="radio" name="is_open" value="1" checked> 是
                                    </label>
                                    <label class="radio-inline fl ml10">
                                        <input type="radio" name="is_open" value="0"> 否
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">文章重要性：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl ml10">
                                        <input type="radio" name="article_type" value="1"> 置顶
                                    </label>
                                    <label class="radio-inline fl ml10">
                                        <input type="radio" name="article_type" value="0" checked> 普通
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">文章作者：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="author" class="form-control" value=""
                                           placeholder="文章作者">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">作者email：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="author_email" class="form-control" value=""
                                           placeholder="作者email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">关键字：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="keywords" class="form-control" value=""
                                           placeholder="关键字">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">网页描述：</label>
                                <div class="col-sm-4">
                                    <textarea name="description" id="" cols="30" rows="5"
                                              class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">外部链接：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="link_url" class="form-control" value="http://"
                                           placeholder="外部链接">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">上传图片：</label>
                                <div class="col-sm-4">
                                    <input type="file" name="textfile" value="" class="fl">
                                </div>
                            </div>
                        </div>

                        <div class="switch-info mar-all-10" style="display: none;">
                            <script id="editor" name="content" type="text/plain"></script>
                        </div>

                        <div class="switch-info" style="display: none;">
                            <div class="step-near clearfix" style="margin-left:1%;">
                                <input name="" value="" class="form-control fl max-wd-350 input-sm keyword"
                                       placeholder="关键字">
                                <a class="btn btn-primary btn-search mar-left-10 fl input-sm"
                                   style="padding: 4px 10px;">搜索</a>
                            </div>

                            <div class="sort-info clearfix">
                                <div id="cate-add" class="clearfix">
                                    <div class="clearfix">
                                        <h4 class="fl goods-s">可选商品</h4><h4 class="fl goods-s">已选商品</h4>
                                    </div>
                                    <div class="sort-list sort-list-one"
                                         style="width: 45%;margin-left:1%;margin-right: 8%">
                                        <div class="sort-list-warp">
                                            <div class="category-list ps-container ps-active-y">
                                                <ul class="s-list">
                                                </ul>
                                            </div>
                                            <div class="sort-point"></div>
                                        </div>
                                    </div>
                                    <div class="sort-list sort-list-one" style="width: 45%;margin-right: 0%">
                                        <div class="sort-list-warp">
                                            <div class="category-list ps-container ps-active-y">
                                                <ul class="sd-list">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix">
                                        <div class="fl goods-s-left">
                                            <a class="btn btn-default select-all mar-left-10 fl btn-sm">全选</a>
                                            <a class="btn btn-danger select-add mar-left-10 fl btn-sm">添加</a>
                                        </div>
                                        <div class="fl goods-s-right">
                                            <a class="btn btn-default select-all-r mar-left-10 fl btn-sm">全选</a>
                                            <a class="btn btn-danger select-remove mar-left-10 fl btn-sm">移除</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.config.js')}}"></script>
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.all.min.js')}}"></script>
    <script>
        $(function () {
            var ue = UE.getEditor('editor', {
                initialFrameHeight: 500,
                scaleEnabled: true
            });
            ue.ready(function () {
                // ue.setHeight(500);
            });

            $('.btn-search').on('click', function () {
                var keywords = $('.keyword').val();
                if (!keywords) {
                    layer.msg('请填写关键字');
                }
                $.post("{{url('admin/search')}}", {
                    '_token': "{{csrf_token()}}",
                    type: 3,
                    keywords: keywords
                }, function (data) {
                    var html = '';
                    for (i in data.data) {
                        html += '<li class=""><i class="sc-icon sc_icon_ok"></i>' +
                            '<a href="javascript:;" data-value="' + data.data[i].id + '" class="s-goods-name">' + data.data[i].name + '</a>' +
                            '<input type="hidden" name="" value="' + data.data[i].id + '">' +
                            '</li>';
                    }
                    $('.s-list').html(html);
                })
            });
            $('.s-list, .sd-list').on('click', 'li', function () {
                if ($(this).hasClass('current')) {
                    $(this).removeClass('current');
                } else {
                    $(this).addClass('current');
                }
            });

            //未选全选
            $('.select-all').on('click', function () {
                var bool = true;
                $('.s-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        bool = false;
                    }
                });
                if (bool) {
                    $('.s-list').find('li').addClass('current');
                } else {
                    $('.s-list').find('li').removeClass('current');
                }
            });
            //添加
            $('.select-add').on('click', function () {
                var ids = [];
                $('.s-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        var text = $(this).find('a').html();
                        var goods_id = $(this).find('input').val();
                        var bool = true;
                        $('.sd-list').find('li').each(function () {
                            if (goods_id == $(this).find('a').data('value')) {
                                bool = false;
                            }
                        });
                        var html = '<li class=""><i class="sc-icon sc_icon_no"></i><a href="javascript:;" data-value="' + goods_id + '"' +
                            'class="s-goods-name">' + text + '</a><input type="hidden" name="search[]" value="' + data.data[i].id + '"></li>';
                        if (bool) {
                            ids.push(goods_id);
                            $('.sd-list').append(html);
                        }
                    }
                });
            });

            //已选全选
            $('.select-all-r').on('click', function () {
                var bool = true;
                $('.sd-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        bool = false;
                    }
                });
                if (bool) {
                    $('.sd-list').find('li').addClass('current');
                } else {
                    $('.sd-list').find('li').removeClass('current');
                }
            });
            //移除
            $('.select-remove').on('click', function () {
                var ids = [];
                $('.sd-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        var goods_id = $(this).find('a').data('value');
                        ids.push(goods_id);
                        $(this).remove();
                    }
                });
            });
        });

        function setNextCate(that) {
            var id = $(that).val();
            var parent = $(that).data('parent');
            $('input[name="parent_id"]').val(id);
            if (id > 0 && parent == 0) {
                var html = '';
                $.post("{{url('admin/artcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<div class="cate-option fl"><select class="form-control select" onchange="setNextCate(this)"><option value="0">顶级分类</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.cat_id + '">' + v.cat_name + '</option>';
                        })
                        html += '</select></div>';
                        $(that).parent().nextAll().remove();
                        $('.pre-cate').append(html);
                    } else {
                        $(that).parent().nextAll().remove();
                    }
                })
            }
        }
    </script>
@endsection
@endsection