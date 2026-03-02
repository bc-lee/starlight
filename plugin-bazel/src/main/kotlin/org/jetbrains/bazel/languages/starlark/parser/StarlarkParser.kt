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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/parser/StarlarkParser.kt
package org.jetbrains.bazel.languages.starlark.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType

class StarlarkParser : PsiParser {
  override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
    val rootMarker = builder.mark()
    val context = ParsingContext(builder)
    var lastAfterSemicolon = false

    while (!builder.eof()) {
      context.pushScope(context.emptyParsingScope())
      if (lastAfterSemicolon) {
        context.statementParser.parseSimpleStatement()
      } else {
        context.statementParser.parseStatement()
      }
      lastAfterSemicolon = context.getScope().isAfterSemicolon
      context.popScope()
    }

    rootMarker.done(root)
    return builder.treeBuilt
  }
}
