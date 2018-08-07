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

class MerchantsGradeModel extends Model
{
    protected $table = 'merchants_grade';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
