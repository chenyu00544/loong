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

class GoodsTypeCateModel extends Model
{
    protected $table = 'goods_type_cate';
    protected $primaryKey = 'cate_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getTypeCates($where = [])
    {
        return $this->where($where)
            ->orderBy('sort_order','asc')
            ->get();
    }

    public function getTypeCate($id)
    {
        return $this->where('cate_id', $id)
            ->first();
    }

    public function setTypeCate($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function addTypeCate($data)
    {
        return $this->create($data);
    }

    public function deleteCate($where)
    {
        return $this->where($where)
            ->delete();
    }

}
