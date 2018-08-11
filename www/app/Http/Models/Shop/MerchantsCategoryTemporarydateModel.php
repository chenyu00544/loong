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

class MerchantsCategoryTemporarydateModel extends Model
{
    protected $table = 'merchants_category_temporarydate';
    protected $primaryKey = 'ct_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsCategoryTemporarydates($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getMerchantsCategoryTemporarydate($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addMerchantsCategoryTemporarydate($data)
    {
        return $this->create($data);
    }

    public function delMerchantsCategoryTemporarydate($where)
    {
        return $this->where($where)
            ->delete();
    }
}
