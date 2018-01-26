:: Beginning of hub batch file
set SERVER_VERSION=3.8.1
set TASK_NAME=SeleniumServer2
set NODE_PORT=5557
set HUB_PORT=4442
set REGISTER_IP=localhost
set GECKO_DRIVER=d:\Install\WebDriver\ffdriver\geckodriver.exe
java -jar selenium-server-standalone-3.8.1.jar -role node -hub http://%REGISTER_IP%:%HUB_PORT%/grid/register -browser "maxInstances=3,browserName=firefox" -port  %NODE_PORT%
:: End of hub batch file
pause
