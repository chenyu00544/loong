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

class GoodsTypeModel extends Model
{
    protected $table = 'goods_type';
    protected $primaryKey = 'cat_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsTypesPage($size = 10, $where = [], $columns = ['*'], $keywords = '')
    {
        $goods = $this->select($columns);
        if (!empty($where)) {
            $goods->where($where);
        }
        if (!empty($keywords)) {
            $goods->where('cat_name', 'like', '%' . $keywords . '%');
        }
        return $goods->orderBy('cat_id', 'desc')
            ->paginate($size);
    }

    public function getGoodsTypes($where, $columns = ['*'])
    {
        return $this->select($columns)
            ->where($where)
            ->get();
    }

    public function getGoodsType($where)
    {
        return $this->where($where)
            ->first();
    }

    public function addGoodsType($updata)
    {
        return $this->create($updata);
    }

    public function setGoodsType($updata, $id)
    {
        return $this->where('cat_id', $id)
            ->update($updata);
    }

    public function deleteType($where)
    {
        return $this->where($where)
            ->delete();
    }
}
