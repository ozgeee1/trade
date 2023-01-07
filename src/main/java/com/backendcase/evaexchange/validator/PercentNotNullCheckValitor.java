package com.backendcase.evaexchange.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PercentNotNullCheckValitor implements ConstraintValidator<PercentNotNullCheck, BigDecimal> {
    @Override
    public void initialize(PercentNotNullCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal!=null && (!BigDecimal.ZERO.equals(bigDecimal));
    }
}
