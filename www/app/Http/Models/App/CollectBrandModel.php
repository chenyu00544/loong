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

class CollectBrandModel extends Model
{
    protected $table = 'collect_brand';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCollectBrand($where)
    {
        return $this->where($where)->first();
    }

    public function setCollectBrand($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addCollectBrand($data)
    {
        return $this->create($data);
    }
}
