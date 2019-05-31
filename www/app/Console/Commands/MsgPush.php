<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Workerman\Worker;
use Workerman\Lib\Timer;
use PHPSocketIO\SocketIO;

class MsgPush extends Command
{
    protected $signature = 'msg-push
    {action=start : start | restart | reload(平滑重启) | stop | status | connetions}
    {--d : deamon or debug}';

    //启动
    protected $description = 'web消息推送服务';

    // 全局数组保存uid在线数据
    private static $uidConnectionCounter = [];
    // 广播的在线用户数,一个uid代表一个用户
    private static $onlineCount = 0;
    // 广播的在线页面数,同一个uid可能开启多个页面
    private static $onlinePageCount = 0;
    //PHPSocketIO服务
    private static $senderIo = null;

    public function __construct()
    {
        parent::__construct();
    }

    /**
     * Execute the console command.
     *
     * @return mixed
     */
    public function handle()
    {
        self::$senderIo = new SocketIO(3120);
        // 当有客户端连接时打印一行文字
        self::$senderIo->on('connection', function ($socket) {
            // 当客户端发来登录事件时触发,$uid目前由页面传值决定,当然也可以根据业务需要由服务端来决定
            $socket->on('login', function ($uid) use ($socket) {
                // 已经登录过了
                if (isset($socket->uid)) return;
                echo $uid;
                // 更新对应uid的在线数据
                $uid = (string)$uid;

                // 这个uid有self::$uidConnectionCounter[$uid]个socket连接
                self::$uidConnectionCounter[$uid] = isset(self::$uidConnectionCounter[$uid]) ? self::$uidConnectionCounter[$uid] + 1 : 1;

                // 将这个连接加入到uid分组，方便针对uid推送数据
                $socket->join($uid);
                $socket->uid = $uid;
                // 更新这个socket对应页面的在线数据
                self::emitOnlineCount();
            });

            $socket->on('message', function ($msg) use ($socket) {
                $msg_arr = explode('_', $msg);
                self::$senderIo->to($msg_arr[1])->emit('back_msg', $msg_arr[0]);
            });

            // 当客户端断开连接是触发（一般是关闭网页或者跳转刷新导致）
            $socket->on('disconnect', function () use ($socket) {
                if (!isset($socket->uid)) {
                    return;
                }

                // 将uid的在线socket数减一
                if (--self::$uidConnectionCounter[$socket->uid] <= 0) {
                    unset(self::$uidConnectionCounter[$socket->uid]);
                }
            });
        });

        // 当self::$senderIo启动后监听一个http端口，通过这个端口可以给任意uid或者所有uid推送数据
        self::$senderIo->on('workerStart', function () {
            // 监听一个http端口
            $innerHttpWorker = new Worker('http://0.0.0.0:2121');
            // 当http客户端发来数据时触发
            $innerHttpWorker->onMessage = function ($httpConnection, $data) {

                $type = $_REQUEST['type'] ?? '';
                $content = htmlspecialchars($_REQUEST['content'] ?? '');
                $to = (string)($_REQUEST['to'] ?? '');

                // 推送数据的url格式 type=publish&to=uid&content=xxxx
                switch ($type) {
                    case 'publish':
                        // 有指定uid则向uid所在socket组发送数据
                        if ($to) {
                            self::$senderIo->to($to)->emit('new_msg', $content);
                        } else {
                            // 否则向所有uid推送数据
                            self::$senderIo->emit('new_msg', $content);
                        }
                        // http接口返回，如果用户离线socket返回fail
                        if ($to && !isset(self::$uidConnectionCounter[$to])) {
                            return $httpConnection->send('offline');
                        } else {
                            return $httpConnection->send('ok');
                        }
                }
                return $httpConnection->send('fail');
            };
            // 执行监听
            $innerHttpWorker->listen();

            // 一个定时器，定时向所有uid推送当前uid在线数及在线页面数
            Timer::add(1, [self::class, 'emitOnlineCount']);
        });
        Worker::runAll();
    }

    /**
     * 将在线数变化推送给所有登录端
     * 须是public方法,可供其它类调用
     */
    public static function emitOnlineCount()
    {
        $newOnlineCount = count(self::$uidConnectionCounter);
        $newOnlinePageCount = array_sum(self::$uidConnectionCounter);

        // 只有在客户端在线数变化了才广播，减少不必要的客户端通讯
        if ($newOnlineCount != self::$onlineCount || $newOnlinePageCount != self::$onlinePageCount) {
//            var_dump('emitOnlineCount: ', self::$uidConnectionCounter);
            //将在线数变化推送给所有登录端
            self::$senderIo->emit(
                'update_online_count',
                [
                    'onlineCount' => $newOnlineCount,
                    'onlinePageCount' => $newOnlinePageCount
                ]
            );
            self::$onlineCount = $newOnlineCount;
            self::$onlinePageCount = $newOnlinePageCount;
        }
    }
}
