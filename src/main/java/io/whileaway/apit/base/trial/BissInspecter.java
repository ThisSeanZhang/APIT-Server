package io.whileaway.apit.base.trial;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.function.Predicate;

public class BissInspecter<A ,B, C> {

    final private A arg;

    public BissInspecter(A arg) {
        this.arg = arg;
    }

    public BissFinder<A, B, C> inspect(Predicate<A> isNull) {
        if (isNull.test(this.arg)) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        return new BissFinder<A, B, C>(this.arg);
    }

}
