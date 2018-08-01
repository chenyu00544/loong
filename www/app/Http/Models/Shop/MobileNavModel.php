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

class MobileNavModel extends Model
{
    protected $table = 'mobile_nav';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMobileNavByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }

    public function getMobileNav($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setMobileNav($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMobileNav($data)
    {
        return $this->create($data);
    }

    public function delMobileNav($where)
    {
        return $this->where($where)->delete();
    }
}
