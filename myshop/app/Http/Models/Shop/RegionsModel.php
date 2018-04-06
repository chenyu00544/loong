<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class RegionsModel extends Model
{
    protected $table = 'regions';
    protected $primaryKey = 'region_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getRegions($type = 0, $parent = 0)
    {
        return $this->select('region_id', 'region_name', 'parent_id')->where([['region_type',$type],['parent_id',$parent]])->get();
    }

    public function getRegion($id)
    {
        return $this->select('region_id', 'region_name')->where('region_id',$id)->first();
    }
}
