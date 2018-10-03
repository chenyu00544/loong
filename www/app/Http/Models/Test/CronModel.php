<?php

namespace App\Http\Models\Test;

use Illuminate\Database\Eloquent\Model;

class CronModel extends Model
{
    protected $table = 'cron';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function incCron()
    {
        $this->where(['name'=>'test'])
            ->increment('cron');
    }
}
