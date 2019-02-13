package io.whileaway.apit.api.specs;

import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class NodeSpec {

    public static<A> Specification<A> ownerId(Supplier<Long> supplier) {
        return Spec.equal("ownerId", Objects::isNull, supplier);
    }

    public static<A> Specification<A> ownerId(Long ownerId) {
        return ownerId(() -> ownerId);
    }

    public static<A> Specification<A> belongProject(Supplier<Long> supplier) {
        return Spec.equal("belongProject", Objects::isNull, supplier);
    }

    public static<A> Specification<A> belongProject(Long belongProject) {
        return belongProject(() -> belongProject);
    }

    public static<A> Specification<A> parentIdIsNull() {
        return Spec.isNull("parentId");
    }

    public static<A> Specification<A> parentId(Supplier<Long> supplier) {
        return Spec.equal("parentId", Objects::isNull, supplier);
    }
}
