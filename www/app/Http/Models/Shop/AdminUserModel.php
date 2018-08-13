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

class AdminUserModel extends Model
{
    protected $table = 'admin_user';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAdminUserByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('user_id', 'asc')
            ->paginate($size);
    }

    public function getAdminUser($where)
    {
        $uid = 1048575;
        $this->table($uid);
        $rep = $this->where($where)->first();
        return $rep;
    }

    public function setAdminUser($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAdminUser($data)
    {
        return $this->create($data);
    }

    public function delAdminUser($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function countAdminUser($where)
    {
        return $this->where($where)->count();
    }

    public function test($where)
    {
        $this->table($where['uid']);
        return $this->toSql();
    }

    //分表
    public function table($uid, $bit = 5, $seed = 20)
    {
        if($uid >> $seed != 0){
            $tb = $this->table . '_' . sprintf('%0' . $bit . 'd', ($uid >> $seed));
            $this->setTable($tb);
        }
    }
}
