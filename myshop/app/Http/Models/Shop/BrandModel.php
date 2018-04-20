<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BrandModel extends Model
{
    protected $table = 'brand';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getBrands($size = 10)
    {
        return $this->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->paginate($size);
    }

    public function getBrandsAll()
    {
        return $this->get();
    }

    public function getBrand($id)
    {
        return $this->orderBy('id', $id)
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
