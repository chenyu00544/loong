<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UserRankModel extends Model
{
    protected $table = 'user_rank';
    protected $primaryKey = 'rank_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUserRanks($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('discount', 'desc')
            ->orderBy('rank_id', 'asc')
            ->get();
    }
}
