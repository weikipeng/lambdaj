// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.function.closure;

import java.util.*;

/**
 * A generic (not strongly typed) lambdaj closure
 * @author Mario Fusco
 */
public class Closure extends AbstractClosure {

    /**
     * Invokes this closure once by applying the given set of variables to it.
     * @param vars The set of variable used to invoke this closure once
     * @return The result of the closure invocation
     * @throws WrongClosureInvocationException if the number of the passed variables doesn't correspond to one
     * with which this closure has been defined
     */
	public Object apply(Object... vars) throws WrongClosureInvocationException {
		return closeOne(vars);
	}

    /**
     * Invokes this closure once for each passed variable.
     * It is then assumed that this closure has been defined with exactly one free variable
     * @param vars The set of variables used to invoke this closure once for each variable
     * @return A list of Object containing the results of each closure invocation
     * @throws WrongClosureInvocationException if this closure hasn't been defined with exactly one free variable
     */
	public List<?> each(Object... vars) throws WrongClosureInvocationException {
		return closeAll(vars);
	}

    /**
     * Invokes this closure once for each passed set of variables.
     * Each iterable is used as a different set of variables with which this closure is invoked
     * @param vars The variables used to invoke this closure once for each set of variables
     * @return A list of Object containing the results of each closure invocation
     * @throws WrongClosureInvocationException if the number of the passed variables doesn't correspond to one
     * with which this closure has been defined
     */
	public List<?> each(Iterable<?>... vars) throws WrongClosureInvocationException {
		return closeAll(vars);
	}

    /**
     * Curry this closure by fixing one of its free variable to a given value.
     * @param curry The value to which the free variable should be curry
     * @param position The 1-based position of the variable to which apply the curry operation
     * @return A Closure having a free variable less than this one since one of them has been fixed to the given value
     * @throws IllegalArgumentException if this closure doesn't have a free variable in the specified position
     */
	public Closure curry(Object curry, int position) throws IllegalArgumentException {
		return curry(new Closure(), curry, position);
	}
}
