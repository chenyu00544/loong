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

class MerchantsDocumentTitleModel extends Model
{
    protected $table = 'merchants_document_title';
    protected $primaryKey = 'dt_id';
    public $timestamps = false;
    protected $guarded = [];

    public function mdf()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsDtFileModel', 'dt_id', 'dt_id');
    }

    public function getMerchantsDocumentTitles($whereIn, $uid, $column = ['*'])
    {
        return $this->select($column)
            ->with(['mdf' => function ($query) use ($uid) {
                $query->where(['user_id' => $uid]);
            }])
            ->whereIn('cat_id', $whereIn)
            ->get();
    }
}
