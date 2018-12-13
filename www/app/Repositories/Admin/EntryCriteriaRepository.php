<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\EntryCriteriaRepositoryInterface;
use App\Http\Models\Shop\EntryCriteriaModel;

class EntryCriteriaRepository implements EntryCriteriaRepositoryInterface
{

    private $entryCriteriaModel;

    public function __construct(
        EntryCriteriaModel $entryCriteriaModel
    )
    {
        $this->entryCriteriaModel = $entryCriteriaModel;
    }

    public function getEntryCriteriasByPage($where)
    {
        return $this->entryCriteriaModel->getEntryCriteriasByPage($where);
    }

    public function getEntryCriterias($where)
    {
        return $this->entryCriteriaModel->getEntryCriterias($where);
    }

    public function getEntryCriteria($id)
    {
        $where['id'] = $id;
        $rep = $this->entryCriteriaModel->getEntryCriteria($where);
        $rep->option_value = explode(',',$rep->option_value);
        return $rep;
    }

    public function setEntryCriteria($data, $id)
    {
        $where['id'] = $id;
        $updata = [];
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'is_cumulative':
                    break;
                case 'charge':
                    break;
                case 'data_type':
                    break;
                case 'is_mandatory':
                    break;
                case 'option_value':
                    break;
                case 'type':
                    if(!empty($value)){
                        if ($value == 'text') {
                            $updata['data_type'] = $data['data_type'];
                            $updata['is_mandatory'] = $data['is_mandatory'];
                        } elseif ($value == 'select') {
                            $updata['option_value'] = implode(',', $data['option_value']);
                        } elseif ($value == 'charge') {
                            $updata['is_cumulative'] = $data['is_cumulative'];
                            $updata['charge'] = $data['charge'];
                        }
                        $updata['type'] = $value;
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        return $this->entryCriteriaModel->setEntryCriteria($where, $updata);
    }

    public function addEntryCriteria($data)
    {
        $updata = [];
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'is_cumulative':
                    break;
                case 'charge':
                    break;
                case 'data_type':
                    break;
                case 'is_mandatory':
                    break;
                case 'option_value':
                    break;
                case 'type':
                    if(!empty($value)){
                        if ($value == 'text') {
                            $updata['data_type'] = $data['data_type'];
                            $updata['is_mandatory'] = $data['is_mandatory'];
                        } elseif ($value == 'select') {
                            $updata['option_value'] = implode(',', $data['option_value']);
                        } elseif ($value == 'charge') {
                            $updata['is_cumulative'] = $data['is_cumulative'];
                            $updata['charge'] = $data['charge'];
                        }
                        $updata['type'] = $value;
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        return $this->entryCriteriaModel->addEntryCriteria($updata);
    }

    public function delEntryCriterias($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        //查询出所有子数据
        $ids = $this->entryCriteriaModel->getSubEntryCriteria([$id]);
        $ids[] = $id;
        $re =  $this->entryCriteriaModel->delEntryCriterias($ids);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}