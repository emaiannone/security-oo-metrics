package org.surface.surface.core.metrics.projectlevel.ccr;

import org.surface.surface.core.metrics.projectlevel.cc.CC;
import org.surface.surface.results.ProjectAnalyzerResults;
import org.surface.surface.results.values.DoubleMetricValue;

public class CCRImpl extends CCR {
    private final CC cc;

    public CCRImpl(CC cc) {
        this.cc = cc;
    }

    @Override
    public DoubleMetricValue compute(ProjectAnalyzerResults projectResults) {
        int numClasses = projectResults.getResults().size();
        int ccValue = cc.compute(projectResults).getValue();
        double value = numClasses != 0.0 ? (double) ccValue / numClasses : 0.0;
        return new DoubleMetricValue(getName(), getCode(), value);
    }
}
