/*
 *
 *  Copyright 2017 Alexander Stephen Cameron
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
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
package au.org.cooperation.modules.base.dom.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.joda.time.DateTime;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Task.class
)
public class TaskRepository {

    public List<Task> listAllTasks() {
        return repositoryService.allInstances(Task.class);
    }

    public List<Effort> listAllEfforts() {
        return repositoryService.allInstances(Effort.class);
    }
    
    /*public List<Task> findByName(final String name) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Task.class,
                        "findByName",
                        "name", name));
    }*/

	public Task createTask(final Organisation organisation, final String name) {
        final Task object = new Task(organisation, name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}

	public Task createSubTask(Task task, String name) {
        final Task object = new Task(task, name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}	
	
	public Task createTask(final Goal goal, final String name) {
        final Task object = new Task(goal.getOrganisation(), goal, name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
	
	public Effort createEffort(Task task, Person person, DateTime start, DateTime end) {
        final Effort object = new Effort(task.getOrganisation(),task, person, start, end);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
	
	public Result createResult(Task task, String description) {
        final Result object = new Result(task.getOrganisation(), task, description);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;



}
