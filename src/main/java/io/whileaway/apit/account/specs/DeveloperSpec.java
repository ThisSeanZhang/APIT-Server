package io.whileaway.apit.account.specs;


import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class DeveloperSpec {

    public static<A> Specification<A> likeDeveloperName(Supplier<String> supplier) {
        return Spec.like("developerName", StringUtils::isEmptyOrBlank, supplier);
    }

    public static<A> Specification<A> likeEmail(Supplier<String> supplier) {
        return Spec.like("email", StringUtils::isEmptyOrBlank, supplier);
    }

    public static<A> Specification<A> admin(Supplier<Boolean> supplier) {
        return Spec.equal("admin", Objects::isNull, supplier);
    }
}
