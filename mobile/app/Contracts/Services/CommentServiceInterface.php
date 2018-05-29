<?php

namespace App\Contracts\Services;

/**
 * Interface CommentServiceInterface
 * @package App\Contracts\Services
 */
interface CommentServiceInterface
{
    /**
     * 商品评价和评论，复合条件查询接口
     *
     * @return mixed
     */
    public function query();

    /**
     * 商品评价统计，包括好评数、中评数、差评数
     *
     * @return mixed
     */
    public function count();
}