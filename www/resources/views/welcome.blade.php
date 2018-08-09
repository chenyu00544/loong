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
        </label><label class="radio-inline fl">
            <input type="radio" name="kf_type" value="1"> 旺旺客服
        </label>
    </div>
</div>


<div class="item">
    <div class="label">&nbsp;</div>
    <div class="">
        <input type="submit" value="　确定　"
               class="btn btn-danger clearfix">
    </div>
</div>

</body>
</html>
