package io.whileaway.apit.base.trial;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Biss<A, B, C> {
    final private A arg;
    private B target;

    public Biss(A arg) {
        this.arg = arg;
    }

    private Biss(A arg, B target) {
        this.arg = arg;
        this.target = target;
    }

    public Biss<A, B, C> inspectParam(Predicate<A> isNull) {
        if (isNull.test(this.arg)) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        return new Biss<>(this.arg);
    }

    public Biss<A, B, C>  findInDB (Function<A, Optional<B>> findInDB) {
        Optional<B> targetOP = findInDB.apply(this.arg);
        if (targetOP.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new Biss<>(this.arg, targetOP.get());
    }

    public Result<C> convert(Function<B, C> convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, convert.apply(this.target));
    }
}
