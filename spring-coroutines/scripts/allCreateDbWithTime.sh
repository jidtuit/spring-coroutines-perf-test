#!/bin/sh

ROWS=1000
DELAY=10

echo "No flow nor coroutines.."
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbNoCoroutines\?rows\=$ROWS\&delay\=$DELAY

echo "Regular flow..."
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDb\?rows\=$ROWS\&delay\=$DELAY

echo "Regular flow buffer..."
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbBuffer\?rows\=$ROWS\&delay\=$DELAY

echo "Flow with context changing... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbCtxChange\?rows\=1000\&delay\=$DELAY

echo "Flow with context changing and buffer... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbCtxChangeBuffer\?rows\=1000\&delay\=$DELAY

echo "async await... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbAsyncAwait\?rows\=$ROWS\&delay\=$DELAY

echo "async await with Buffer... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbAsyncAwaitBuffer\?rows\=$ROWS\&delay\=$DELAY

echo "async await without flow... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbAsyncAwaitWithoutFlow\?rows\=$ROWS\&delay\=$DELAY

