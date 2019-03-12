 @ECHO OFF

set path="C:\Program Files\Java\jdk-11.0.1\bin";
TITLE SPACE_INVADERS

javac -d gierkaCLASS ./src/*.java

set CLASSPATH=.\gierkaCLASS

java ProgramMain

PAUSE