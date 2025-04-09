package mg.projects.wallet.common.DtoConversion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.common.baseModel.DTO;

public class ConversionService{
    /*
     * first = l'objet de retour
     * second = l'objet à migrer dans first
     * on prend les methodes get de second
     * on prend les methodes set de first
     * on invoke les methode first dans result avec comme arguments, les valeur des methodes get de second
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
            Object result_get = getters[i].invoke(second);
            if(result_get instanceof DTO){
                DTO temp = (DTO) result_get;
                setters[i].invoke(result, temp.dtoToBaseEntity());
            }else if (result_get instanceof BaseEntity) {
                BaseEntity temp = (BaseEntity) result_get;
                setters[i].invoke(result, temp.EntityToDTO());
            }else
                setters[i].invoke(result, result_get);
        }
        return result;
    }
    /*
     * Function qui récupère les méthodes getters et setters selon la valeur de prefix
     */
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
     * Méthode qui récupère les méthodes de la classe mère et de la classe fille
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


    /*
     * 
     * Méthode qui récupère les fields valides pour la conversion dto <-> entity
     */
    private Field[] fieldsValidConversion(Class<?> first, Object second){
        Field[] firstField = getAllFields(first);
        Field[] secondField = getAllFields(second.getClass());
        int count = countFieldValue(firstField);
        Field[] result = new Field[count];
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
     * Méthode qui récupère les fields de la classe mère et de la classe fille
     */
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
     * Méthode qui compte les fields valides pour la conversion d'objet
     * il servira de size pour le tableau de liste de field valide
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
