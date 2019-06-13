#!/usr/bin/env bash
#set -x
#set -v

MEDIA_FOLDER="$1"
MEDIA_GENRE="$2"
FIND_MODE="d"

mapfile -t -d '' INPUT_ARRAY < <(find "${MEDIA_FOLDER}" -maxdepth 3 -depth \
    ! -name '*BDMV*' \
    ! -name '*VIDEO_TS*' \
    ! -name '*BACKUP*' \
    ! -name '*BDJO*' \
    ! -name '*CLIPINF*' \
    ! -name '*JAR*' \
    ! -name '*META*' \
    ! -name '*PLAYLIST*' \
-type "${FIND_MODE}" -print0)

filebotFunction() {
    filebot -script fn:amc \
    --output "/home6/omg/cloud" \
    --action move \
    --conflict auto \
    -non-strict \
    "$1" \
    --def @/home6/omg/scripts/filebot/args.txt "ut_label=$2" \
    excludelist=excludes.txt -no-xattr >$HOME/logs/amcOperations.txt 2>&1
}

export -f filebotFunction

find "${MEDIA_FOLDER}" -maxdepth 3 -depth \
! -name '*BDMV*' \
! -name '*VIDEO_TS*' \
! -name '*BACKUP*' \
! -name '*BDJO*' \
! -name '*CLIPINF*' \
! -name '*JAR*' \
! -name '*META*' \
! -name '*PLAYLIST*' \
-type "${FIND_MODE}" -print0 |
parallel \
--joblog ~/logs/amc.log \
--use-cores-instead-of-threads \
--progress \
--nice 19 \
-0 \
--jobs 1 \
--load 10% filebotFunction ::: "${INPUT_ARRAY[@]}" ::: "$MEDIA_GENRE"
