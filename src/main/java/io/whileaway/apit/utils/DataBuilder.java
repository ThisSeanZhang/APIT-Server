package io.whileaway.apit.utils;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataBuilder<A, B, C> {

    final private A arg;
    private B target;

    public DataBuilder(A arg) {
        this.arg = arg;
    }

    private DataBuilder(A arg, B target) {
        this.arg = arg;
        this.target = target;
    }

    public DataBuilder<A, B, C> inspectParam(Predicate<A> isNull) {
        if (isNull.test(this.arg)) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        return new DataBuilder<>(this.arg);
    }

    public DataBuilder<A, B, C> findInDB (Function<A, Optional<B>> findInDB) {
        Optional<B> primitive = findInDB.apply(this.arg);
        if (primitive.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new DataBuilder<>(this.arg, primitive.get());
    }

    public Result<C> convert(Function<B, C> convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, convert.apply(this.target));
    }
}
