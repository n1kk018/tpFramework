package fr.afcepf.al28.framework.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
            InputStream stream = getServletContext().getResourceAsStream("/WEB-INF/routing.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document xml = null;
            try {
                xml = dbf.newDocumentBuilder().parse(stream);
                NodeList actions = xml.getElementsByTagName("action");
                String className = "";
                System.out.println(className);
                for (int i = 0; i < actions.getLength(); i++) {
                    if (actions.item(i).getChildNodes().item(1).getTextContent().equals(strAction)) {
                        className = actions.item(i).getChildNodes().item(0).getTextContent();
                    }
                }
                System.out.println(className);
                if (className != "") {
                    System.out.println(className);
                    ActionFactory factory = new ActionFactory();
                    IAction action =  factory.getAction(className);
                    RequestDispatcher disp = request.getRequestDispatcher("/" + action.execute(request, response));
                    disp.forward(request, response);
                }
            } catch (SAXException | IOException | ParserConfigurationException paramE) {
                // TODO Auto-generated catch block
                paramE.printStackTrace();
            }
        }
    }
}
