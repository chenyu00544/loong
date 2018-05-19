<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

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
}
