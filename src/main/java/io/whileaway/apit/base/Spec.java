package io.whileaway.apit.base;

import org.springframework.data.jpa.domain.Specification;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Spec {

    public static<T, B> Specification<B> equal(String paramName, Predicate<T> predicate, Supplier<T> supplier) {
        return predicate.test(supplier.get())
                ? null
                : (root, query, builder) -> builder.equal(root.get(paramName), supplier.get());
    }

    public static<T> Specification andJoinSpec(Specification<T> perCondition, Specification<T> currentCondition) {
        if (perCondition != null && currentCondition != null) {
            return perCondition.and(currentCondition);
        }

        if (perCondition != null) {
            return perCondition;
        }

        if (currentCondition != null) {
            return currentCondition;
        }
        return  null;
    }

}
