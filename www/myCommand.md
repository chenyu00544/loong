php artisan make:migration create_****_table           创建数据表文件

php artisan migrate  执行创建数据表里的操作

php artisan make:seeder LinksTableSeeder
  创建测试数据文件
  
php artisan db:seed  执行创建测试数据文件

php artisan make:controller ****\***\**

php artisan make:model ****\***\**

1048575 为一库

添加表字段
ALTER TABLE cyc_seo add xxxxxxyy VARCHAR(6) NOT NULL DEFAULT ''

修改表字段
ALTER TABLE cyc_seo CHANGE xxxxxxyy xyy VARCHAR(50) NOT NULL DEFAULT ''

##mySql分区
    1、创建一个数据表并分区：
    
    CREATE TABLE ` table_name` (
    
      `id` INT(11) NOT NULL AUTO_INCREMENT,
    
      `uid` VARCHAR(50) DEFAULT NULL,
    
      `action` VARCHAR(10) DEFAULT NULL,
    
      ` channel` VARCHAR(20) DEFAULT NULL,
    
      `count_left` INT(11) DEFAULT NULL,
    
      `end_time` INT(11) DEFAULT '0',
    
      PRIMARY KEY (`id`,`end_time`),
    
      KEY `time` (`end_time`)
    
    ) ENGINE=MYISAM DEFAULT CHARSET=utf8
    
    PARTITION BY RANGE(`end_time`) (
    
        PARTITION p161130 VALUES LESS THAN (1480550399),
    
        PARTITION p161231 VALUES LESS THAN (1483228799),
    
        PARTITION p170131 VALUES LESS THAN (1485907199),
    
        PARTITION p170228 VALUES LESS THAN (1488326399),
    
        PARTITION p170331 VALUES LESS THAN (1491004799),
    
        PARTITION p170430 VALUES LESS THAN (1493596799),
    
        PARTITION p170531 VALUES LESS THAN (1496275199),
    
        PARTITION p170631 VALUES LESS THAN (1498867199),
    
        PARTITION pnow VALUES LESS THAN MAXVALUE
    
    );
    
     
    
    2、修改一个数据表分区：
    
    ALTER TABLE `table_name`
    
    PARTITION BY RANGE(`end_time`) (
    
          PARTITION p161130 VALUES LESS THAN (1480550399),
    
    　　PARTITION p161231 VALUES LESS THAN (1483228799),
    
    　　PARTITION p170131 VALUES LESS THAN (1485907199),
    
    　　PARTITION p170228 VALUES LESS THAN (1488326399),
    
    　　PARTITION p170331 VALUES LESS THAN (1491004799),
    
    　　PARTITION p170430 VALUES LESS THAN (1493596799),
    
    　　PARTITION p170531 VALUES LESS THAN (1496275199),
    
    　　PARTITION p170631 VALUES LESS THAN (1498867199),
    
    　　PARTITION pnow VALUES LESS THAN MAXVALUE
    
    );
    
    分区后可以执行以下语句查看效果（后面也可以用该语句查看每个分区中有多少数据）：
    
    SELECT PARTITION_NAME,TABLE_ROWS FROM INFORMATION_SCHEMA.PARTITIONS WHERE TABLE_NAME = 'table_name';

    3、删除一个分区：
    
    执行语句：ALTER TABLE table_name DROP PARTITION p_name;
    
    注意：删除一个分区时，该分区内的所有数据也都会被删除；
    
    4、新增一个分区：
    
    执行语句：ALTER TABLE table_name ADD PARTITION (PARTITION p_name VALUES LESS THAN (xxxxxxxxx));
    
    注意：如果原先最后一个分区是PARTITION pnow VALUES LESS THAN MAXVALUE; 那么应该先删除该分区，然后在执行新增分区语句，然后再新增回该分区；