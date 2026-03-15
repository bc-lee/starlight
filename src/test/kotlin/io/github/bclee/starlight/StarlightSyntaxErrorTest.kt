package io.github.bclee.starlight

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.PsiErrorElementUtil

class StarlightSyntaxErrorTest : BasePlatformTestCase() {
  fun testValidStarlarkHasNoPsiErrors() {
    val psiFile =
      myFixture.configureByText(
        "valid.bzl",
        """
        def f():
          return 1
        """.trimIndent(),
      )

    assertFalse(PsiErrorElementUtil.hasErrors(project, psiFile.virtualFile))
  }

  fun testInvalidStarlarkHasPsiErrors() {
    val psiFile =
      myFixture.configureByText(
        "invalid.bzl",
        """
        def f(
          return 1
        """.trimIndent(),
      )

    assertTrue(PsiErrorElementUtil.hasErrors(project, psiFile.virtualFile))
  }
}
