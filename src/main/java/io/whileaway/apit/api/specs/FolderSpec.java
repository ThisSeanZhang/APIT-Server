package io.whileaway.apit.api.specs;

import io.whileaway.apit.base.Spec;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.function.Supplier;

public class FolderSpec {

    public static<A> Specification<A> folderName(Supplier<String> supplier) {
        return Spec.equal("folderName", StringUtils::isEmptyOrBlank, supplier);
    }

    public static<A> Specification<A> belongProject(Supplier<Long> supplier) {
        return Spec.equal("belongProject", Objects::isNull, supplier);
    }

    public static<A> Specification<A> folderOwnerId(Supplier<Long> supplier) {
        return Spec.equal("folderOwnerId", Objects::isNull, supplier);
    }

    public static<A> Specification<A> folderParentIsNull() {
        return Spec.isNull("parentId");
    }

    public static<A> Specification<A> folderParentId(Supplier<Long> supplier) {
        return Spec.equal("parentId", Objects::isNull, supplier);
    }




}
