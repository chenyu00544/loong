<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class TransportExpressModel extends Model
{
    protected $table = 'transport_express';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
