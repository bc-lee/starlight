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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/pluginTests/test/org/jetbrains/bazel/languages/starlark/parser/StarlarkParsingStmtTest.kt
package org.jetbrains.bazel.languages.starlark.parser

import org.jetbrains.bazel.languages.starlark.fixtures.StarlarkParsingTestCase

class StarlarkParsingStmtTest : StarlarkParsingTestCase("stmt") {
  fun testAssignStmt() = doTest(true)

  fun testBreakStmt() = doTest(true)

  fun testContinueStmt() = doTest(true)

  fun testExprStmt() = doTest(true)

  fun testLoadStmt() = doTest(true)

  fun testPassStmt() = doTest(true)

  fun testReturnStmt() = doTest(true)
}
