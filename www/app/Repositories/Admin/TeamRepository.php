<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\TeamRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\TeamCategoryModel;
use App\Http\Models\Shop\TeamGoodsModel;
use App\Http\Models\Shop\TeamLogModel;

class TeamRepository implements TeamRepositoryInterface
{

    private $teamCategoryModel;
    private $teamGoodsModel;
    private $teamLogModel;

    public function __construct(
        TeamCategoryModel $teamCategoryModel,
        TeamGoodsModel $teamGoodsModel,
        TeamLogModel $teamLogModel
    )
    {
        $this->teamCategoryModel = $teamCategoryModel;
        $this->teamGoodsModel = $teamGoodsModel;
        $this->teamLogModel = $teamLogModel;
    }

    public function changeTeam($data)
    {
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'sort_order':
                $updata['sort_order'] = $data['value'];
                break;
        }
        return $this->teamGoodsModel->setTeam($where, $updata);
    }

    public function getTeamsByPage($search = [])
    {
        $res = $this->teamGoodsModel->getTeamsByPage([], $search);
        foreach ($res as $re) {
            $re->goods->goods_thumb = FileHandle::getImgByOssUrl($re->goods->goods_thumb);
            $re->goods->goods_img = FileHandle::getImgByOssUrl($re->goods->goods_img);
            $re->goods->original_img = FileHandle::getImgByOssUrl($re->goods->original_img);
        }
        return $res;
    }

    public function getTeam($id)
    {
        $where['id'] = $id;
        return $this->teamGoodsModel->getTeam($where);
    }

    public function addTeam($data)
    {
        $updata = [
            'team_name' => trim($data['team_name']),
            'goods_id' => intval($data['goods_id']),
            'team_price' => doubleval($data['team_price']),
            'team_num' => intval($data['team_num']),
            'validity_time' => intval($data['validity_time']),
            'astrict_num' => intval($data['astrict_num']),
            'tc_id' => intval($data['tc_id']),
            'limit_num' => intval($data['limit_num']),
            'team_desc' => trim($data['team_desc']),
            'sort_order' => intval($data['sort_order']),
            'is_team' => intval($data['is_team']),
            'is_audit' => intval($data['is_audit']),
            'isnot_aduit_reason' => trim($data['isnot_aduit_reason']),
        ];
        return $this->teamGoodsModel->addTeam($updata);
    }

    public function setTeam($data, $id)
    {
        $where['id'] = $id;
        $updata = [
            'team_name' => trim($data['team_name']),
            'goods_id' => intval($data['goods_id']),
            'team_price' => doubleval($data['team_price']),
            'team_num' => intval($data['team_num']),
            'validity_time' => intval($data['validity_time']),
            'astrict_num' => intval($data['astrict_num']),
            'tc_id' => intval($data['tc_id']),
            'limit_num' => intval($data['limit_num']),
            'team_desc' => trim($data['team_desc']),
            'sort_order' => intval($data['sort_order']),
            'is_team' => intval($data['is_team']),
            'is_audit' => intval($data['is_audit']),
            'isnot_aduit_reason' => trim($data['isnot_aduit_reason']),
        ];

        return $this->teamGoodsModel->setTeam($where, $updata);
    }

    public function changeTeamCate($data)
    {
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'isshow':
                $updata['status'] = $data['value'];
                break;
        }

        return $this->teamCategoryModel->setTeamCate($where, $updata);
    }

    public function getTeamCatesByPage($pid = 0)
    {
        $where['parent_id'] = $pid;
        return $this->teamCategoryModel->getTeamCatesByPage($where);
    }

    public function getTeamCatesBySub($pid = 0)
    {
        $where['parent_id'] = $pid;
        $cates = $this->teamCategoryModel->getTeamCatesBySub($where);
        foreach ($cates as $cate) {
            foreach ($cate->subCates as $key => $subCate) {
                if ($cate->subCates->count() - 1 == $key) {
                    $subCate->name = '┗━' . $subCate->name;
                } else {
                    $subCate->name = '┣━' . $subCate->name;
                }
            }
        }
        return $cates;
    }

    public function getTeamCates($pid = 0)
    {
        $where['parent_id'] = $pid;
        return $this->teamCategoryModel->getTeamCates($where);
    }

    public function getTeamCate($id)
    {
        $where['id'] = $id;
        $re = $this->teamCategoryModel->getTeamCate($where);
        $re->tc_img_oss = FileHandle::getImgByOssUrl($re->tc_img);
        return $re;
    }

    public function addTeamCates($data)
    {
        $path = 'team_cate';
        $url = FileHandle::upLoadImage($data['tc_img'], $path);
        $updata = [
            'name' => trim($data['name']),
            'parent_id' => intval($data['parent_id']),
            'content' => trim($data['content']),
            'tc_img' => trim($url),
            'sort_order' => intval($data['sort_order']),
            'status' => intval($data['status']),
        ];
        return $this->teamCategoryModel->addTeamCate($updata);
    }

    public function setTeamCate($data, $id)
    {
        $where['id'] = $id;
        $url = trim($data['old_tc_img']);
        if (!empty($data['tc_img'])) {
            FileHandle::deleteFile($url);
            $path = 'team_cate';
            $url = FileHandle::upLoadImage($data['tc_img'], $path);
        }
        $updata = [
            'name' => trim($data['name']),
            'parent_id' => intval($data['parent_id']),
            'content' => trim($data['content']),
            'tc_img' => trim($url),
            'sort_order' => intval($data['sort_order']),
            'status' => intval($data['status']),
        ];
        return $this->teamCategoryModel->setTeamCate($where, $updata);
    }

    public function delTeamCate($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $subWhere['parent_id'] = $id;
        $count = $this->teamCategoryModel->countTeamCates($subWhere);
        if ($count > 0) {
            return $req;
        }
        $where['id'] = $id;
        $re = $this->teamCategoryModel->delTeamCate($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getTeamLogByPage($search, $nav_type)
    {
        $time = time();
        $where = [
            ['is_show', '=', 1]
        ];
        $raw = '';
        switch ($nav_type) {
            case 'all':
                break;
            case 'teaming'://拼团中
                $raw = 'start_time > ' . $time . '-validity_time*3600';
                $where[] = ['status', '<>', 1];
                break;
            case 'success'://成功团
                $where[] = ['status', '=', 1];
                break;
            case 'failed'://失败团
                $raw = 'start_time < ' . $time . '-validity_time*3600';
                $where[] = ['status', '<>', 1];
                break;
        }
        $res = $this->teamLogModel->getTeamLogByPage($where, $search, $raw);
        foreach ($res as $re) {
            $re->goods_thumb = FileHandle::getImgByOssUrl($re->goods_thumb);
            $re->goods_img = FileHandle::getImgByOssUrl($re->goods_img);
            $re->original_img = FileHandle::getImgByOssUrl($re->original_img);
            //团状态
            if ($re->status != 1 && $time < ($re->start_time + ($re->validity_time * 3600)) && $re->is_team == 1) {//进项中
                $re->status = '进行中';
            } elseif ($re->status != 1 && ($time > ($re->start_time + ($re->validity_time * 3600)) || $re->is_team != 1)) {//失败
                $re->status = '失败团';
            } elseif ($re->status == 1) {
                $re->status = '成功团';
            }
            //剩余时间
            $endtime = $re->start_time + $re->validity_time * 3600;
            $cle = $endtime - $time; //得出时间戳差值
            $d = floor($cle / 3600 / 24);
            $h = floor(($cle % (3600 * 24)) / 3600);
            $m = floor((($cle % (3600 * 24)) % 3600) / 60);
            if ($d) {
                $re->time = $d . '天' . $h . '小时' . $m . '分钟';
            } else {
                $re->time = $h . '小时' . $m . '分钟';
            }
            $re->cle = $cle;
            $re->surplus = $re->team_num - $re->order->count();
        }
        return $res;
    }
}