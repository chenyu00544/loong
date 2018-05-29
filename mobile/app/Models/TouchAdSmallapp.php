<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Class TouchAd
 */
class TouchAdSmallapp extends Model
{
    protected $table = 'touch_ad_smallapp';

    protected $primaryKey = 'ad_id';

    public $timestamps = false;

    protected $fillable = [
        
        'ad_name',
        'ext_url',
        'nav_id',
        'ad_image',
        
        'is_show',
        'ad_title1',
		'ad_title2',
		'ad_title3',
		'button_title',
		 'ad_sort',
        
        'ad_type',
        'add_time'
    ];

    protected $guarded = [];

	/*
    public function position()
    {
        return $this->belongsTo('App\Models\TouchAdPosition', 'position_id', 'position_id');
    }

	*/
	
    
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
    public function getExtUrl()
    {
        return $this->ext_url;
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
    public function getAdType()
    {
        return $this->ad_type;
    }

    /**
     * @return mixed
     */
    public function getGoodsName()
    {
        return $this->goods_name;
    }

	 /**
     * @return mixed
     */
    public function getAdTitle1()
    {
        return $this->ad_title1;
    }

	 public function getAdTitle2()
    {
        return $this->ad_title2;
    }
	
	 public function getAdTitle3()
    {
        return $this->ad_title3;
    }
	
	public function getButtonTitle()
    {
        return $this->button_title;
    }
    
     
}
