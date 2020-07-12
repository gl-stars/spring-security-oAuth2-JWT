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

set /p version=«Î ‰»Î∞Ê±æ∫≈:

call mvn versions:set -DnewVersion=%version%

call mvn versions:commit

pause