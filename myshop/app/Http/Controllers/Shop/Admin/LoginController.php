<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Common;
use App\Facades\LangConfig;
use App\Facades\Verifiable;
use App\Http\Models\Shop\UserModel;
use Illuminate\Http\Request;
use App\Http\Requests;
use Illuminate\Support\Facades\Input;

class LoginController extends CommonController
{
    public function login()
    {
        $lang = LangConfig::LangAdminConf();
        if ($input = Input::except('_token')) {

            $validator = Verifiable::loginVer($input, $lang);

            if($validator->passes()){
                $user = (new UserModel)->getOne($input);
                if($user->user_name != $input['username']) {
                    return back()->with('errors', $lang['login_faild']);
                }

                if($user->password != Common::md5Encrypt($input['password'], $user->salt)){
                    return back()->with('errors', $lang['login_faild']);
                }
                session(['user'=>$user]);
                return redirect('admin/index');
            }else{
                return back()->withErrors($validator);
            }
        } else {
            return view('shop.admin.login', compact('lang'));
        }
    }

    public function change()
    {
        $lang = 'zh_cn';
        $dir = 'admin';
        $filename = 'shop_config.php';
        require_once base_path().DIRECTORY_SEPARATOR.'public'.DIRECTORY_SEPARATOR.'languages'.DIRECTORY_SEPARATOR.$lang.DIRECTORY_SEPARATOR.$dir.DIRECTORY_SEPARATOR.$filename;

        $path = base_path().DIRECTORY_SEPARATOR.'config'.DIRECTORY_SEPARATOR.'languages'.DIRECTORY_SEPARATOR.$lang.DIRECTORY_SEPARATOR.$dir.DIRECTORY_SEPARATOR.$filename;
        $str = '<?php return '.var_export($_LANG, true).';';
        file_put_contents($path,$str);
    }

    public function tool()
    {

    }
}
