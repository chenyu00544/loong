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

class UsersModel extends Model
{
    protected $table = 'users';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUsersByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->leftJoin('user_rank', 'users.user_rank', '=', 'user_rank.rank_id')
            ->where($where)
            ->paginate($size);
    }

    public function getUser($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getUsersByOr($wheres = [], $column = ['*'])
    {
        $m = $this->select($column);
        foreach ($wheres as $where){
            $m->orWhere($where);
        }
        return $m->get();
    }

    public function setUser($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addUser($data)
    {
        return $this->create($data);
    }

    public function setUserMoney($where, $user_money)
    {
        $re = $this->where($where)->first();
        $res = $this->where($where)->update(['user_money' => ($re->user_money + $user_money)]);
        return $res;
    }

    public function searchUsers($search, $column = ['*'])
    {
        $m = $this->select($column);
        foreach ($search as $key => $value) {
            $m->orWhere($key, 'like', '%' . $value . '%');
        }
        return $m->get();
    }

    public function delUser($where)
    {
        return $this->where($where)->delete();
    }

    public function countUser($where)
    {
        return $this->where($where)
            ->count();
    }

    //分表
    public function table($uid, $bit = 1, $seed = 20)
    {
        if($uid >> $seed != 0){
            $tb = $this->table . '_' . sprintf('%0' . $bit . 'd', ($uid >> $seed));
            $this->setTable($tb);
        }
    }

    //分库
    public function connection($uid, $bit = 1, $seed = 20)
    {
        if($uid >> $seed != 0){
            $cc = 'shop_' . sprintf('%0' . $bit . 'd', ($uid >> $seed));
            $this->setConnection($cc);
        }
    }

    public function test($where)
    {
        $this->table($where['uid']);
        $this->connection($where['uid']);
        return $this->toSql();
    }
}
