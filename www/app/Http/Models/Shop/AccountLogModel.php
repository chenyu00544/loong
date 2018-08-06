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

class AccountLogModel extends Model
{
    protected $table = 'account_log';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
