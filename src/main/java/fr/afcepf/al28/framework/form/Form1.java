package fr.afcepf.al28.framework.form;

import fr.afcepf.al28.framework.api.IActionForm;

public class Form1 implements IActionForm{
    private String civility;
    private String firstname;
    private String lastname;
    /**
     * 
     */
    public Form1() {
        super();
    }
    


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



    @Override
    public Boolean validateForm() {
        return true;
    }

}
