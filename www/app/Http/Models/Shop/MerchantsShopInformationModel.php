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

class MerchantsShopInformationModel extends Model
{
    protected $table = 'merchants_shop_information';
    protected $primaryKey = 'shop_id';
    public $timestamps = false;
    protected $guarded = [];

    public function user()
    {
        return $this->hasOne('App\Http\Models\Shop\UsersModel', 'user_id', 'user_id');
    }

    public function shopinfo()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function category()
    {
        return $this->hasOne('App\Http\Models\Shop\CategoryModel', 'id', 'shop_categoryMain');
    }

    public function msf()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsStepsFieldsModel', 'user_id', 'user_id');
    }

    public function getMerchantsShopsByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->with(['shopinfo'])
            ->with(['user'])
            ->with(['category'])
            ->with(['msf'])
            ->leftJoin('merchants_grade', 'merchants_shop_information.user_id', '=', 'merchants_grade.ru_id')
            ->leftJoin('seller_grade', 'merchants_grade.grade_id', '=', 'seller_grade.id')
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('rz_shopName', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        $m->orderBy('shop_id', 'desc');
        return $m->paginate($size);
    }

    public function getMerchantsShopInfo($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }
}
