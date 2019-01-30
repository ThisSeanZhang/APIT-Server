package io.whileaway.apit.api.service;

import io.whileaway.apit.api.entity.Project;
import io.whileaway.apit.base.Result;

import java.util.List;

public interface ProjectService {

    Result<List<Project>> getProjectsByOwnerId (Long projectOwner);

}
