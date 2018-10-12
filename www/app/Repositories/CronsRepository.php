<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CronsRepositoryInterface;
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
        $where['cron_id'] = $data['id'];
        $cron = $this->cronsModel->getCron($where);
        $cron->nextime = $this->computingNexTime($cron);
        dd(date('Y-m-d H:i:s', $cron->nextime));
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
                $cron->nextime = $this->computingNexTime($cron);
            }
        }
    }

    public function computingNexTime($cron)
    {
        //时间处理
        $year = date('Y', time());
        $month = date('m', time());
        $day = date('d', time());
        $week = date('w', time());
        $hour = date('H', time());
        $minute = date('i', time());

        $days = date('t', time());
        $bool = false;
        if (!empty($cron->day)) {
            do {
                if ($month == '12') {
                    $year += 1;
                    $month = 1;
                } else {
                    $month += 1;
                }
                $_days = date('t', strtotime($year . '-' . ($month)));
            } while ($cron->day > $_days);
            $day = $cron->day;
        } elseif (!empty($cron->week)) {
            $week = 7 - $week + $cron->week;
            if ($day + $week > $days) {
                $month += 1;
                $day = $day + $week - $days;
            } else {
                $day = $day + $week;
            }
        }

        if (!empty($cron->hour)) {
            if ($cron->hour == 'all') {
                $hour += 1;
                if ($hour == 24) {
                    $hour = 0;
                    $day += 1;
                    if ($day > $days) {
                        $day = 1;
                        $month += 1;
                        if ($month > 12) {
                            $month = 1;
                            $year += 1;
                        }
                    }
                }
            } else {
                $hours = explode(',', $cron->hour);
                foreach ($hours as $k => $h) {
                    if (!empty($h) && $h > $hour) {
                        $hour = $h;
                        break;
                    }
                }
                if ($hour > $hours[count($hours) - 1]) {
                    $bool = true;
                    $hour = $hours[0];
                    $day += 1;
                    if ($day > $days) {
                        $day = 1;
                        $month += 1;
                        if ($month > 12) {
                            $month = 1;
                            $year += 1;
                        }
                    }
                }
            }
        }
        if (!empty($cron->minute)) {
            $minutes = explode(',', $cron->minute);
            foreach ($minutes as $k => $min) {
                if (!empty($min) && $minute < $min) {
                    $minute = $min;
                    break;
                }
            }
            if ($bool) {
                $minute = $minutes[0];
            }
        }
        $dateFormatBefore = $year . '-' . $month . '-' . $day;
        $dateFormatAfter = ' ' . $hour . ':' . $minute;
        return strtotime($dateFormatBefore . $dateFormatAfter);
    }
}