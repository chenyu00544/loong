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

class ProductsModel extends Model
{
    protected $table = 'products';
    protected $primaryKey = 'product_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getProducts($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('product_id', 'asc')
            ->get();
    }

    public function setProduct($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addProduct($data)
    {
        return $this->create($data);
    }

    public function getMaxProductsId()
    {
        return $this->max('product_id');
    }

    public function delProduct($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {

        }
    }
}
