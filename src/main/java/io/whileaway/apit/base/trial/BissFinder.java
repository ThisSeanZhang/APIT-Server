package io.whileaway.apit.base.trial;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.enums.ControllerEnum;

import java.util.Optional;
import java.util.function.Function;

public class BissFinder<A, B, C> {

    final private A arg;

    public BissFinder(A arg) {
        this.arg = arg;
    }

    public BissConverter<B, C> findInDB(Function<A, Optional<B>> findInDB) {
        Optional<B> targetOP = findInDB.apply(this.arg);
        if (targetOP.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new BissConverter<>(targetOP.get());
    }
}
