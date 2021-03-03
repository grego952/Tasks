call runcrud
if "%ERRORLEVEL%" == "0" goto openPage
echo.
echo error while running RUNCRUD - breaking work
goto fail

:openPage
start "" "C:\Program Files\Google\Chrome\Application\chrome.exe" http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo Errors while running RUNCRUD

:end
echo.
echo Work is done.



