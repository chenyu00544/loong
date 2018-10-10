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

class EmailConfigureModel extends Model
{
    protected $table = 'email_configure';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getEmailConfigure()
    {
        return $this->where([])
            ->first();
    }

    public function setEmailConfigure($where, $data)
    {
        $this->where($where)
            ->update($data);
    }

    public function addEmailConfigure($data)
    {
        $this->create($data);
    }
}
