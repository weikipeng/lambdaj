// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.function.aggregate;

/**
 * An aggregator that sums longs
 * @author Mario Fusco
 */
public class SumLong extends InitializedPairAggregator<Long> {

    public SumLong(Long firstItem) {
        super(firstItem);
    }

    /**
     * Aggregates two longs by summing them
     * @param first The first long to be summed
     * @param second The second long to be summed
     * @return The sum of the two longs
     */
	public Long aggregate(Long first, Long second) {
		return first + second;
	}
}
