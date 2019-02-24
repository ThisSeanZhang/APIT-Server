package io.whileaway.apit.api.enums;

import io.whileaway.apit.base.enums.DictEnum;

import java.util.stream.Stream;

public enum StatusDict implements DictEnum {
    NORMAL(1, "正常"),
    DELETE(-1, "删除");

    private Integer code;
    private String desc;

    StatusDict(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public Stream<DictEnum> getEnumStream() {
        return Stream.of(values());
    }

    @Override
    public DictEnum findDictByCode(Integer code) {
        return getEnumStream().filter(dict -> dict.getCode().equals(code)).findFirst().orElse(null);
    }
}
