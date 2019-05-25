<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

class WxappServerMsgModel extends Model
{
    protected $table = 'wxapp_server_msg';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function wechatUser()
    {
        return $this->hasOne('App\Http\Models\Shop\WechatUserModel', 'openid', 'open_id');
    }


    public function getWxappServerMsgByPage($where = array(), $search = '', $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->leftJoin('wechat_user', 'wechat_user.openid', '=', 'wxapp_server_msg.open_id')
            ->leftJoin('users', 'users.user_id', '=', 'wechat_user.ect_uid')
            ->where($where);
        if ($search != '') {
            $m->where(DB::raw('concat("nickname","nick_name")'), 'like', '%' . $search . '%');
        }
        return $m->orderBy('update_time', 'desc')
            ->paginate($size);
    }

    public function getWxappServerMsg($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['wechatUser'])
            ->first();
    }

    public function getWxappServerMsgs($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('create_time', 'desc')
            ->get();
    }

    public function setWxappServerMsg($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWxappServerMsg($data)
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
