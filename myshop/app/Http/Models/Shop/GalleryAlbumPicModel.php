<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;

class GalleryAlbumPicModel extends Model
{
    protected $table = 'gallery_album_pic';
    protected $primaryKey = 'pic_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGalleryPicsByPage($where, $column = ['*'], $size = 30)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('add_time', 'desc')
            ->paginate($size);
    }

    public function getGalleryPic($where, $column = ['*'])
    {
        return $this->select($column)
            ->whereIn('pic_id', $where)
            ->first();
    }

    public function addGalleryAlbumPic($data)
    {
        return $this->create($data);
    }

    public function setGalleryPic($where, $data)
    {
        return $this->whereIn('pic_id', $where)
            ->update($data);
    }

    public function delGalleryPic($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function countGalleryPic($whereIn)
    {
        return $this->select(DB::raw('count(*) as count , album_id'))
            ->whereIn('album_id', $whereIn)
            ->groupBy('album_id')
            ->get();
    }
}
