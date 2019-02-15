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

class BonusUserModel extends Model
{
    protected $table = 'bonus_user';
    protected $primaryKey = 'bu_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Bonus()
    {
        return $this->hasOne('App\Http\Models\Wxapp\BonusModel', 'bonus_id', 'bonus_type_id');
    }

    public function getBonuses($where, $column=['*'])
    {
        $m = $this->select($column)
            ->where($where)
            ->with(['Bonus']);
        return $m->get();
    }

    public function countBonusUser($where)
    {
        return $this->where($where)
            ->count();
    }

}
