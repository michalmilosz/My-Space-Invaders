ECHO OFF
set path="C:\Program Files\Java\jdk-11.0.1\bin";
SET mypath=%~dp0
javadoc -quiet -private -d  javaDoc ./src/*.java
ECHO Dokumentacja zosta³a wygenerowana i znajduje siê w katalogu javaDoc
pause