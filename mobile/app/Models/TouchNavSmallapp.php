<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class TouchNav
 */
class TouchNavSmallapp extends Model
{
    protected $table = 'touch_nav_smallapp';
	
	 protected $primaryKey = 'id';//默认为id  可不指定

    public $timestamps = false;

    protected $fillable = 
	[
        'ctype',
        'cid',
        'name',
        'ifshow',
        'vieworder',
        'opennew',
        'url',
        'type',
        'pic',
		'goods_cate_id',
		'nav_type'
    ];

    protected $guarded = [];

	 public function getNavType()
    {
        return $this->nav_type;
    }
	
	 public function getGoodsCateId()
    {
        return $this->goods_cate_id;
    }
    
    /**
     * @return mixed
     */
    public function getCtype()
    {
        return $this->ctype;
    }

    /**
     * @return mixed
     */
    public function getCid()
    {
        return $this->cid;
    }

    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getIfshow()
    {
        return $this->ifshow;
    }

    /**
     * @return mixed
     */
    public function getVieworder()
    {
        return $this->vieworder;
    }

    /**
     * @return mixed
     */
    public function getOpennew()
    {
        return $this->opennew;
    }

    /**
     * @return mixed
     */
    public function getUrl()
    {
        return $this->url;
    }

    /**
     * @return mixed
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @return mixed
     */
    public function getPic()
    {
        return $this->pic;
    }


    
    /**
     * @param $value
     * @return $this
     */
    public function setCtype($value)
    {
        $this->ctype = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setCid($value)
    {
        $this->cid = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setName($value)
    {
        $this->name = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setIfshow($value)
    {
        $this->ifshow = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setVieworder($value)
    {
        $this->vieworder = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setOpennew($value)
    {
        $this->opennew = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setUrl($value)
    {
        $this->url = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setType($value)
    {
        $this->type = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setPic($value)
    {
        $this->pic = $value;
        return $this;
    }
}
