<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Repositories\NavigationRepository;
use Illuminate\Http\Request;

class NavigationController extends CommonController
{

    protected $navigationRepository;

    public function __construct(NavigationRepository $navigationRepository)
    {
        parent::__construct();
        $this->navigationRepository = $navigationRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $req)
    {
        $size = $req->get('size') ? $req->get('size') : 10;
        $navs = $this->navigationRepository->getNavs($size);
        $lang = LangConfig::LangAdminConf();
        $lang += LangConfig::LangAdminCutNavConf();
        return view('shop.admin.nav.navigation', compact('navs', 'lang'));
    }

    public function showOrView(Request $req)
    {
        $re = $this->navigationRepository->showOrOpenView($req->except('_token'));
        return $re;
    }

    public function changeOrder(Request $req){
        $re = $this->navigationRepository->changeOrder($req->except('_token'));
        return $re;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $navsTop = $this->navigationRepository->getNavsTop();
        return view('shop.admin.nav.navAdd', compact('navsTop'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $re = $this->navigationRepository->addNav($request->except('_token'));
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
        $field = $this->navigationRepository->getNav($id);
        $navsTop = $this->navigationRepository->getNavsTop();
        return view('shop.admin.nav.navEdit',compact('field','navsTop'));
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
        $re = $this->navigationRepository->upDateNav($request->except('_token','_method'),$id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $re = $this->navigationRepository->delete($id);
        return $re;
    }
}
