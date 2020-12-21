#!/bin/sh
################################################################################
# Title         : generateDocumentation.sh
# Date created  : 2017
# Notes         : original script from Jeroen de Bruijn
__AUTHOR__="Jesus Navarro"
# Preconditions:
# - Packages doxygen doxygen-doc doxygen-gui graphviz
#   must be installed.
# - Doxygen configuration file must have the destination directory empty and
#   source code directory with a $(TRAVIS_BUILD_DIR) prefix.
# - An gh-pages branch should already exist. See below for mor info on hoe to
#   create a gh-pages branch.
#
# Required global variables:
# - TRAVIS_BUILD_NUMBER : The number of the current build.
# - TRAVIS_COMMIT       : The commit that the current build is testing.
# - DOXYFILE            : The Doxygen configuration file.
# - GH_REPO_NAME        : The name of the repository.
# - GH_REPO_REF         : The GitHub reference to the repository.
# - GH_REPO_TOKEN       : Secure token to the github repository.
#
# For information on how to encrypt variables for Travis CI please go to
# https://docs.travis-ci.com/user/environment-variables/#Encrypted-Variables
# or https://gist.github.com/vidavidorra/7ed6166a46c537d3cbd2
# For information on how to create a clean gh-pages branch from the master
# branch, please go to https://gist.github.com/vidavidorra/846a2fc7dd51f4fe56a0
#
# This script will generate Doxygen documentation and push the documentation to
# the gh-pages branch of a repository specified by GH_REPO_REF.
# Before this script is used there should already be a gh-pages branch in the
# repository.
# 
################################################################################

################################################################################
##### Setup this script and get the current gh-pages branch.               #####
echo 'Setting up the script...'
# Exit with nonzero exit code if anything fails
set -e

GH_REPO_ORG=`echo $TRAVIS_REPO_SLUG | cut -d "/" -f 1`
GH_REPO_NAME=`echo $TRAVIS_REPO_SLUG | cut -d "/" -f 2`
GH_REPO_REF="github.com/$GH_REPO_ORG/$GH_REPO_NAME.git"
DOXYFILE=$TRAVIS_BUILD_DIR/Doxyfile


# Get the current gh-pages branch
git clone -b gh-pages git@github.com:$GH_REPO_ORG/$GH_REPO_NAME.git code_docs
cd code_docs

# Copy DoxygenLayout.xml here
cp $TRAVIS_BUILD_DIR/doc/DoxygenLayout.xml .

##### Configure git.
# Set the push default to simple i.e. push only the current branch.
git config --global push.default simple
# Pretend to be an user called Travis CI.
git config user.name "Travis CI"
git config user.email "travis@travis-ci.org"

# go back to first commit
git reset --hard `git rev-list --max-parents=0 --abbrev-commit HEAD`

# Need to create a .nojekyll file to allow filenames starting with an underscore
# to be seen on the gh-pages site. Therefore creating an empty .nojekyll file.
# Presumably this is only needed when the SHORT_NAMES option in Doxygen is set
# to NO, which it is by default. So creating the file just in case.
echo "" > .nojekyll

################################################################################
##### Generate the Doxygen code documentation and log the output.          #####
echo 'Generating Doxygen code documentation...'
# Redirect both stderr and stdout to the log file AND the console.
doxygen $DOXYFILE 2>&1 | tee doxygen.log

###########
