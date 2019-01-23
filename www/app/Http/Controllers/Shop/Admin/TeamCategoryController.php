<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\TeamRepository;
use Illuminate\Http\Request;

class TeamCategoryController extends CommonController
{
    private $teamRepository;

    public function __construct(
        TeamRepository $teamRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('team');
        $this->teamRepository = $teamRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $nav_type = 'cate';
        $teamCates = $this->teamRepository->getTeamCatesByPage();
        return view('shop.admin.team.teamCate', compact('teamCates', 'nav_type'));
    }

    public function change(Request $request)
    {
        return $this->teamRepository->changeTeamCate($request->all());
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $pCates = $this->teamRepository->getTeamCates();
        return view('shop.admin.team.teamCateAdd', compact('pCates'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->teamRepository->addTeamCates($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $nav_type = 'cate';
        $teamCates = $this->teamRepository->getTeamCatesByPage($id);
        return view('shop.admin.team.teamCate', compact('teamCates', 'nav_type', 'id'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $pCates = $this->teamRepository->getTeamCates();
        $teamCate = $this->teamRepository->getTeamCate($id);
        return view('shop.admin.team.teamCateEdit', compact('pCates', 'teamCate'));
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
        $ver = Verifiable::Validator($request->all(), ["name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->teamRepository->setTeamCate($request->except('_token', '_method'), $id);
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
        return $this->teamRepository->delTeamCate($id);
    }
}
