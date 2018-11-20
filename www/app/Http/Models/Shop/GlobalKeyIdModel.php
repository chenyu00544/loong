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

class GlobalKeyIdModel extends Model
{
    protected $table = 'global_key_id';
    protected $primaryKey = 'key_id';
    public $timestamps = false;
    protected $guarded = [];

    public function setGlobalKeyId($data)
    {
        $this->where(['key_id'=>1])
            ->update($data);
    }
}
