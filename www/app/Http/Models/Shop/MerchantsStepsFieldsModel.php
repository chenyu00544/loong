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

class MerchantsStepsFieldsModel extends Model
{
    protected $table = 'merchants_steps_fields';
    protected $primaryKey = 'fid';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsStepsFields($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addMerchantsStepsFields($data)
    {
        return $this->create($data);
    }
}
