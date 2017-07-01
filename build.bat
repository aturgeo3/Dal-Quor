@echo off
call java -jar Build.jar gradle.properties modVersionValue
call gradlew clean build
call pause