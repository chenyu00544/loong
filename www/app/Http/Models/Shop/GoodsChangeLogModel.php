<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsChangeLogModel extends Model
{
    protected $table = 'goods_change_log';
    protected $primaryKey = 'log_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsChangelogByPage($where = [], $column = ['*'], $size = 20)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('log_id', 'desc')
            ->paginate($size);
    }

    public function addGoodsChangelog($data)
    {
        return $this->create($data);
    }
}
