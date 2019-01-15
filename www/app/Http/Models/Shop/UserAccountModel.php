<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Shop;

use function foo\func;
use Illuminate\Database\Eloquent\Model;

class UserAccountModel extends Model
{

    protected $table = 'user_account';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAccountsByPages($where, $keywords, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->join('users', 'users.user_id', '=', 'user_account.user_id')
            ->where($where);
        if (!empty($keywords)) {
            $m->where(function ($query) use ($keywords) {
                $query->orWhere('users.user_name', 'like', $keywords);
                $query->orWhere('users.email', 'like', $keywords);
                $query->orWhere('users.mobile_phone', 'like', $keywords);
            });
        }
        return $m->orderBy('add_time', 'desc')
            ->paginate($size);
    }

    public function getAccount($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setUserAccount($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAccount($data)
    {
        return $this->create($data);
    }

    public function delAccount($where)
    {
        try {
            return $this->where($where)
                ->delete();
        } catch (\Exception $e) {
        }
    }

    public function countUserAccount($where)
    {
        return $this->where($where)
            ->count();
    }
}
