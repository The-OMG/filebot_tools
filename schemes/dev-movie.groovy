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
{ transl = { it.transliterate("Any-Latin; NFD; NFC; Title") }
  isLatin = { java.text.Normalizer.normalize(it, java.text.Normalizer.Form.NFD)
                                  .replaceAll(/\p{InCombiningDiacriticalMarks}+/, "") ==~ /^\p{InBasicLatin}+$/ } }
{ allOf
  {"Movies"}
  // Movies directory
  {n.colon(" - ") + " ($y, $director)"}
  // File name
  { allOf 
    { isLatin(primaryTitle) ? primaryTitle : transl(primaryTitle) }
    {" ($y)"}
    // tags + a few more variants
    { specials = { allOf 
                     {tags}
                     { fn.findAll(/(?i:alternate[ ._-]cut|limited|proper|repack)/)*.upperInitial()*.lowerTrail()*.replaceAll(/[._-]/, " ") }
                     .flatten().sort() }
      specials().size() > 0 ? specials().join(", ").replaceAll(/^/, " - ") : "" }
    {" PT $pi"}
    {" ["}
    { allOf
      // Video stream
      {[vf,vc].join(" ")}
      { allOf
        // Audio stream and language
        {[channels,ac].join(" ")}
        { def a = audioLanguages
          a.size() > 1 ? a.ISO3.join(", ").upperInitial() : a.name.first() }
        .join(" ") }
      {source}
      .join(" - ") }
    {"]"}
    {"-" + group}
    {subt}
    .join("") }
  .join("/") }
