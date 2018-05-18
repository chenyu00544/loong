<?php

namespace App\Http\Controllers\Shop\Admin;

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
        $gallerys = $this->galleryRepository->getGallerys();
        return view('shop.admin.gallery.gallery', compact('rank', 'gallerys'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.gallery.gallerAddy', compact());
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
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
        $gallerys = $this->galleryRepository->getGallerys(['parent_album_id' => $id]);
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
