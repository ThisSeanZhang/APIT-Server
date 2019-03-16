package io.whileaway.apit.api.specs;

import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.function.Supplier;

public class ProjectSpec {
    public static<A> Specification<A> likeProjectName(Supplier<String> supplier) {
        return Spec.like("projectName", StringUtils::isEmptyOrBlank, supplier);
    }

    public static<A> Specification<A> projectOwner(Supplier<Long> supplier) {
        return Spec.equal("projectOwner", Objects::isNull, supplier);
    }

    public static<A> Specification<A> isOvert(Supplier<Boolean> supplier) {
        return Spec.equal("overt", Objects::isNull, supplier);
    }

    public static<A> Specification<A> statusNormal() {
        return Spec.equal("status", Objects::isNull, StatusDict.NORMAL::getCode);
    }

    public static<A> Specification<A> whoIsJoins(Supplier<Long> supplier) {
        if (Objects.isNull(supplier.get())) return null;
        return ((Specification<A>) (root, query, builder) ->
                builder.like(root.get("whoJoins"), supplier.get() + "%"))
                .or(whoIsJoins1(supplier))
                .or(whoIsJoins2(supplier));
    }

    private static<A> Specification<A> whoIsJoins2(Supplier<Long> supplier) {
        if (Objects.isNull(supplier.get())) return null;
        return Spec.like("whoJoins", "%" + supplier.get());
    }

    private static<A> Specification<A> whoIsJoins1(Supplier<Long> supplier) {
        return Spec.like("whoJoins", "%" + supplier.get() + "%");
    }


}
