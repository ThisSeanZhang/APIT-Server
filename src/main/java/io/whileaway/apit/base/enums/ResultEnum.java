package io.whileaway.apit.base.enums;

import java.util.stream.Stream;

public interface ResultEnum {

    Integer getCode();

    String getMessage();

    Stream<ResultEnum> getEnumStream();
}
