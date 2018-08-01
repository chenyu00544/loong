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

class TransportModel extends Model
{
    protected $table = 'transport';
    protected $primaryKey = 'tid';
    public $timestamps = false;
    protected $guarded = [];

    public function getTransportAll($where = ['ru_id' => 0], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('tid', 'desc')
            ->get();
    }

    public function getTransport($id, $column = ['*'])
    {
        return $this->select($column)
            ->where('tid', $id)
            ->first();
    }

    public function setTransport($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addTransport($data)
    {
        return $this->create($data);
    }

    public function deleteTransport($where)
    {
        return $this->where($where)
            ->delete();
    }
}
