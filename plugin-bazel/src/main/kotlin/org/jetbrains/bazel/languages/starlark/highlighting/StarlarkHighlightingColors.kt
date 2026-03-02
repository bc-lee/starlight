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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/highlighting/StarlarkHighlightingColors.kt
package org.jetbrains.bazel.languages.starlark.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

object StarlarkHighlightingColors {
  val KEYWORD = createTextAttributesKey("STARLARK_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
  val STRING = createTextAttributesKey("STARLARK_STRING", DefaultLanguageHighlighterColors.STRING)
  val NUMBER = createTextAttributesKey("STARLARK_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
  val LINE_COMMENT = createTextAttributesKey("STARLARK_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
  val SEMICOLON = createTextAttributesKey("STARLARK_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
  val COMMA = createTextAttributesKey("STARLARK_COMMA", DefaultLanguageHighlighterColors.COMMA)
  val DOT = createTextAttributesKey("STARLARK_DOT", DefaultLanguageHighlighterColors.DOT)
  val PARENTHESES = createTextAttributesKey("STARLARK_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
  val BRACKETS = createTextAttributesKey("STARLARK_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
  val IDENTIFIER = createTextAttributesKey("STARLARK_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
  val FUNCTION_DECLARATION =
    createTextAttributesKey("STARLARK_FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
  val NAMED_ARGUMENT = createTextAttributesKey("STARLARK_NAMED_ARGUMENT")
}
