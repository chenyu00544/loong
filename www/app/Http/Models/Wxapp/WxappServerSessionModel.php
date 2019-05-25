<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class WxappServerSessionModel extends Model
{
    protected $table = 'wxapp_server_session';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function wechatUser()
    {
        return $this->hasOne('App\Http\Models\Wxapp\WechatUserModel', 'openid', 'open_id');
    }

    public function getWxappServerSession($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['wechatUser'])
            ->first();
    }

    public function getWxappServerSessions($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('update_time', 'desc')
            ->get();
    }

    public function setWxappServerSession($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWxappServerSession($data)
    {
        return $this->create($data);
    }

    public function delWxappServerSession($where)
    {
        try {
            $this->where($where)->delete();
        } catch (\Exception $e) {

        }
    }

}
