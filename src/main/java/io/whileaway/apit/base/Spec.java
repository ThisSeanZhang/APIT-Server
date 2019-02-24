package io.whileaway.apit.base;

import io.whileaway.apit.base.enums.ControllerEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Spec<A, B> {

    final private List<Specification<A>> conditions = new ArrayList<>();

    final private List<A> primitive ;

    private Spec(List<A> primitive) {
        this.primitive = primitive;
    }

    public Spec() {
        this.primitive = new ArrayList<>();
    }


    public Spec<A, B> findInDB (Function<Specification<A>, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition);
        if (primitive.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new Spec<>(primitive);
    }

    public Spec<A, B> findInDBUnCheck (Function<Specification<A>, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition);
        return new Spec<>(primitive);
    }

    public Spec<A, B> appendCondition(Specification<A> specification) {
        this.conditions.add(specification);
        return this;
    }

    public Result<List<B>> convert(Function<A, B> convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, this.primitive.stream().map(convert).collect(Collectors.toList()));
    }

    public Result<List<A>> doNothing() {
        return ResultUtil.success(ControllerEnum.SUCCESS, this.primitive);
    }

    private Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }

    public static<T, B> Specification<B> equal(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.equal(root.get(paramName), supplier.get());
    }

    public static<B> Specification<B> isNull(String paramName) {
        return (root, query, builder) -> builder.isNull(root.get(paramName));
    }
}
