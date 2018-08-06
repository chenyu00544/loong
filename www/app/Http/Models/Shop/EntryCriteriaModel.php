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

class EntryCriteriaModel extends Model
{
    protected $table = 'entry_criteria';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getEntryCriterias($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }
}
