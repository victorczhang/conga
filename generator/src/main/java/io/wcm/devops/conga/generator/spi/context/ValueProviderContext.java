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
package io.wcm.devops.conga.generator.spi.context;

import java.util.Collections;
import java.util.Map;

/**
 * Context for {@link io.wcm.devops.conga.generator.spi.ValueProviderPlugin}.
 */
public final class ValueProviderContext extends AbstractPluginContext<ValueProviderContext> {

  private Map<String, Map<String, Object>> valueProviderConfig;

  /**
   * @return Configuration for value providers.
   *         The outer map uses the value provider plugin name as key, the inner map contain the config properties.
   *         Never null.
   */
  public Map<String, Map<String, Object>> getValueProviderConfig() {
    if (this.valueProviderConfig == null) {
      return Collections.emptyMap();
    }
    return this.valueProviderConfig;
  }

  /**
   * @param valueProviderPluginName Value provider plugin name
   * @return Configuration for the given value provider. Never null.
   */
  public Map<String, Object> getValueProviderConfig(String valueProviderPluginName) {
    Map<String, Object> config = getValueProviderConfig().get(valueProviderPluginName);
    if (config == null) {
      return Collections.emptyMap();
    }
    return config;
  }

  /**
   * @param value Configuration for value providers.
   *          The outer map uses the value provider plugin name as key, the inner map contain the config properties.
   * @return this
   */
  public ValueProviderContext valueProviderConfig(Map<String, Map<String, Object>> value) {
    this.valueProviderConfig = value;
    return this;
  }

}
