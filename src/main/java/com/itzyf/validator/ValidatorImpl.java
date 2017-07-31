/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.itzyf.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author tianshuo
 * @version Id: ValidatorImpl.java, v 0.1 2016/7/28 0028 9:50 tianshuo Exp $$
 */
public class ValidatorImpl implements IValidator{

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator;

    public ValidatorImpl() {
    }

    public void validateMutil(Object obj) throws ValidationException {
        Set constraintViolations = validator.validate(obj, new Class[0]);
        ArrayList errList = new ArrayList();
        if(constraintViolations != null && constraintViolations.size() != 0) {
            Iterator i$ = constraintViolations.iterator();

            while(i$.hasNext()) {
                ConstraintViolation error = (ConstraintViolation)i$.next();
                String errorMsg = error.getPropertyPath() + ":" + error.getMessage();
                errList.add(errorMsg);
            }

            throw new ValidationException(errList);
        }
    }

    public void validate(Object obj) throws ValidationException {
        Set constraintViolations = validator.validate(obj, new Class[0]);
        String errorMsg = null;
        if(constraintViolations != null && constraintViolations.size() != 0) {
            ConstraintViolation error = (ConstraintViolation)constraintViolations.iterator().next();
            errorMsg = error.getPropertyPath() + ":" + error.getMessage();
            throw new ValidationException(errorMsg);
        }
    }

    static {
        validator = factory.getValidator();
    }
}