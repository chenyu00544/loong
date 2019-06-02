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

class WechatReplyModel extends Model
{
    protected $table = 'wechat_reply';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function wechatRuleKeywords()
    {
        return $this->hasOne('App\Http\Models\Shop\WechatRuleKeywordsModel', 'rid', 'id');
    }

    public function getWechatReply($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('add_time', 'DESC')
            ->first();
    }

    public function getWechatReplys($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('add_time', 'DESC')
            ->get();
    }

    public function getWechatReplyHas($where, $column = ['*'], $key = '')
    {
        $m = $this->select($column)
            ->where($where);
        if ($key != '') {
            $m->whereHas('wechatRuleKeywords', function ($query) use ($key) {
                $query->where('rule_keywords', $key);
            });
        }
        return $m->orderBy('add_time', 'DESC')
            ->first();
    }

    public function setWechatReply($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addWechatReply($data)
    {
        return $this->create($data);
    }

    public function getWechatReplyCount($where)
    {
        return $this->where($where)->count();
    }

    public function delWechatReply($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {

        }
    }
}
