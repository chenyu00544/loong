<?php

namespace App\Http\Controllers\Shop\App;

use App\Repositories\App\AdRepository;
use Illuminate\Http\Request;

class IndexController extends CommonController
{

    private $adRepository;

    public function __construct(AdRepository $adRepository)
    {
        parent::__construct();
        $this->adRepository = $adRepository;
    }

    public function index(Request $request)
    {
        $data['ads'] = $this->adRepository->getAdPositionAndAds();
        return ['code' => 1, 'msg' => '', 'data' => $data];
    }
}
