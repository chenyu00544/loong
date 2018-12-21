<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class OrderReturnImagesModel extends Model
{
    protected $table = 'order_return_images';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function addImg($data)
    {
        return $this->create($data);
    }
}
