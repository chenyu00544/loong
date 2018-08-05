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
use App\Repositories\MerchantsRepository;
use Illuminate\Http\Request;

class ApplyProcessController extends CommonController
{
    private $merchantsRepository;

    public function __construct(
        MerchantsRepository $merchantsRepository
    )
    {
        parent::__construct();
        $this->merchantsRepository = $merchantsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $process = $this->merchantsRepository->getMerchantsStepsProcesses();
        return view('shop.admin.store.applyProcessAdd', compact('process'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["fields_steps" => 'required', "fields_titles" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->merchantsRepository->addMerchantsStepsFieldsCentent($request->except('_token'));
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
        $applyProcesses = $this->merchantsRepository->getMerchantsStepsTitleByPage($id);
        return view('shop.admin.store.applyProcess', compact('applyProcesses'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $process = $this->merchantsRepository->getMerchantsStepsProcesses();
        $mst = $this->merchantsRepository->getMerchantsStepsTitle($id);
        return view('shop.admin.store.applyProcessEdit', compact('mst', 'process'));
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
        $ver = Verifiable::Validator($request->all(), ["fields_steps" => 'required', "fields_titles" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->merchantsRepository->setMerchantsStepsFieldsCentent($request->except('_token', '_method'), $id);
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
        return $this->merchantsRepository->delMerchantsStepsTitle($id);
    }
}
