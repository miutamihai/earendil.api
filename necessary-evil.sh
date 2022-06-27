#!/bin/sh

git clone https://github.com/miutamihai/shite.git evil
./earendil run ./evil/earendil/steps.json
rm -rf evil