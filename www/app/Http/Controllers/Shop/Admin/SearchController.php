<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 广告设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\SearchRepository;
use Illuminate\Http\Request;

class SearchController extends CommonController
{
    private $searchRepository;

    public function __construct(
        SearchRepository $searchRepository
    )
    {
        parent::__construct();
        $this->searchRepository = $searchRepository;
    }

    public function index()
    {
        return ['code' => 5, 'msg' => '操作失败'];
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["keywords" => 'required']);
        if (!$ver->passes()) {
            return ['code' => 5, 'msg' => '操作错误'];
        }
        return $this->searchRepository->search($request->except('_token'));
    }
}
