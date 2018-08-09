<html>
<head></head>
<body>

<div class="form-group">
    <label class="col-sm-4 control-label">公司名称：</label>
    <div class="col-sm-4">
        <input type="text" name="shop_name" class="form-control input-sm"
               value="" placeholder="店铺名称"/>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-4 control-label">所在城市：</label>
    <div class="col-sm-4">
        <select name="city" class="form-control input-sm shop_city">
            <option value="0">市</option>
            <option value="0">市</option>
            <option value="0">市</option>
        </select>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-4 control-label">客服淘宝旺旺：</label>
    <div class="col-sm-4">
        <textarea name="kf_ww" class="form-control" row="5" placeholder="客服淘宝旺旺"></textarea>
    </div>
    <div class="notic col-sm-3">旺旺客服名称和号码请用“|”隔开（如：客服2|654321），如果您有多个客服的旺旺号码，请换行。</div>
</div>

<div class="form-group">
    <label class="col-sm-4 control-label">二维码中间Logo：</label>
    <div class="col-sm-4">
        <input type="file" name="qrcode_thumb" class="fl">
        <input type="hidden" name="qrcode_thumb_bak" value="">
        <a href="{{url()}}"
           target="_blank" class="nyroModal">
            <i class="glyphicon glyphicon-picture top5"
               data-tooltipimg="{{url()}}"
               ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
        </a>
    </div>
    <div class="notic col-sm-3">80*80像素。</div>
</div>

<div class="form-group">
    <label class="col-sm-4 control-label"><b>*</b>会员等级：</label>
    <div class="col-sm-6">
        <div class="checkbox">
            <label class="mar-right-10">
                <input type="checkbox" name="rank[]" value="1" checked="">会员
            </label>
            <label class="mar-right-10">
                <input type="checkbox" name="rank[]" value="2" checked="">会员
            </label>
        </div>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-4 control-label">客服样式：</label>
    <div class="col-sm-4">
        <label class="radio-inline fl">
            <input type="radio" name="kf_type" value="0"> QQ客服
        </label>
        <label class="radio-inline fl">
            <input type="radio" name="kf_type" value="1"> 旺旺客服
        </label>
    </div>
</div>

<table class="table table-hover table-condensed" style="margin-bottom: 2px">
    <thead>
    <tr>
        <th width="5%">编号</th>
        <th width="10%">[ID]会员名称</th>
        <th width="12%">店铺名称</th>
        <th width="8%">公司类型</th>
        <th width="5%">等级</th>
        <th width="12%">主营类目</th>
        <th width="10%">入驻审核状态</th>
        <th width="7%">排序</th>
        <th width="5%">店铺街</th>
        <th width="6%">在线客服</th>
        <th width="7%">店铺信息</th>
        <th width="14%" class="text-center">操作</th>
    </tr>
    </thead>
    @if($stores->count() == 0)
        <tbody>
        <tr class="">
            <td class="no-records" colspan="20">没有找到任何记录</td>
        </tr>
        </tbody>
    @else
        <tbody>
        @foreach($stores as $store)
            <tr class="">
                <td>{{$store->shop_id}}</td>
                <td>[{{$store->user_id}}]{{$store->user->user_name}}</td>
                <td>
                    <font class="red">{{$store->rz_shopName}}</font>
                </td>
                <td>
                    @if(!empty($store->msf->company_type)) {{$store->msf->company_type}} @else
                        空 @endif
                </td>
                <td>
                    <img src="{{url($store->grade_img)}}" alt="" width="25" height="25">
                </td>
                <td>
                    @if(!empty($store->category->cat_name)) {{$store->category->cat_name}} @else
                        空 @endif
                </td>
                <td>
                    @if($store->steps_audit == 1)
                        @if($store->merchants_audit == 0)
                            <font class="gray">未审核</font>
                        @elseif($store->merchants_audit == 1)
                            <font class="skyblue">审核已通过</font>
                        @elseif($store->merchants_audit == 2)
                            <font class="oranges">审核未通过</font>
                        @endif
                    @else
                        <font class="oranges">尚未提交信息</font>
                    @endif
                </td>
                <td>
                    <input class="form-control input-sm order" name="sort_order" type="text"
                           data-id="{{$store->shop_id}}" value="{{$store->sort_order}}"
                           autocomplete="off">
                </td>
                <td>
                    <div class="switch-wrap clearfix">
                        <div class="switch @if($store->is_street) active @endif"
                             data-type="is_street"
                             title="是">
                            <div class="circle"></div>
                            <input type="hidden" value="{{$store->shop_id}}">
                        </div>
                    </div>
                </td>
                <td>
                    <div class="switch-wrap clearfix">
                        <div class="switch @if($store->is_im) active @endif" data-type="is_im"
                             title="是">
                            <div class="circle"></div>
                            <input type="hidden" value="{{$store->shop_id}}">
                        </div>
                    </div>
                </td>
                <td>
                    @if($store->review_status == 1)
                        <font class="gray">未审核</font>
                    @elseif($store->review_status == 2)
                        <font class="red">审核未通过</font>
                    @elseif($store->review_status == 3)
                        <font class="skyblue">审核已通过</font>
                    @endif
                </td>
                <td class="text-center">
                    <a type="button" href="{{url('admin/storelist/info/'.$store->user_id)}}"
                       class="btn btn-info btn-edit btn-sm">店铺管理</a>
                    <a type="button" href="{{url('admin/storelist/'.$store->user_id.'/edit')}}"
                       class="btn btn-info btn-edit btn-sm">编辑</a>
                    <a type="button" href="javascript:;" class="btn btn-danger btn-del btn-sm"
                       data-id="{{$store->cou_id}}">删除</a>
                </td>
            </tr>
        @endforeach
        </tbody>
    @endif
</table>

<div class="item">
    <div class="label">&nbsp;</div>
    <div class="">
        <input type="submit" value="　确定　"
               class="btn btn-danger clearfix">
    </div>
</div>

</body>
</html>
