package io.whileaway.apit.utils;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class DatasBuilder<A, B, C> {

    final private A arg;
    private List<B> target;

    public DatasBuilder(A arg) {
        this.arg = arg;
    }

    private DatasBuilder(A arg, List<B> target) {
        this.arg = arg;
        this.target = target;
    }

    public DatasBuilder<A, B, C> inspectParam(Predicate<A> isNull) {
        if (isNull.test(this.arg)) {
            throw new CommonException(ControllerEnum.PARAMETER_ERROR);
        }
        return new DatasBuilder<>(this.arg);
    }

    public DatasBuilder<A, B, C> findInDB (Function<A, Optional<List<B>>> findInDB) {
        Optional<List<B>> targetOP = findInDB.apply(this.arg);
        if (targetOP.isEmpty() || targetOP.get().isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new DatasBuilder<>(this.arg, targetOP.get());
    }

    public Result<List<C>> convert(Function<List<B>, List<C> > convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, convert.apply(this.target));
    }

    public Result<List<B>> doNothing() {
        return ResultUtil.success(ControllerEnum.SUCCESS, this.target);
    }

    public List<B> original () { return this.target; }
}
