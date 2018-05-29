<?php

namespace App\Modules\Api\Foundation;

use App\Modules\Base\Controllers\FrontendController;

/**
 * Class Controller
 * @package App\Modules\Api\Foundation
 */
class Controller extends FrontendController
{
    /**
     * API 返回
     * @param $data
     * @param int $code
     */
    protected function resp($data, $code = 200)
    {
        $res = ['code' => $code];

        if ($code != 200) {
            $res['message'] = $data;
        } else {
            $res['data'] = $data;
        }

        $this->response($res, 'json', $code);
    }

    /**
     * 接口参数校验
     * @param $args
     * @param $pattern
     * @return bool|string
     */
    protected function validate($args, $pattern)
    {
        $validator = Validation::createValidation();

        $rules = Validation::transPattern($pattern);

        if ($validator->validate($rules)->create($args) === false) {
            return $validator->getError();
        } else {
            return true;
        }
    }
}
