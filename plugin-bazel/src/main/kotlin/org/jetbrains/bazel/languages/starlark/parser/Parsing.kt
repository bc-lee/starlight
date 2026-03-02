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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/parser/Parsing.kt
package org.jetbrains.bazel.languages.starlark.parser

import com.intellij.lang.PsiBuilder
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.NlsContexts.ParsingError
import com.intellij.psi.tree.IElementType
import org.jetbrains.bazel.languages.starlark.StarlarkBundle
import org.jetbrains.bazel.languages.starlark.elements.StarlarkTokenSets
import org.jetbrains.bazel.languages.starlark.elements.StarlarkTokenType
import org.jetbrains.bazel.languages.starlark.elements.StarlarkTokenTypes

open class Parsing(val context: ParsingContext) {
  protected val builder = context.builder

  protected fun checkMatches(token: IElementType, message: @ParsingError String): Boolean {
    if (atToken(token)) {
      builder.advanceLexer()
      return true
    }
    builder.error(message)
    return false
  }

  protected fun parseIdentifierOrSkip(): Boolean =
    if (atToken(StarlarkTokenTypes.IDENTIFIER)) {
      builder.advanceLexer()
      true
    } else {
      val nameExpected = builder.mark()
      if (!atToken(StarlarkTokenTypes.STATEMENT_BREAK) && !atToken(StarlarkTokenTypes.LPAR)) {
        builder.advanceLexer()
      }
      nameExpected.error(StarlarkBundle.message("parser.expected.identifier"))
      false
    }

  protected fun assertCurrentToken(tokenType: StarlarkTokenType) {
    LOG.assertTrue(atToken(tokenType))
  }

  protected fun atToken(tokenType: IElementType?): Boolean = builder.tokenType === tokenType

  protected fun atAnyOfTokens(tokenTypes: List<IElementType?>): Boolean {
    val currentTokenType = builder.tokenType
    for (tokenType in tokenTypes) {
      if (currentTokenType === tokenType) return true
    }
    return false
  }

  protected fun matchToken(tokenType: IElementType): Boolean {
    if (atToken(tokenType)) {
      builder.advanceLexer()
      return true
    }
    return false
  }

  protected fun nextToken() {
    builder.advanceLexer()
  }

  companion object {
    private val LOG = Logger.getInstance(Parsing::class.java)

    @JvmStatic
    protected fun advanceIdentifierLike(builder: PsiBuilder) {
      if (isReservedKeyword(builder)) {
        val tokenText = builder.tokenText ?: ""
        advanceError(builder, StarlarkBundle.message("parser.reserved.keyword.cannot.be.used.as.identifier", tokenText))
      } else {
        builder.advanceLexer()
      }
    }

    @JvmStatic
    protected fun advanceError(builder: PsiBuilder, message: @ParsingError String) {
      val err = builder.mark()
      builder.advanceLexer()
      err.error(message)
    }

    @JvmStatic
    protected fun isIdentifierLike(builder: PsiBuilder): Boolean =
      builder.tokenType === StarlarkTokenTypes.IDENTIFIER || isReservedKeyword(builder)

    private fun isReservedKeyword(builder: PsiBuilder): Boolean = StarlarkTokenSets.RESERVED_KEYWORDS.contains(builder.tokenType)

    @JvmStatic
    protected fun buildTokenElement(type: IElementType?, builder: PsiBuilder) {
      val marker = builder.mark()
      advanceIdentifierLike(builder)
      marker.done(type!!)
    }
  }
}
