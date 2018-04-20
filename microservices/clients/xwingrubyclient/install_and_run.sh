#!/bin/bash

# download dependencies
bundle install

# run in context of bundle
bundle exec ruby app.rb -p $PORT -e production
