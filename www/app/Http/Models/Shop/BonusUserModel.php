<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BonusUserModel extends Model
{
    protected $table = 'bonus_user';
    protected $primaryKey = 'bu_id';
    public $timestamps = false;
    protected $guarded = [];

    public function delBonusUser($where)
    {
        return $this->where($where)
            ->delete();
    }
}
