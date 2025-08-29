package com.bettor.slangrecommend.data.model

object UdTextFormatter {
    private val udBracket = Regex("""\[(.+?)\]""")
    private val emojiA = Regex("""[\uD83C-\uDBFF][\uDC00-\uDFFF]""")
    private val emojiB = Regex("""[\u2600-\u27BF\uFE0F\u200D]""")
    private val multiSpaces = Regex("[ \t]+")
    private val multiNewlines = Regex("\n{3,}")

    fun removeUdBrackets(s: String) = s.replace(udBracket, "$1")
    fun removeEmojis(s: String) = s.replace(emojiA, "").replace(emojiB, "")
    fun normalizeSpaces(s: String) = s.replace(multiSpaces, " ")
        .replace(multiNewlines, "\n\n").trim()

    fun normalizeForDisplay(raw: String): String =
        normalizeSpaces(removeEmojis(removeUdBrackets(raw)))
}