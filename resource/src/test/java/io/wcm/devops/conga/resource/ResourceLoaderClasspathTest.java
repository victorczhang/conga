/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2015 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.devops.conga.resource;

import static io.wcm.devops.conga.resource.ResourceLoader.CLASSPATH_PREFIX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.junit.Test;

public class ResourceLoaderClasspathTest {

  private static final String ROOT = "/test-files";

  @Test
  public void testResource() throws Exception {
    Resource resource = ResourceLoader.getResource(CLASSPATH_PREFIX + ROOT + "/folder1/file1.txt");

    assertTrue(resource.exists());
    assertEquals("file1.txt", resource.getName());
    assertEquals(ROOT + "/folder1/file1.txt", resource.getPath());
    assertEquals(CLASSPATH_PREFIX + ROOT + "/folder1/file1.txt", resource.getCanonicalPath());

    assertTrue(resource.getLastModified() > 0);

    try (InputStream is = resource.getInputStream()) {
      assertEquals("File 1", IOUtils.toString(is, CharEncoding.UTF_8));
    }
  }

  @Test
  public void testResourceCollection() throws Exception {
    ResourceCollection col = ResourceLoader.getResourceCollection(CLASSPATH_PREFIX + ROOT + "/folder1");

    assertTrue(col.exists());
    assertEquals("folder1", col.getName());
    assertEquals(ROOT + "/folder1", col.getPath());
    assertEquals(CLASSPATH_PREFIX + ROOT + "/folder1", col.getCanonicalPath());

    List<Resource> resources = col.getResources();
    assertEquals(2, resources.size());
    assertEquals("file1.txt", resources.get(0).getName());
    assertEquals("file2.txt", resources.get(1).getName());

    List<ResourceCollection> resourceCollections = col.getResourceCollections();
    assertEquals(1, resourceCollections.size());
    assertEquals("folder2", resourceCollections.get(0).getName());

    List<Resource> folder2Resources = resourceCollections.get(0).getResources();
    assertEquals(1, folder2Resources.size());
    assertEquals("file3.txt", folder2Resources.get(0).getName());
  }

  @Test
  public void testResourceAutoDetect() throws Exception {
    Resource resource = ResourceLoader.getResource(ROOT + "/folder1/file1.txt");

    assertTrue(resource.exists());
    assertEquals("file1.txt", resource.getName());
    assertEquals(ROOT + "/folder1/file1.txt", resource.getPath());
    assertEquals(CLASSPATH_PREFIX + ROOT + "/folder1/file1.txt", resource.getCanonicalPath());

    assertTrue(resource.getLastModified() > 0);

    try (InputStream is = resource.getInputStream()) {
      assertEquals("File 1", IOUtils.toString(is, CharEncoding.UTF_8));
    }
  }

  @Test
  public void testNonExistingResource() throws Exception {
    Resource resource = ResourceLoader.getResource(CLASSPATH_PREFIX + ROOT + "/folder1/invalid.txt");
    assertFalse(resource.exists());
    assertTrue(resource instanceof ClasspathResourceImpl);
  }

}
