<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class CollectStoreModel extends Model
{
    protected $table = 'collect_store';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCollectStore($where)
    {
        return $this->where($where)->first();
    }

    public function setCollectStore($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addCollectStore($data)
    {
        return $this->create($data);
    }
}
