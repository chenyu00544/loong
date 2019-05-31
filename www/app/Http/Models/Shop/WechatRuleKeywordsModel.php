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

class WechatRuleKeywordsModel extends Model
{
    protected $table = 'wechat_rule_keywords';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getWechatRuleKeywords($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('add_time', 'DESC')
            ->first();
    }

    public function setWechatRuleKeywords($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatRuleKeywords($data)
    {
        return $this->create($data);
    }

    public function getWechatRuleKeywordsCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatRuleKeywords($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){

        }
    }
}
