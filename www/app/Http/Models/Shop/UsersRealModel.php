<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UsersRealModel extends Model
{
    protected $table = 'users_real';
    protected $primaryKey = 'real_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUserRealsByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->join('users', 'users_real.user_id', '=', 'users.user_id')
            ->where($where)
            ->orderBy('real_id', 'desc')
            ->paginate($size);
    }
}
