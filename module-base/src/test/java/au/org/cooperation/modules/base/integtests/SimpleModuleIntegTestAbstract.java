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
package au.org.cooperation.modules.base.integtests;

import org.junit.BeforeClass;

import au.org.cooperation.modules.base.dom.CooperationBaseModuleDomSubmodule;

import org.apache.isis.applib.AppManifestAbstract.Builder;
import org.apache.isis.core.integtestsupport.IntegrationTestAbstract2;

public abstract class SimpleModuleIntegTestAbstract extends IntegrationTestAbstract2 {

	public static final Builder BUILDER = Builder.forModules(
			CooperationBaseModuleDomSubmodule.class,
            org.isisaddons.module.security.SecurityModule.class)
		.withConfigurationProperty("isis.objects.editing", "true")
		//.withConfigurationProperty("au.com.vuse.onecloud.fixtures.companyinputfile",
		//		"C:\\Users\\stevec\\eclipse\\neon3\\workspace\\OneId-temp\\doc\\onecloud.xml")
		//.withConfigurationProperty("au.com.vuse.onecloud.fixtures.invitesinputfile",
		//		"C:\\Users\\stevec\\eclipse\\neon3\\workspace\\OneId-temp\\doc\\invites.xml")
		.withConfigurationProperty("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionDriverName",
				"com.mysql.jdbc.Driver")
		.withConfigurationProperty("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionURL",
				"jdbc:mysql://localhost:3306/cooperation?zeroDateTimeBehavior=convertToNull")
		.withConfigurationProperty("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionUserName",
				"cooperation")
		.withConfigurationProperty("isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionPassword",
				"password");

	@BeforeClass
	public static void initSystem() {
		bootstrapUsing(BUILDER);
	}

}
