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

class MobileAuthModel extends Model
{
    protected $table = 'mobile_auth';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMobileAuths($where)
    {
        return $this->whereIn('type', $where)
            ->get();
    }

    public function getMobileAuth($where)
    {
        return $this->where($where)
            ->first();
    }

    public function setMobileAuth($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMobileAuth($data)
    {
        return $this->create($data);
    }

    public function delMobileAuth($where)
    {
        return $this->where($where)
            ->delete();
    }
}
