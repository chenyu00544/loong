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

class FavourableGoodsModel extends Model
{
    protected $table = 'favourable_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getFGs($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getFG($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setFG($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function addFG($updata)
    {
        return $this->create($updata);
    }

    public function delFG($where)
    {
        return $this->where($where)
            ->delete();
    }

}
