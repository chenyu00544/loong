<?php

namespace App\Custom\Site\Controllers;

use App\Modules\Site\Controllers\IndexController as FoundationController;

class IndexController extends FoundationController
{
    /**
     * URL路由访问地址: mobile/index.php?m=site&c=index&a=about


2、关于数据库操作（CURD）：
$this->model->table('post')->where($condition)->find();
$this->model->table('post')->field("value")->where($condition)->select();
$this->model->table('post')->data($data)->insert();
$this->model->table('post')->where($condition)->delete();
$this->model->table('post')->data($data)->where($condition)->update(); 
     */

	public function actionTest()
	{
		echo "test";
	}


    public function actionAbout()
    {
       // $this->display();

$getAllSql = 
		"
			select * from dsc_touch_nav_smallapp
		";
		
		echo $getAllSql;
		
		$couList = $GLOBALS['db']->getAll($getAllSql);

print_r($couList);
    }

    public function actionPhpinfo()
    {
        // phpinfo();
    }


  public function actionAddFromId()
    {
		//echo "actionAddFromId";
		   // $this->display();
		$from_id = I('request.from_id');//$_REQUEST['from_id'];
		
		$user_wxid = I('request.user_wxid');//$_REQUEST['user_wxid'];

		//$js_code = I('request.js_code');

		//echo "js_code：".$js_code;

		////根据获取的js code  获取用户的open id  

// || empty($user_wxid)
//|| empty($js_code)
		if(empty($from_id) || empty($user_wxid) )
		{
			//exit;
		}


		
						
		
		
						
		/*
			CURLOPT_ENCODING => "",
			CURLOPT_MAXREDIRS => 10,
			CURLOPT_TIMEOUT => 30,
			CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,

			
appid: APP_ID,
            secret: APP_SECRET,
            js_code: res.code,
            grant_type: 'authorization_code'

		*/

/*
$ch = curl_init();

		$appId = "wx870dbb58111b2295";
		$appSecret = "cd7e7a33be516b80ef025106a55418fa";

		$url = "https://api.weixin.qq.com/sns/jscode2session?appid=$appId&js_code=$js_code&secret=$appSecret&grant_type=authorization_code";

		//echo $url;exit;

	$timeout = 30;

        if (stripos($url, "https://") !== false) 
		{
            curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
            curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
            curl_setopt($ch, CURLOPT_SSLVERSION, 1); //CURL_SSLVERSION_TLSv1
        }
        curl_setopt($ch, CURLOPT_HTTP_VERSION, 1);
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);
        curl_setopt($ch, CURLOPT_TIMEOUT, $timeout);
	*/
		/*
		curl_setopt_array($curl, 
						array(
						  CURLOPT_URL => $url,
						  CURLOPT_RETURNTRANSFER => true,
						  CURLOPT_SSL_VERIFYPEER => FALSE,
							CURLOPT_SSL_VERIFYHOST => FALSE,
						  CURLOPT_CUSTOMREQUEST => "GET"
						));

		*/

		/*
						$response = curl_exec($ch);

		//print_r($response);
						
						$err = curl_error($ch);

						curl_close($ch);
						
						if (!empty($err)) 
						{
							echo "error";
						  exit;
						}
		
			$resArray = json_decode($response,true);

			$user_wxid = $resArray['openid'];
		*/
		$create_time_stamp = time();

		$addSql = 
		"
			insert into dsc_small_app_fromid 
			(	
				from_id,
				wxid,
				create_time,
				create_time_stamp,
				is_use
			)
			values 
			(
				'$from_id','$user_wxid',now(),'$create_time_stamp',0
			)
		";

		echo $addSql;
 
			
			$GLOBALS['db']->query($addSql);
 
		}
}
