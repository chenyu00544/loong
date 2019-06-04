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

class WechatMenuModel extends Model
{
    protected $table = 'wechat_menu';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function subMenus()
    {
        return $this->hasMany('App\Http\Models\Shop\WechatMenuModel', 'pid','id');
    }

    public function getWechatMenus($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['subMenus'])
            ->where($where)
            ->orderBy('sort', 'DESC')
            ->get();
    }

    public function getWechatMenu($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWechatMenu($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatMenu($data)
    {
        return $this->create($data);
    }

    public function delWechatMenu($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
