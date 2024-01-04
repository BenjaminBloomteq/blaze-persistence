/*
 * Copyright 2014 - 2024 Blazebit.
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

package com.blazebit.persistence.view.testsuite.correlation.batchsubselect.model;

import com.blazebit.persistence.SubqueryInitiator;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.Mapping;
import com.blazebit.persistence.view.MappingSubquery;
import com.blazebit.persistence.view.SubqueryProvider;
import com.blazebit.persistence.view.testsuite.entity.LegacyOrder;
import com.blazebit.persistence.view.testsuite.entity.LegacyOrderPositionElement;

/**
 *
 * @author Christian Beikov
 * @since 1.6.9
 */
@EntityView(LegacyOrderPositionElement.class)
public interface LegacyOrderPositionElementView extends IdHolderView<Long> {
    public String getText();

    @MappingSubquery(MySubqueryProvider.class)
    public Long getSubquery();

    class MySubqueryProvider implements SubqueryProvider {
        @Override
        public <T> T createSubquery(SubqueryInitiator<T> subqueryInitiator) {
            return subqueryInitiator.from(LegacyOrder.class, "lo")
                .where("lo.id").eqExpression("EMBEDDING_VIEW(orderId)")
                .select("id")
                .end();
        }
    }
}
