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

class WechatMediaModel extends Model
{
    protected $table = 'wechat_media';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatMediaByPage($where=array(), $column = ['*'], $size = 5)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->paginate($size);
    }

    public function getWechatMediasIn($inwhere=array(), $column = ['*'])
    {
        return $this->select($column)
            ->whereIn('id',$inwhere)
            ->get();
    }

    public function getWechatMedias($where=array(), $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getWechatMedia($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWechatMedia($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatMedia($data)
    {
        return $this->create($data);
    }

    public function getWechatMediaCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatMedia($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
