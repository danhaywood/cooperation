/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package au.org.cooperation.modules.base;

import org.apache.isis.applib.AppManifestAbstract;

import au.org.cooperation.modules.base.dom.CooperationBaseModuleDomSubmodule;
import au.org.cooperation.modules.base.fixture.SimpleModuleFixtureSubmodule;

/**
 * Used by <code>isis-maven-plugin</code> (build-time validation of the module) and also by module-level integration tests.
 */
public class SimpleModuleManifest extends AppManifestAbstract {

    public static final Builder BUILDER = Builder.forModules(
            CooperationBaseModuleDomSubmodule.class,
            SimpleModuleFixtureSubmodule.class
    );

    public SimpleModuleManifest() {
        super(BUILDER);
    }


}
