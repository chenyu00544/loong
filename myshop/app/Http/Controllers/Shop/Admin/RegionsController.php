<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\RegionsRepository;
use Illuminate\Http\Request;

class RegionsController extends CommonController
{
    private $regionsRepository;

    public function __construct(RegionsRepository $regionsRepository)
    {
        parent::__construct();
        $this->regionsRepository = $regionsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $typeNav = 'regions';
        $level = 1;
        $regions = $this->regionsRepository->getRegionsLevel();
        return view('shop.admin.regions.regions', compact('typeNav', 'regions', 'level'));
    }

    public function nextRegions($id, $type)
    {
        $typeNav = 'regions';
        $regions = $this->regionsRepository->getRegionsLevel($id,$type + 1);
        return view('shop.admin.regions.regions', compact('typeNav', 'regions'));
    }

    public function changes(Request $request)
    {
        return $this->regionsRepository->changes($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {

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
