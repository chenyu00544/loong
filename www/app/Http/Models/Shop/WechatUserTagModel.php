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

class WechatUserTagModel extends Model
{
    protected $table = 'wechat_user_tag';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatUserTag($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWechatUserTag($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatUserTag($data)
    {
        return $this->create($data);
    }

    public function getWechatUserTagCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatUserTag($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
