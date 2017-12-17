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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;

import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
@XmlJavaTypeAdapter(org.apache.isis.schema.utils.jaxbadapters.PersistentEntityAdapter.class)
public class Organisation {

	@Column(allowsNull = "false", length=50)
	@Getter
	@Setter
	protected String name;

	@Column(allowsNull = "true", length=1000)
	@Getter
	@Setter
	protected String description;

	@Persistent(mappedBy = "organisation", column = "aim_id")
	@Order(column = "org_aim_idx")
	@Getter
	protected List<Aim> aims;

	@Persistent(mappedBy = "organisation", column = "goal_id")
	@Order(column = "org_goal_idx")
	@Getter
	protected List<Goal> goals;

	@Persistent(mappedBy = "organisation")
	@Getter(value = AccessLevel.PRIVATE)
	protected List<OrganisationPerson> persons;

	@Persistent(mappedBy = "organisation", column = "plan_id")
	@Order(column = "org_plan_idx")
	@Getter
	protected List<Plan> plans;

	public Organisation() {
	}

	public Organisation(String name) {
		setName(name);
	}

	public String title() {
		return getName();
	}

	public Organisation addAim(@ParameterLayout(named = "Aim name") String name) {
		this.getAims().add(organisationRepository.createAim(this, name));
		return this;
	}

	public Organisation addGoal(@ParameterLayout(named = "Goal name") String name,
			@ParameterLayout(named = "Primary Aim") Aim aim) {
		this.getGoals().add(organisationRepository.createGoal(this, name, aim));
		return this;
	}

	public List<Aim> choices1AddGoal() {
		return this.getAims();
	}

	public String disableAddGoal() {
		if (this.getAims().size() == 0)
			return "A Goal must be linked to at least one Aim, so add an Aim first";
		else
			return null;
	}

	public Organisation addPlan(@ParameterLayout(named = "Plan name") String name) {
		this.getPlans().add(organisationRepository.createPlan(this, name));
		return this;
	}

	@Programmatic
	public Organisation addPerson(Person person) {
		this.getPersons()
				.add(organisationRepository.createOrganisationPerson(this, person, OrganisationPersonStatus.ACTIVE));
		return this;
	}
	
	public Integer getActiveLinkedPersonCount() {
		return this.listActiveOrganisationPersons().size();
	}

	public OrganisationPersons showLinkedPersons() {
		return new OrganisationPersons(this);
	}
	
	@Programmatic
	public boolean hasLinkedPerson(Person person) {
		boolean found = false;
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getPerson().equals(person)) {
				found = true;
				break;
			}
		}
		return found;
	}

	@Programmatic
	public List<Person> listActivePersons() {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(op.getPerson());
			}
		}
		return temp;
	}
	
	@Programmatic
	public List<PersonView> listActivePersonViews() {
		List<PersonView> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(new PersonView(op.getPerson()));
			}
		}
		return temp;
	}
	
	
	
	@Programmatic
	List<OrganisationPerson> listActiveOrganisationPersons() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(op);
			}
		}
		return temp;
	}
	
	@Programmatic
	public List<Person> listInactivePersons() {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(op.getPerson());
			}
		}
		return temp;
	}
	
	@Programmatic
	public List<PersonView> listInactivePersonViews() {
		List<PersonView> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(new PersonView(op.getPerson()));
			}
		}
		return temp;
	}
	
	@Programmatic
	public OrganisationPersonStatus linkedPersonStatus(Person person) {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getPerson().equals(person)) {
				return op.getStatus();
			}
		}
		return null;
	}
	
	@Programmatic
	public List<OrganisationPerson> listInactiveOrganisationPersons() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(op);
			}
		}
		return temp;
	}
	
	@Inject
	OrganisationRepository organisationRepository;

	@Inject
	PersonRepository personRepository;


}
