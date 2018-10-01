阿里云的centos7.4配置NGINX

    yum update 更新资源库
    yum install nginx即可
    nginx操作 
    systemctl start nginx.serive打开nginx 
    systemctl status nginx.service 查看状态 
    service nginx start开启nginx
    
    设置开机启动nginx服务
    创建nginx启动命令脚本 
    vi /etc/init.d/nginx 
    插入以下内容, 注意修改PATH和NAME字段, 匹配自己的安装路径 (这段是从网上copy的)
    
    
    #! /bin/bash 
    # chkconfig: - 85 15 
    PATH=/usr/local/nginx 
    DESC="nginx daemon" 
    NAME=nginx 
    DAEMON=$PATH/sbin/$NAME 
    CONFIGFILE=$PATH/conf/$NAME.conf 
    PIDFILE=$PATH/logs/$NAME.pid 
    SCRIPTNAME=/etc/init.d/$NAME 
    set -e 
    [ -x "$DAEMON" ] || exit 0 
    do_start() { 
    $DAEMON -c $CONFIGFILE || echo -n "nginx already running" 
    } 
    do_stop() { 
    $DAEMON -s stop || echo -n "nginx not running" 
    } 
    do_reload() { 
    $DAEMON -s reload || echo -n "nginx can't reload" 
    } 
    case "$1" in 
    start) 
    echo -n "Starting $DESC: $NAME" 
    do_start 
    echo "." 
    ;; 
    stop) 
    echo -n "Stopping $DESC: $NAME" 
    do_stop 
    echo "." 
    ;; 
    reload|graceful) 
    echo -n "Reloading $DESC configuration..." 
    do_reload 
    echo "." 
    ;; 
    restart) 
    echo -n "Restarting $DESC: $NAME" 
    do_stop 
    do_start 
    echo "." 
    ;; 
    *) 
    echo "Usage: $SCRIPTNAME {start|stop|reload|restart}" >&;2 
    exit 3 
    ;; 
    esac 
    exit 0 
    设置执行权限 
    chmod a+x /etc/init.d/nginx 
    注册成服务 
    chkconfig --add nginx 
    设置开机启动 
    chkconfig nginx on 
    
    #重新读取nginx配置(这个最常用, 不用停止nginx服务就能使修改的配置生效) 
    systemctl reload nginx.service
    
阿里云centos7.4安装mysql5.7
使用root登录
Long19860212

安装PHP

    yum -y  install autoconf
    1、安装gcc及libxml2yum install gcc -yyum install libxml2* -y
    2、下载最新PHP官方安装包3、解压安装包tar zxvf php-5.6.28.tar.gz
    4、安装php#cd php-5.6.28  
    #./configure --prefix=/usr/local/php --enable-fpm  
    #make  
    #make install  
    
    配置php-fpm
    
    # cp php.ini-production /etc/php.ini
    # cp /usr/local/php/etc/php-fpm.conf.default /usr/local/php/etc/php-fpm.conf
    
    # cp /usr/local/php/etc/php-fpm.d/www.conf.default /usr/local/php/etc/php-fpm.d/www.conf
    # cp sapi/fpm/init.d.php-fpm /etc/init.d/php-fpm
    # chmod +x /etc/init.d/php-fpm
    
    service php-fpm restart   启动fpm服务
    netstat -nlpt|grep php-fpm      查看php-fpm监听的端口(一般为9000)
    
    vi   /etc/hosts     编辑hosts文件
    在其中增加一行      127.0.0.1    test.com
    chkconfig --add /etc/init.d/php-fpm
    chkconfig php-fpm on 
    
安装redis

    $ wget http://download.redis.io/releases/redis-4.0.11.tar.gz 
    cd /usr/local
    mkdir redis
    cd ~
    tar -xzvf redis-4.0.11.tar.gz -C /usr/local/redis
    cd /usr/local/redis/redis-4.0.9
    make
    cd /usr/local/redis/redis-4.0.9/src
    cp {redis-server,redis-cli,redis-benchmark,redis-check-aof,redis-check-rdb,redis-sentinel} /usr/local/bin
    make install
    执行基本配置
    ./utils/install_server.sh
    chkconfig --list
    
    设置redis开机自启
    $ cd /etc/init.d
    $ vi redis_6379 //在第二行添加# chkconfig: 2345 90 10
    $ chmod a+x redis_6379
    $ chkconfig --add redis_6379
    $ chkconfig redis_6379 on
    
1.确保服务器系统处于最新状态
[root@localhost ~]# yum -y update
如果显示以下内容说明已经更新完成
Replaced:
  grub2.x86_64 1:2.02-0.64.el7.centos   grub2-tools.x86_64 1:2.02-0.64.el7.centos
Complete!

2.重启服务器
[root@localhost ~]# reboot

3.首先检查是否已经安装，如果已经安装先删除以前版本，以免安装不成功
[root@localhost ~]# php -v
或
[root@localhost ~]# rpm -qa | gerp mysql
或
[root@localhost ~]# yum list installed | grep mysql

如果显示以下内容说明没有安装服务
-bash: gerp: command not found

4.下载MySql安装包
[root@localhost ~]# rpm -ivh http://dev.mysql.com/get/mysql57-community-release-el7-8.noarch.rpm
或
[root@localhost ~]# rpm -ivh http://dev.mysql.com/get/mysql-community-release-el7-5.noarch.rpm


5.安装MySql
[root@localhost ~]# yum install -y mysql-server
或
[root@localhost ~]# yum install mysql-community-server
如果显示以下内容说明安装成功
Complete!

6.设置开机启动Mysql
[root@localhost ~]# systemctl enable mysqld.service

7.检查是否已经安装了开机自动启动
[root@localhost ~]# systemctl list-unit-files | grep mysqld
如果显示以下内容说明已经完成自动启动安装
mysqld.service                                enabled

8.设置开启服务
[root@localhost ~]# systemctl start mysqld.service

9.查看MySql默认密码
[root@localhost ~]# grep 'temporary password' /var/log/mysqld.log

10.登陆MySql，输入用户名和密码
[root@localhost ~]# mysql -uroot -p

11.修改当前用户密码
mysql>SET PASSWORD = PASSWORD('Abc123!_');

12.开启远程登录，授权root远程登录
mysql>GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'a123456!' WITH GRANT OPTION;

13.命令立即执行生效
mysql>flush privileges;

---------------------------------------------------------------------

其他功能:

# 检查并且显示Apache相关安装包
[root@localhost ~]# rpm -qa | grep mysql

# 删除MySql
[root@localhost ~]# yum remove -y mysql mysql mysql-server mysql-libs compat-mysql51
或
[root@localhost ~]# rpm -e mysql-community-libs-5.7.20-1.el7.x86_64 --nodeps
或
[root@localhost ~]# yum -y remove mysql-community-libs-5.7.20-1.el7.x86_64

# 查看MySql相关文件
[root@localhost ~]# find / -name mysql

# 重启MySql服务
[root@localhost ~]# service mysqld restart

# 查看MySql版本
[root@localhost ~]# yum repolist all | grep mysql

# 查看当前的启动的 MySQL 版本
[root@localhost ~]# yum repolist enabled | grep mysql

# 通过Yum来安装MySQL,会自动处理MySQL与其他组件的依赖关系
[root@localhost ~]# yum install mysql-community-server 

# 查看MySQL安装目录
[root@localhost ~]# whereis mysql

# 启动MySQL服务
[root@localhost ~]# systemctl start mysqld

# 查看MySQL服务状态
[root@localhost ~]# systemctl status mysqld

# 关闭MySQL服务
[root@localhost ~]# systemctl stop mysqld

# 测试MySQL是否安装成功
[root@localhost ~]# mysql

# 查看MySql默认密码
[root@localhost ~]# grep 'temporary password' /var/log/mysqld.log

# 查看所有数据库
mysql>show databases;

# 退出登录数据库
mysql>exit;

# 查看所有数据库用户
mysql>SELECT DISTINCT CONCAT('User: ''',user,'''@''',host,''';') AS query FROM mysql.user;



mysql密码：Loong00544!#%&

mysql 远程登录密码 Vcvbuy00544!#%@$^