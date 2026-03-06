/*
 * Copyright 2021-2023 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Originally written by JetBrains s.r.o. (Apache-2.0).
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/pluginTests/test/org/jetbrains/bazel/languages/starlark/lexer/StarlarkLexerTest.kt
package io.github.bclee.starlight.lexer

import org.junit.Assert.assertEquals
import org.junit.Test

class StarlarkLexerTest {
  @Test
  fun lexesSimpleExpressionWithStatementBreakAtEof() {
    assertEquals(
      listOf(
        "Starlark:IDENTIFIER",
        "Starlark:=",
        "Starlark:INT",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens("x=0"),
    )
  }

  @Test
  fun lexesAndMergesSpaces() {
    assertEquals(
      listOf(
        "Starlark:IDENTIFIER",
        "Starlark:SPACE",
        "Starlark:=",
        "Starlark:SPACE",
        "Starlark:INT",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens("x  =   0"),
    )
  }

  @Test
  fun lexesLineBreakInParentheses() {
    val code =
      """
      |(a,
      |b)
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:(",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:)",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesLineBreakInBrackets() {
    val code =
      """
      |[a,
      |b]
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:[",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:]",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesLineBreakInBraces() {
    val code =
      """
      |{a,
      |b}
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:{",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:}",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesLineBreakInBracesAfterAssignment() {
    val code =
      """
      |x={a,
      |b}
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:IDENTIFIER",
        "Starlark:=",
        "Starlark:{",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:}",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesIndentAndDedent() {
    val code =
      """
      if a:
        b
      c
      """.trimIndent()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:DEDENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesCommentInsideBracesAndLineBreak() {
    val code =
      """
      x={a, #com
      b}
      """.trimIndent()

    assertEquals(
      listOf(
        "Starlark:IDENTIFIER",
        "Starlark:=",
        "Starlark:{",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:SPACE",
        "Starlark:COMMENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:}",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesBraceAfterIndent() {
    val code =
      """
      |x=
      |  {a, #comment
      |  b}
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:IDENTIFIER",
        "Starlark:=",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:{",
        "Starlark:IDENTIFIER",
        "Starlark:,",
        "Starlark:SPACE",
        "Starlark:COMMENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:}",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesMultilineIndent() {
    val code =
      """
      |if a:
      |  b
      |  c
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesMultiDedent() {
    val code =
      """
      |if a:
      |  b
      |    c
      |d
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:DEDENT",
        "Starlark:DEDENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesMultiCascadeDedent() {
    val code =
      """
      |if a:
      |  b
      |    c
      |  d
      |e
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:DEDENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:DEDENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesCommentAndDedentOrdering() {
    val code =
      """
      |if a:
      |  b
      |  #comment
      |c
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:COMMENT",
        "Starlark:DEDENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  @Test
  fun lexesIndentedCommentAndCode() {
    val code =
      """
      |if a:
      |  b
      |  #comment
      |  c
      """.trimMargin()

    assertEquals(
      listOf(
        "Starlark:if",
        "Starlark:SPACE",
        "Starlark:IDENTIFIER",
        "Starlark::",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:INDENT",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
        "Starlark:LINE_BREAK",
        "Starlark:COMMENT",
        "Starlark:LINE_BREAK",
        "Starlark:IDENTIFIER",
        "Starlark:STATEMENT_BREAK",
      ),
      lexTokens(code),
    )
  }

  private fun lexTokens(code: String): List<String> {
    val lexer = StarlarkIndentingLexer()
    lexer.start(code)

    val tokens = mutableListOf<String>()
    while (lexer.tokenType != null) {
      tokens += lexer.tokenType.toString()
      lexer.advance()
    }

    return tokens
  }
}
