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
package com.blazebit.persistence.examples.quarkus.testsuite.postgresql;

import com.blazebit.persistence.examples.quarkus.testsuite.base.AbstractQuarkusExampleTest;
import io.quarkus.test.junit.NativeImageTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

/**
 * @author Moritz Becker
 * @since 1.5.0
 */
@NativeImageTest
public class QuarkusExampleIT extends AbstractQuarkusExampleTest {
}
