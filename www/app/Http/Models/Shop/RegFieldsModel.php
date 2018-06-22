<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class RegFieldsModel extends Model
{
    protected $table = 'reg_fields';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getRegFields($where =[], $column =['*'])
    {
        $where['display'] = 1;
        return $this->select($column)
            ->where($where)
            ->get();
    }
}
