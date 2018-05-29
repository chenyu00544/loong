<?php

namespace App\Contracts\Services;

/**
 * Interface CategoryServiceInterface
 * @package App\Contracts\Services
 */
interface CategoryServiceInterface
{
    /**
     * 新增商品分组
     *
     * @return mixed
     */
    public function create();

    /**
     * 获取商品分组
     *
     * @return mixed
     */
    public function get();

    /**
     * 更新商品分组
     *
     * @return mixed
     */
    public function update();

    /**
     * 删除商品分组
     *
     * @return mixed
     */
    public function delete();

    /**
     * 获取商品分组列表
     *
     * @return mixed
     */
    public function search();

    /**
     * 获取商品类目列表
     *
     * @return mixed
     */
    public function category();

}
