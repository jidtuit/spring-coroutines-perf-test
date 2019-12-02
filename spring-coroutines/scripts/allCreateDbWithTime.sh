
ROWS=1000
DELAY=10

echo "Regular flow..."
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDb\?rows\=$ROWS\&delay\=$DELAY

echo "Flow with context changing... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbCtxChange\?rows\=1000\&delay\=$DELAY

echo "async await... "
time curl -X POST http://localhost:8080/calcAndSave-suspend/createDbAsyncAwait\?rows\=$ROWS\&delay\=$DELAY


