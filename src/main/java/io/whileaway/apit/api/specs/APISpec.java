package io.whileaway.apit.api.specs;

import io.whileaway.apit.api.enums.StatusDict;
import io.whileaway.apit.base.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class APISpec {

    public static<A> Specification<A> belongFolder(Supplier<Long> supplier) {
        return Spec.equal("belongFolder", Objects::isNull, supplier);
    }

    public static<A> Specification<A> belongProject(Supplier<Long> supplier) {
        return Spec.equal("belongProject", Objects::isNull, supplier);
    }

    public static<A> Specification<A> belongFolderIsNull() {
        return Spec.isNull("belongFolder");
    }

    public static<A> Specification<A> statusNormal() {
        return Spec.equal("status", (i)->false, StatusDict.NORMAL::getCode);
    }

}
