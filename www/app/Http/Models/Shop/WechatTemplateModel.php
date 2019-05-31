<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2019/5/30
 * Time: 21:53
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class WechatTemplateModel extends Model
{
    protected $table = 'wechat_template_log';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatTemplate($where, $column = ['*'])
    {

    }

}