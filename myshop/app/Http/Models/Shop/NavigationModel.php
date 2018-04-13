<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class NavigationModel extends Model
{
    protected $table = 'nav';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getNavsAll()
    {

    }

    public function getNavs($size = 10)
    {
        return $this->orderBy('position', 'desc')
            ->orderBy('vieworder', 'asc')
            ->paginate($size);
    }

    public function getNavsTop()
    {
        return $this->where('cid', 0)
            ->orderBy('vieworder', 'asc')
            ->get();
    }

    public function getNav($id)
    {
        return $this->find($id);
    }

    public function addNav($data)
    {
        return $this->create($data);
    }

    public function upDateNav($data, $id)
    {
        return $this->where('id',$id)
            ->update($data);
    }

    public function setNav($data, $where)
    {
        return $this->where($where)
            ->update($data);
    }

    public function deleteNav($where)
    {
        return $this->where($where)
            ->delete();
    }
}
