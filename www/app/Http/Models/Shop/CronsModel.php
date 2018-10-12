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

class CronsModel extends Model
{
    protected $table = 'crons';
    protected $primaryKey = 'cron_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCronsByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('cron_order', 'DESC')
            ->paginate($size);
    }

    public function getCrons()
    {
        return $this->where(['enable' => 1])
            ->orderBy('cron_order', 'DESC')
            ->get();
    }

    public function getCron($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setCron($where, $data)
    {
        return $this->where($where)->update($data);
    }
}
