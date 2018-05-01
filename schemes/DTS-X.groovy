{
def ChannelString = any{(0.0+audio.'ChannelPositionsString2'*.replaceAll(/Object\sBased\s\/|0.(?=\d.\d)/, '')*.split(' / ')*.collect{ it.split('/')*.toBigDecimal().sum() }*.max().max()).toString()}{channels};
def codecSubVersion = any{audio.any{ a -> call{a.FormatProfile} =~ 'HRA' } ? '.HRA' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'X / MA / Core' } ? '-X' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'MA / Core' } ? '.MA' : null}{audio.any{ a -> call{a.FormatProfile} =~ 'ES Matrix / Core' } ? '-ES' : null}{null};
def codecVersion = any{audio.Codec.join().match(/DTS-HD/)+codecSubVersion+'.'+audio.Codec.join().match(/TrueHD/)}{audio.Codec.join().match(/DTS-HD/) && codecSubVersion == '-X' ? 'DTS-X' : null}{audio.Codec.join().match(/DTS-HD/)+codecSubVersion}{audio.Codec.join().match(/TrueHD/)}{audio.Codec.join().match(/DTS/)+codecSubVersion.replaceAll(/null/)}{ac};
(allOf{'.'+codecVersion.replaceAll(/null/)}
{if( ((ac == 'AAC'||ac == 'MP3') && (channels != '2.0' || ChannelString != channels) ) || ( (ac == 'AC3'||ac == 'DTS'||ac == 'TrueHD') && (channels != '5.1' || ChannelString != channels) ) ) return {any{ChannelString}{channels}}}
{aco.match(/Atmos/)}).join('.')
}