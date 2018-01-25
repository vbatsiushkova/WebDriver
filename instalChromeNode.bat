:: Beginning of hub batch file
set SERVER_VERSION=3.8.1
set TASK_NAME=SeleniumServer1
set NODE_PORT=5556
set HUB_PORT=4443
set REGISTER_IP=10.6.209.204
set CHROME_DRIVER=d:\Install\WebDriver\chromedriver\chromedriver.exe
java -jar selenium-server-standalone-%SERVER_VERSION%.jar -role node -hub http://%REGISTER_IP%:%HUB_PORT%/grid/register -browser "browserName=chrome,platform=WINDOWS" -port %NODE_PORT%
:: End of hub batch file
pause