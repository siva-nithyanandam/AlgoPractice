@rem To restage all running running apps in PCF
@echo off
:init
echo For performing any PCF related activities.

echo Choose the environment - "edc", "ecc".
set /p env=:
if /I %env% EQU edc  goto :edc
if /I %env% EQU ecc  goto :ecc
goto :error_info

:edc
echo logging on "https://api.sys.pp01.edc1.cf.ford.com"
cf login -a https://api.sys.pp01.edc1.cf.ford.com -sso
goto :perform_activity

:ecc
echo logging on "https://api.sys-pcf02v2.cf.ford.com"
cf login -a https://api.sys-pcf02v2.cf.ford.com -sso
goto :perform_activity

:perform_activity
echo Choose the activity - "start", "stop", "restage"
set /p activity=:

if /I "%activity%" EQU "start"  goto :start
if /I %activity% EQU stop  goto :stop
if /I %activity% EQU restage  goto :restage
goto error_info

:start
for /f %%a in (PP_Services.txt) do (
	echo Starting %%a
	start "Starting %%a" cmd /k cf start %%a
)
goto :continue

:stop
for /f %%a in (PP_Services.txt) do (
	echo Stopping %%a
	start "Stopping %%a" cmd /k cf stop %%a
)
goto :continue

:restage
for /f %%a in (PP_Services.txt) do (
	echo Restaging %%a
	start "Restaging %%a" cmd /k cf restage %%a
)
goto :continue

:error_info
echo :Inappropriate action performed.
goto continue

:continue
echo exiting

pause