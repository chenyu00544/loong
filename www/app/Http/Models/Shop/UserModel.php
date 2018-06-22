<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UserModel extends Model
{
    protected $table = 'admin_user';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOne($where)
    {
        return $this->where('user_name', $where['username'])->first();
    }
}
