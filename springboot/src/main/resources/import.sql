-- 数据库初始化数据插入

INSERT INTO `springboot`.`t_user` (`id`, `password`, `username`, `salt`) VALUES ('1', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'aaa');
INSERT INTO `springboot`.`t_user` (`id`, `password`, `username`, `salt`) VALUES ('2', '05eb09db306ef2f9898bea68efad5de2', 'hello', 'aaa');

INSERT INTO `springboot`.`t_role` (`id`, `rolename`) VALUES ('1', 'admin');
INSERT INTO `springboot`.`t_role` (`id`, `rolename`) VALUES ('2', 'hello');

INSERT INTO `springboot`.`t_user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `springboot`.`t_user_role` (`user_id`, `role_id`) VALUES ('2', '2');

INSERT INTO `springboot`.`t_permission` (`id`, `permissionname`) VALUES ('1', 'admin');
INSERT INTO `springboot`.`t_permission` (`id`, `permissionname`) VALUES ('2', 'hello');

INSERT INTO `springboot`.`t_role_permission` (`role_id`, `permission_id`) VALUES ('1', '1');
INSERT INTO `springboot`.`t_role_permission` (`role_id`, `permission_id`) VALUES ('2', '2');