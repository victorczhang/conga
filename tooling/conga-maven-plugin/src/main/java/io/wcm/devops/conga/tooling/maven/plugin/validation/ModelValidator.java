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
package io.wcm.devops.conga.tooling.maven.plugin.validation;

import org.apache.maven.plugin.MojoFailureException;

import io.wcm.devops.conga.model.reader.ModelReader;
import io.wcm.devops.conga.resource.Resource;

/**
 * Validates YAML model file against it's reader.
 * @param <T> Model class
 */
public final class ModelValidator<T> implements DefinitionValidator<T> {

  private final String modelName;
  private final ModelReader<T> modelReader;

  /**
   * @param modelName Model name (for log message)
   * @param modelReader Model reader implementation
   */
  public ModelValidator(String modelName, ModelReader<T> modelReader) {
    this.modelName = modelName;
    this.modelReader = modelReader;
  }

  @Override
  @SuppressWarnings("PMD.PreserveStackTrace")
  public T validate(Resource resource, String pathForLog) throws MojoFailureException {
    try {
      return modelReader.read(resource);
    }
    /*CHECKSTYLE:OFF*/ catch (Exception ex) { /*CHECKSTYLE:ON*/
      throw new MojoFailureException(modelName + " definition " + pathForLog + " is invalid:\n" + ex.getMessage());
    }
  }

}
