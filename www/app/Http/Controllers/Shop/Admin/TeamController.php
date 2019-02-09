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

class TeamController extends CommonController
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
        $nav_type = 'team';
        $search['keywords'] = '';
        $teams = $this->teamRepository->getTeamsByPage($search);
        return view('shop.admin.team.team', compact('teams', 'nav_type', 'search'));
    }

    public function change(Request $request)
    {
        return $this->teamRepository->changeTeam($request->all());
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $teamCates = $this->teamRepository->getTeamCatesBySub();
        return view('shop.admin.team.teamAdd', compact('teamCates'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["goods_id" => 'required', 'team_name' => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->teamRepository->addTeam($request->except('_token'));
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
        $nav_type = $id;
        $search['keywords'] = $request->get('keywords');
        $teams = $this->teamRepository->getTeamsByPage($search);
        return view('shop.admin.team.team', compact('teams', 'nav_type', 'search'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $team = $this->teamRepository->getTeam($id);
        $teamCates = $this->teamRepository->getTeamCatesBySub();
        return view('shop.admin.team.teamEdit', compact('team', 'teamCates'));
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
        $ver = Verifiable::Validator($request->all(), ["goods_id" => 'required', 'team_name' => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->teamRepository->setTeam($request->except('_token', '_method'), $id);
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

    }

    public function getTeamInfo(Request $request, $id)
    {
        $nav_type = $id;
        $search['keywords'] = $request->get('keywords');
        $teamLogs = $this->teamRepository->getTeamLogByPage($search, $nav_type);
        return view('shop.admin.team.teamInfo', compact('nav_type', 'search', 'teamLogs'));
    }
}
