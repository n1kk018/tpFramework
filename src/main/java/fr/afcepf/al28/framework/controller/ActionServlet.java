package fr.afcepf.al28.framework.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al28.framework.api.IAction;
import fr.afcepf.al28.framework.api.IActionForm;
import fr.afcepf.al28.framework.exception.FrameworkException;
import fr.afcepf.al28.framework.factory.ActionFactory;
import fr.afcepf.al28.framework.form.Form1;
import fr.afcepf.al28.framework.util.MyBeanPopulate;
/**
 * Front Controller of my framework.
 * @author stagiaire
 *
 */
@WebServlet("/test/*")
public class ActionServlet extends HttpServlet {
    /**
     * map of an action instance associated to an url.
     */
    private Map<String, IAction> actionsMap;
    /**
     * map associating an action classname to a page according to the return of the execute method.
     */
    private Map<String, Map<String, String>> returnPagesMap;
    /**
     * map associating an action classname to a form class.
     */
    private Map<String, String> formsPageMap;
    /**
     * map associating an action classname to a form class.
     */
    private Map<String, IActionForm> formsMap;
    /**
     * Init method execute only once at start.
     * @throws ServletException exception
     */
    public void init()
            throws ServletException {
            super.init();
            ActionFactory factory = new ActionFactory(getServletContext());
            actionsMap = factory.getActionsMap();
            returnPagesMap = factory.getReturnsPageMap();
            formsMap = factory.getFormsMap();
            formsPageMap = factory.getFormsPageMap();
      }

    /**
     * Serialization ID.
     */
    private static final long serialVersionUID = 57992836127294465L;
    /**
     * Générique get servlet method.
     * @param request request object
     * @param response response object
     * @throws ServletException exception
     * @throws IOException exception
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlPattern = request.getRequestURL().substring(request.getRequestURL().toString().lastIndexOf('/') + 1);
        if (urlPattern.contains(".perform")) {
            IAction action =  actionsMap.get(urlPattern);
            RequestDispatcher disp = request.getRequestDispatcher("/" + returnPagesMap.get(action.getClass().getName()).get(action.execute(request, response)));
            disp.forward(request, response);
        }
    }
    /**
     * Générique post servlet method.
     * @param request request object
     * @param response response object
     * @throws ServletException exception
     * @throws IOException exception
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlPattern = request.getRequestURL().substring(request.getRequestURL().toString().lastIndexOf('/') + 1);
        IAction action =  actionsMap.get(urlPattern);
        IActionForm form = null;
        try {
            if (formsPageMap.containsKey(action.getClass().getName()))  {
                form = formsMap.get(formsPageMap.get(action.getClass().getName()));
                form = MyBeanPopulate.populateBean(form, request.getParameterMap());
                RequestDispatcher disp = null;
                if (form.validateForm()) {
                    disp = request.getRequestDispatcher("/");
                    disp.forward(request, response);
                } else if(form.hasError()){
                    request.setAttribute("error_message", form.getError());
                }
            }
        } catch (FrameworkException paramE) {
            request.setAttribute("error_message", paramE.getMessage());
        }
        doGet(request, response);
    }
}
