<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use function foo\func;
use Illuminate\Database\Eloquent\Model;

class WechatUserModel extends Model
{
    protected $table = 'wechat_user';
    protected $primaryKey = 'uid';
    public $timestamps = false;
    protected $guarded = [];

    public function user()
    {
        return $this->hasOne('App\Http\Models\Wxapp\UsersModel', 'user_id', 'ect_uid');
    }

    public function userReal()
    {
        return $this->hasOne('App\Http\Models\Wxapp\UsersRealModel', 'user_id', 'ect_uid');
    }

    public function getWechat($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['user' => function ($query) {
                $query->select(['user_id','server_id','user_name','nick_name','logo']);
            }])
            ->with(['userReal' => function ($query) {
                $query->select(['review_status','user_id']);
            }])
            ->where($where)
            ->first();
    }

    public function setWechat($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechat($data)
    {
        return $this->create($data);
    }

    public function delWechat($where)
    {
        try {
            $this->where($where)->delete();
        } catch (\Exception $e) {

        }
    }
}
