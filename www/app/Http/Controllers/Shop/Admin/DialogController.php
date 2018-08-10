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

class DialogController extends CommonController
{

    private $merchantsRepository;

    public function __construct(
        MerchantsRepository $merchantsRepository
    )
    {
        parent::__construct();
        $this->merchantsRepository = $merchantsRepository;
    }

    public function merchantsBrand($id)
    {
        $date = date('Y-m-d', time());
        return view('shop.admin.merchants.modal.merchantsBrandAdd', compact('date', 'id'));
    }

    public function editMerchantsBrand($id)
    {
        $bid = $id;
        $mBrand = $this->merchantsRepository->getMerchantsBrand($id);
        return view('shop.admin.merchants.modal.merchantsBrandEdit', compact('mBrand', 'bid'));
    }

    public function modifyMerchantsBrand(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["brand_name" => 'required']);
        if (!$ver->passes()) {
            return ['code' => 5, 'msg' => '操作失败', 'data' => ''];
        }
        $data = $request->except('_token');
        if(empty($data['bid'])){
            return $this->merchantsRepository->addMerchantsBrand($data);
        }else{
            return $this->merchantsRepository->setMerchantsBrand($data);
        }

    }

    public function delMerchantsBrand($id)
    {
        return $this->merchantsRepository->delMerchantsBrand($id);
    }

}
