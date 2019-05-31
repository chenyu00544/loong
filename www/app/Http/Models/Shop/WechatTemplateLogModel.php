<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2019/5/30
 * Time: 21:53
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class WechatTemplateLogModel extends Model
{
    protected $table = 'wechat_template_log';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatTemplateLog($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->first();
    }

    public function setWechatTemplateLog($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }
}