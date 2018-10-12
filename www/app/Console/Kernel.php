<?php

namespace App\Console;

use Illuminate\Console\Scheduling\Schedule;
use Illuminate\Foundation\Console\Kernel as ConsoleKernel;
use Illuminate\Support\Facades\Log;

class Kernel extends ConsoleKernel
{
    /**
     * The Artisan commands provided by your application.
     *
     * @var array
     */
    protected $commands = [
        \App\Console\Commands\TestCommand::class,
        \App\Console\Commands\CronCommand::class,
    ];

    /**
     * Define the application's command schedule.
     *  定义调度任务
     * @param  \Illuminate\Console\Scheduling\Schedule $schedule
     * @return void
     *
     * ->cron(‘* * * * *’);    在自定义Cron调度上运行任务
     * ->everyMinute();    每分钟运行一次任务
     * ->everyFiveMinutes();   每五分钟运行一次任务
     * ->everyTenMinutes();    每十分钟运行一次任务
     * ->everyThirtyMinutes(); 每三十分钟运行一次任务
     * ->hourly(); 每小时运行一次任务
     * ->daily();  每天凌晨零点运行任务
     * ->dailyAt(‘13:00’); 每天13:00运行任务
     * ->twiceDaily(1, 13);    每天1:00 & 13:00运行任务
     * ->weekly(); 每周运行一次任务
     * ->monthly();    每月运行一次任务
     */
    protected function schedule(Schedule $schedule)
    {
        //闭包
//        $schedule->call(function () {
//            Log::info('任务调度');
//        })->everyMinute();
        $schedule->command('command:TestCommand')->everyMinute();
        $schedule->command('command:CronCommand')->everyMinute();
    }

    /**
     * Register the Closure based commands for the application.
     *
     * @return void
     */
    protected function commands()
    {
        require base_path('routes/console.php');
    }
}
