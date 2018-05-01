// moves "The" after the filename "Amazing Spiderman, The"
{ norm = {it.upperInitial()
			.lowerTrail()
			.replaceTrailingBrackets()
			.replaceAll(/[`´‘’ʻ""“”]/, "'")
			.replaceAll(/[:|]/, " - ")
			.replaceAll(/[?]/, "!")
			.replaceAll(/[*\s]+/, " ")
			.replaceAll(/\b[IiVvXx]+\b/, { it.upper() })
			.replaceAll(/\b[0-9](?i:th|nd|rd)\b/, { it.lower() })
			.replaceFirst(/^(?i)(The)\s(.+)/, /$2, $1/)}; norm(n)}
// File Title
{if (norm(n) != norm(primaryTitle)) ' ('+norm(primaryTitle)+')'}
// adds "3D" tag if applicable
{fn.contains('3D') || fn.contains('3-D') ? ' '+'3D':""} 
// Year
({y}
// File certification "G, PG, PG-13....."
{' '+any{certification}{imdb.certification}.replaceAll(/^\d+$/, 'PG-$0')})
/
// Episode numbering in Absolute Mode. "No seasons"
{norm(n)} - {absolute.pad(episodelist.size() < 99 ? 2 : 3)}
// Speical Episode numbering in Absolute Mode. "No seasons"
{'Special '+special.pad(episodelist.size() < 99 ? 2 : 3)}
- 
// adds "3D" tag if applicable
{norm(t)}{fn.contains('3D') || fn.contains('3-D') ? ' '+'3D':""}
// Tags file with available tags.
{' (' + fn.matchAll(/extended|uncensored|remastered|unrated|uncut|directors.cut|special.edition/)*.upperInitial()*.lowerTrail().sort().join(', ').replaceAll(/[._]/, " ") + ')'}
{" Part $pi"}
{" [$vf $vc $ac $af $group]"}