package biz.rageintegro.erealt.helpers;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ToStringHelper {
    /**
     * 
     * @param obj
     * @param excludes - name of fields to exclude, <b color="red">only in lower case</b>
     * @return
     */
    public static String getStringRepresentation(Object obj, String...excludes) {
        Set<String> excludesSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        excludesSet.addAll(Arrays.asList(excludes));
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");        

        builder.append(System.getProperty("line.separator", "\n"));
        builder.append(obj.getClass().getSimpleName());
        builder.append(" = [ ");

        @SuppressWarnings("rawtypes")
        Class aClass = obj.getClass();
        while (!"java.lang.Object".equals(aClass.getName())) {
            try {
                for (Field field : aClass.getDeclaredFields()) {
                    if(excludesSet.contains(field.getName())){
                        continue;
                    }
                    if (Modifier.isStatic(field.getModifiers())) {
                        continue;
                    }

                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), aClass);
                    Method method = propertyDescriptor.getReadMethod();
                    Object value = method.invoke(obj);

                    if (null == value) {
                        continue;
                    } else if (value.getClass().isArray()) {
                        int length = 0;
                        if (value instanceof Object[]) {
                            length =  ((Object[])value).length;
                        } else if (value instanceof int[]) {
                            length =  ((int[])value).length;
                        } else if (value instanceof long[]) {
                            length =  ((long[])value).length;
                        } else if (value instanceof float[]) {
                            length =  ((float[])value).length;
                        } else if (value instanceof double[]) {
                            length =  ((double[])value).length;
                        } else if (value instanceof boolean[]) {
                            length =  ((boolean[])value).length;
                        } else if (value instanceof byte[]) {
                            length =  ((byte[])value).length;
                        } else if (value instanceof short[]) {
                            length =  ((short[])value).length;
                        } else if (value instanceof char[]) {
                            length =  ((char[])value).length;
                        }
                        if (length == 0) {
                            continue;
                        }
                    } else if (value instanceof Collection) {
                        @SuppressWarnings("rawtypes")
                        Collection col = (Collection) value;
                        if (col.isEmpty()) {
                            continue;
                        }
                    } else if (value instanceof Map) {
                        @SuppressWarnings("rawtypes")
                        Map map = (Map) value;
                        if (map.isEmpty()) {
                            continue;
                        }
                    }
                    builder.append(field.getName());
                    builder.append(" = ");
                    if (value.getClass().equals(Date.class)) {
                        builder.append(defaultFormat.format(value));
                    } else {
                        builder.append(value);
                    }
                    builder.append("; ");
                }
            } catch (Exception e) {
                builder.append(e.getMessage());
            }

            aClass = aClass.getSuperclass();
        }

        builder.append("]");
        builder.append(System.getProperty("line.separator", "\n"));

        return builder.toString();
    }
}
