<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BrandModel extends Model
{
    protected $table = 'brand';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getBrands($size = 10, $column =['*'])
    {
        return $this->select($column)
            ->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->paginate($size);
    }

    public function getBrandsAll($columns = ['*'])
    {
        return $this->select($columns)
            ->get();
    }

    public function getBrandsBySearch($where)
    {
        return $this->where($where)
            ->get();
    }

    public function getBrand($id)
    {
        return $this->where('id', $id)
            ->first();
    }

    public function addBrand($updata)
    {
        return $this->create($updata);
    }

    public function upDateBrand($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function deleteBrand($where)
    {
        return $this->where($where)
            ->delete();
    }
}
