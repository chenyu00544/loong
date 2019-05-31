##批量生成models
    reliese/laravel
    
    composer require reliese/laravel
    composer require reliese/laravel --dev 开发环境
    
    config/app.php
    
    'providers' => [
        /*
         * Package Service Providers...
         */
    
        Reliese\Coders\CodersServiceProvider::class,
    ],
    
    Then you'll need to register the provider in app/Providers/AppServiceProvider.php file.
    public function register()
    {
        if ($this->app->environment() == 'local') {
            $this->app->register(\Reliese\Coders\CodersServiceProvider::class);
        }
    }
    
    Add the models.php configuration file to your config directory and clear the config cache:
    
    php artisan vendor:publish --tag=reliese-models
    php artisan config:clear
    
    生成models
    php artisan code:models
    
    生成model
    php artisan code:models --table=users