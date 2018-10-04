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

class AdModel extends Model
{
    protected $table = 'ad';
    protected $primaryKey = 'ad_id';
    public $timestamps = false;
    protected $guarded = [];

}
