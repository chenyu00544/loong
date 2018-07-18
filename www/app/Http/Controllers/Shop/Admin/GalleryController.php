<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品相册设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\GalleryRepository;
use Illuminate\Http\Request;

class GalleryController extends CommonController
{
    private $galleryRepository;

    public function __construct(GalleryRepository $galleryRepository)
    {
        parent::__construct();
        $this->checkPrivilege('gallery');
        $this->galleryRepository = $galleryRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $rank[1] = 10;
        $gallerys = $this->galleryRepository->getGallerysByPage();
        $albumIds = [];
        foreach ($gallerys as $gallery) {
            $albumIds[] = $gallery->album_id;
            $gallery->count = 0;
            $countPics[$gallery->album_id] = 0;
        }
        $countPics_re = $this->galleryRepository->countGalleryPic($albumIds);
        foreach ($countPics_re as $countPic) {
            $countPics[$countPic->album_id] = $countPic->count;
        }
        return view('shop.admin.gallery.gallery', compact('rank', 'gallerys', 'countPics'));
    }

    public function getGallerys($id)
    {
        $re = $this->galleryRepository->getGallerys(['parent_album_id' => $id]);
        if ($re->count() > 0) {
            return $re->toArray();
        }
    }

    //显示相册图片列表
    public function galleryView(Request $request, $id)
    {
        $galleryPics = $this->galleryRepository->getGalleryPicsByPage(['album_id' => $id]);
        return view('shop.admin.gallery.galleryPics', compact('galleryPics', 'id'));
    }

    //转移相册窗口
    public function transferGalleryPic($id)
    {
        $ids = explode('-', $id);
        $gallerys = $this->galleryRepository->getGallerys();
        $galleryPic = $this->galleryRepository->getGalleryPic(array_filter($ids));
        return view('shop.admin.gallery.modal.transferPic', compact('gallerys', 'galleryPic', 'id'));
    }

    //上传图片窗口
    public function upPicView($id)
    {
        $gallerys = $this->galleryRepository->getGallerys();
        $album = $this->galleryRepository->getGallery($id);
        return view('shop.admin.gallery.modal.upPic', compact('gallerys', 'id', 'album'));
    }

    //上传图片api
    public function upGalleryPic(Request $request)
    {
        return $this->galleryRepository->upGalleryPic($request->except('_token'));
    }

    //设置图片所属相册api
    public function setGalleryPic(Request $request)
    {
        return $this->galleryRepository->setGalleryPic($request->except('_token'));
    }

    //删除图片api
    public function delGalleryPic(Request $request)
    {
        return $this->galleryRepository->delGalleryPic($request->except('_token'));
    }

    public function getGalleryPics(Request $request)
    {
        return $this->galleryRepository->getGalleryPics($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $gallerys = $this->galleryRepository->getGallerys();
        return view('shop.admin.gallery.galleryAdd', compact('gallerys'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["album_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->galleryRepository->addGallery($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $rank[1] = 0;
        $gallerys = $this->galleryRepository->getGallerysByPage(['parent_album_id' => $id]);
        $albumIds = [];
        $countPics = [];
        foreach ($gallerys as $gallery) {
            $albumIds[] = $gallery->album_id;
            $gallery->count = 0;
            $countPics[$gallery->album_id] = 0;
        }
        $countPics_re = $this->galleryRepository->countGalleryPic($albumIds);
        foreach ($countPics_re as $countPic) {
            $countPics[$countPic->album_id] = $countPic->count;
        }
        return view('shop.admin.gallery.gallery', compact('rank', 'gallerys', 'countPics'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $gallery = $this->galleryRepository->getGallery($id);
        $gallerys = $this->galleryRepository->getGallerys();
        $parentGallerys = $this->galleryRepository->getParentGallerys($id);
        unset($parentGallerys[0]);
        return view('shop.admin.gallery.galleryEdit', compact('gallery', 'parentGallerys', 'gallerys'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $ver = Verifiable::Validator($request->all(), ["album_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->galleryRepository->setGallery($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request, $id)
    {
        return $this->galleryRepository->delGallery($id, $request->except('_token', '_method')['url']);
    }
}
