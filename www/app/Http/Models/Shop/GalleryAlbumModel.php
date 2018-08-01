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

class GalleryAlbumModel extends Model
{
    protected $table = 'gallery_album';
    protected $primaryKey = 'album_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGallerysByPage($where, $size = 10, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'asc')
            ->paginate($size);
    }

    public function getGallerys($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'asc')
            ->get();
    }

    public function getGallery($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addGallery($data)
    {
        return $this->create($data);
    }

    public function setGallery($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delGallery($where)
    {
        return $this->where($where)
            ->delete();
    }
}
