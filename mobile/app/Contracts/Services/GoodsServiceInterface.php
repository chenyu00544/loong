<?php

namespace App\Contracts\Services;

/**
 * Interface GoodsServiceInterface
 * @package App\Contracts\Services
 */
interface GoodsServiceInterface
{
    /**
     * 新增商品
     *
     * @return mixed
     */
    public function create();

    /**
     * 获取单个商品信息
     *
     * @return mixed
     */
    public function get();

    /**
     * 更新商品
     *
     * @return mixed
     */
    public function update();

    /**
     * 删除商品
     *
     * @return mixed
     */
    public function delete();

    /**
     * 上架商品
     *
     * @return mixed
     */
    public function listing();

    /**
     * 下架商品
     *
     * @return mixed
     */
    public function delisting();

    /**
     * 分页查询商品标准列表
     *
     * @return mixed
     */
    public function search();

    /**
     * 获取仓库中的商品列表
     *
     * @return mixed
     */
    public function getInventory();

    /**
     * 获取出售中的商品列表
     *
     * @return mixed
     */
    public function getOnsale();

}
