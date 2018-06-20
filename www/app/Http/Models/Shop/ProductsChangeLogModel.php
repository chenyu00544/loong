<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ProductsChangeLogModel extends Model
{
    protected $table = 'products_change_log';
    protected $primaryKey = 'product_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getProductsChangeLog($where)
    {
        return $this->where($where)
            ->get();
    }

    public function addProductsChangeLog($data)
    {
        return $this->create($data);
    }

    public function delAll($where)
    {
        $this->where($where)->delete();
    }
}
