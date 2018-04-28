<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class AttributeModel extends Model
{
    protected $table = 'attribute';
    protected $primaryKey = 'attr_id';
    public $timestamps = false;
    protected $guarded = [];
}
