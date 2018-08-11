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

class MerchantsShopBrandModel extends Model
{
    protected $table = 'merchants_shop_brand';
    protected $primaryKey = 'bid';
    public $timestamps = false;
    protected $guarded = [];

    public function msbf()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsShopBrandFileModel', 'bid', 'bid');
    }

    public function getMerchantsShopBrands($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getMerchantsShopBrand($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['msbf'])
            ->where($where)
            ->first();
    }

    public function setMerchantsShopBrand($where, $data, $whereIn = [])
    {
        $m =  $this->where($where);
        if(count($whereIn) > 0){
            $m->whereIn('bid', $whereIn);
        }
        return $m->update($data);
    }

    public function addMerchantsShopBrand($data)
    {
        return $this->create($data);
    }

    public function delMerchantsBrand($where)
    {
        return $this->where($where)
            ->delete();
    }
}
