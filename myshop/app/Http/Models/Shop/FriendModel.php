<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class FriendModel extends Model
{
    protected $table = 'friend_link';
    protected $primaryKey = 'link_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getFriends($where = [], $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('show_order', 'desc')
            ->paginate($size);;
    }

    public function setFriend($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }
}
