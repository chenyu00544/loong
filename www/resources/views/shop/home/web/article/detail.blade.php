@extends('shop.layouts.web')
@section('content')
    <div>
        @if($article) {!! $article->content !!} @endif
    </div>
@endsection
