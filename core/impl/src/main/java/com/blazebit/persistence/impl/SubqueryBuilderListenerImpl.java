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
package com.blazebit.persistence.impl;

import com.blazebit.persistence.SubqueryInitiator;
import com.blazebit.persistence.impl.BuilderChainingException;

/**
 *
 * @author Christian Beikov
 * @author Moritz Becker
 * @since 1.0
 */
public class SubqueryBuilderListenerImpl<T> implements SubqueryBuilderListener<T> {

    private SubqueryInitiator<?> currentSubqueryInitiator;
    private SubqueryBuilderImpl<?> currentSubqueryBuilder;

    public void verifySubqueryBuilderEnded() {
        if (currentSubqueryInitiator != null) {
            throw new BuilderChainingException("An initiator was not ended properly.");
        }
        if (currentSubqueryBuilder != null) {
            throw new BuilderChainingException("A builder was not ended properly.");
        }
    }

    @Override
    public void onBuilderEnded(SubqueryBuilderImpl<T> builder) {
        if (currentSubqueryBuilder == null) {
            throw new BuilderChainingException("There was an attempt to end a builder that was not started or already closed.");
        }
        currentSubqueryBuilder = null;
    }

    @Override
    public void onBuilderStarted(SubqueryBuilderImpl<T> builder) {
        if (currentSubqueryBuilder != null) {
            throw new BuilderChainingException("There was an attempt to start a builder but a previous builder was not ended.");
        }

        currentSubqueryInitiator = null;
        currentSubqueryBuilder = builder;
    }

	@Override
	public void onInitiatorStarted(SubqueryInitiator<?> initiator) {
		if (currentSubqueryInitiator != null) {
            throw new BuilderChainingException("There was an attempt to start an initiator but a previous initiator was not ended.");
        }
        if (currentSubqueryBuilder != null) {
            throw new BuilderChainingException("There was an attempt to start a builder but a previous builder was not ended.");
        }

        currentSubqueryInitiator = initiator;		
	}
}
