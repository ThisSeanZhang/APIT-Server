package io.whileaway.apit.api.specs;

import io.whileaway.apit.base.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class APISpec {

    public static<A> Specification<A> belongFolder(Supplier<Long> supplier) {
        return Spec.equal("belongFolder", Objects::isNull, supplier);
    }

}
