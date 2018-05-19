<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GalleryRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\GalleryAlbumModel;
use App\Http\Models\Shop\GalleryAlbumPicModel;

class GalleryRepository implements GalleryRepositoryInterface
{

    private $galleryAlbumModel;
    private $galleryAlbumPicModel;

    public function __construct(
        GalleryAlbumModel $galleryAlbumModel,
        GalleryAlbumPicModel $galleryAlbumPicModel
    )
    {
        $this->galleryAlbumModel = $galleryAlbumModel;
        $this->galleryAlbumPicModel = $galleryAlbumPicModel;
    }

    public function getGallerysByPage($where = ['parent_album_id' => 0])
    {
        return $this->galleryAlbumModel->getGallerysByPage($where);
    }

    public function getGallerys($where = ['parent_album_id' => 0])
    {
        return $this->galleryAlbumModel->getGallerys($where);
    }

    public function getGalleryPics($where = ['album_id' => 0])
    {
        return $this->galleryAlbumPicModel->getGalleryPics($where);
    }

    public function addGallery($data)
    {
        $updata['add_time'] = time();
        foreach ($data as $key => $value) {
            if ($key == 'album_cover') {
                $updata[$key] = FileHandle::upLoadImage($value, 'gallery_album');
            } else {
                $updata[$key] = $value;
            }
        }

        return $this->galleryAlbumModel->addGallery($updata);
    }

    public function getGalleryPicsByPage($where = [])
    {
        return $this->galleryAlbumPicModel->getGalleryPicsByPage($where);
    }
}