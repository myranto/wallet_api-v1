package mg.projects.wallet.common.DtoConversion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class ConversionService{
    /*
     * first = l'objet de retour
     * second = l'objet à migrer dans first
     */
    public Object ObjectConversion(Class<?> first, Object second) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        @SuppressWarnings("deprecation")
        Object result= first.newInstance();
        Field[] validFields = fieldsValidConversion(first, second);
        Method[] FirstMethods = getAllMethods(first);
        Method[] SecondMethods = getAllMethods(second.getClass());
        Method[] setters = getPersonalMethod(validFields, FirstMethods, "set");
        Method[] getters = getPersonalMethod(validFields, SecondMethods, "get");
        Arrays.sort(getters, Comparator.comparing(Method::getName));
        Arrays.sort(setters, Comparator.comparing(Method::getName));
        for (int i = 0; i < getters.length; i++) {
            setters[i].invoke(result, getters[i].invoke(second));
        }
        return result;
    }
    private Method[] getPersonalMethod(Field[] fields, Method[] methods, String prefix){
        Method[] result = new Method[fields.length];
        int i=0;
        for (Field field : fields) {
            for (Method method : methods) {
                if (method.getName().toLowerCase().equals(prefix+field.getName().toLowerCase())) {
                        result[i] = method;
                        i++;
                }
            }
       }
       return result;
    }
    /*
     * getAll method
     */
    private Method[] getAllMethods(Class<?> objet){
        Method[] superCLass = objet.getSuperclass().getDeclaredMethods();
        Method[] son = objet.getDeclaredMethods();
        int count = superCLass.length+son.length;
        Method[] result = new Method[count];
        System.arraycopy(superCLass, 0, result, 0, superCLass.length);
        System.arraycopy(son, 0, result, superCLass.length, son.length);
        return result;
    }
/* maka field rehetra anaty class ray */
    private Field[] getAllFields(Class<?> classs){
        Field[] superCLass = classs.getSuperclass().getDeclaredFields();
        Field[] son = classs.getDeclaredFields();
        int count = superCLass.length+son.length;
        Field[] result = new Field[count];
        System.arraycopy(superCLass, 0, result, 0, superCLass.length);
        System.arraycopy(son, 0, result, superCLass.length, son.length);
        return result;
    }

    /*
     * 
     * maka anzay field valid alefa amle conversion
     */
    private Field[] fieldsValidConversion(Class<?> first, Object second){
        Field[] firstField = getAllFields(first);
        Field[] secondField = getAllFields(second.getClass());
        int count = countFieldValue(firstField);
        Field[] result = new Field[count];
        System.out.println(count);
        int i = 0;
        for (Field field : firstField) {
            for (Field raw : secondField) {
                if (field.getName().equals(raw.getName())) {
                    if (field.getAnnotation(InvalidConversion.class) != null)
                        continue;
                    result[i] = field;
                    i++;
                }
            }
        }
        return result;
    }
    /*
     * manisa field ho atao anle field vaovao
     */
    private int countFieldValue(Field[] fields){
        int i = 0;
        for (Field field : fields) {
            if (field.getAnnotation(InvalidConversion.class) != null) {
                i++;
            }
        }
        return fields.length-i;
    }
}
