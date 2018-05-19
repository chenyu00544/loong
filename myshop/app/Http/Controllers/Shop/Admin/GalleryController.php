<?php

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
        return view('shop.admin.gallery.gallery', compact('rank', 'gallerys'));
    }

    public function getGallerys($id)
    {
        $re = $this->galleryRepository->getGallerys(['parent_album_id' => $id]);
        if ($re->count() > 0) {
            return $re->toArray();
        }
    }

    public function galleryView($id)
    {
        $galleryPics = $this->galleryRepository->getGalleryPicsByPage(['album_id' => $id]);
        return view('shop.admin.gallery.galleryPics', compact('galleryPics'));
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
        $ver = Verifiable::Validator($request->all(), ["album_mame" => 'required']);
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
        return view('shop.admin.gallery.gallery', compact('rank', 'gallerys'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
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
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
