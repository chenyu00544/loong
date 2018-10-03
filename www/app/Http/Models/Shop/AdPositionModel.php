<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class AdPositionModel extends Model
{
    protected $table = 'ad_position';
    protected $primaryKey = 'position_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAdPosByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->leftJoin('merchants_shop_information', 'merchants_shop_information.user_id', '=', 'ad_position.user_id')
            ->where($where);
        if (!empty($search['keywords'])) {
            $m->where('position_name', 'like', '%' . $search['keywords'] . '%');
        }
        return $m->orderBy('sort', 'desc')
            ->paginate($size);
    }

    public function getAdPoses($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getAdPos($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setAdPos($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAdPos($data)
    {
        return $this->create($data);
    }

    public function delAdPos($where)
    {
        return $this->where($where)
            ->delete();
    }
}
