package io.whileaway.apit.api.service;

import io.whileaway.apit.api.response.Node;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface APIService {

    Result<List<Node>> findByBelongFolder(Long belongFolder);
}
