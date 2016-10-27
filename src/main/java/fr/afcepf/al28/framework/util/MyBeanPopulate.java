package fr.afcepf.al28.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import fr.afcepf.al28.framework.api.IActionForm;
import fr.afcepf.al28.framework.exception.FrameworkErrorCode;
import fr.afcepf.al28.framework.exception.FrameworkException;

public class MyBeanPopulate {
    public static IActionForm populateBean(IActionForm form, Map<String,String[]> params) throws FrameworkException {
        Class<?> c = form.getClass();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            try {
                Method getter = c.getMethod("get" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1).toLowerCase());
                Class<?> t = getter.getReturnType();
                Method setter = c.getMethod("set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1).toLowerCase(),
                           new Class[] {t});
               if (entry.getValue().length == 1) {
                   setter.invoke(form, new Object[] {castType(t, entry.getValue()[0])});
               }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException paramE) {
                paramE.printStackTrace();
            }
        }
        return form;
    }
    
    private static Object castType(Class<?> paramT, String paramString) throws FrameworkException {
        Object o = null;
        try {
            if (paramT.getCanonicalName().equals("java.lang.String"))
                o = paramString;
            if (paramT.getCanonicalName().equals("java.lang.Integer"))
                o = Integer.parseInt(paramString);
            if (paramT.getCanonicalName().equals("java.lang.Double"))
                o = Double.parseDouble(paramString);
            if (paramT.getCanonicalName().equals("java.lang.Boolean"))
                o = Boolean.parseBoolean(paramString);
        } catch (Exception e) {
            throw new FrameworkException(FrameworkErrorCode.INVALID_TYPE_ERROR, "Type de champs incorrect.");
        }
        return o;
    }
}
