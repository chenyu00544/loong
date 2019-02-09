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

class TeamLogModel extends Model
{
    protected $table = 'team_log';
    protected $primaryKey = 'team_id';
    public $timestamps = false;
    protected $guarded = [];

    public function order()
    {
        return $this->hasMany('App\Http\Models\App\OrderInfoModel', 'team_id', 'team_id');
    }
}
