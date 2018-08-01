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

class WxappModel extends Model
{
    protected $table = 'wxapp';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWxapp($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setWxapp($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWxapp($data)
    {
        return $this->create($data);
    }
}
