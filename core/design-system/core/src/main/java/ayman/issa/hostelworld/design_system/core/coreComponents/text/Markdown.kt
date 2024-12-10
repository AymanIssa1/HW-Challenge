package ayman.issa.hostelworld.design_system.core.coreComponents.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor

internal data class SpanEraseData(
    val start: Int,
    val end: Int,
)

internal sealed class Markdown(
    val pattern: Regex,
    val spanStyle: SpanStyle,
    val eraseData: SpanEraseData,
) {
    object UnderLine : Markdown(
        pattern = Regex("_(.*?)_"),
        spanStyle = SpanStyle(textDecoration = TextDecoration.Underline),
        eraseData = SpanEraseData(
            start = 1,
            end = 1
        ),
    )

    object Bold : Markdown(
        pattern = Regex("\\*(.*?)\\*"),
        spanStyle = SpanStyle(fontWeight = TextWeight.Bold.weightUnit),
        eraseData = SpanEraseData(
            start = 1,
            end = 1
        ),
    )

    object SemiBold : Markdown(
        pattern = Regex("\\*\\*(.*?)\\*\\*"),
        spanStyle = SpanStyle(fontWeight = TextWeight.SemiBold.weightUnit),
        eraseData = SpanEraseData(
            start = 1,
            end = 1
        )
    )

    object RedColor : Markdown(
        pattern = Regex("\\#(.*?)\\#\\[R\\]"),
        spanStyle = SpanStyle(color = HostelWorldColor.Red.color),
        eraseData = SpanEraseData(
            start = 1,
            end = 4
        ),
    )
}

internal fun applyMarkdown(string: String): AnnotatedString {
    val markdowns = listOf(
        Markdown.UnderLine,
        Markdown.RedColor,
        Markdown.Bold,
        Markdown.SemiBold,
    )

    var modifiedText = string
    val markdownRanges = mutableListOf<AnnotatedString.Range<Markdown>>()

    markdowns.forEach { markdown ->
        val matches = markdown.pattern.findAll(string)
        matches.forEach { match ->
            markdownRanges += AnnotatedString.Range(
                item = markdown,
                start = match.range.first,
                end = match.range.last,
            )
        }
    }

    val spanStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()

    markdownRanges
        .forEach { markdownRange ->
            modifiedText = StringBuilder(modifiedText).apply {
                repeat(markdownRange.item.eraseData.start) { padding ->
                    setCharAt(markdownRange.start + padding, Char.MIN_VALUE)
                }

                repeat(markdownRange.item.eraseData.end) { padding ->
                    setCharAt(markdownRange.end - padding, Char.MIN_VALUE)
                }
            }.toString()

            spanStyles += AnnotatedString.Range(
                item = markdownRange.item.spanStyle,
                start = markdownRange.start,
                end = markdownRange.end,
            )
        }

    return AnnotatedString(modifiedText, spanStyles)
}
