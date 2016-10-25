package fr.afcepf.al28.framework.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al28.framework.api.IAction;

public class Action2 implements IAction{

    @Override
    public String execute(HttpServletRequest paramRequest, HttpServletResponse paramResponse) {
        return "toto.jsp";
    }

}
