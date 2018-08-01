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
        return $this->where($where)->first();
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
}
