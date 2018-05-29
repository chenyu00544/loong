<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class AccountLog
 */
class TouchAdWxapp extends Model
{
    protected $table = 'touch_ad_wxapp';

    protected $primaryKey = 'ad_id';

    public $timestamps = false;

    protected $fillable = [
        'ad_id',
        'ext_url',
        'ad_type',
        'nav_id',
        'ad_sort',
        'add_time',
        'ad_image',
        'is_show',
        'ad_name',
        'show_page_type',
        'ads_bonus_id'
    ];

    protected $guarded = [];

    
    /**
     * @return mixed
     */
    public function getAdId()
    {
        return $this->ad_id;
    }

    /**
     * @return mixed
     */
    public function getExtUrl()
    {
        return $this->ext_url;
    }

    /**
     * @return mixed
     */
    public function getAdType()
    {
        return $this->ad_type;
    }

    /**
     * @return mixed
     */
    public function getNavId()
    {
        return $this->nav_id;
    }

    /**
     * @return mixed
     */
    public function getAdSort()
    {
        return $this->ad_sort;
    }

    /**
     * @return mixed
     */
    public function getAddTime()
    {
        return $this->add_time;
    }

    /**
     * @return mixed
     */
    public function getAdImage()
    {
        return $this->ad_image;
    }

    /**
     * @return mixed
     */
    public function getIsShow()
    {
        return $this->is_show;
    }

    /**
     * @return mixed
     */
    public function getAdName()
    {
        return $this->ad_name;
    }

    /**
     * @return mixed
     */
    public function getShowPageType()
    {
        return $this->show_page_type;
    }

    /**
     * @return mixed
     */
    public function getAdsBonusId()
    {
        return $this->ads_bonus_id;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdId($value)
    {
        $this->ad_id = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setExtUrl($value)
    {
        $this->ext_url = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdType($value)
    {
        $this->ad_type = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setNavId($value)
    {
        $this->nav_id = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdSort($value)
    {
        $this->ad_sort = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAddTime($value)
    {
        $this->add_time = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdImage($value)
    {
        $this->ad_image = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setIsShow($value)
    {
        $this->is_show = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdName($value)
    {
        $this->ad_name = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setShowPageType($value)
    {
        $this->show_page_type = $value;
        return $this;
    }

    /**
     * @param $value
     * @return $this
     */
    public function setAdsBonusId($value)
    {
        $this->ads_bonus_id = $value;
        return $this;
    }
}