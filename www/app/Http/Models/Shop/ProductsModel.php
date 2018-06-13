<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ProductsModel extends Model
{
    protected $table = 'products';
    protected $primaryKey = 'product_id';
    public $timestamps = false;
    protected $guarded = [];

    public function addProduct($data)
    {
        return $this->create($data);
    }

    public function getMaxProductsId()
    {
        return $this->max('product_id');
    }
}
