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
// Original Source: https://github.com/JetBrains/hirschgarten/blob/51366707c8894dfb6fffbf51e60848785c26b3de/plugin-bazel/src/main/kotlin/org/jetbrains/bazel/assets/BazelPluginIcons.kt
package io.github.bclee.starlight.assets

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

@Suppress("unused") // should be able to provide us with all available icons
object BazelPluginIcons {
  private fun loadIcon(path: String): Icon = IconLoader.getIcon(path, BazelPluginIcons::class.java.classLoader)

  @JvmField
  val bazel: Icon = loadIcon("icons/bazel.svg")

  @JvmField
  val bazelConfig: Icon = loadIcon("icons/bazelConfig.svg")

  @JvmField
  val bazelDirectory: Icon = loadIcon("icons/bazelDirectory.svg")
  val bazelError: Icon = loadIcon("icons/bazelError.svg")

  @JvmField
  val bazelReload: Icon = loadIcon("icons/bazelReload.svg")

  @JvmField
  val bazelToolWindow: Icon = loadIcon("icons/toolWindowBazel.svg")

  @JvmField
  val bazelWarning: Icon = loadIcon("icons/bazelWarning.svg")
}
