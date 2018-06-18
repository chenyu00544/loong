<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class MemberPriceModel extends Model
{
    protected $table = 'member_price';
    protected $primaryKey = 'price_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMemberPrice($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function addMemberPrice($data)
    {
        return $this->create($data);
    }
}
