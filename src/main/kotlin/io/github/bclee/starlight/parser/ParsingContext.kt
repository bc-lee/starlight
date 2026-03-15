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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/parser/ParsingContext.kt
package io.github.bclee.starlight.parser

import com.intellij.lang.PsiBuilder
import java.util.*

class ParsingContext(val builder: PsiBuilder) {
  val expressionParser = ExpressionParsing(this)
  val statementParser = StatementParsing(this)
  val functionParser = FunctionParsing(this)
  private val scopes = ArrayDeque(listOf(emptyParsingScope()))

  fun popScope(): ParsingScope {
    val prevScope = scopes.pop()
    resetBuilderCache(prevScope)
    return prevScope
  }

  fun pushScope(scope: ParsingScope) {
    val prevScope = getScope()
    scopes.push(scope)
    resetBuilderCache(prevScope)
  }

  fun getScope(): ParsingScope = scopes.peek()

  fun emptyParsingScope(): ParsingScope = ParsingScope()

  private fun resetBuilderCache(prevScope: ParsingScope) {
    if (getScope() != prevScope) {
      builder.mark().rollbackTo()
    }
  }
}
