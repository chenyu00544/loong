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

class TransportModel extends Model
{
    protected $table = 'transport';
    protected $primaryKey = 'tid';
    public $timestamps = false;
    protected $guarded = [];

    public function t_exp()
    {
        return $this->hasMany('App\Http\Models\App\TransportExpressModel', 'tid', 'tid');
    }

    public function t_ext()
    {
        return $this->hasMany('App\Http\Models\App\TransportExtendModel', 'tid', 'tid');
    }

    public function getTransport($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['t_exp'])
            ->with(['t_ext'])
            ->where($where)
            ->first();
    }

    public function getTransportByShip($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['t_exp'=>function($query){
                $query->with(['shipping']);
            }])
            ->with(['t_ext'])
            ->where($where)
            ->first();
    }
}
