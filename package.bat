@echo on
@echo =============================================================
@echo $                                                           $
@echo $               SSOJ Spring-Security-OAuth2-JWT             $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  SSOJ All Right Reserved                                  $
@echo $  Copyright (C) 2020-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title SSOJ Spring-Security-OAuth2-JWT
@color 0e

call mvn clean package -Dmaven.test.skip=true

pause