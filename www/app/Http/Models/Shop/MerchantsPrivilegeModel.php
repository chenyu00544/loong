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

class MerchantsPrivilegeModel extends Model
{
    protected $table = 'merchants_privilege';
    protected $primaryKey = 'grade_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsPrivilege($where, $column = ['*'])
    {

    }

    public function setMerchantsPrivilege($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMerchantsPrivilege($data)
    {

    }

    public function delMerchantsPrivilege($where)
    {

    }
}
