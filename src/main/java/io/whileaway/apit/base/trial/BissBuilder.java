package io.whileaway.apit.base.trial;

import io.whileaway.apit.account.entity.Developer;

public abstract class BissBuilder {

    public static Biss<String, Developer, String> findByWhat(String arg) {
        return new Biss<>(arg);
    }
}
