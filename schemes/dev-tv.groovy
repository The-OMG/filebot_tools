{ norm = { it.upperInitial()
             .lowerTrail()
             .replaceTrailingBrackets()
             .replaceAll(/[`´‘’ʻ""“”]/, "'")
             .replaceAll(/[:|]/, " - ")
             .replaceAll(/[?]/, "!")
             .replaceAll(/[*\s]+/, " ")
             .replaceAll(/\b[IiVvXx]+\b/, { it.upper() })
             .replaceAll(/\b[0-9](?i:th|nd|rd)\b/, { it.lower() })
             .replaceFirst(/^(?i)(The)\s(.+)/, /$2, $1/) } } 
{ allOf
  {"TV Shows"}
  { allOf 
      { (norm(n) == norm(primaryTitle)) ? norm(n) : norm(n) + ' [' + norm(primaryTitle) + ']' }
      { "($y)" }
    .join(" ") }
  { episode.special ? 'Specials' : 'Season ' + s.pad(2) }
  { allOf
    { norm(n) }
    { episode.special ? 'S00E' + special.pad(2) : s00e00 }
    { allOf 
      { norm(t) }
      {"PT $pi"}
      { allOf 
        { allOf 
          {"["}
          { allOf
            {[vf,vc].join(" ")}
            {[channels,ac].join(" ")}
            {source}
            .join(" - ") }
          {"]"}
          .join("") }
        {"-$group"}
        {subt}
        .join("") }
      .join(" ") }
    .join(" - ") }
  .join("/") }
