<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UsersModel extends Model
{
    protected $table = 'users';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUserByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }
}
