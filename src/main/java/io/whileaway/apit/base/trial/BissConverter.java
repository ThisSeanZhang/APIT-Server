package io.whileaway.apit.base.trial;

import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.function.Function;

public class BissConverter<A, B> {

    final private A origin;

    public BissConverter(A origin) {
        this.origin = origin;
    }

    public Result<B> convert(Function<A, B> convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, convert.apply(this.origin));
    }
}
