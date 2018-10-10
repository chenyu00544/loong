<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\QuestionAnswerRepository;
use Illuminate\Http\Request;

class QuestionAnswerController extends CommonController
{
    private $questionAnswerRepository;

    public function __construct(
        QuestionAnswerRepository $questionAnswerRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('qa');
        $this->questionAnswerRepository = $questionAnswerRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $search['keywords'] = $request->get('keywords');
        $search['seller'] = 'selfsale';
        $comments = $this->questionAnswerRepository->getQuestionAnswerByPage($search);
        return view('shop.admin.qanswer.qanswer', compact('comments', 'search'));
    }

    public function change(Request $request)
    {
        return $this->questionAnswerRepository->change($request->except('_token'));
    }

    public function adAdd($id)
    {

    }

    public function adEdit($id, $ad_type)
    {

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
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
    public function show(Request $request, $id)
    {
        $search['keywords'] = $request->get('keywords');
        $search['seller'] = $id ? $id : 'selfsale';
        $comments = $this->questionAnswerRepository->getQuestionAnswerByPage($search);
        return view('shop.admin.qanswer.qanswer', compact('comments', 'search'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $comments = $this->questionAnswerRepository->getQuestionAnswers($id);
        $user = $this->user;
        return view('shop.admin.qanswer.qanswerInfo', compact('comments', 'id', 'user'));
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
        $ver = Verifiable::Validator($request->all(), ["content" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->questionAnswerRepository->setQuestionAnswers($request, $id, $this->user);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->questionAnswerRepository->delQuestionAnswer($id);
    }
}
