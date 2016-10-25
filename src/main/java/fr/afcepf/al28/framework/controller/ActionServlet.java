package fr.afcepf.al28.framework.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

import fr.afcepf.al28.framework.api.IAction;
import fr.afcepf.al28.framework.factory.ActionFactory;
/**
 * Front Controller of my framework.
 * @author stagiaire
 *
 */
@WebServlet("/test/*")
public class ActionServlet extends HttpServlet {

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 57992836127294465L;
    
    //private static Logger log = Logger.getLogger(ActionServlet.class);
    /**
     * Générique get servlet method.
     * @param request request object
     * @param response response object
     * @throws ServletException exception
     * @throws IOException exception
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    /**
     * Générique post servlet method.
     * @param request request object
     * @param response response object
     * @throws ServletException exception
     * @throws IOException exception
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String strAction = request.getRequestURL().substring(request.getRequestURL().toString().lastIndexOf('/') + 1);
        if (strAction.contains(".perform")) {
            ActionFactory factory = new ActionFactory();
            IAction action =  factory.getAction(strAction.substring(0, strAction.lastIndexOf(".")));
            RequestDispatcher disp = request.getRequestDispatcher("/" + action.execute(request, response));
            disp.forward(request, response);
        }
    }
}
