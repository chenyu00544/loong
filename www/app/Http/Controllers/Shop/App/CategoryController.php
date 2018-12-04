<?php

namespace App\Http\Controllers\Shop\App;

use App\Repositories\App\CategoryRepository;
use Illuminate\Http\Request;

class CategoryController extends CommonController
{
    private $categoryRepository;

    public function __construct(
        CategoryRepository $categoryRepository
    )
    {
        parent::__construct();
        $this->categoryRepository = $categoryRepository;
    }

    public function index(Request $request)
    {
        $res = $this->categoryRepository->getCates();
        if ($res) {
            return ['code' => 0, 'msg' => '', 'data' => $res];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }
}
