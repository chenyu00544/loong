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
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {}
    }
}
