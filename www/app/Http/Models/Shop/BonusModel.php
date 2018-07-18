<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BonusModel extends Model
{
    protected $table = 'bonus';
    protected $primaryKey = 'bonus_id';
    public $timestamps = false;
    protected $guarded = [];
}
