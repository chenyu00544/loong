<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
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
        $level = 0;
        $regions = $this->regionsRepository->getRegionsLevel();
        $parent_id = $regions[0]->parent_id;
        return view('shop.admin.regions.regions', compact('typeNav', 'regions', 'level', 'parent_id'));
    }

    public function nextRegions($id, $type)
    {
        $typeNav = 'regions';
        $regions = $this->regionsRepository->getRegionsLevel($id, $type + 1);
        if(!$regions->isEmpty()){
            $parent_id = $regions[0]->parent_id;
        }else{
            $parent_id = 0;
        }
        $level = $type+1;
        return view('shop.admin.regions.regions', compact('typeNav', 'regions', 'parent_id', 'level'));
    }

    public function addRegion($pid, $type)
    {
        $parent_id = $pid;
        $region_type = $type;
        return view('shop.admin.regions.regionsAdd', compact('parent_id', 'region_type'));
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
        $ver = Verifiable::Validator($request->all(), ["region_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->regionsRepository->addRegion($request->except('_token'));
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
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $parent_id = $id;
        $region_type = $id;
        return view('shop.admin.regions.regionsEdit', compact('parent_id', 'region_type'));
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
        return $this->regionsRepository->delRegion($id);
    }
}
