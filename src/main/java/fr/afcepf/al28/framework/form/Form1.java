package fr.afcepf.al28.framework.form;

import java.io.Serializable;

import fr.afcepf.al28.framework.api.IActionForm;
import fr.afcepf.al28.framework.exception.FrameworkErrorCode;
import fr.afcepf.al28.framework.exception.FrameworkException;

public class Form1 implements IActionForm, Serializable {
    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 1L;
    private String civility="";
    private String firstname="";
    private String lastname="";
    private Integer age=0;
    private String error_field="";
    
    /**
     * @return the civility
     */
    public String getCivility() {
        return civility;
    }



    /**
     * @param paramCivility the civility to set
     */
    public void setCivility(String paramCivility) {
        civility = paramCivility;
    }



    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }



    /**
     * @param paramFirstname the firstname to set
     */
    public void setFirstname(String paramFirstname) {
        firstname = paramFirstname;
    }



    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }



    /**
     * @param paramLastname the lastname to set
     */
    public void setLastname(String paramLastname) {
        lastname = paramLastname;
    }
    
     /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @param paramAge the age to set
     */
    public void setAge(Integer paramAge) {
        age = paramAge;
    }
    @Override
    public Boolean hasError() {
        return error_field != "";
    }
    @Override
    public String getError() {
        return error_field;
    }
    @Override
    public Boolean validateForm() {
        if (age < 18) {
            error_field = "Inscription interdite aux mineurs!";
            return false;
        }
        return true;
    }

}
