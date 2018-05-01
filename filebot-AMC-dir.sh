#!/bin/bash
filebot -script fn:amc \
--output "$HOME/cloud/orionsbelt-RW/Plex-Media" \
--action move \
--conflict auto \
-non-strict ./** \
--log-file "$HOME/logs/amc.log" \
-get-subtitles \
--def unsorted=y \
--def subtitles=en,de,fr \
--def music=n \
--def artwork=y \
--def excludeList=$HOME/logs/filebot_excludes.txt \
--def movieFormat="M:/Movies/{ny}/{fn}" \
--def seriesFormat="S:/TV/{n}/{fn}" \
--def animeFormat="T:/Anime/{n}/{fn}" \
--def musicFormat="N:/Music/{n}/{fn}" \
--def clean=y

filebot -script fn:amc --action test --output "/<disk>/Media" --conflict --def clean=y unsorted=y subtitles=en artwork=y excludeList=".excludes" ut_dir="$ARG_PATH" ut_kind="multi" ut_title="$ARG_NAME" ut_label="$ARG_LABEL" exec="chmod 664 '{file}'" --def @/<disk>/scripts/pushover.txt --def movieFormat=@/<disk>/scripts/movies.groovy seriesFormat=@/<disk>/scripts/tvshows.groovy
