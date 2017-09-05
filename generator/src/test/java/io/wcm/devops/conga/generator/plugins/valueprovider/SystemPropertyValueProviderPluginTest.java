/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
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
package io.wcm.devops.conga.generator.plugins.valueprovider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import io.wcm.devops.conga.generator.spi.ValueProviderPlugin;
import io.wcm.devops.conga.generator.util.PluginManagerImpl;


public class SystemPropertyValueProviderPluginTest {

  private ValueProviderPlugin underTest;

  @Before
  public void setUp() {
    underTest = new PluginManagerImpl().get(SystemPropertyValueProviderPlugin.NAME, ValueProviderPlugin.class);
  }

  @Test
  public void testResolve() {
    System.setProperty("test.prop1", "value1");

    assertEquals("value1", underTest.resolve("test.prop1"));
    assertNull(underTest.resolve("test.prop2"));

    System.clearProperty("test.prop1");
  }

}
