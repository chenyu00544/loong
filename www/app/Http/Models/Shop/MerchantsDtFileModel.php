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

class MerchantsDtFileModel extends Model
{
    protected $table = 'merchants_dt_file';
    protected $primaryKey = 'dtf_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsDtFiles($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function addMerchantsDtFile($data)
    {
        return $this->create($data);
    }

    public function delMerchantsDtFile($where)
    {
        return $this->where($where)
            ->delete();
    }
}
