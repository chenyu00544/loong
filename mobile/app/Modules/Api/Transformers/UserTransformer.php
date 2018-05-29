<?php

namespace App\Modules\Api\Transformers;

use App\Models\Users as User;

class UserTransformer
{
    public function transform(User $user)
    {
        return [
            'id' => $user->user_id,
            'name' => $user->user_name,
        ];
    }
}
