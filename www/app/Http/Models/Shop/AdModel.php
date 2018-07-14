<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class AdModel extends Model
{
    protected $table = 'ad';
    protected $primaryKey = 'ad_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAdByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($search['keywords'])) {
            $m->where('ad_name', 'like', '%' . $search['keywords'] . '%');
        }
        return $m->orderBy('ad_id', 'desc')
            ->paginate($size);
    }
}
