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

class SellerQrcodeModel extends Model
{
    protected $table = 'seller_qrcode';
    protected $primaryKey = 'qrcode_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getSellerQrcode($where)
    {
        return $this->where($where)
            ->first();
    }

    public function setSellerQrcode($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }
}
