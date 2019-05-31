<?php
$config = array (
		//应用ID,您的APPID。
		'app_id' => "2017041906814693",

		//商户私钥
		'merchant_private_key' => "MIIEpQIBAAKCAQEA1KOGMljCyI+6Gk2eE44spZ92Q7f06xt0unqO5gbOPv8AtBFnXsZfVnf7EAR8efz1825n/m2nUAxLni9QOTnN8QOFOmCbk176kZL7quMLpJISYXNSDm4ig8tUG9LF/6f1co7VKU4vpR4+M9mnWHCKWuOfK448E3EqJa8uEFDdvjXjOWD/bZ9E8yVVRfizOY3GjvAFeDVK1DWRbADKAZpjt67qGAnjUwYyYxWZAyCMNk6CBbmKNzDCsFi1ovkUvVUKiyBuQ2AbWF2atFo2aAyZGrEqsnXoCUOv1h6gbmcO92DTJWWtSveOjktFVK+4RpCK1cZZXWhGBjL+jjYkUB+bxwIDAQABAoIBAQC7DVp4oZI/ggexKcQ76Z6m137elU1TIKTvge6AtU3rJsFEpear/Y9SblshvQCPAxKM4PuEWouLJDXE4lQM8yeRs4oYT3GsEyoSsxzGN9I/I+wdY6e0X/fmvg+wJhrdCbAl5TXojwnkk5UeYYIPj3h1+bRESWvZonv0CIVZ/vkBX6UIH/s5zNWuRaXoqiNadJ0JIeikFP288jBcEZfYu1N3zKA9tdr2tUqyDRXA/bt680a4P6scjYz6oLAo3eq+fYpwNQ+pu0UYXCNUExyHqICo31s0cj52pSswl1apyBDMxnKmz302z4fLQ9fZD9VafeOFoYngtQ4kdOMvlygPi9EBAoGBAPW+TmJk6/VMKytglUI4aIemMYwJupQKlBbrguxk4Z+zq6OUBVzC51twvZakJzytJ42Pdmjx8Z7/4izjuCJCOIWddISgi56n/RQKhHtpFXxkRiCSv2Tekqc3n4sLjEEQVL3+ji1L2HuzuHBdNM+t4mM933OCCLccha/0sCaV7MyBAoGBAN2DgVPA0A1tDJvWlWSGJLg2NqYhOrj7RGQEudSq5SSuPm6JWtCsMTeVgcgzLw56rVt+K0dDNb7QOjzFmnFpQTz4lGROXyyGqpGKrsewHpCQWp5BfWvNIItoF+XxdD+CYlVPwYzoguy95fm+U+NQsIspIhHkdFTmULy8UVdtUORHAoGBAKSoBs1W4eNSTfqalGfyc9ewO7KhMirPi7pvwyGx1puzP2YzYARvc6WJPP8ark+xlbb8UVKwCj2HJnz9d/s8c7LTdacIC4Ua+i/bfDlUh9j5NyPMMAHeQFVap8P60NcnoiBL1+fx0fPQz9w6F5Cv6eilJHu4vS40Jj9nrD+uZSQBAoGBAKNpbw+1LwaoiuOwApec6hvHSrt1yhdKreJHJjwAMnjD3iS3SXqlfM1visWBFNbOo3LaedzhdRWmEUBh2hSOPGVZUEdLFaUIV25064k+oN2u0FrtHzRWqAqPqQml/UcYzaf35WYo5OT3FmAOC16nWQXgO1UNvtAe9DK3jh4786/jAoGAZ0uRbrzCT0PlgEbWiMHby/6AoqF11VOI85LiS9GPMltsplq23XBzpK7rlM+wBrT8iHO+p3IuNvT1+SXIF4F1RgkSoOY0NcgycKTCrM5rcJTZMLIehNxZUt+5MT7D4qBnT1P3MRom0qf6hc8+vWykNtTOSCut+x7MSC+wdI6Hrc4=",

		//异步通知地址
		'notify_url' => "http://testapi.51moyoo.com/alipay/notify",

		//同步跳转
		'return_url' =>  "http://mmoyoo.moyootech.com/pay_success.html",

		//编码格式
		'charset' => "UTF-8",

		//签名方式
		'sign_type'=>"RSA2",

		//支付宝网关
		'gatewayUrl' => "https://openapi.alipay.com/gateway.do",

		//支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
		'alipay_public_key' => "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1KOGMljCyI+6Gk2eE44spZ92Q7f06xt0unqO5gbOPv8AtBFnXsZfVnf7EAR8efz1825n/m2nUAxLni9QOTnN8QOFOmCbk176kZL7quMLpJISYXNSDm4ig8tUG9LF/6f1co7VKU4vpR4+M9mnWHCKWuOfK448E3EqJa8uEFDdvjXjOWD/bZ9E8yVVRfizOY3GjvAFeDVK1DWRbADKAZpjt67qGAnjUwYyYxWZAyCMNk6CBbmKNzDCsFi1ovkUvVUKiyBuQ2AbWF2atFo2aAyZGrEqsnXoCUOv1h6gbmcO92DTJWWtSveOjktFVK+4RpCK1cZZXWhGBjL+jjYkUB+bxwIDAQAB",
);
