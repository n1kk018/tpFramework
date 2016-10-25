package fr.afcepf.al28.framework.factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fr.afcepf.al28.framework.action.Action1;
import fr.afcepf.al28.framework.action.Action2;
import fr.afcepf.al28.framework.api.IAction;
/**
 * Factory of Action classes.
 * @author stagiaire
 *
 */
public class ActionFactory {
    /**
     * return the current action associated to an url.
     * @param path url path
     * @return IAction action Object
     */
    private Map<String, IAction> dipsatchConfig;
    /**
     * Default constructor.
     */
    public ActionFactory() {
    }
    /**
     * url dispatcher.
     * @param urlPattern dispattching part of the url
     * @return action class
     */
    public IAction getAction(String classe) {
        Class<?> type;
        IAction action=null;
        try {
            type = Class.forName(classe);
            action = (IAction)type.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
        return action;
    }
}
