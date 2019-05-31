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

class WechatMassageHistoryModel extends Model
{
    protected $table = 'wechat_user';
    protected $primaryKey = 'uid';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatMassageHistory($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWechatMassageHistory($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatMassageHistory($data)
    {
        return $this->create($data);
    }

    public function getWechatMassageHistoryCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatMassageHistory($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
