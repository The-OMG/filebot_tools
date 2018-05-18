Z:\OMG_share\Anime
{collection.replaceFirst(/^(?i)(The)\s(.+)/, /$2, $1/).replaceFirst(/^(?i)(Collection of the)\s(.+)/, /$2 Collection/).replaceAll(/Saga Collection/, "Saga").replaceAll(/[`´‘’ʻ""“”]/, "'").replaceAll(/[:|]/, " - ").replaceAll(/[?]/, "!").replaceAll(/[*\s]+/, " ")}\{norm = {it.upperInitial().lowerTrail().replaceTrailingBrackets().replaceAll(/[`´‘’ʻ""“”]/, "'").replaceAll(/[:|]/, " - ").replaceAll(/[?]/, "!").replaceAll(/[*\s]+/, " ").replaceAll(/\b[IiVvXx]+\b/, { it.upper() }).replaceAll(/\b[0-9](?i:th|nd|rd)\b/, { it.lower() }).replaceFirst(/^(?i)(The)\s(.+)/, /$2, $1/)}; norm(n)}{if (norm(n) != norm(primaryTitle)) ' ('+norm(primaryTitle)+')'}
({"$y"})
/
{norm(n)}
({y})
{' (' + fn.matchAll(/extended|uncensored|remastered|unrated|uncut|directors.cut|special.edition/)*.upperInitial()*.lowerTrail().sort().join(', ').replaceAll(/[._]/, " ") + ')'}
-
{" $order.airdate.s00e00 "}
-
{" $t "}({"$SOURCE "}{"$VF"}
{def ChannelString = any{(0.0+audio.'ChannelPositionsString2'*.replaceAll(/Object\sBased\s\/|0.(?=\d.\d)/, '')*.split(' / ')*.collect{ it.split('/')*.toBigDecimal().sum() }*.max().max()).toString()}{channels};
def codecSubVersion = any{audio.any{ a -> call{a.FormatProfile} =~ 'HRA' } ? '.HRA' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'X / MA / Core' } ? '-X' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'MA / Core' } ? ' MA' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'ES Matrix / Core' } ? '-ES' : null}{null};
def codecVersion = any{audio.Codec.join().match(/DTS-HD/)+codecSubVersion+' '+audio.Codec.join().match(/TrueHD/)}{audio.Codec.join().match(/DTS-HD/) && codecSubVersion == '-X' ? 'DTS-X' : null}{audio.Codec.join().match(/DTS-HD/)+codecSubVersion}{audio.Codec.join().match(/TrueHD/)}{audio.Codec.join().match(/DTS/)+codecSubVersion.replaceAll(/null/)}{ac};
(allOf{' '+codecVersion.replaceAll(/null/)}
{aco.match(/Atmos/)}).join(' ')}
{video[0].CodecID =~ /HEVC/ ? ' HEVC' : ''}
{def mHDRCol = ["BT.709" : "NO", "BT.2020" : "YES"]; if(bitdepth >= 10 &&  mHDRCol.get(video[0].colourprimaries) == "YES" ) ' HDR ' else '';}
{" $VC"}{
def dn = call{(file.parentFile.name).match(/(?:\-\w+(?=\[\w+\])|\-\w+$)/)};
def nn = call{fn.match(/(?:(?=(?:19|20)+[0-9]+)\w.+$)/)};
def g = call{(file.parentFile.name+'.'+fn).find(/\-/) ? call{group.replaceAll(/\bDK\b|\bFS\b/,'')} : null};
def gg = '-'+g;
def gb = call{fn.match(/(?:^\w{3,}(?=[-]))/)};
def fm = call{fn.match(/(?:(?:\-(?!\bDL\b|\bRip\b|\bES\b|\bHD\b)\w+$)|(?:\-(?!\bDL\b|\bRip\b|\bES\b|\bHD\b)\w+[\.\_]\w+[\-]\w+$)|(?:\-\w+(?=\.cd[0-9]|\-trailer|\.part[0-9]|\.dan|\.eng))|(?:\-(?!\bDL\b|\bRip\b|\bES\b|\bHD\b)\w+[\-\.\_](?!\b720p\b|\b1080p\b)\w+$)|(?:\-\w+[\.]+\w+(?=\.cd[0-9]))|(?:\-(?!\bDL\b|\bRip\b|\bES\b|\bHD\b)\w+(?=\[\w+\]$)))/)};
if(g!=null && dn!=null && gg.equalsIgnoreCase(dn)) return '-'+g;
if(g!=null && dn!=null) return dn;
if(nn==null && dn!=null) return dn;
if(g!=null && (fm!=null||gb!=null) && (gg.equalsIgnoreCase(fm) || g.equalsIgnoreCase(gb))) return '-'+g;
if(g!=null && g!= '' && (fm!=null && gb!=null)) return '-'+g;
if(g==null && fm!=null && nn!=null) return ''+fm;
if(gb!=null && fm!=null && nn!=null) return '-'+gb;
if(gb!=null && g==null && fm==null && g==null && dn==null && nn==null) return '-'+gb;
if(g!=null && fm!=null && g!=fm) return fm;
if(g==null && fm==null && gb!=null && dn==null && nn==null) return '-'+gb;
if(g==null && fm!=null && gb!=null && dn==null && nn==null) return '-'+gb;
if(fm!=null) return fm}) [{"$crc32"}]
