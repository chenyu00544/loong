<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\BrandRepository;
use Illuminate\Http\Request;

class BrandController extends CommonController
{
    protected $brandRepository;

    public function __construct(BrandRepository $brandRepository)
    {
        parent::__construct();
        $this->brandRepository = $brandRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $size = $request->get('size') ? $request->get('size') : 10;
        $brands = $this->brandRepository->getBrands($size);
        return view('shop.admin.brand.brand', compact('brands'));
    }

    public function change(Request $request)
    {
        return $this->brandRepository->changBrand($request->except('_token'));
    }

    public function getFirstChar()
    {
        $re = $this->brandRepository->getBrandsAll();
        if ($re = $re->toArray()) {
            foreach ($re as $val) {
                $char = $this->brandRepository->getFirstChar($val['brand_name']);
                if ($char) {
                    $val['brand_first_char'] = $char;
                    $where['id'] = $val['id'];
                    $this->brandRepository->setBrand($where, $val);
                }
            }
        }
        return ['code' => 1, 'msg' => '生成成功'];
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.brand.brandAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["brand_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->brandRepository->addBrand($request->except('_token'));
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
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $brand = $this->brandRepository->getBrand($id);
        return view('shop.admin.brand.brandEdit', compact('brand'));
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
        $ver = Verifiable::Validator($request->all(), ["brand_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->brandRepository->upDateBrand($request->except('_token', '_method'), $id);
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
        return $this->brandRepository->delete($id);
    }
}
