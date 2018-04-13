<?php

namespace App\Http\Models\shop;

use Illuminate\Database\Eloquent\Model;

class CategoryModel extends Model
{
    protected $table = 'category';
    protected $primaryKey = 'cat_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getComCatesForFirst()
    {
        return $this->where('parent_id',0)
            ->orderBy('sort_order','asc')
            ->get();
    }
}
