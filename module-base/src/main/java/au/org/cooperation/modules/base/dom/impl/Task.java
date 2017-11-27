//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.10 at 09:30:22 PM AEST 
//

package au.org.cooperation.modules.base.dom.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.value.DateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Task", propOrder = { "name", "efforts" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Task {

	@XmlElement(required = true)
	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected String name;

	@XmlTransient
	@Column(allowsNull = "true", name = "goal_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	public Goal goal;

	@XmlTransient
	@Persistent
	@Join
	@Getter
	protected List<Person> persons;

	@Persistent(mappedBy = "task")
	@Order(column = "task_effort_idx")
	@Getter
	protected List<Effort> efforts;

	@Persistent(mappedBy = "task")
	@Order(column = "task_result_idx")
	@Getter
	protected List<Result> results;

	@Persistent(mappedBy = "task")
	@Order(column = "task_outcome_idx")
	@Getter
	protected List<Outcome> outcomes;

	Task() {
	}

	public Task(String name) {
		this.setName(name);
	}

	public Task(Goal goal, String name) {
		this.setGoal(goal);
		this.setName(name);
	}

	public String title() {
		return getName();
	}

	public Task addPerson(@ParameterLayout(named = "Person") Person person) {
		this.getPersons().add(person);
		person.getTasks().add(this);
		return this;
	}

	public List<Person> choices0AddPerson() {
		return personRepository.listAll();
	}

	public Task addEffort(@ParameterLayout(named = "Person") Person person, LocalDateTime start, LocalDateTime end) {
		Effort effort = taskRepository.createEffort(this, person, start, end);
		this.getEfforts().add(effort);
		person.getEfforts().add(effort);
		return this;
	}

	public List<Person> choices0AddEffort() {
		return this.getPersons();
	}

	public Task addResult(@ParameterLayout(named = "Result description") String description) {
		this.getResults().add(taskRepository.createResult(this, description));
		return this;
	}

	public Task addOutcome(@ParameterLayout(named = "Outcome description") String description,
			@ParameterLayout(named = "First result") Result result) {
		Outcome outcome = organisationRepository.createOutcome(this, description);
		this.getOutcomes().add(outcome);
		if (this.getGoal() != null) {
			this.getGoal().getOutcomes().add(outcome);
		}
		if(result != null){
			outcome.getResults().add(result);
			result.getOutcomes().add(outcome);
		}
		return this;
	}
	
	public List<Result> choices1AddOutcome(){
		//TODO has result been added?
		return this.getResults();
	}

	@XmlTransient
	@Inject
	TaskRepository taskRepository;

	@XmlTransient
	@Inject
	OrganisationRepository organisationRepository;

	@XmlTransient
	@Inject
	PersonRepository personRepository;
}
