<?php

namespace App\Console\Commands;

use App\Repositories\Admin\CronsRepository;
use Illuminate\Console\Command;

//php artisan make:command TestCommand
class CronCommand extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'command:CronCommand';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'CronCommand description';

    private $cronsRepository;
    /**
     * Create a new command instance.
     *
     * @return void
     */
    public function __construct(
        CronsRepository $cronsRepository
    )
    {
        parent::__construct();
        $this->cronsRepository = $cronsRepository;
    }

    /**
     * Execute the console command.
     *
     * @return mixed
     */
    public function handle()
    {
        $this->cronsRepository->implementes();
    }
}
