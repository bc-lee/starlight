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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/parser/StarlarkParserDefinition.kt
package io.github.bclee.starlight.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.bazel.languages.starlark.StarlarkLanguage
import org.jetbrains.bazel.languages.starlark.elements.StarlarkElementTypes
import org.jetbrains.bazel.languages.starlark.elements.StarlarkTokenSets
import org.jetbrains.bazel.languages.starlark.lexer.StarlarkIndentingLexer
import org.jetbrains.bazel.languages.starlark.psi.StarlarkFile

class StarlarkParserDefinition : ParserDefinition {
  private val file = IFileElementType(StarlarkLanguage)

  override fun createLexer(project: Project?): Lexer = StarlarkIndentingLexer()

  override fun createParser(project: Project?): PsiParser = StarlarkParser()

  override fun getFileNodeType(): IFileElementType = file

  override fun getWhitespaceTokens(): TokenSet = StarlarkTokenSets.WHITESPACE

  override fun getCommentTokens(): TokenSet = StarlarkTokenSets.COMMENT

  override fun getStringLiteralElements(): TokenSet = StarlarkTokenSets.STRINGS

  override fun createElement(node: ASTNode): PsiElement = StarlarkElementTypes.createElement(node)

  override fun createFile(viewProvider: FileViewProvider): PsiFile = StarlarkFile(viewProvider)
}
