package fr.afcepf.al28.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import fr.afcepf.al28.framework.api.IActionForm;

public class MyBeanPopulate {
    public static IActionForm populateBean(IActionForm form, Map<String,String[]> params) {
        Class<?> c = form.getClass();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            try {
                Method getter = c.getMethod("get"+ entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1).toLowerCase());
                Class<?> t = getter.getReturnType();
                Method setter = c.getMethod("set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1).toLowerCase(),
                           new Class[] {t});
               if (entry.getValue().length == 1) {
                   setter.invoke(form, new Object[] {entry.getValue()[0]});
               }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException paramE) {
                paramE.printStackTrace();
            }
        }
        return form;
    }
    
    private Object castParameter(Class<?> t,String param) {
        Object o = null;
        if(t.)
        return 
    }
}
