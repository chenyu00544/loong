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
            ->join('ad_position', 'ad_position.position_id', '=', 'ad.position_id')
            ->where($where);
        if (!empty($search['keywords'])) {
            $m->where('ad_name', 'like', '%' . $search['keywords'] . '%');
        }
        if (!empty($search['advance_date']) && $search['advance_date'] > 0) {
            if ($search['advance_date'] == 1) {
                $m->where([['end_time', '>', time()], ['end_time', '<', time() + 86400]]);
            } elseif ($search['advance_date'] == 2) {
                $m->where('end_time', '<', time());
            }
        }
        return $m->orderBy('ad_id', 'desc')
            ->paginate($size);
    }

    public function getAd($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setAd($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delAd($where)
    {
        return $this->where($where)
            ->delete();
    }
}
