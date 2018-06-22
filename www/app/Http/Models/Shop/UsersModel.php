<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UsersModel extends Model
{
    protected $table = 'users';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUsersByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->leftJoin('user_rank', 'users.user_rank', '=', 'user_rank.rank_id')
            ->where($where)
            ->paginate($size);
    }

    public function getUser($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setUser($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addUser($data)
    {
        return $this->create($data);
    }

    public function delUser($where)
    {
        return $this->where($where)->delete();
    }

    public function countUser($where)
    {
        return $this->where($where)
            ->count();
    }
}
