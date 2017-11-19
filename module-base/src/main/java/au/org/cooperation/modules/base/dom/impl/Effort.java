package au.org.cooperation.modules.base.dom.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Effort", propOrder = { "result", "start", "end" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Effort {

	@XmlElement()
	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Result result;

	@XmlTransient
	@Column(allowsNull = "false")
	@Getter
	@Setter
	private Person person;

	@XmlTransient
	@Column(allowsNull = "true")
	@Getter
	@Setter
	private Task task;

	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected Date start;

	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected Date end;

	Effort() {
	}

	public Effort(Task task, Person person, Date start, Date end) {
		setPerson(person);
		setTask(task);
		setStart(start);
		setEnd(end);
	}

	public Effort assignToResult(Result result) {
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	public String disableAssignToResult(){
		if(this.getTask() == null || this.getTask().getResults().size() == 0)
			return "No parent Task or Results associated with the parent Task";
		else
			return null;
	}
	
	public List<Result> choices0AssignToResult(){
		if(this.getTask() != null && this.getTask().getResults().size() > 0)
			return this.getTask().getResults();
		else
			return null;		
	}

	public Effort addResult(@ParameterLayout(named = "Result description") String description) {
		Result result = taskRepository.createResult(this.getTask(), description);
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	@XmlTransient
	@Inject
	TaskRepository taskRepository;

}
