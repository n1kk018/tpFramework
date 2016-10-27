package fr.afcepf.al28.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al28.framework.api.IAction;

public class Action1 implements IAction {
    private String errorField="";
    /**
     * @return the errorField
     */
    public String getErrorField() {
        return errorField;
    }



    /**
     * @param paramErrorField the errorField to set
     */
    public void setErrorField(String paramErrorField) {
        errorField = paramErrorField;
    }

    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        /*if(Math.random() > 0.5) {
            return "ok";
        }
        return "ko";*/
        return "ok";
    }

}
