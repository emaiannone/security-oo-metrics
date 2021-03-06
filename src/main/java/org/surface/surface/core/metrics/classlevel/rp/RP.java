package org.surface.surface.core.metrics.classlevel.rp;

import org.surface.surface.core.metrics.api.ClassMetric;
import org.surface.surface.results.values.BooleanMetricValue;

public abstract class RP extends ClassMetric<BooleanMetricValue> {
    public static final String NAME = "Reflection Package";
    public static final String CODE = "RP";

    protected RP() {
        setName(NAME);
        setCode(CODE);
    }
}
