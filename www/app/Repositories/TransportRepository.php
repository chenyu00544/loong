<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\TransportRepositoryInterface;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\ShippingModel;
use App\Http\Models\Shop\TransportExpressModel;
use App\Http\Models\Shop\TransportExtendModel;
use App\Http\Models\Shop\TransportModel;
use Illuminate\Support\Facades\Cache;

class TransportRepository implements TransportRepositoryInterface
{
    protected $transportModel;
    protected $transportExtendModel;
    protected $transportExpressModel;
    protected $regionsModel;
    protected $shippingModel;

    public function __construct(
        TransportModel $transportModel,
        TransportExtendModel $transportExtendModel,
        TransportExpressModel $transportExpressModel,
        RegionsModel $regionsModel,
        ShippingModel $shippingModel
    )
    {
        $this->transportModel = $transportModel;
        $this->transportExtendModel = $transportExtendModel;
        $this->transportExpressModel = $transportExpressModel;
        $this->regionsModel = $regionsModel;
        $this->shippingModel = $shippingModel;
    }

    public function getTransportAll()
    {
        return $this->transportModel->getTransportAll();
    }

    public function getTransportExpressByRuId($id)
    {
        return $this->transportExpressModel->getExpressAll(['ru_id' => $id]);
    }

    public function getTransportByRuId($id)
    {
        return $this->transportModel->getTransportAll(['ru_id' => $id]);
    }

    public function getTransportExtendByTid($id, $tid, $regions)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;
        $res = $this->transportExtendModel->getExtendAll([['tid', '=', $tid], ['admin_id', '=', $userId]]);
        $topAreaId = [];
        $areaId = [];
        $topAreaIdSel = [];
        $areaIdSel = [];
        foreach ($res as $val) {
            if ($id != $val->id) {
                if (!empty($val->top_area_id)) {
                    $top_area_id = explode(',', $val->top_area_id);
                    $topAreaId = array_merge($topAreaId, $top_area_id);
                }
                if (!empty($val->area_id)) {
                    $area_id = explode(',', $val->area_id);
                    $areaId = array_merge($areaId, $area_id);
                }
            } else {
                if (!empty($val->top_area_id)) {
                    $top_area_id = explode(',', $val->top_area_id);
                    $topAreaIdSel = array_merge($topAreaIdSel, $top_area_id);
                }
                if (!empty($val->area_id)) {
                    $area_id = explode(',', $val->area_id);
                    $areaIdSel = array_merge($areaIdSel, $area_id);
                }
            }
        }

        foreach ($regions as $key => $value) {
            $i = 0;
            $j = 0;
            foreach ($value['subRegion'] as $k => $val) {
                if (in_array($k, $areaId)) {
                    $i++;
                    $regions[$key]['subRegion'][$k]['disabled'] = true;
                } else {
                    $regions[$key]['subRegion'][$k]['disabled'] = false;
                }
                if (in_array($k, $areaIdSel)) {
                    $j++;
                    $regions[$key]['subRegion'][$k]['selected'] = true;
                } else {
                    $regions[$key]['subRegion'][$k]['selected'] = false;
                }
            }
            $regions[$key]['num'] = $j;
            if ($i >= count($value['subRegion'])) {
                $regions[$key]['disabled'] = true;
                $regions[$key]['selected'] = false;
            } else {
                $regions[$key]['disabled'] = false;
                if ($j > 0) {
                    $regions[$key]['selected'] = true;
                } else {
                    $regions[$key]['selected'] = false;
                }
            }
        }
        return $regions;
    }

    public function getTransportExpressByTid($id, $tid)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;
        $res = $this->transportExpressModel->getExpressAll([['tid', '=', $tid], ['admin_id', '=', $userId]]);

        $shippingId = [];
        $shippingIdSel = [];

        foreach ($res as $val) {
            if ($id != $val->id) {
                if (!empty($val->shipping_id)) {
                    $shipping_id = explode(',', $val->shipping_id);
                    $shippingId = array_merge($shippingId, $shipping_id);
                }
            } else {
                if (!empty($val->shipping_id)) {
                    $shipping_id = explode(',', $val->shipping_id);
                    $shippingIdSel = array_merge($shippingIdSel, $shipping_id);
                }
            }
        }

        $shippings = $this->shippingModel->getShippingAll();
        foreach ($shippings as $key => $value) {
            if (in_array($shippings[$key]->shipping_id, $shippingId)) {
                $shippings[$key]->disabled = true;
                $shippings[$key]->selected = false;
            } else {
                $shippings[$key]->disabled = false;
                $shippings[$key]->selected = false;
            }
            if (in_array($shippings[$key]->shipping_id, $shippingIdSel)) {
                $shippings[$key]->selected = true;
            } else {
                $shippings[$key]->selected = false;
            }
        }
        return $shippings;
    }

    public function getTransportInfo($id = 0, $userId = 0)
    {
        $extends = $this->transportExtendModel->getExtendAll(['ru_id' => 0, 'tid' => $id, 'admin_id' => $userId]);
        $regions = $this->regionsModel->getRegionsRange(1, 2);
        $regionArr = [];
        foreach ($regions as $region) {
            $regionArr[$region->region_id]['name'] = $region->region_name;
            $regionArr[$region->region_id]['pid'] = $region->parent_id;
        }
        foreach ($extends as $extend) {
            $topAreas = [];
            $areas = [];
            if (!empty($extend->top_area_id)) {
                $topAreas = explode(',', $extend->top_area_id);
            }
            if (!empty($extend->area_id)) {
                $areas = explode(',', $extend->area_id);
            }
            foreach ($topAreas as $topArea) {
                $extend->area_html .= '<p><strong>';
                $extend->area_html .= $regionArr[$topArea]['name'] . ':</strong>';

                foreach ($areas as $area) {
                    if ($regionArr[$area]['pid'] == $topArea) {
                        $extend->area_html .= $regionArr[$area]['name'] . ',';
                    }
                }
                $extend->area_html = substr($extend->area_html, 0, -1);
                $extend->area_html .= '</p>';
            }
        }

        $express = $this->transportExpressModel->getExpressAll(['ru_id' => 0, 'tid' => $id, 'admin_id' => $userId]);
        $shippings = $this->shippingModel->getShippingAll();
        $shipsArr = [];
        foreach ($shippings as $shipping) {
            foreach ($shipping->toArray() as $key => $val) {
                $shipsArr[$shipping->shipping_id][$key] = $val;
            }
        }
        foreach ($express as $exp) {
            $shipArr = [];

            if (!empty($exp->shipping_id)) {
                $shipArr = explode(',', $exp->shipping_id);

            }
            foreach ($shipArr as $ship) {
                $exp->ship_html .= $shipsArr[$ship]['shipping_name'] . ',';
            }
            $exp->ship_html = substr($exp->ship_html, 0, -1);
        }

        $portInfo = $this->transportModel->getTransport($id);
        $req['extend'] = $extends;
        $req['express'] = $express;
        $req['transport'] = $portInfo;
        return $req;
    }

    public function setTransport($data)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;
        $id = 0;
        $tid = 0;
        $type = '';
        $updata = [];
        foreach ($data as $key => $val) {
            switch ($key) {
                case 'type':
                    $type = $val;
                    break;
                case 'id':
                    $id = $val;
                    break;
                case 'tid':
                    $tid = $val;
                    break;
                default:
                    $updata[$key] = $val;
                    break;
            }
        }
        if ($type == 'area_add') {
            return $this->transportExtendModel->addExtend(['ru_id' => 0, 'admin_id' => $userId, 'tid' => $tid]);
        } elseif ($type == 'area_del') {
            return $this->transportExtendModel->delExtend(['id' => $id]);
        } elseif ($type == 'area_update') {
            return $this->transportExtendModel->setExtend(['id' => $id], $updata);
        } elseif ($type == 'express_add') {
            return $this->transportExpressModel->addExpress(['ru_id' => 0, 'admin_id' => $userId, 'tid' => $tid]);
        } elseif ($type == 'express_del') {
            return $this->transportExpressModel->delExpress(['id' => $id]);
        } elseif ($type == 'express_update') {
            return $this->transportExpressModel->setExpress(['id' => $id], $updata);
        }
    }

    public function addTransport($data)
    {
        foreach ($data as $key => $val) {
            if (!is_array($val)) {
                $saveData[$key] = $val;
            }
        }
        $saveData['update_time'] = time();
        $transportId = $this->transportModel->addTransport($saveData);
        foreach ($data['areaid'] as $k => $v) {
            $this->transportExtendModel->setExtend(['id' => $v], ['sprice' => $data['sprice'][$k], 'tid' => $transportId->tid]);
        }

        foreach ($data['expressid'] as $k => $v) {
            $this->transportExpressModel->setExpress(['id' => $v], ['shipping_fee' => $data['shipping_fee'][$k], 'tid' => $transportId->tid]);
        }
        return $transportId;
    }

    public function upDateTransport($data, $id)
    {
        foreach ($data as $key => $val) {
            if (!is_array($val)) {
                $upData[$key] = $val;
            }
        }
        $upData['update_time'] = time();
        $re = $this->transportModel->setTransport(['tid' => $id], $upData);
        foreach ($data['areaid'] as $k => $v) {
            $this->transportExtendModel->setExtend(['id' => $v], ['sprice' => $data['sprice'][$k], 'tid' => $id]);
        }

        foreach ($data['expressid'] as $k => $v) {
            $this->transportExpressModel->setExpress(['id' => $v], ['shipping_fee' => $data['shipping_fee'][$k], 'tid' => $id]);
        }
        return $re;
    }

    public function deleteTransport($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $re = $this->transportModel->deleteTransport(['tid' => $id]);
        $this->transportExpressModel->delExpress(['tid' => $id]);
        $this->transportExtendModel->delExtend(['tid' => $id]);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }
}