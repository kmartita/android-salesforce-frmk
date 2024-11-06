package io.omni.example.tools.api.salesforce;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation @Custom should be added to:
 * - record types;
 * - fields;
 * that were created by admin user and do not have 'OCE__' prefix
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Custom {
}
