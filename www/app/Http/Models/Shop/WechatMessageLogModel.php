<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2019/5/30
 * Time: 21:53
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class WechatMessageLogModel extends Model
{
    protected $table = 'wechat_message_log';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatMessageLog($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->first();
    }

    public function setWechatMessageLog($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatMessageLog($data)
    {
        return $this->create($data);
    }

    public function delWechatMessageLog($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
