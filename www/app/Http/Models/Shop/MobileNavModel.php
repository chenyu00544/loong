<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class MobileNavModel extends Model
{
    protected $table = 'mobile_nav';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMoveNavByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }

    public function setMoveNav($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delMoveNav($where)
    {
        return $this->where($where)->delete();
    }
}
