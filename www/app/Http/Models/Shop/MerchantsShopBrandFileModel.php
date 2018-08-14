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

class MerchantsShopBrandFileModel extends Model
{
    protected $table = 'merchants_shop_brand_file';
    protected $primaryKey = 'b_fid';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsShopBrandFile($where)
    {
        return $this->where($where)->first();
    }

    public function setMerchantsShopBrandFile($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMerchantsShopBrandFile($data)
    {
        return $this->create($data);
    }

    public function delMerchantsShopBrandFile($where)
    {
        return $this->where($where)
            ->delete();
    }
}
