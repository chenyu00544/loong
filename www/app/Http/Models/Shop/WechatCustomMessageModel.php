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

class WechatCustomMessageModel extends Model
{
    protected $table = 'wechat_custom_message';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatCustomMessage($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWechatCustomMessage($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatCustomMessage($data)
    {
        return $this->create($data);
    }

    public function getWechatCustomMessageCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatCustomMessage($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
