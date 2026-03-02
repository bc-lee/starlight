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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/elements/StarlarkTokenSets.kt
package org.jetbrains.bazel.languages.starlark.elements

import com.intellij.psi.tree.TokenSet

object StarlarkTokenSets {
  val WHITESPACE =
    TokenSet.create(
      StarlarkTokenTypes.SPACE,
      StarlarkTokenTypes.TAB,
      StarlarkTokenTypes.LINE_BREAK,
      StarlarkTokenTypes.LINE_CONTINUATION,
    )

  val IDENTIFIER = TokenSet.create(StarlarkTokenTypes.IDENTIFIER)

  val COMMENT = TokenSet.create(StarlarkTokenTypes.COMMENT)

  val STRINGS = TokenSet.create(StarlarkTokenTypes.STRING, StarlarkTokenTypes.BYTES)

  val OPEN_BRACKETS = TokenSet.create(StarlarkTokenTypes.LBRACKET, StarlarkTokenTypes.LBRACE, StarlarkTokenTypes.LPAR)

  val CLOSE_BRACKETS = TokenSet.create(StarlarkTokenTypes.RBRACKET, StarlarkTokenTypes.RBRACE, StarlarkTokenTypes.RPAR)

  val RESERVED_KEYWORDS =
    TokenSet.create(
      StarlarkTokenTypes.AS_KEYWORD,
      StarlarkTokenTypes.ASSERT_KEYWORD,
      StarlarkTokenTypes.ASYNC_KEYWORD,
      StarlarkTokenTypes.AWAIT_KEYWORD,
      StarlarkTokenTypes.CLASS_KEYWORD,
      StarlarkTokenTypes.DEL_KEYWORD,
      StarlarkTokenTypes.EXCEPT_KEYWORD,
      StarlarkTokenTypes.FINALLY_KEYWORD,
      StarlarkTokenTypes.FROM_KEYWORD,
      StarlarkTokenTypes.GLOBAL_KEYWORD,
      StarlarkTokenTypes.IMPORT_KEYWORD,
      StarlarkTokenTypes.IS_KEYWORD,
      StarlarkTokenTypes.NONLOCAL_KEYWORD,
      StarlarkTokenTypes.RAISE_KEYWORD,
      StarlarkTokenTypes.TRY_KEYWORD,
      StarlarkTokenTypes.WHILE_KEYWORD,
      StarlarkTokenTypes.WITH_KEYWORD,
      StarlarkTokenTypes.YIELD_KEYWORD,
    )

  val PRIMARY_EXPRESSION_STARTERS =
    TokenSet.create(
      StarlarkTokenTypes.IDENTIFIER,
      StarlarkTokenTypes.INT,
      StarlarkTokenTypes.FLOAT,
      StarlarkTokenTypes.LPAR,
      StarlarkTokenTypes.LBRACKET,
      StarlarkTokenTypes.LBRACE,
      StarlarkTokenTypes.STRING,
      StarlarkTokenTypes.BYTES,
      StarlarkTokenTypes.NONE_KEYWORD,
      StarlarkTokenTypes.TRUE_KEYWORD,
      StarlarkTokenTypes.FALSE_KEYWORD,
    )

  val ENDS_OF_STATEMENT = TokenSet.create(StarlarkTokenTypes.STATEMENT_BREAK, StarlarkTokenTypes.SEMICOLON)

  val COMPOUND_ASSIGN_OPERATIONS =
    TokenSet.create(
      StarlarkTokenTypes.PLUSEQ,
      StarlarkTokenTypes.MINUSEQ,
      StarlarkTokenTypes.MULTEQ,
      StarlarkTokenTypes.DIVEQ,
      StarlarkTokenTypes.FLOORDIVEQ,
      StarlarkTokenTypes.PERCEQ,
      StarlarkTokenTypes.ANDEQ,
      StarlarkTokenTypes.OREQ,
      StarlarkTokenTypes.XOREQ,
      StarlarkTokenTypes.LTLTEQ,
      StarlarkTokenTypes.GTGTEQ,
    )

  val COMPARISON_OPERATIONS =
    TokenSet.create(
      StarlarkTokenTypes.LT,
      StarlarkTokenTypes.GT,
      StarlarkTokenTypes.EQEQ,
      StarlarkTokenTypes.LE,
      StarlarkTokenTypes.GE,
      StarlarkTokenTypes.NE,
      StarlarkTokenTypes.IN_KEYWORD,
      StarlarkTokenTypes.NOT_KEYWORD,
    )

  val SHIFT_OPERATIONS = TokenSet.create(StarlarkTokenTypes.LTLT, StarlarkTokenTypes.GTGT)

  val ADDITIVE_OPERATIONS = TokenSet.create(StarlarkTokenTypes.PLUS, StarlarkTokenTypes.MINUS)

  val MULTIPLICATIVE_OPERATIONS =
    TokenSet.create(
      StarlarkTokenTypes.MULT,
      StarlarkTokenTypes.FLOORDIV,
      StarlarkTokenTypes.DIV,
      StarlarkTokenTypes.PERC,
    )

  val UNARY_OPERATIONS =
    TokenSet.create(
      StarlarkTokenTypes.PLUS,
      StarlarkTokenTypes.MINUS,
      StarlarkTokenTypes.TILDE,
      StarlarkTokenTypes.NOT_KEYWORD,
    )
}
