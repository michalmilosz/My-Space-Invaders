ECHO OFF
set path="C:\Program Files\Java\jdk-11.0.1\bin";
SET mypath=%~dp0
javadoc -quiet -private -d  javaDoc ./src/*.java
ECHO Dokumentacja zosta�a wygenerowana i znajduje si� w katalogu javaDoc
pause