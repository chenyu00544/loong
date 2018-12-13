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
use App\Repositories\Admin\SellerGradeRepository;
use Illuminate\Http\Request;

class SellerGradeController extends CommonController
{
    private $sellerGradeRepository;

    public function __construct(
        SellerGradeRepository $sellerGradeRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('store');
        $this->sellerGradeRepository = $sellerGradeRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $nav = 'grade';
        $grades = $this->sellerGradeRepository->getSellerGradesByPage();
        return view('shop.admin.sellergrade.grade', compact('nav', 'grades'));
    }

    public function change(Request $request)
    {
        return $this->sellerGradeRepository->change($request->except('_token'));
    }


    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $ecs = $this->sellerGradeRepository->getEntryCriterias(['parent_id' => 0]);
        return view('shop.admin.sellergrade.gradeAdd', compact('ecs'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ['grade_name' => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->sellerGradeRepository->addSellerGrade($request->except('_token'));
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

    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $grade = $this->sellerGradeRepository->getSellerGrade($id);
        $ecs = $this->sellerGradeRepository->getEntryCriterias(['parent_id' => 0]);
        return view('shop.admin.sellergrade.gradeEdit', compact('grade', 'ecs'));
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
        $ver = Verifiable::Validator($request->all(), ['grade_name' => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->sellerGradeRepository->setSellerGrade($request->except('_token', '_method'), $id);
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
        return $this->sellerGradeRepository->delSellerGrade($id);
    }
}
