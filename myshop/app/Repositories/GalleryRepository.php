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

    public function getParentGallerys($id, $parentGallerys = [])
    {
        $PGallerys = $parentGallerys;
        $re = $this->galleryAlbumModel->getGallery(['album_id' => $id]);
        if ($re) {
            $PGallerys[] = $re;
        }

        if ($re && $re->parent_album_id != 0) {
            return $this->getParentGallerys($re->parent_album_id, $PGallerys);
        } else {
            krsort($PGallerys);
            return $PGallerys;
        }
    }

    public function getGallery($id)
    {
        $where = ['album_id' => $id];
        return $this->galleryAlbumModel->getGallery($where);
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

    public function setGallery($data, $id)
    {
        $where['album_id'] = $id;
        $updata['add_time'] = time();
        foreach ($data as $key => $value) {
            if ($key == 'album_cover') {
                $updata[$key] = FileHandle::upLoadImage($value, 'gallery_album');
                if ($updata[$key]) {
                    FileHandle::deleteFile($data['album_cover_bak']);
                }
            } elseif ($key == 'album_cover_bak') {
            } else {
                $updata[$key] = $value;
            }
        }
        return $this->galleryAlbumModel->setGallery($where, $updata);
    }

    public function delGallery($id, $path)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['album_id'] = $id;
        $re = $this->galleryAlbumModel->delGallery($where);
        if ($re) {
            FileHandle::deleteFile($path);
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    public function getGalleryPicsByPage($where = [])
    {
        return $this->galleryAlbumPicModel->getGalleryPicsByPage($where);
    }

    public function getGalleryPic($id)
    {
        $where['pic_id'] = $id;
        return $this->galleryAlbumPicModel->getGalleryPic($where);
    }

    public function setGalleryPic($data)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where = [];
        $updata = [];
        foreach ($data as $key => $value){
            if($key == 'pic_id'){
                $where[$key] = $value;
            }else{
                $updata[$key] = $value;
            }
        }
        $re = $this->galleryAlbumPicModel->setGalleryPic($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '删除成功'];
        }
        return $req;
    }

    public function upGalleryPic($data)
    {
        $file = $data['pic'];
        $album_id = $data['album_id'];
        list($width, $height) = getimagesize($file);
        $original_img = 'gallery_album' . DIRECTORY_SEPARATOR . $album_id . DIRECTORY_SEPARATOR . 'original_img';
        $thumb_img = 'gallery_album' . DIRECTORY_SEPARATOR . $album_id . DIRECTORY_SEPARATOR . 'thumb_img';
        $exhibition_img = 'gallery_album' . DIRECTORY_SEPARATOR . $album_id . DIRECTORY_SEPARATOR . 'exhibition_img';
        $updata['pic_file'] = FileHandle::upLoadImage($file, $original_img);
        $updata['pic_thumb'] = FileHandle::upLoadThumbImage($updata['pic_file'], $thumb_img);
        $updata['pic_image'] = FileHandle::upLoadExhibitionImage($updata['pic_file'], $exhibition_img, 0.8);
        $updata['pic_size'] = $file->getClientSize();
        $updata['pic_spec'] = $width . '×' . $height;
        $updata['album_id'] = $album_id;
        $updata['pic_name'] = md5(time() . rand(10000, 99999));
        $updata['add_time'] = time();
        return $this->galleryAlbumPicModel->addGalleryAlbumPic($updata);
    }

    public function delGalleryPic($data)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['pic_id'] = $data['pic_id'];
        $re = $this->galleryAlbumPicModel->delGalleryPic($where);
        if ($re) {
            FileHandle::deleteFile($data['pic_image']);
            FileHandle::deleteFile($data['pic_thumb']);
            FileHandle::deleteFile($data['pic_file']);
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    public function countGalleryPic($arr)
    {
        return $this->galleryAlbumPicModel->countGalleryPic($arr);
    }

}