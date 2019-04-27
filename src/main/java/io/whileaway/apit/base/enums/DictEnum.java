package io.whileaway.apit.base.enums;

import java.util.stream.Stream;

public interface DictEnum {

    Integer getCode();

    String getDesc();

    Stream<DictEnum> getEnumStream();

    DictEnum findDictByCode(Integer code);
}
