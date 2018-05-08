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
}
