package fr.afcepf.al28.framework.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import fr.afcepf.al28.framework.api.IAction;
import fr.afcepf.al28.framework.api.IActionForm;
/**
 * Factory of Action classes.
 * @author stagiaire
 *
 */
public class ActionFactory {
    /**
     * Current context of the instantiating servlet.
     */
    private ServletContext context;
    /**
     * Default constructor.
     * @param paramContext servlet context Parameter (manadatory)
     */
    public ActionFactory(ServletContext paramContext) {
        context = paramContext;
    }
    /**
     * return a mapping of url-pattern/action classes instances.
     * @return map
     */
    public Map<String, IAction> getActionsMap() {
        Map<String, IAction> retour = new HashMap<String, IAction>();
        try {
            Element racine = getXmlRoot();
            NodeList actions = racine.getElementsByTagName("action");
            for (int i = 0; i < actions.getLength(); i++) {
                retour.put(((Element) actions.item(i)).getElementsByTagName("url-pattern").item(0).getTextContent(),
                        (IAction) Class.forName(((Element) actions.item(i)).getElementsByTagName("action-name").item(0).getTextContent()).newInstance());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
        return retour;
    }
    /**
     * return a map of className/pages.
     * @return map
     */
    public Map<String, Map<String, String>> getReturnsPageMap() {
        Map<String, Map<String, String>> retour = new HashMap<String, Map<String, String>>();
        Element racine = getXmlRoot();
        NodeList actions = racine.getElementsByTagName("action");
        for (int i = 0; i < actions.getLength(); i++) {
            NodeList pages = ((Element) actions.item(i)).getElementsByTagName("retour").item(0).getChildNodes();
            Map<String, String> mapPages = new HashMap<String, String>();
            for (int j = 0; j < pages.getLength(); j++) {
                mapPages.put(pages.item(j).getNodeName(), pages.item(j).getTextContent());
            }
            retour.put(((Element) actions.item(i)).getElementsByTagName("action-name").item(0).getTextContent(),mapPages);
        }
        return retour;
    }
    
    /**
     * return a map of className/pages.
     * @return map
     */
    public Map<String, String> getFormsPageMap() {
        Map<String, String> retour = new HashMap<String, String>();
        Element racine = getXmlRoot();
        NodeList actions = racine.getElementsByTagName("action");
        for (int i = 0; i < actions.getLength(); i++) {
            if (((Element) actions.item(i)).getElementsByTagName("form-name").item(0) != null) {
                retour.put(((Element) actions.item(i)).getElementsByTagName("action-name").item(0).getTextContent(),
                        ((Element) actions.item(i)).getElementsByTagName("form-name").item(0).getTextContent());
            }
        }
        return retour;
    }
    /**
     * return a map of formName/formclasses.
     * @return map
     */
    public Map<String, IActionForm> getFormsMap() {
        Map<String, IActionForm> retour = new HashMap<String, IActionForm>();
        try {
            Element racine = getXmlRoot();
            NodeList forms = racine.getElementsByTagName("form");
            for (int i = 0; i < forms.getLength(); i++) {
                retour.put(((Element) forms.item(i)).getElementsByTagName("form-name").item(0).getTextContent(),
                        (IActionForm) Class.forName(((Element) forms.item(i)).getElementsByTagName("form-class").item(0).getTextContent()).newInstance());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException paramE) {
            paramE.printStackTrace();
        }
        return retour;
    }
    
    private Element getXmlRoot() {
        Document xml = null;
        try {
        InputStream stream = context.getResourceAsStream("/WEB-INF/routing.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        xml = dbf.newDocumentBuilder().parse(stream);
        } catch (SAXException | IOException | ParserConfigurationException paramE) {
            paramE.printStackTrace();
        }
        return xml.getDocumentElement();
    }
}
