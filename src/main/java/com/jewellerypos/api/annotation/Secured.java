package com.jewellerypos.api.annotation;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

import com.jewellerypos.api.util.Role;

/** 
 * @author Viswanath
 * Custom annotation to secure methods using Basic auth security
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})//use at method level only
public @interface Secured {
    Role[] value() default {};
}
