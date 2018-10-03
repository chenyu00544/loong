<?php

namespace App\Console\Commands;

use App\Http\Models\Test\CronModel;
use Illuminate\Console\Command;
use Illuminate\Support\Facades\Log;

//php artisan make:command TestCommand
class TestCommand extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'command:TestCommand';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'TestCommand description';

    private $cronModel;
    /**
     * Create a new command instance.
     *
     * @return void
     */
    public function __construct(CronModel $cronModel)
    {
        parent::__construct();
        $this->cronModel = $cronModel;
    }

    /**
     * Execute the console command.
     *
     * @return mixed
     */
    public function handle()
    {
        $this->cronModel->incCron();
    }
}
