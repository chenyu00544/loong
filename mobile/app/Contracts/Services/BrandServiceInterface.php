<?php

namespace App\Contracts\Services;

/**
 * Interface BrandServiceInterface
 * @package App\Contracts\Services
 */
interface BrandServiceInterface
{
    /**
     * 新增品牌
     *
     * @return mixed
     */
    public function create();

    /**
     * 获取品牌
     *
     * @return mixed
     */
    public function get();

    /**
     * 更新品牌
     *
     * @return mixed
     */
    public function update();

    /**
     * 删除品牌
     *
     * @return mixed
     */
    public function delete();

    /**
     * 获取品牌列表
     *
     * @return mixed
     */
    public function search();
}