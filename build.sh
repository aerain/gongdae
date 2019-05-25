echo "Moving built file..."

SERVER_PATH=./src/main/resources

STATIC_PATH=$SERVER_PATH/static

mv build $SERVER_PATH;
rm $SERVER_PATH/templates/index.html
rm -rf $STATIC_PATH/static
rm $STATIC_PATH/asset-manifest.json $STATIC_PATH/favicon.ico $STATIC_PATH/manifest.json $STATIC_PATH/precache-* $STATIC_PATH/service-worker.js
mv $SERVER_PATH/build/index.html $SERVER_PATH/templates/
mv $SERVER_PATH/build/* $STATIC_PATH/
rm -r $SERVER_PATH/build

echo "done"