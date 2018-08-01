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

class TradeSnapshotModel extends Model
{
    protected $table = 'trade_snapshot';
    protected $primaryKey = 'trade_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getTradeSnapshot($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }
}
