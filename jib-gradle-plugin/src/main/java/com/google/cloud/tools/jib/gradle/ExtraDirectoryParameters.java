/*
 * Copyright 2018 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.jib.gradle;

import com.google.cloud.tools.jib.plugins.common.ConfigurationPropertyValidator;
import com.google.cloud.tools.jib.plugins.common.PropertyNames;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Internal;

/** Object in {@link JibExtension} that configures the extra directory. */
@Deprecated
public class ExtraDirectoryParameters {

  @Deprecated @Nullable private Path path;
  @Deprecated private Map<String, String> permissions = Collections.emptyMap();

  @Deprecated
  @Input
  @Nullable
  public String getPathString() {
    // Gradle warns about @Input annotations on File objects, so we have to expose a getter for a
    // String to make them go away.
    return getPath() == null ? null : getPath().toString();
  }

  @Deprecated
  @Internal
  @Nullable
  public Path getPath() {
    // Gradle warns about @Input annotations on File objects, so we have to expose a getter for a
    // String to make them go away.
    String property = System.getProperty(PropertyNames.EXTRA_DIRECTORY_PATH);
    if (property != null) {
      return Paths.get(property);
    }
    return path;
  }

  // for the deprecated "jib.extraDirectory.path" config parameter
  @Deprecated
  public void setPath(File file) {
    // TODO: warn
    this.path = file.toPath();
  }

  /**
   * Gets the permissions for files in the extra layer on the container. Maps from absolute path on
   * the container to a 3-digit octal string representation of the file permission bits (e.g. {@code
   * "/path/on/container" -> "755"}).
   *
   * @return the permissions map from path on container to file permissions
   */
  @Deprecated
  @Input
  public Map<String, String> getPermissions() {
    String property = System.getProperty(PropertyNames.EXTRA_DIRECTORY_PERMISSIONS);
    if (property != null) {
      return ConfigurationPropertyValidator.parseMapProperty(property);
    }
    return permissions;
  }

  @Deprecated
  public void setPermissions(Map<String, String> permissions) {
    // TODO: warn
    this.permissions = permissions;
  }
}
