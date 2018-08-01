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

class BonusUserModel extends Model
{
    protected $table = 'bonus_user';
    protected $primaryKey = 'bu_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Bonus()
    {
        return $this->belongsTo('App\Http\Models\Shop\BonusModel', 'bonus_type_id', 'bonus_id');
    }

    public function User()
    {
        return $this->belongsTo('App\Http\Models\Shop\UsersModel', 'user_id', 'user_id');
    }

    public function getBonusUserByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->with(['Bonus'])
            ->with(['User'])
            ->where($where)
            ->orderBy('bu_id', 'desc')
            ->paginate($size);
    }

    public function addBonusUser($data)
    {
        return $this->create($data);
    }

    public function delBonusUser($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function delBonusUsers($where)
    {
        return $this->whereIn('bu_id', $where)
            ->delete();
    }
}
