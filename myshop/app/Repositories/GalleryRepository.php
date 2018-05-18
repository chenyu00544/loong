<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GalleryRepositoryInterface;
use App\Http\Models\Shop\GalleryAlbumModel;

class GalleryRepository implements GalleryRepositoryInterface
{

    private $galleryAlbumModel;

    public function __construct(GalleryAlbumModel $galleryAlbumModel)
    {
        $this->galleryAlbumModel = $galleryAlbumModel;
    }

    public function getGallerys($where = ['parent_album_id' => 0])
    {
        return $this->galleryAlbumModel->getGallerys($where);
    }
}