package io.whileaway.apit.api.specs;

import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

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
}
