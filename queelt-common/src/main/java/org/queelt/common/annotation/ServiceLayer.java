package org.queelt.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A stereotype annotation to represent a class that belongs to the Application
 * Service Layer.
 * <p/>
 * Application Layer [his name for Service Layer]: Defines the
 * jobs the software is supposed to do and directs the expressive domain objects
 * to work out problems. The tasks this layer is responsible for are meaningful
 * to the business or necessary for interaction with the application layers of
 * other systems. This layer is kept thin. It does not contain business rules or
 * knowledge, but only coordinates tasks and delegates work to collaborations of
 * domain objects in the next layer down. It does not have state reflecting the
 * business situation, but it can have state that reflects the progress of a
 * task for the user or the program.
 * <p/>
 * From P of EAA {@link http://martinfowler.com/eaaCatalog/serviceLayer.html}.
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional
@Service
@Inherited
public @interface ServiceLayer {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested component name, if any
     */
    String value() default "";

}
