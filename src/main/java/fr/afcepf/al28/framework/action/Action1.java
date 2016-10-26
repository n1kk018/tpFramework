package fr.afcepf.al28.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al28.framework.api.IAction;

public class Action1 implements IAction {

    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        if(Math.random() > 0.5) {
            return "ok";
        }
        return "ko";
    }

}
