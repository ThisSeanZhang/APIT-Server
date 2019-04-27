package io.whileaway.apit.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PageSpec<A, B> {

    final private List<Specification<A>> conditions = new ArrayList<>();

    final private Page<A> primitive ;

    final private Pageable pageable;

    private PageSpec(Page<A> primitive,Pageable pageable) {
        this.primitive = primitive;
        this.pageable = pageable;
    }

    public PageSpec(Pageable pageable) {
        this.primitive = new PageImpl<>(new ArrayList<>());
        this.pageable = pageable;
    }


    public PageSpec<A, B> findInDB (BiFunction<Specification<A>, Pageable, Page<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        Page<A> primitive = findInDB.apply(filterCondition, pageable);
        return new PageSpec<>(primitive, pageable);
    }

    public PageSpec<A, B> appendCondition(Specification<A> specification) {
        this.conditions.add(specification);
        return this;
    }

    public Page<A> nothing() {
        return this.primitive;
    }

    public Page<B> convertOtherPage(Function<A, B> convert) {
        return new PageImpl<>(
                convertOtherList(convert),
                this.pageable,
                this.primitive.getTotalElements()
        );
    }

    public List<B> convertOtherList(Function<A, B> convert) {
        return this.primitive.getContent().stream().map(convert).collect(Collectors.toList());
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

    public static<T, B> Specification<B> like(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.like(root.get(paramName), "%"+supplier.get()+"%");
    }
}
