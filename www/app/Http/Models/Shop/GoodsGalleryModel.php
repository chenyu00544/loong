<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsGalleryModel extends Model
{
    protected $table = 'goods_gallery';
    protected $primaryKey = 'img_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsGallerys($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('front_cover', 'desc')
            ->get();
    }

    public function getGoodsGallery($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addGoodsGallery($data)
    {
        return $this->create($data);
    }

    public function setGoodsGallery($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delGoodsGallery($where)
    {
        return $this->where($where)
            ->delete();
    }
}
