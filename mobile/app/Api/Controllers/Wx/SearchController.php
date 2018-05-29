<?php

namespace App\Api\Controllers\Wx;

use App\Api\Controllers\Controller;
use App\Services\AuthService;
use App\Services\SearchService;
use Illuminate\Http\Request;

class SearchController extends Controller
{
    private $searchService;
    private $authService;

    public $keyword;
    public $area_id;
    public $id;
    public $page;
    public $per_page;
    public $shop;
    public $sort_key;
    public $sort_value;
    public $warehouse_id;

    public function __construct(SearchService $searchService, AuthService $authService)
    {
        $this->searchService = $searchService;
        $this->authService = $authService;
    }

    public function index(Request $request)
    {

        $this->validate($request, [
            'page' => 'required|int',
            'warehouse_id' => 'required|integer',    //仓库id
            'area_id' => 'required|integer'          //地区id
        ]);
        $uid = $this->authService->authorization(); //返回用户ID

        $this->init_params($request);

        $list = $this->searchService->get_search_goods($this->keyword, $this->area_id, $this->id, $this->page, $this->per_page, $this->shop, $this->sort_key, $this->sort_value, $this->warehouse_id);

        return $this->apiReturn($list);
    }

    public function init_params($req)
    {
        foreach ($req as $val) {
            foreach ($val as $k => $v) {
                switch ($k) {
                    case 'keyword':
                        $this->keyword = $v;
                        break;
                    case 'area_id':
                        $this->area_id = $v;
                        break;
                    case 'id':
                        $this->id = $v;
                        break;
                    case 'page':
                        $this->page = $v;
                        break;
                    case 'per_page':
                        $this->per_page = $v;
                        break;
                    case 'shop':
                        $this->shop = $v;
                        break;
                    case 'sort_key':
                        $this->sort_key = $v;
                        break;
                    case 'sort_value':
                        $this->sort_value = $v;
                        break;
                    case 'warehouse_id':
                        $this->warehouse_id = $v;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}