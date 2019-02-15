<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class PaymentModel extends Model
{
    protected $table = 'payment';
    protected $primaryKey = 'pay_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getPayments($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('pay_order', 'DESC')
            ->get();
    }

    public function getPayment($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('pay_order', 'DESC')
            ->first();
    }
}
