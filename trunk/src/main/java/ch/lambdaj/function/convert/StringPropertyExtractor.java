// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.function.convert;


/**
 * Converts an Object in the String representation of the value it has on the name property.
 * @author Mario Fusco
 */
public class StringPropertyExtractor<T> extends PropertyExtractor<T, String> implements StringConverter<T> {
	
	public StringPropertyExtractor(String propertyName) {
		super(propertyName); 
	}

	@Override
	public String convert(T from) {
		Object converted = super.convert(from); 
		return converted == null ? "" : converted.toString();
	}
}
