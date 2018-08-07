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
use App\Repositories\EntryCriteriaRepository;
use Illuminate\Http\Request;

class EntryCriteriaController extends CommonController
{

    private $entryCriteriaRepository;

    public function __construct(
        EntryCriteriaRepository $entryCriteriaRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('store');
        $this->entryCriteriaRepository = $entryCriteriaRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $criterias = $this->entryCriteriaRepository->getEntryCriteriasByPage(['parent_id'=>0]);
        return view('shop.admin.entrycriteria.criteria', compact('criterias'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $parents = $this->entryCriteriaRepository->getEntryCriterias(['parent_id'=>0]);
        return view('shop.admin.entrycriteria.criteriaAdd', compact('parents'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["criteria_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->entryCriteriaRepository->addEntryCriteria($request->except('_token'));
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
        $criterias = $this->entryCriteriaRepository->getEntryCriteriasByPage(['parent_id'=>$id]);
        return view('shop.admin.entrycriteria.criteria', compact('criterias'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $parents = $this->entryCriteriaRepository->getEntryCriterias(['parent_id'=>0]);
        $ec = $this->entryCriteriaRepository->getEntryCriteria($id);
        return view('shop.admin.entrycriteria.criteriaEdit', compact('parents', 'ec'));
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
        $ver = Verifiable::Validator($request->all(), ["criteria_name" => 'required', "type" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->entryCriteriaRepository->setEntryCriteria($request->except('_token', '_method'), $id);
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
        return $this->entryCriteriaRepository->delEntryCriterias($id);
    }
}
