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

class CollectStoreModel extends Model
{
    protected $table = 'collect_store';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];
}
