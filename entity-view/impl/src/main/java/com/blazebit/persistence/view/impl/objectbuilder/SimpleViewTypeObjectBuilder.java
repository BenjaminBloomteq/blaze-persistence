/*
 * Copyright 2014 Blazebit.
 *
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
 */

package com.blazebit.persistence.view.impl.objectbuilder;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cpbec
 */
public class SimpleViewTypeObjectBuilder<T> extends AbstractViewTypeObjectBuilder<T> {
    
    public SimpleViewTypeObjectBuilder(ViewTypeObjectBuilderTemplate<T> template) {
        super(template);
        
        if (template.hasParameters()) {
            throw new IllegalArgumentException("No templates with parameters allowed for this object builder!");
        }
    }

    @Override
    public T build(Object[] tuple, String[] aliases) {
        try {
            return proxyConstructor.newInstance(tuple);
        } catch (Exception ex) {
            throw new RuntimeException("Could not invoke the proxy constructor '" + proxyConstructor + "' with the given tuple: " + Arrays.toString(tuple), ex);
        }
    }

    @Override
    public List<T> buildList(List<T> list) {
        return list;
    }
}