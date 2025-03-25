package mg.projects.wallet.common.DtoConversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// anotation destiné aux field pour dire que l'on ne prend pas compte de ce dernier pour la conversion
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidConversion {

}
