<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GalleryAlbumModel extends Model
{
    protected $table = 'gallery_album';
    protected $primaryKey = 'album_id';
    public $timestamps = false;
    protected $guarded = [];
}
