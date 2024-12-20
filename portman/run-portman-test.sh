#!/bin/bash

script_dir=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$script_dir"

portman \
	--cliOptionsFile    portman-cli.yml \
	--portmanConfigFile portman-config.yml