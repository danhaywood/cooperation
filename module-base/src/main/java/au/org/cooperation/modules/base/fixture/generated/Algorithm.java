//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.07 at 08:19:45 PM AEDT 
//


package au.org.cooperation.modules.base.fixture.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Algorithm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Algorithm"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="effort" type="{http://au.org.cooperation/base}Effort" maxOccurs="unbounded"/&gt;
 *         &lt;element name="outcome" type="{http://au.org.cooperation/base}Outcome" maxOccurs="unbounded"/&gt;
 *         &lt;element name="reward" type="{http://au.org.cooperation/base}Reward" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Algorithm", propOrder = {
    "name",
    "effort",
    "outcome",
    "reward"
})
public class Algorithm {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected List<Effort> effort;
    @XmlElement(required = true)
    protected List<Outcome> outcome;
    @XmlElement(required = true)
    protected List<Reward> reward;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the effort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the effort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEffort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Effort }
     * 
     * 
     */
    public List<Effort> getEffort() {
        if (effort == null) {
            effort = new ArrayList<Effort>();
        }
        return this.effort;
    }

    /**
     * Gets the value of the outcome property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outcome property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutcome().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Outcome }
     * 
     * 
     */
    public List<Outcome> getOutcome() {
        if (outcome == null) {
            outcome = new ArrayList<Outcome>();
        }
        return this.outcome;
    }

    /**
     * Gets the value of the reward property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reward property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReward().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reward }
     * 
     * 
     */
    public List<Reward> getReward() {
        if (reward == null) {
            reward = new ArrayList<Reward>();
        }
        return this.reward;
    }

}
