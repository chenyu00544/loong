<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\RedisCache;
use App\Repositories\App\FaatRepository;
use Illuminate\Http\Request;

class FaatController extends CommonController
{

    private $faatRepository;

    public function __construct(
        FaatRepository $faatRepository
    )
    {
        parent::__construct();
        $this->faatRepository = $faatRepository;
    }

    public function index(Request $request)
    {
        $id = $request->get('id');
        $re = $this->faatRepository->getFaatByGroupId($id);
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }
}
