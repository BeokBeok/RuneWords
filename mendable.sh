#!/bin/zsh

# generate Compose compiler metrics
./gradlew assembleRelease -PenableMultiModuleComposeReports=true --rerun-tasks

# copy mendable.jar
cp mendable.jar build/compose_metrics/

# run mendable.jar
cd build/compose_metrics
java -jar mendable.jar
