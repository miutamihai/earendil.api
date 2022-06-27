#!/bin/sh

git clone https://github.com/nerds-sh/earendil-cli.git cli
cd ./cli
mix deps.get
MIX_ENV=prod mix release
mv ./_build/prod/rel/bakeware/earendil ../earendil
cd ..
rm -rf cli
chmod 777 ./earendil