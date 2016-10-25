package fr.afcepf.al28.framework.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Api action qui tiendra lieu de dispatcher dans notre framework.
 * @author stagiaire
 *
 */
public interface IAction {
    /**
     * request dispatcher.
     * @param request objet resuete
     * @param response objet reponse
     * @return String return if the response is ok or ko
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
