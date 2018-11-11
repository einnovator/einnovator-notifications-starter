package org.einnovator.notifications.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotificationSelectors.class)
public @interface NotificationSelector {
	SourceSelector source() default @SourceSelector;
	ActionSelector action() default @ActionSelector;
	String value() default "";
}
