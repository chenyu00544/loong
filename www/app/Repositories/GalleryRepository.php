<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GalleryRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Http\Models\Shop\GalleryAlbumModel;
use App\Http\Models\Shop\GalleryAlbumPicModel;
use App\Http\Models\Shop\GoodsGalleryModel;

class GalleryRepository implements GalleryRepositoryInterface
{

    private $galleryAlbumModel;
    private $galleryAlbumPicModel;
    private $goodsGalleryModel;

    public function __construct(
        GalleryAlbumModel $galleryAlbumModel,
        GalleryAlbumPicModel $galleryAlbumPicModel,
        GoodsGalleryModel $goodsGalleryModel
    )
    {
        $this->galleryAlbumModel = $galleryAlbumModel;
        $this->galleryAlbumPicModel = $galleryAlbumPicModel;
        $this->goodsGalleryModel = $goodsGalleryModel;
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

    public function getGalleryPics($data = ['album_id' => 0])
    {
        $pageSize = empty($data['size']) ? 20 : $data['size'];
        $where['album_id'] = $data['album_id'];
        $page = $data['page'];
        $res = $this->galleryAlbumPicModel->getGalleryPics($where, $page, $pageSize);
        $count = $this->galleryAlbumPicModel->countGalleryPic([$data['album_id']]);
        if ($count->count() > 0) {
            foreach ($count as $c) {
                $count = $c->count;
            }
        } else {
            $count = 0;
        }
        foreach ($res as $key => $value) {
            $value->pic_image_bak = FileHandle::getImgByOssUrl($value->pic_image);
            $value->pic_file_bak = FileHandle::getImgByOssUrl($value->pic_file);
            $value->pic_thumb_bak = FileHandle::getImgByOssUrl($value->pic_thumb);
            $pic_spec = explode('×', $value->pic_spec);
            if(count($pic_spec)>0){
                $value->width = $pic_spec[0];
                $value->height = $pic_spec[1];
            }else{
                $value->width = 0;
                $value->height = 0;
            }
        }
        $rep = Common::paginate($res, $count, $page, $pageSize);
        return $rep;
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
            if (!empty($path)) {
                FileHandle::deleteFile($path);
            }
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    public function getGalleryPicsByPage($where = [])
    {
        $res = $this->galleryAlbumPicModel->getGalleryPicsByPage($where);
        foreach ($res as $re) {
            $re->pic_file_bak = FileHandle::getImgByOssUrl($re->pic_file);
        }
        return $res;
    }

    public function getGalleryPic($ids)
    {
        return $this->galleryAlbumPicModel->getGalleryPic($ids);
    }

    public function setGalleryPic($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where = [];
        $updata = [];
        foreach ($data as $key => $value) {
            if ($key == 'pic_id') {
                $where = array_filter($value);
            } else {
                $updata[$key] = $value;
            }
        }

        $re = $this->galleryAlbumPicModel->setGalleryPic($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function upGalleryPic($data)
    {
        $album_id = $data['album_id'];
        $files = $data['pic'];
        $rep = [];
        foreach ($files as $file) {
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
            $rep[] = $this->galleryAlbumPicModel->addGalleryAlbumPic($updata);
        }
        foreach ($rep as $k => $re){
            $rep[$k]->pic_file_bak = FileHandle::getImgByOssUrl($rep[$k]->pic_file);
        }
        return $rep;
    }

    public function delGalleryPic($data)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        foreach ($data['pic_id'] as $key => $value) {
            $where['pic_id'] = $value;
            $re = $this->galleryAlbumPicModel->delGalleryPic($where);
            if ($re) {
                if(!empty($data['pic_image'][$key])){
                    FileHandle::deleteFile($data['pic_image'][$key]);
                }
                if(!empty($data['pic_thumb'][$key])){
                    FileHandle::deleteFile($data['pic_thumb'][$key]);
                }
                if(!empty($data['pic_file'][$key])){
                    FileHandle::deleteFile($data['pic_file'][$key]);
                }
                $req['code'] = 1;
                $req['msg'] = '删除成功';
            }
        }
        return $req;
    }

    public function countGalleryPic($arr)
    {
        return $this->galleryAlbumPicModel->countGalleryPic($arr);
    }

    public function getGoodsGallerys($id = 0)
    {
        $where['goods_id'] = $id;
        return $this->goodsGalleryModel->getGoodsGallerys($where);
    }

    public function setGoodsGallery($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];

        if (isset($data['cur_sort']) && isset($data['cur_img_id']) && isset($data['main_sort']) && isset($data['main_img_id'])) {
            $cWhere['img_id'] = $data['cur_img_id'];
            $cData['img_desc'] = $data['main_sort'];

            $mWhere['img_id'] = $data['main_img_id'];
            $mData['img_desc'] = $data['cur_sort'];

            $this->goodsGalleryModel->setGoodsGallery($cWhere, $cData);
            $re = $this->goodsGalleryModel->setGoodsGallery($mWhere, $mData);
            if ($re) {
                $req['code'] = 1;
                $req['msg'] = '操作成功';
            }
        }

        return $req;
    }

    public function addGoodsGallery($data)
    {
        $picIds = $data['pic_ids'];
        $pics = $this->galleryAlbumPicModel->getGalleryPicsByIn($picIds);
        if (empty($data['goods_id'])) {
            $where['goods_id'] = 0;
        } else {
            $where['goods_id'] = $data['goods_id'];
            $updata['goods_id'] = $data['goods_id'];
        }
        foreach ($pics as $key => $pic) {
            $updata['img_desc'] = 100;
            $updata['front_cover'] = 0;
            $updata['img_url'] = $pic->pic_image;
            $updata['thumb_url'] = $pic->pic_thumb;
            $updata['img_original'] = $pic->pic_file;
            $re = $this->goodsGalleryModel->addGoodsGallery($updata);
            $re->img_original = '/' . $re->img_original;
            $rep[] = $re;
        }
        return ['code' => 1, 'data' => $rep];
    }

    public function upGoodsGalleryPic($data)
    {
        $goods_id = $data['goods_id'];
        $files = $data['pic'];
        $rep = [];
        foreach ($files as $file) {
            $original_img = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'original_img';
            $thumb_img = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'thumb_img';
            $exhibition_img = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'exhibition_img';
            $updata['img_original'] = FileHandle::upLoadImage($file, $original_img);
            $updata['thumb_url'] = FileHandle::upLoadThumbImage($updata['img_original'], $thumb_img);
            $updata['img_url'] = FileHandle::upLoadExhibitionImage($updata['img_original'], $exhibition_img, 0.8);
            $updata['goods_id'] = $goods_id;
            $updata['is_source'] = 0;
            $re = $this->goodsGalleryModel->addGoodsGallery($updata);
            $re->img_original = '/' . $re->img_original;
            $rep[] = $re;
        }
        return $rep;
    }

    public function delGoodsGalleryPic($data)
    {
        $req = ['code' => 0];
        $where['img_id'] = $data['imgid'];
        $re = $this->goodsGalleryModel->getGoodsGallery($where);
        if ($re) {
            if ($re->is_source == 0) {
                if (!empty($re->img_url)) {
                    FileHandle::deleteFile($re->img_url);
                }
                if (!empty($re->thumb_url)) {
                    FileHandle::deleteFile($re->thumb_url);
                }
                if (!empty($re->img_original)) {
                    FileHandle::deleteFile($re->img_original);
                }
            }
            $this->goodsGalleryModel->delGoodsGallery($where);
            $req['code'] = 1;
        }
        return $req;
    }

}