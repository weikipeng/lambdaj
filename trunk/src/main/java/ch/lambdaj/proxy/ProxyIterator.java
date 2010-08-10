// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.proxy;

import static ch.lambdaj.proxy.ProxyUtil.*;
import ch.lambdaj.util.iterator.*;

import java.lang.reflect.*;
import java.util.*;

/**
 * Proxies a list of objects in order to seamlessly iterate on them by exposing the API of a single object.
 * @author Mario Fusco
 */
public class ProxyIterator<T> extends InvocationInterceptor implements Iterable<T> {

	private final ResettableIterator<? extends T> proxiedIterator;

    /**
     * Creates a proxy that wraps the given Iterator in order to seamlessly iterate on them by exposing the API of a single object
     * @param proxiedIterator The Iterator to be proxied
     */
	protected ProxyIterator(ResettableIterator<? extends T> proxiedIterator) {
        this.proxiedIterator = proxiedIterator;
	}

    /**
     * {@inheritDoc}
     */
    public Object invoke(Object obj, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
		if (method.getName().equals("iterator")) return iterator();
		return createProxyIterator(iterateOnValues(method, args), (Class<Object>)method.getReturnType());
	}

    /**
     * Invokes the given method with the given arguments on all the object in the iterator wrapped by this proxy
     * @param method The method to be invoked
     * @param args The arguments used to invoke the given method
     * @return An Iterator over the results on all the invoctions of the given method
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
	protected ResettableIterator<Object> iterateOnValues(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (method.getName().equals("finalize")) return null;
        method.setAccessible(true);
        proxiedIterator.reset();
        List<Object> list = new LinkedList<Object>();
        while (proxiedIterator.hasNext()) { list.add(method.invoke(proxiedIterator.next(), args)); }
		return new ResettableIteratorOnIterable(list);
	}

    /**
     * Creates a ProxyIterator of the given class that wraps the given Iterator
     * @param proxiedIterator The Iterator to be proxied
     * @param clazz The class dinamically implemented by the newly created proxy
     * @return The newly created proxy
     */
	public static <T> T createProxyIterator(ResettableIterator<? extends T> proxiedIterator, Class<T> clazz) {
		return createIterableProxy(new ProxyIterator<T>(proxiedIterator), clazz);
	}

    /**
     * Creates a ProxyIterator of the same class of the given item that wraps the given Iterator
     * @param proxiedIterator The Iterator to be proxied
     * @param firstItem An instance of the class dinamically implemented by the newly created proxy
     * @return The newly created proxy
     */
    public static <T> T createProxyIterator(ResettableIterator<? extends T> proxiedIterator, T firstItem) {
        T proxy = createProxyIterator(proxiedIterator, (Class<T>)firstItem.getClass());
        proxiedIterator.reset();
        return proxy;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return (Iterator<T>)proxiedIterator;
	}
}
