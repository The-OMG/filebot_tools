filebot -rename -r "/home13/omg/cloud/orionsbelt-RW/Plex-Media/Anime.parse/Space Dandy" \
	--conflict auto \
	--db AniDB \
	-non-strict \
	--format "/home13/omg/cloud/orionsbelt-RW/Plex-Media/Anime/{" $COLLECTION"}/{n}{" $ALIAS"} ({y}{" $CERTIFICATION"})/{n} ({y}) - {s00e00} - {t} [{vf} {hd}{" $SOURCE"} {vc} {ac} {af}{" $GROUP"}]" \
	--action test \
	--mode interactive \
	--log info