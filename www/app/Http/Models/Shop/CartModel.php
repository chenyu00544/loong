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

class CartModel extends Model
{
    protected $table = 'cart';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function delCartsByTime()
    {
        return $this->where([['add_time', '<', time() - 86400 * 15], ['user_id', '=', '0']])
            ->delete();
    }
}
