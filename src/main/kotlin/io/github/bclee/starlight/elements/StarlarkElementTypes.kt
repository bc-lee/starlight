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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/languages/starlark/elements/StarlarkElementTypes.kt
package io.github.bclee.starlight.elements

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

object StarlarkElementTypes {
  val ASSIGNMENT_STATEMENT = StarlarkElementType("ASSIGNMENT_STATEMENT")
  val AUG_ASSIGNMENT_STATEMENT = StarlarkElementType("AUG_ASSIGNMENT_STATEMENT")
  val BREAK_STATEMENT = StarlarkElementType("BREAK_STATEMENT")
  val CONTINUE_STATEMENT = StarlarkElementType("CONTINUE_STATEMENT")
  val EXPRESSION_STATEMENT = StarlarkElementType("EXPRESSION_STATEMENT")
  val FOR_STATEMENT = StarlarkElementType("FOR_STATEMENT")
  val IF_STATEMENT = StarlarkElementType("IF_STATEMENT")
  val LOAD_STATEMENT = StarlarkElementType("LOAD_STATEMENT")
  val FILENAME_LOAD_VALUE = StarlarkElementType("FILENAME_LOAD_VALUE")
  val NAMED_LOAD_VALUE = StarlarkElementType("NAMED_LOAD_VALUE")
  val STRING_LOAD_VALUE = StarlarkElementType("STRING_LOAD_VALUE")
  val PASS_STATEMENT = StarlarkElementType("PASS_STATEMENT")
  val RETURN_STATEMENT = StarlarkElementType("RETURN_STATEMENT")
  val STATEMENT_LIST = StarlarkElementType("STATEMENT_LIST")

  val FUNCTION_DECLARATION = StarlarkElementType("FUNCTION_DECLARATION")
  val MANDATORY_PARAMETER = StarlarkElementType("MANDATORY_PARAMETER")
  val OPTIONAL_PARAMETER = StarlarkElementType("OPTIONAL_PARAMETER")
  val VARIADIC_PARAMETER = StarlarkElementType("VARIADIC_PARAMETER")
  val KEYWORD_VARIADIC_PARAMETER = StarlarkElementType("KEYWORD_VARIADIC_PARAMETER")
  val KEYWORD_ONLY_BOUNDARY = StarlarkElementType("KEYWORD_ONLY_BOUNDARY")
  val PARAMETER_LIST = StarlarkElementType("PARAMETER_LIST")

  val ARGUMENT_EXPRESSION = StarlarkElementType("ARGUMENT_EXPRESSION")
  val ARGUMENT_LIST = StarlarkElementType("ARGUMENT_LIST")
  val BINARY_EXPRESSION = StarlarkElementType("BINARY_EXPRESSION")
  val CALL_EXPRESSION = StarlarkElementType("CALL_EXPRESSION")
  val CONDITIONAL_EXPRESSION = StarlarkElementType("CONDITIONAL_EXPRESSION")
  val DICT_COMP_EXPRESSION = StarlarkElementType("DICT_COMP_EXPRESSION")
  val DICT_LITERAL_EXPRESSION = StarlarkElementType("DICT_LITERAL_EXPRESSION")
  val DOUBLE_STAR_EXPRESSION = StarlarkElementType("DOUBLE_STAR_EXPRESSION")
  val EMPTY_EXPRESSION = StarlarkElementType("EMPTY_EXPRESSION")
  val FALSE_LITERAL_EXPRESSION = StarlarkElementType("FALSE_LITERAL_EXPRESSION")
  val FLOAT_LITERAL_EXPRESSION = StarlarkElementType("FLOAT_LITERAL_EXPRESSION")
  val GENERATOR_EXPRESSION = StarlarkElementType("GENERATOR_EXPRESSION")
  val GLOB_EXPRESSION = StarlarkElementType("GLOB_EXPRESSION")
  val TARGET_EXPRESSION = StarlarkElementType("TARGET_EXPRESSION")
  val INTEGER_LITERAL_EXPRESSION = StarlarkElementType("INTEGER_LITERAL_EXPRESSION")
  val KEY_VALUE_EXPRESSION = StarlarkElementType("KEY_VALUE_EXPRESSION")
  val LAMBDA_EXPRESSION = StarlarkElementType("LAMBDA_EXPRESSION")
  val LIST_COMP_EXPRESSION = StarlarkElementType("LIST_COMP_EXPRESSION")
  val LIST_LITERAL_EXPRESSION = StarlarkElementType("LIST_LITERAL_EXPRESSION")
  val NAMED_ARGUMENT_EXPRESSION = StarlarkElementType("NAMED_ARGUMENT_EXPRESSION")
  val NONE_LITERAL_EXPRESSION = StarlarkElementType("NONE_LITERAL_EXPRESSION")
  val PARENTHESIZED_EXPRESSION = StarlarkElementType("PARENTHESIZED_EXPRESSION")
  val PREFIX_EXPRESSION = StarlarkElementType("PREFIX_EXPRESSION")
  val REFERENCE_EXPRESSION = StarlarkElementType("REFERENCE_EXPRESSION")
  val SLICE_EXPRESSION = StarlarkElementType("SLICE_EXPRESSION")
  val SLICE_ITEM = StarlarkElementType("SLICE_ITEM")
  val STAR_ARGUMENT_EXPRESSION = StarlarkElementType("STAR_ARGUMENT_EXPRESSION")
  val STAR_EXPRESSION = StarlarkElementType("STAR_EXPRESSION")
  val STRING_LITERAL_EXPRESSION = StarlarkElementType("STRING_LITERAL_EXPRESSION")
  val SUBSCRIPTION_EXPRESSION = StarlarkElementType("SUBSCRIPTION_EXPRESSION")
  val TRUE_LITERAL_EXPRESSION = StarlarkElementType("TRUE_LITERAL_EXPRESSION")
  val TUPLE_EXPRESSION = StarlarkElementType("TUPLE_EXPRESSION")

  fun createElement(node: ASTNode): PsiElement = ASTWrapperPsiElement(node)
}
