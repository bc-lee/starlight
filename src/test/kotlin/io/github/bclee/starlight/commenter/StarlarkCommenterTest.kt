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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/pluginTests/test/org/jetbrains/bazel/languages/starlark/commenter/StarlarkCommenterTest.kt
package io.github.bclee.starlight.commenter

import com.google.idea.testing.runfiles.Runfiles
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.vfs.newvfs.impl.VfsRootAccess
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.jetbrains.bazel.test.framework.BazelPathManager
import kotlin.io.path.pathString

class StarlarkCommenterTest : BasePlatformTestCase() {
  override fun getTestDataPath(): String =
    BazelPathManager.getTestFixture("starlark/commenter")

  fun testComment() = doTest()

  fun testUncomment() = doTest()

  private fun doTest() {
    VfsRootAccess.allowRootAccess(testRootDisposable, testDataPath)

    val name = getTestName(false)
    val source = "$name.bzl"
    myFixture.configureByFile(source)
    myFixture.performEditorAction(IdeActions.ACTION_COMMENT_LINE)
    val result = "${name}Result.bzl"
    myFixture.checkResultByFile(result)
  }
}
