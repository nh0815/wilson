package com.github.nhirakawa.wilson.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@JsonSerialize
@Value.Style(
    get = {"is*", "get*"}, // Detect 'get' and 'is' prefixes in accessor methods
    init = "set*", // Builder initialization methods will have 'set' prefix
    typeAbstract = {"Abstract*", "*IF"}, // 'Abstract' prefix will be detected and trimmed
    typeImmutable = "*", // No prefix or suffix for generated immutable type
//    builder = "new", // construct builder using 'new' instead of factory method
    visibility = ImplementationVisibility.PUBLIC
)
public @interface WilsonStyle {
}