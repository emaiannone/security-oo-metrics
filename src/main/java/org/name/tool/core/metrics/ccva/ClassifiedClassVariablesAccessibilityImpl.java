package org.name.tool.core.metrics.ccva;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import org.name.tool.core.metrics.ca.ClassifiedAttributes;
import org.name.tool.core.results.ClassifiedAnalyzerResults;
import org.name.tool.core.results.SecurityMetricValue;

import java.util.Set;

public class ClassifiedClassVariablesAccessibilityImpl extends ClassifiedClassVariablesAccessibility {
    private final ClassifiedAttributes ca;

    public ClassifiedClassVariablesAccessibilityImpl(ClassifiedAttributes ca) {
        this.ca = ca;
    }

    @Override
    public SecurityMetricValue compute(ClassifiedAnalyzerResults classResults) {
        int nonPrivateStatic = 0;
        Set<VariableDeclarator> classifiedAttributes = classResults.getClassifiedAttributes();
        for (VariableDeclarator classifiedAttribute : classifiedAttributes) {
            FieldDeclaration fieldDecl = classifiedAttribute.resolve().asField().toAst().orElse(null);
            if (fieldDecl != null) {
                if (!fieldDecl.isPrivate() && fieldDecl.isStatic()) {
                    nonPrivateStatic++;
                }
            }
        }
        double caValue = ca.compute(classResults).getValue();
        double value = caValue != 0.0 ? nonPrivateStatic / caValue : 0.0;
        return new SecurityMetricValue(getName(), getCode(), value);
    }
}
