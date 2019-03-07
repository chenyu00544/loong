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

class WechatModel extends Model
{
    protected $table = 'wechat';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechat($where, $column = ['*'])
    {
        return $this->select($column)
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
        try{
            $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
