package com.dragovorn.gamecraft.annotation;

import com.dragovorn.gamecraft.command.CommandExecutor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CraftCommand {
    boolean child() default false;

    String value();
    String[] aliases() default { };

    Class<? extends CommandExecutor>[] children() default { };
}