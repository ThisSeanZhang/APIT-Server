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
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FolderSpec<A, B> {

    final private List<Specification<A>> conditions = new ArrayList<>();

    final private List<A> primitive ;

    private FolderSpec(List<A> primitive) {
        this.primitive = primitive;
    }

    public FolderSpec() {
        this.primitive = new ArrayList<>();
    }

    public static<A> Specification<A> folderName(Supplier<String> supplier) {
        return Spec.equal("folderName", StringUtils::isEmptyOrBlank, supplier);
    }

    public static<A> Specification<A> belongProject(Supplier<Long> supplier) {
        return Spec.equal("belongProject", Objects::isNull, supplier);
    }

    public static<A> Specification<A> folderOwnerId(Supplier<Long> supplier) {
        return Spec.equal("folderOwnerId", Objects::isNull, supplier);
    }

    public FolderSpec<A, B> appendCondition(Specification<A> specification) {
        this.conditions.add(specification);
        return this;
    }

    public FolderSpec<A, B> findInDB (Function<Specification<A>, List<A>> findInDB) {
        Specification<A> filterCondition = this.conditions.stream().filter(Objects::nonNull).reduce(this::andJoin).orElse(null);
        List<A> primitive = findInDB.apply(filterCondition);
        if (primitive.isEmpty()) {
            throw new CommonException(ControllerEnum.NOT_FOUND);
        }
        return new FolderSpec<>(primitive);
    }

    public Result<List<B>> convert(Function<A, B> convert) {
        return ResultUtil.success(ControllerEnum.SUCCESS, this.primitive.stream().map(convert).collect(Collectors.toList()));
    }

    public Result<List<A>> doNothing() {
        return ResultUtil.success(ControllerEnum.SUCCESS, this.primitive);
    }

    private Specification<A> andJoin(Specification<A> perCondition, Specification<A> condition) {
        if (perCondition != null && condition != null)
            return perCondition.and(condition);
        return perCondition == null ? condition : perCondition;
    }

}
