<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class PaymentModel extends Model
{
    protected $table = 'payment';
    protected $primaryKey = 'pay_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getPaymentAll($column = ['*'])
    {
        return $this->select($column)
            ->orderBy('pay_order', 'asc')
            ->get();
    }

    public function getPayment($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('pay_id', 'asc')
            ->first();
    }

    public function setPayment($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function addPayment($data)
    {
        return $this->create($data);
    }
}
