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

class SearchKeywordModel extends Model
{
    protected $table = 'search_keyword';
    protected $primaryKey = 'keyword_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getKeywords($where, $column=['*'])
    {
        return $this->select($column)
            ->where($where)
            ->offset(0)
            ->limit(10)
            ->orderBy('count','DESC')
            ->get();
    }

    public function incrementKeyword($where)
    {
        return $this->where($where)
            ->increment('count', 1);
    }

    public function addKeyword($data)
    {
        return $this->create($data);
    }
}
