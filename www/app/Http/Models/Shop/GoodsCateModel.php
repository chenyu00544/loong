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

class GoodsCateModel extends Model
{
    protected $table = 'goods_cate';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsCates($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setGoodsCate($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addGoodsCate($data)
    {
        return $this->create($data);
    }

    public function delGoodsCate($where)
    {
        return $this->where($where)
            ->delete();
    }
}
