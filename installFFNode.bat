:: Beginning of hub batch file
set SERVER_VERSION=3.8.1
set TASK_NAME=SeleniumServer2
set NODE_PORT=5557
set HUB_PORT=4443
set REGISTER_IP=10.6.209.204
set GECKO_DRIVER=d:\Install\WebDriver\ffdriver\geckodriver.exe
java -Dwebdriver.gecko.driver=%GECKO_DRIVER% -jar selenium-server-standalone-3.8.1.jar -role node -hub http://%REGISTER_IP%:%HUB_PORT%/grid/register -browser “browserName=firefox,platform=WINDOWS” -port %NODE_PORT%
:: End of hub batch file
pause
