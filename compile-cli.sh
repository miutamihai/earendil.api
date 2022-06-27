#!/bin/sh

git clone https://github.com/nerds-sh/earendil-cli.git cli
cd ./cli
mix deps.get
mix escript.build
cd ..
mv ./cli/earendil .
rm -rf cli
chmod 777 ./earendil