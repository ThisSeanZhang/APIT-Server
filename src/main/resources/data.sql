# 用户
INSERT INTO `developer`(`developer_id`, `developer_name`, `developer_pass`, `email`) VALUES (1, 'sean', '123456', 'cc@cc.cc');

# 项目
INSERT INTO `apit_server`.`project`(`pid`, `project_name`, `project_owner`) VALUES (1, '默认项目', 1);

# 文件夹
INSERT INTO `apit_server`.`folder`(`fid`, `belong_project`, `folder_name`, `folder_owner`, `parent_id`) VALUES (1, 1, '默认文件夹', '1', NULL);
INSERT INTO `apit_server`.`folder`(`fid`, `belong_project`, `folder_name`, `folder_owner_id`, `parent_id`) VALUES (2, 1, 'AAA', 1, NULL);
