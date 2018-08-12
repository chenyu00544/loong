## cd d:/wamp64/www/github

#phpredis
        $redis = new Redis(); // 创建一个redis客户端对象
        $redis->connect('127.0.0.1') || die('连接redis服务器失败！'); // 连接redis服务器
        $redis->auth('foobared'); // 密码验证
        $redis->select(0); // 选择0号数据库
        $redis->setOption(Redis::OPT_PREFIX, 'my-prefix:'); // 设置键名的前缀（相当于MySQL的表前缀）
        // 这里以hash数据类型为例
        $redis->del('test'); // 先删除hash表test（即hash类型的键test）
        $redis->hSetNx('test', 'key1', 'hello'); // 仅当hash表中不存在字段key1时，插入一条记录（键值对）
        $redis->hSetNx('test', 'key2', 'world');
        $redis->hMset('test', array('user_id'=>1, 'name'=>'jack')); // 一次性插入多条记录（存在就更新）
        $redis->expire('test', 600); // 设置test的有效期为600秒
        $res = $redis->hGetAll('hash'); // 获取哈希表test中的所有记录，返回的数据格式为数组
        $redis->close(); // 关闭连接
        
        /*2.共性的运算归类*/
        $redis->expire('key',10);//设置失效时间[true | false]
        $redis->move('key',15);//把当前库中的key移动到15库中[0|1]
        
        //string
        $redis->strlen('key');//获取当前key的长度
        $redis->append('key','string');//把string追加到key现有的value中[追加后的个数]
        $redis->incr('key');//自增1，如不存在key,赋值为1(只对整数有效,存储以10进制64位，redis中为str)[new_num | false]
        $redis->incrby('key',$num);//自增$num,不存在为赋值,值需为整数[new_num | false]
        $redis->decr('key');//自减1，[new_num | false]
        $redis->decrby('key',$num);//自减$num，[ new_num | false]
        $redis->setex('key',10,'value');//key=value，有效期为10秒[true]
        //list
        $redis->llen('key');//返回列表key的长度,不存在key返回0， [ len | 0]
        //set
        $redis->scard('key');//返回集合key的基数(集合中元素的数量)。[num | 0]
        $redis->sMove('key1', 'key2', 'member');//移动，将member元素从key1集合移动到key2集合。[1 | 0]
        //Zset
        $redis->zcard('key');//返回集合key的基数(集合中元素的数量)。[num | 0]
        $redis->zcount('key',0,-1);//返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。[num | 0]
        //hash
        $redis->hexists('key','field');//查看hash中是否存在field,[1 | 0]
        $redis->hincrby('key','field',$int_num);//为哈希表key中的域field的值加上量(+|-)num,[new_num | false]
        $redis->hlen('key');//返回哈希表key中域的数量。[ num | 0]
        
        
        
        /*3.Server*/
        $redis->dbSize();//返回当前库中的key的个数
        $redis->flushAll();//清空整个redis[总true]
        $redis->flushDB();//清空当前redis库[总true]
        $redis->save();//同步??把数据存储到磁盘-dump.rdb[true]
        $redis->bgsave();//异步？？把数据存储到磁盘-dump.rdb[true]
        $redis->info();//查询当前redis的状态 [verson:2.4.5....]
        $redis->lastSave();//上次存储时间key的时间[timestamp]
        
        $redis->watch('key','keyn');//监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断 [true]
        $redis->unwatch('key','keyn');//取消监视一个(或多个) key [true]
        $redis->multi(Redis::MULTI);//开启事务，事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令在一个原子时间内执行。
        $redis->multi(Redis::PIPELINE);//开启管道，事务块内的多条命令会按照先后顺序被放进一个队列当中，最后由 EXEC 命令在一个原子时间内执行。
        $redis->exec();//执行所有事务块内的命令，；【事务块内所有命令的返回值，按命令执行的先后顺序排列，当操作被打断时，返回空值 false】
        
        
        
        /*4.String，键值对，创建更新同操作*/
        $redis->setOption(Redis::OPT_PREFIX,'hf_');//设置表前缀为hf_
        $redis->set('key',1);//设置key=aa value=1 [true]
        $redis->mset($arr);//设置一个或多个键值[true]
        $redis->setnx('key','value');//key=value,key存在返回false[|true]
        $redis->get('key');//获取key [value]
        $redis->mget($arr);//(string|arr),返回所查询键的值
        $redis->del($key_arr);//(string|arr)删除key，支持数组批量删除【返回删除个数】
        $redis->delete($key_str,$key2,$key3);//删除keys,[del_num]
        $redis->getset('old_key','new_value');//先获得key的值，然后重新赋值,[old_value | false]
        
        
        
        /*5.List栈的结构,注意表头表尾,创建更新分开操作*/
        $redis->lpush('key','value');//增，只能将一个值value插入到列表key的表头，不存在就创建 [列表的长度 |false]
        $redis->rpush('key','value');//增，只能将一个值value插入到列表key的表尾 [列表的长度 |false]
        $redis->lInsert('key', Redis::AFTER, 'value', 'new_value');//增，将值value插入到列表key当中，位于值value之前或之后。[new_len | false]
        $redis->lpushx('key','value');//增，只能将一个值value插入到列表key的表头，不存在不创建 [列表的长度 |false]
        $redis->rpushx('key','value');//增，只能将一个值value插入到列表key的表尾，不存在不创建 [列表的长度 |false]
        $redis->lpop('key');//删，移除并返回列表key的头元素,[被删元素 | false]
        $redis->rpop('key');//删，移除并返回列表key的尾元素,[被删元素 | false]
        $redis->lrem('key','value',0);//删，根据参数count的值，移除列表中与参数value相等的元素count=(0|-n表头向尾|+n表尾向头移除n个value) [被移除的数量 | 0]
        $redis->ltrim('key',start,end);//删，列表修剪，保留(start,end)之间的值 [true|false]
        $redis->lset('key',index,'new_v');//改，从表头数，将列表key下标为第index的元素的值为new_v, [true | false]
        $redis->lindex('key',index);//查，返回列表key中，下标为index的元素[value|false]
        $redis->lrange('key',0,-1);//查，(start,stop|0,-1)返回列表key中指定区间内的元素，区间以偏移量start和stop指定。[array|false]
        
        /*6.Set，没有重复的member，创建更新同操作*/
        $redis->sadd('key','value1','value2','valuen');//增，改，将一个或多个member元素加入到集合key当中，已经存在于集合的member元素将被忽略。[insert_num]
        $redis->srem('key','value1','value2','valuen');//删，移除集合key中的一个或多个member元素，不存在的member元素会被忽略 [del_num | false]
        $redis->smembers('key');//查，返回集合key中的所有成员 [array | '']
        $redis->sismember('key','member');//判断member元素是否是集合key的成员 [1 | 0]
        $redis->spop('key');//删，移除并返回集合中的一个随机元素 [member | false]
        $redis->srandmember('key');//查，返回集合中的一个随机元素 [member | false]
        $redis->sinter('key1','key2','keyn');//查，返回所有给定集合的交集 [array | false]
        $redis->sunion('key1','key2','keyn');//查，返回所有给定集合的并集 [array | false]
        $redis->sdiff('key1','key2','keyn');//查，返回所有给定集合的差集 [array | false]
        
        
        /*7.Zset，没有重复的member，有排序顺序,创建更新同操作*/
        $redis->zAdd('key',$score1,$member1,$scoreN,$memberN);//增，改，将一个或多个member元素及其score值加入到有序集key当中。[num | 0]
        $redis->zrem('key','member1','membern');//删，移除有序集key中的一个或多个成员，不存在的成员将被忽略。[del_num | 0]
        $redis->zscore('key','member');//查,通过值反拿权 [num | null]
        $redis->zrange('key',$start,$stop);//查，通过(score从小到大)【排序名次范围】拿member值，返回有序集key中，【指定区间内】的成员 [array | null]
        $redis->zrevrange('key',$start,$stop);//查，通过(score从大到小)【排序名次范围】拿member值，返回有序集key中，【指定区间内】的成员 [array | null]
        $redis->zrangebyscore('key',$min,$max[,$config]);//查，通过scroe权范围拿member值，返回有序集key中，指定区间内的(从小到大排)成员[array | null]
        $redis->zrevrangebyscore('key',$max,$min[,$config]);//查，通过scroe权范围拿member值，返回有序集key中，指定区间内的(从大到小排)成员[array | null]
        $redis->zrank('key','member');//查，通过member值查(score从小到大)排名结果中的【member排序名次】[order | null]
        $redis->zrevrank('key','member');//查，通过member值查(score从大到小)排名结果中的【member排序名次】[order | null]
        $redis->ZINTERSTORE();//交集
        $redis->ZUNIONSTORE();//差集
        
        /*8.Hash，表结构，创建更新同操作*/
        $redis->hset('key','field','value');//增，改，将哈希表key中的域field的值设为value,不存在创建,存在就覆盖【1 | 0】
        $redis->hget('key','field');//查，取值【value|false】
        $arr = array('one'=>1,2,3);$arr2 = array('one',0,1);
        $redis->hmset('key',$arr);//增，改，设置多值$arr为(索引|关联)数组,$arr[key]=field, [ true ]
        $redis->hmget('key',$arr2);//查，获取指定下标的field，[$arr | false]
        $redis->hgetall('key');//查，返回哈希表key中的所有域和值。[当key不存在时，返回一个空表]
        $redis->hkeys('key');//查，返回哈希表key中的所有域。[当key不存在时，返回一个空表]
        $redis->hvals('key');//查，返回哈希表key中的所有值。[当key不存在时，返回一个空表]
        $redis->hdel('key',$arr2);//删，删除指定下标的field,不存在的域将被忽略,[num | false]


#predis入门级操作
    self::$redis = app('redis.connection');  //predis初始化

####1. 获取所有键
    redis::command('keys',['*']);
    
####2.获取指定键值
    redis::get(KEY_NAME);
    
####3.设置键值
    redis::set(KEY_NAME,VALUE);
    
####4.设置键值和过期时间
    redis::setex(KEY_NAME,TIMEOUT,VALUE);
    
####5.删除键值
    redis::del(KEY_NAME)
    
####6.set/get多个 key-value
    
    $mkv = array(
        'usr:0001' => 'First user',
        'usr:0002' => 'Second user',
        'usr:0003' => 'Third user'
    );
    $redis->mset($mkv);  // 存储多个 key 对应的 value
    $retval = $redis -> mget (array_keys( $mkv));  
    
####7.add操作,不会覆盖已有值
    $redis->setnx('foo', 12) ;  // 返回 true ， 添加成功
    $redis->setnx('foo', 34) ;  // 返回 false， 添加失败，因为已经存在键名为 foo 的记录
    
####8.set的变种，结果返回替换前的值
    $redis->getset('foo', 56) ; // 返回 34； 如果之前不存在记录，则返回 null
    
####9.incrby/incr/decrby/decr 对值的递增和递减
    $redis->incr('foo') ;  // 返回 57，同时 foo 的值为 57
    $redis->incrby('foo', 2 ) ;  // 返回 59，同时 foo 的值为 59
    
####10.检测是否存在值
    $redis->exists('foo');
    
####11.删除
    $redis->del('foo'); // 成功删除返回 true, 失败则返回 false
    
####12.type类型检测，字符串返回 string，列表返回 list，set 表返回 set/zset，hash 表返回 hash;
    $redis->type('foo');
    
####13.append 连接到已存在字符串
    $redis->get('str'); // 返回 test
    $redis->append('str', '_123');  // 返回累加后的字符串长度 8,此时 str 为 'test_123'
    
    
####14.setrange 部分替换操作, 并返回字符串长度
    $redis->setrange('str', 0, 'abc');  // 返回 3, 第2个参数为 0 时等同于 set 操作
    $redis->setrange('str', 2, 'cd'); // 返回 4, 表示从第2个字符后替换，这时 'str' 为 'abcd'
    
####15.substr 部分获取操作
    $redis->substr('str', 0, 2); // 返回'abc'; 表示从第 0 个起，取到第 2 个字符
    
####16.setbit位存储
    $redis->setbit('binary', 31, 1);   //表示在第31位存入1,这边可能会有大小端问题?不过没关系, getbit 应该不会有问题
    
####17.getbit位获取
    $redis->getbit('binary', 31);     //返回1
    
####18.keys 模糊查找功能,支持 * 号以及 ? 号 (匹配一个字符)
    $redis->set('foo1', 123);
    $redis->set('foo2', 456);
    $redis->keys('foo*');   // 返回 foo1 和 foo2 的 array
    $redis->keys('f?o?');   // 同上
    
####19.randomkey随机返回一个key
    $redis->randomkey() ;  // 可能是返回 'foo1' 或者是 'foo2' 及其它任何已存在的 key
    
####20.rename/renamenx方法对key进行改名，所不同的是renamenx不允许改成已存在的key
    $redis->rename('str', 'str2');  // 把原先命名为'str'的 key 改成了 'str2'
    
####21.expire 设置 key-value 的时效性
    ttl 获取剩余有效期
    persist 重新设置为永久存储
    $redis->expire('foo', 10);  // 设置有效期为 10 秒
    $redis->ttl('foo');  // 返回剩余有效期值 10 秒
    $redis->persisit('foo');  // 取消 expire 行为
    
####22.dbsize 返回redis当前数据库的记录总数
    $redis->dbsize() ;

#redis 清空缓存

    $redis->flushall() ——> 清空整个 Redis 服务器的数据(删除所有数据库的所有 key )
    
    $redis->flushdb() ——> 清空当前数据库中的所有 key

#redis队列操作

####1.rpush/rpushx 有序列表操作,从队列后插入元素；lpush/lpushx 和 rpush/rpushx 的区别是插入到队列的头部,同上,'x'含义是只对已存在的 key 进行操作
    $redis->rpush('fooList', 'bar1');  // 返回列表长度 1
    $redis->lpush('fooList', 'bar0');  // 返回列表长度 2
    $redis->rpushx('fooList', 'bar2');  // 返回 3, rpushx只对已存在的队列做添加,否则返回 0
    
####2.llen返回当前列表长度
    $redis->llen('fooList'); // 返回 3
    
####3.lrange 返回队列中一个区间的元素
    $redis->lrange ('fooList', 0, 1);  // 返回数组包含第 0 个至第 1 个, 共2个元素
    $redis->lrange ('fooList', 0, -1); //返回第0个至倒数第一个, 相当于返回所有元素
    
####4.lindex 返回指定顺序位置的 list 元素
    $redis->lindex('fooList', 1) ;  // 返回'bar1'
    
####5.lset 修改队列中指定位置的value
    $redis->lset('fooList', 1, '123'); // 修改位置 1 的元素, 返回 true
    
####6.lrem 删除队列中左起指定数量的字符
    $redis->lrem('fooList', 1, '_') ;  // 删除队列中左起(右起使用-1) 1个 字符'_'(若有)
    
####7.lpop/rpop 类似栈结构地弹出(并删除)最左或最右的一个元素
    $redis->lpop('fooList') ;  // 返回 'bar0'
    $redis->rpop('fooList') ;  // 返回 'bar2'
    
####8.ltrim队列修改，保留左边起若干元素，其余删除
    $redis->ltrim('fooList',  0, 1) ;  // 保留左边起第 0 个至第 1 个元素
    
####9.rpoplpush 从一个队列中 pop 出元素并 push 到另一个队列
    $redis->rpush('list1', 'ab0');
    $redis->rpush('list1', 'ab1');
    $redis->rpush('list2', 'ab2');
    $redis->rpush('list2', 'ab3');
    $redis->rpoplpush('list1', 'list2'); // 结果list1 =>array('ab0'), list2 =>array('ab1','ab2','ab3')
    $redis->rpoplpush('list2', 'list2'); // 也适用于同一个队列, 把最后一个元素移到头部 list2 =>array('ab3','ab1','ab2')
    
####10.linsert在队列的中间指定元素前或后插入元素
    $redis->linsert('list2', 'before', 'ab1', '123');  //表示在元素 'ab1' 之前插入 '123'
    $redis->linsert('list2', 'after', 'ab1', '456');   //表示在元素 'ab1' 之后插入 '456'
    
####11.blpop/brpop 阻塞并等待一个列队不为空时，再pop出最左或最右的一个元素（这个功能在php以外可以说非常好用）
    $redis->blpop('list3', 10) ;  // 如果 list3 为空则一直等待,直到不为空时将第一元素弹出, 10 秒后超时
    
    
#redis set集合操作

####sadd增加set集合元素， 返回true， 重复返回false
    
    $redis->sadd('set1', 'ab');
    $redis->sadd('set1', 'cd');
    $redis->sadd('set1', 'ef');
####srem 移除指定元素
    
    $redis->srem('set1', 'cd');  // 删除'cd'元素
####spop 弹出首元素
    
    $redis->spop('set1');  // 返回 'ab'
####smove 移动当前set集合的指定元素到另一个set集合
    
    $redis->sadd('set2', '123');
    $redis->smove('set1', 'set2', 'ab'); // 移动'set1'中的'ab'到'set2', 返回true or false；此时 'set1'集合不存在 'ab' 这个值
####scard 返回当前set表元素个数
    
    $redis->scard('set2'); // 返回 2
####sismember 判断元素是否属于当前set集合
    
    $redis->sismember('set2', '123');  // 返回 true or false
####smembers 返回当前set集合的所有元素
    
    $redis->smembers('set2');  // 返回 array('123','ab')
####sinter/sunion/sdiff 返回两个表中元素的交集/并集/补集
    
    $redis->sadd('set1', 'ab') ;
    $redis->sinter('set2', 'set1') ;  //返回array('ab')
####sinterstore/sunionstore/sdiffstore 将两个表交集/并集/补集元素 copy 到第三个表中
    
    $redis->set('foo', 0);
    $redis->sinterstore('foo', 'set1');  // 等同于将'set1'的内容copy到'foo'中，并将'foo'转为set表
    $redis->sinterstore('foo', array('set1', 'set2'));  // 将'set1'和'set2'中相同的元素 copy 到'foo'表中, 覆盖'foo'原有内容
####srandmember 返回表中一个随机元素
    
    $redis->srandmember('set1') ;
    
    
#redis 有序set表操作

####sadd 增加元素，并设置序号，成功返回true，重复返回false
    
    $redis->zadd('zset1', 1, 'ab');
    $redis->zadd('zset1', 2, 'cd');
    $redis->zadd('zset1', 3, 'ef');
####zincrby 对指定元素索引值的增减,改变元素排列次序
    
    $redis -> zincrby ( 'zset1' , 10 , 'ab' ) ; //返回11
####zrem 移除指定元素
    
    $redis->zrem('zset1', 'ef');  // 返回 true or false
####zrange 按位置次序返回表中指定区间的元素
    
    $redis->zrange('zset1', 0, 1);  // 返回位置 0 和 1 之间(两个)的元素
    $redis->zrange('zset1', 0, -1); // 返回位置 0 和倒数第一个元素之间的元素(相当于所有元素)
####zrevrange 同上,返回表中指定区间的元素,按次序倒排
    
    $redis->zrevrange('zset1', 0, -1);  // 元素顺序和zrange相反
####zrangebyscore/zrevrangebyscore 按顺序/降序返回表中指定索引区间的元素
    
    $redis->zadd('zset1', 3, 'ef');
    $redis->zadd('zset1', 5, 'gh');
    $redis->zrangebyscore('zset1', 2, 9);  //返回索引值2-9之间的元素 array('ef','gh')
    $redis->zrangebyscore('zset1', 2, 9, 'withscores'); // 返回索引值2-9之间的元素并包含索引值 array(array('ef',3),array('gh',5))
    $redis->zrangebyscore('zset1', 2, 9, array('withscores'=>true, 'limit'=>array(1, 2)));  //返回索引值2-9之间的元素,'withscores' =>true表示包含索引值; 'limit'=>array(1, 2),表示偏移1条，返回2条,结果为array(array('ef',3),array('gh',5))
####zunionstore/zinterstore 将多个表的并集/交集存入另一个表中
    
    $redis->zunionstore('zset3', array('zset1', 'zset2', 'zset0'));  //将'zset1','zset2','zset0'的并集存入'zset3'
    $redis->zunionstore('zset3', array('zset1', 'zset2'), array('weights' => array(2, 1))); //weights参数表示权重，其中表示并集后 zset1集合的分 * 2 后存储到 zset3 集合， zset2集合的分 * 1 后存储到 zset3 集合
    $redis->zunionstore('zset3', array('zset1', 'zset2'), array('aggregate' => 'max')); //'aggregate' => 'max'或'min'表示并集后相同的元素是取大值或是取小值
####zcount 统计一个索引区间的元素个数
    
    $redis->zcount('zset1', 3, 5); // 返回 2
    $redis->zcount('zset1', '(3', 5));  //'(3'表示索引值在3-5之间但不含3,同理也可以使用'(5'表示上限为5但不含5
####zcard 统计元素个数
    
    $redis->zcard('zset1'); // 返回 4
####zscore 查询元素的索引
    
    $redis->zscore('zset1', 'ef'); // 返回 3
####zremrangebyscore 删除一个索引区间的元素
    
    $redis->zremrangebyscore('zset1', 0, 2);  // 删除索引在0-2之间的元素('ab','cd'), 返回删除元素个数2
####zrank/zrevrank 返回元素所在表顺序/降序的位置(不是索引)
    
    $redis->zrank('zset1', 'ef'); // 返回0,因为它是第一个元素；zrevrank则返回1(最后一个)
####zremrangebyrank 删除表中指定位置区间的元素
    
    $redis->zremrangebyrank('zset1', 0, 10);  //删除位置为0-10的元素,返回删除的元素个数2 


#redis Hash表操作

####hset/hget 存取hash表的数据
    
    $redis->hset('hash1', 'key1', 'v1');  //将key为'key1' value为'v1'的元素存入hash1表
    $redis->hset('hash1', 'key2', 'v2');
    $redis->hget('hash1', 'key1');   //取出表'hash1'中的key 'key1'的值,返回'v1'
####hexists 返回hash表中的指定key是否存在
    
    $redis->hexists('hash1', 'key1') ;  //true or false
####hdel 删除hash表中指定key的元素
    
    $redis->hdel('hash1', 'key2') ;  //true or false
####hlen 返回hash表元素个数
    
    $redis->hlen('hash1');  // 返回 1
####hsetnx 增加一个元素,但不能重复
    
    $redis->hsetnx('hash1', 'key1', 'v2') ;  // false
    $redis->hsetnx('hash1', 'key2', 'v2') ;  // true
####hmset/hmget 存取多个元素到hash表
    
    $redis->hmset('hash1', array('key3' => 'v3', 'key4' => 'v4')); 
    $redis->hmget('hash1', array('key3', 'key4'));  // 返回相应的值 array('v3','v4')
####hincrby 对指定key进行累加
    
    $redis->hincrby('hash1', 'key5', 3);  // 不存在，则存储并返回 3；存在，即返回 原有值 + 3；
    $redis->hincrby('hash1', 'key5', 10);  // 返回13
####hkeys 返回hash表中的所有key
    
    $redis->hkeys('hash1');  // 返回array('key1', 'key2', 'key3', 'key4', 'key5')
####hvals 返回hash表中的所有value
    
    $redis->hvals('hash1');  // 返回 array('v1','v2','v3','v4',13)
####hgetall 返回整个hash表元素
    
    $redis->hgetall('hash1');  // 返回 array('key1'=>'v1','key2'=>'v2','key3'=>'v3','key4'=>'v4','key5'=>13)
    
#redis 排序操作
    
####sort 排序
    
    $redis->rpush('tab', 3);
    $redis->rpush('tab', 2);
    $redis->rpush('tab', 17);
    $redis->sort('tab');   // 返回 array(2,3,17)
     
    // 使用参数,可组合使用 array('sort' => 'desc','limit' => array(1, 2))
    $redis->sort('tab', array('sort' => 'desc'));   // 降序排列，返回 array(17,3,2)
    $redis->sort('tab', array('limit' => array(1, 2)));   //返回顺序位置中1的元素2个(这里的2是指个数,而不是位置)，返回array(3,17)
    $redis->sort('tab', array('limit' => array('alpha' => true)));  //按首字符排序返回array(17,2,3)，因为17的首字符是'1'所以排首位置
    $redis->sort('tab', array('limit' => array('store' => 'ordered')));  //表示永久性排序，返回元素个数
    $redis->sort('tab', array('limit' => array('get' => 'pre_*')));  //使用了通配符'*'过滤元素，表示只返回以'pre_'开头的元素
    
#Redis管理操作

####info 显示服务当状态信息
    
    $redis->info();
####select 指定要操作的数据库
    
    $redis->select(4);  // 指定数据库的下标
####flushdb 清空当前库
    
    $redis->flushdb();
####move 移动当库的元素到其它数据库
    
    $redis->set('tomove', 'bar');
    $redis->move('tomove', 4);
####slaveof 配置从服务器
    
    $redis->slaveof('127.0.0.1', 80);  // 配置 127.0.0.1 端口 80 的服务器为从服务器
    $redis->slaveof();  // 清除从服务器
####同步保存服务器数据到磁盘
    
    $redis->save();
####异步保存服务器数据到磁盘
    
    $redis->bgsave ();
####返回最后更新磁盘的时间
    
     $redis->lastsave();