@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">文章管理 - 文章列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>文章相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix">
                    <a href="{{url('admin/article/create')}}"
                       class="btn btn-success btn-add btn-sm fl">　添加文章　</a>
                    <div class="clearfix">
                        <div class="fr wd250 pad-tb-10">
                            <form action="{{url('admin/article')}}" method="get">
                                {{csrf_field()}}
                                <input type="text" name="keywords" value="{{$search['keywords']}}"
                                       class="form-control input-sm max-wd-190" placeholder="文章标题">
                                <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th style="width: 40px"><input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-3">文章标题</th>
                            <th class="col-sm-2">文章分类</th>
                            <th class="col-sm-1">文章重要性</th>
                            <th class="col-sm-1">是否显示</th>
                            <th class="col-sm-2">添加日期</th>
                            <th class="col-sm-3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($articles) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($articles as $article)
                            <tr>
                                <td>
                                    <input type="checkbox" name="checkboxes" value="{{$article->article_id}}"
                                           class="checkbox check-all"
                                           id="checkbox_923">
                                </td>
                                <td>{{$article->article_id}}</td>
                                <td>{{$article->title}}</td>
                                <td>@if(!empty($article->cate)) {{$article->cate->cat_name}} @endif</td>
                                <td>@if($article->article_type == 0) 普通 @else 置顶 @endif</td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($article->is_open) active @endif" data-type="is_open"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$article->article_id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    {{date('Y-m-d H:i:s', $article->add_time)}}
                                </td>
                                <td>
                                    <a href="javascript:;"
                                       class="btn btn-info btn-warning btn-sm">查看</a>
                                    <a href="{{url('admin/article/'.$article->article_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    @if($article->cat_id > 2)
                                        <a class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$article->article_id}}" data-img="{{$article->file_url}}">删除</a>
                                    @endif

                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4">
                        <div class="fl mar-all-5 checkwrap">
                            <label class="label-tip">
                                <input type="checkbox" name="all_list" value=""
                                       class="checkbox check-all fl ">全选</label>
                        </div>
                        <div class="fl mar-all-5">
                            <select name="select_type" class="form-control col-md-2 input-sm">
                                <option value="0">请选择</option>
                            </select>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-info btn-sure btn-sm mar-all-5">确定</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$articles->links()}}
                    </div>
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

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked')
                $(".check-all").each(function () {
                    $(this).prop("checked", flage);
                })
            });

            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0
                    $(this).removeClass('active');
                } else {
                    val = 1
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();

                $.post(
                    '{{url("admin/article/change")}}',
                    {
                        id: id,
                        type: tag,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            $('.chang-cate').change(function () {

                var data = {
                    id: $(this).data('id'),
                    type: $(this).data('cate'),
                    val: $(this).val(),
                    _token: '{{csrf_token()}}',
                };

                $.post(
                    '{{url("admin/article/change")}}',
                    data,
                    function (data) {
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            icon: data.code,
                            success: function (layero, index) {
                            }
                        });
                    }
                );
            });

            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                var img = $(this).data('img');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/article/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}', img: img},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection