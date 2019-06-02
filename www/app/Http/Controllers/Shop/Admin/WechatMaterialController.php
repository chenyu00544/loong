<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:公众号
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\WechatRepository;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\View;

class WechatMaterialController extends CommonController
{

    private $wechatRepository;

    public function __construct(
        WechatRepository $wechatRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wechatmaterial');
        View::share('backUrl', url('admin/wechatmaterial'));
        $this->wechatRepository = $wechatRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'news';
        $materials = $this->wechatRepository->getWechatMaterialByPage($navType);
        return view('shop.admin.wechat.wechatMaterial', compact('materials', 'navType'));
    }

    public function addNews()
    {
        return view('shop.admin.wechat.addWechatMaterialNews');
    }

    public function addNewses()
    {
        return view('shop.admin.wechat.addWechatMaterialNewses');
    }

    public function editNews($id)
    {
        $material = $this->wechatRepository->getWechatMaterial($id);
        return view('shop.admin.wechat.setWechatMaterialNews', compact('material'));
    }

    public function addVideo()
    {
        return view('shop.admin.wechat.addWechatMaterialVideo');
    }

    public function editVideo($id)
    {
        $material = $this->wechatRepository->getWechatMaterial($id);
        return view('shop.admin.wechat.setWechatMaterialVideo', compact('material'));
    }

    public function materialModal($id)
    {
        $type = $id;
        $materials = $this->wechatRepository->getWechatMaterials($id);
        return view('shop.admin.wechat.modal.materiaLibrary', compact('type', 'materials'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), []);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->modifyWechatMaterial($request->except('_token'), $this->user);
        if ($re) {
            return view('shop.admin.success');
        } else {
            return view('shop.admin.failed');
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $navType = $id;
        $materials = $this->wechatRepository->getWechatMaterialByPage($navType);
        return view('shop.admin.wechat.wechatMaterial', compact('materials', 'navType'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {

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
        $ver = Verifiable::Validator($request->all(), []);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->modifyWechatMaterial($request->except('_token', '_method'), array(), $id);
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
        return $this->wechatRepository->delWechatMaterial($id);
    }
}
