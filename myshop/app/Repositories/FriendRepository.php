<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\FriendRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\shop\FriendModel;

class FriendRepository implements FriendRepositoryInterface
{

    protected $friendModel;

    public function __construct(FriendModel $friendModel)
    {
        $this->friendModel = $friendModel;
    }

    public function getFriends($size = 10)
    {
        return $this->friendModel->getFriends([], ['*'], $size);
    }

    public function setFriend($data)
    {
        $req = ['code' => 5, 'msg' => '修改失败'];
        foreach ($data as $key => $value){
            if($key == 'id'){
                $where['link_id'] = $value;
            }else{
                $updata['show_order'] = $value;
            }
        }
        $re = $this->friendModel->setFriend($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '修改成功'];
        }
        return $req;
    }

    public function addFriend($data)
    {
        $allow_file_types = '|GIF|JPG|PNG|BMP|SWF|DOC|XLS|PPT|MID|WAV|ZIP|RAR|PDF|CHM|RM|TXT|CERT|';
        $saveData = [];
        foreach ($data as $key => $value) {
            if($key == 'link_logo'){
                if ($value->isValid()) {
                    $tmpName = $value->getFileName();
                    $ext = $value->getClientOriginalExtension();
                    $filename = $value->getClientOriginalName();
                    if (!FileHelper::checkFileType($tmpName, $filename, $allow_file_types)) {
                        dd($ext);
                    } else {
                        $dir = base_path() . '/public/upload/friend_logo/';
                        $file_name = md5(time().rand(10000,99999)) . "." . $ext;

                        /* 判断是否上传成功 */
                        if ($path = $value->move($dir, $file_name)) {
                            $saveData['link_logo'] = 'styles/images/upload/' . $file_name;
                        }
                    }
                }
            }elseif($key != 'link_logo_url'){
                if(!empty($value)){
                    $saveData['link_logo'] = $value;
                }
            }else{
                $saveData[$key] = $value;
            }
        }
        dd($saveData);
    }

}