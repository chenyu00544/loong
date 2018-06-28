<?php

namespace App\Http\Controllers\Shop\Admin;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class DataBaseController extends CommonController
{
    private $prefix;

    public function __construct()
    {
        parent::__construct();
        $this->checkPrivilege('database');
        $this->prefix = Config::get('database.connections.mysql.prefix');
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    public function optimize()
    {
        $tables = DB::select("SHOW TABLE STATUS LIKE '" . $this->prefix . "%'");
        $total = 0;
        foreach ($tables as $key => $value) {
            $tables[$key]->status = DB::select('CHECK TABLE ' . $value->Name);
            $total += $value->Data_free;
        }
        return view('shop.admin.database.optimize', compact('tables', 'total'));
    }

    public function runOptimize(Request $request)
    {
        $req = ['code' => 5, 'msg' => '优化失败'];
        $tables = DB::select("SHOW TABLES LIKE '" . $this->prefix . "%'");
        foreach ($tables as $table) {
            foreach ($table as $k => $val){
                if ($row = DB::select('OPTIMIZE TABLE ' . $val)) {
                    /* 优化出错，尝试修复 */
                    if ($row[0]->Msg_type == 'error' && strpos($row[0]->Msg_text, 'repair') !== false) {
                        DB::select('REPAIR TABLE ' . $val);
                        return $req;
                    }else{
                        $req = ['code' => 1, 'msg' => '优化成功'];
                    }
                }
            }
        }
        return $req;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
