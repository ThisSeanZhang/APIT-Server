package io.whileaway.apit.api.specs;

import io.whileaway.apit.base.CommonException;
import io.whileaway.apit.base.Result;
import io.whileaway.apit.base.ResultUtil;
import io.whileaway.apit.base.Spec;
import io.whileaway.apit.base.enums.ControllerEnum;
import io.whileaway.apit.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FolderSpec<A, B> {

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
