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
    
    重启
    systemctl restart nginx
阿里云centos7.4安装mysql5.7
使用root登录
Long19860212

安装PHP

    #tar zxvf php-5.6.38.tar.gz
    #cd php-5.6.38配置安装进入到目录，我们需要在安装的时候将安装目录配置到/usr/local/php/里
    #./configure --prefix=/usr/local/php --with-curl --with-freetype-dir --with-gd 
    --with-gettext --with-iconv-dir --with-kerberos --with-libdir=lib64 --with-libxml-dir 
    --with-MySQL --with-mysqli --with-openssl --with-pcre-regex --with-pdo-mysql 
    --with-pdo-sqlite --with-pear --with-png-dir --with-xmlrpc --with-xsl --with-zlib 
    --enable-fpm --enable-bcmath --enable-libxml --enable-inline-optimization 
    --enable-gd-native-ttf --enable-mbregex --enable-mbstring --enable-opcache 
    --enable-pcntl --enable-shmop --enable-soap --enable-sockets --enable-sysvsem 
    --enable-xml --enable-zip
    配置的过程中可能会报如下错误
    错误1：xml2-config not found. Please check your libxml2 installation.
    解决办法安装libxml2相关组件#yum install libxml2
    #yum install libxml2-devel -y
    错误2：
    Please reinstall the libcurl distribution -
        easy.h should be in <curl-dir>/include/curl/安装curl相关组件#yum install curl curl-devel
        错误3：configure: error: png.h not found.安装libpng相关组件#yum install libpng
    #yum install libpng-devel
    错误4：freetype-config not found.安装freetype相关组件#yum install freetype-devel
    错误5：xslt-config not found. Please reinstall the libxslt >= 1.1.0 distribution安装libxslt相关组件#yum install libxslt-devel
    
    #make && make install
    
    配置相关php.ini
    配置首先我们需要配置的是php.ini这个文件
    安装目录有2个文件：php.ini-development和php.ini-productionphp.ini-production 线上版本使用
    php.ini-development 开发版本使用我们选择production进行配置
    # cp -a php.ini-production /usr/local/php/etc/php.ini      //拷贝安装包里的php配置文件到安装目录下
    # rm -rf /etc/php.ini      //删除默认的php配置文件
    # ln -s /usr/local/php/etc/php.ini /etc/php.ini       //建立软链接

    php-fpm配置
    拷贝php-fpm启动配置文件
    # cp -a ./sapi/fpm/php-fpm.conf /usr/local/php/etc/php-fpm.conf     //拷贝安装包里的php-fpm配置文件到安装目录下
    # cp -a ./sapi/fpm/init.d.php-fpm /etc/init.d/php-fpm      //拷贝启动文件
    启动service php-fpm start 
    
    查看php是否启动成功
    #ps aux | grep php
    重启及关闭
    service php-fpm restart      service php-fpm stop
    
    配置Nginx支持PHP
    #vim nginx.conf
    location ~ \.php$ {            
    root           /mnt/project;     //项目根目录            
    fastcgi_pass   127.0.0.1:9000;            
    fastcgi_index  index.php;            
    fastcgi_param  SCRIPT_FILENAME  /mnt/project$fastcgi_script_name;       在$符前面加上项目根目录           
     include        fastcgi_params;        
     }
    我们重启Nginx服务。

    #/etc/init.d/nginx restart
    如果你没有按照我们在Nginx的方法配置，可以按照以下的方式重启Nginx服务

    # /usr/local/nginx/sbin/nginx -s reload
    
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
    
mysql数据库安装
    
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

定时任务

    yum install crontabs 
    systemctl enable crond （设为开机启动） 
    systemctl start crond（启动crond服务） 
    systemctl status crond （查看状态） 
    
    vi /etc/crontab 
    
    * * * * * user-name command to be executed
    分钟(0-59) 小时(0-23) 日(1-31) 月(11-12) 星期(0-6,0表示周日) 用户名 要执行的命令
    
    加载任务,使之生效：crontab /etc/crontab
    
    查看任务：crontab -l 
    
    laravel
    * * * * * /usr/local/bin/php /usr/local/var/www/projectName/artisan schedule:run >> /dev/null 2>&1