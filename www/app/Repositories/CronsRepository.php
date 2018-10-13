<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CronsRepositoryInterface;
use App\Facades\Cron;
use App\Http\Models\Shop\CronsModel;

class CronsRepository implements CronsRepositoryInterface
{

    private $cronsModel;

    public function __construct(
        CronsModel $cronsModel
    )
    {
        $this->cronsModel = $cronsModel;
    }

    public function getCronsByPage()
    {
        return $this->cronsModel->getCronsByPage();
    }

    public function getCron($id)
    {
        $where['cron_id'] = $id;
        return $this->cronsModel->getCron($where);
    }

    public function setCron($data, $id)
    {
        $where['cron_id'] = $id;
        if ($data['ttype'] == 'hour') {
            $data['day'] = 0;
            $data['week'] = 0;
        } elseif ($data['ttype'] == 'day') {
            $data['week'] = 0;
        } elseif ($data['ttype'] == 'week') {
            $data['day'] = 0;
        }
        if (empty($data['hour'])) {
            $data['hour'] = 0;
        }
        if (empty($data['minute'])) {
            $data['minute'] = 0;
        }
        unset($data['ttype']);
        $data['nextime'] = $this->computingNexTime($data);
        return $this->cronsModel->setCron($where, $data);
    }

    public function addCron($data)
    {
        if ($data['ttype'] == 'hour') {
            $data['day'] = 0;
            $data['week'] = 0;
        } elseif ($data['ttype'] == 'day') {
            $data['week'] = 0;
        } elseif ($data['ttype'] == 'week') {
            $data['day'] = 0;
        }
        if (empty($data['hour'])) {
            $data['hour'] = 0;
        }
        if (empty($data['minute'])) {
            $data['minute'] = 0;
        }
        unset($data['ttype']);
        $data['nextime'] = $this->computingNexTime($data);
        $re = $this->cronsModel->addCron($data);
        return $re;
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['cron_id'] = $data['id'];
        $updata = [];
        if ($data['type'] == 'order') {
            $updata['cron_order'] = $data['val'];
        } else {
            $updata['enable'] = $data['val'];
        }
        $re = $this->cronsModel->setCron($where, $updata);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function implement($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $time = time();
        $where['cron_id'] = $data['id'];
        $cron = $this->cronsModel->getCron($where);
        $cron->thistime = $time;
        $cron->nextime = $this->computingNexTime($cron->toArray());
        if ($cron->run_once == 1) {
            $cron->enable = 0;
        }
        $re = $this->task($cron);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function implementes()
    {
        $time = time();
        $crons = $this->cronsModel->getCrons();
        foreach ($crons as $cron) {
            if ($cron->nextime < $time) {
                $cron->thistime = $time;
                $cron->nextime = $this->computingNexTime($cron->toArray());
            }
        }
    }

    //返回下次执行的时间戳
    public function computingNexTime($cron)
    {
        //时间处理
        $time = time();

        $year = date('Y', time());
        $month = date('m', time());
        $day = date('d', time());
        $week = date('w', time());

        $days = date('t', time());

        $hours = explode(',', $cron['hour']);
        $minutes = explode(',', $cron['minute']);

        //每个月
        if (!empty($cron['day'])) {
            $mirtime = [];
            if ($cron['day'] != $day) {
                //不在当天内
                do {
                    if ($month == '12') {
                        $year += 1;
                        $month = 1;
                    } else {
                        $month += 1;
                    }
                    $_days = date('t', strtotime($year . '-' . ($month)));
                } while ($cron['day'] > $_days);
                $day = $cron['day'];
            } else {
                //在当天内
                $dateFormatBefore = $year . '-' . $month . '-' . $day;
                foreach ($hours as $h) {
                    foreach ($minutes as $min) {
                        $dateFormatAfter = ' ' . $h . ':' . $min;
                        $mirtime[] = strtotime($dateFormatBefore . $dateFormatAfter);
                    }
                }
                foreach ($mirtime as $mirt) {
                    if ($time < $mirt) {
                        return $mirt;
                    }
                }
            }
            $dateFormatBefore = $year . '-' . $month . '-' . $day;
            $dateFormatAfter = ' ' . (!empty($hours[0]) ? $hours[0] : 0) . ':' . (!empty($minutes[0]) ? $minutes[0] : 0);
            return strtotime($dateFormatBefore . $dateFormatAfter);
        }

        //每个星期
        if (!empty($cron['week'])) {
            if ($cron['week'] != $week) {
                //不在当天内
                $week = 7 - $week + $cron['week'];
                if ($day + $week > $days) {
                    if ($month == '12') {
                        $year += 1;
                        $month = 1;
                    } else {
                        $month += 1;
                    }
                    $day = $day + $week - $days;
                } else {
                    $day = $day + $week;
                }

            } else {
                //在当天内
                $dateFormatBefore = $year . '-' . $month . '-' . $day;
                foreach ($hours as $h) {
                    foreach ($minutes as $min) {
                        $dateFormatAfter = ' ' . $h . ':' . $min;
                        $mirtime[] = strtotime($dateFormatBefore . $dateFormatAfter);
                    }
                }
                foreach ($mirtime as $mirt) {
                    if ($time < $mirt) {
                        return $mirt;
                    }
                }
            }
            $dateFormatBefore = $year . '-' . $month . '-' . $day;
            $dateFormatAfter = ' ' . (!empty($hours[0]) ? $hours[0] : 0) . ':' . (!empty($minutes[0]) ? $minutes[0] : 0);
            return strtotime($dateFormatBefore . $dateFormatAfter);
        }

        //每天
        $dateFormatBefore = $year . '-' . $month . '-' . $day;
        foreach ($hours as $h) {
            foreach ($minutes as $min) {
                $dateFormatAfter = ' ' . $h . ':' . $min;
                $mirtime[] = strtotime($dateFormatBefore . $dateFormatAfter);
            }
        }
        foreach ($mirtime as $mirt) {
            if ($time < $mirt) {
                return $mirt;
            }
        }
        return $mirtime[0] + 86400;
    }

    public function task($cron)
    {
        switch ($cron->alow_files) {
            case 'order':
                Cron::orderConfirmTake($cron->cron_num);
                break;
        }
        $where['cron_id'] = $cron->cron_id;
        $update['thistime'] = time();
        $update['nextime'] = $cron->nextime;
        if($cron->run_once == 1){
            $update['enable'] = 0;
        }
        return $this->cronsModel->setCron($where, $update);
    }
}