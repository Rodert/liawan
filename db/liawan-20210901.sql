
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE
IF EXISTS `liawan_article`;

CREATE TABLE `liawan_article` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT (20) DEFAULT NULL COMMENT '发表用户',
	`article_content` LONGTEXT COMMENT '文章内容html格式',
	`article_content_md` LONGTEXT COMMENT '文章内容Markdown格式',
	`article_newstime` datetime DEFAULT NULL COMMENT '发布时间',
	`article_status` INT (11) DEFAULT NULL COMMENT '文章状态 0已发布1草稿2回收站',
	`article_summary` VARCHAR (255) DEFAULT NULL COMMENT '文章摘要',
	`article_thumbnail` VARCHAR (255) DEFAULT NULL COMMENT '略缩图',
	`article_title` VARCHAR (255) DEFAULT NULL COMMENT '文章标题',
	`article_type` INT (255) DEFAULT NULL COMMENT '文章类型0原创1转载',
	`article_post` VARCHAR (255) DEFAULT NULL COMMENT 'post文章 page页面',
	`article_comment` INT (11) DEFAULT NULL COMMENT '是否开启评论 0开启1不开启',
	`article_updatetime` datetime DEFAULT NULL COMMENT '文章最后修改时间',
	`article_url` VARCHAR (255) DEFAULT NULL COMMENT '文章路径',
	`article_views` BIGINT (20) DEFAULT '0' COMMENT '访问量统计',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`),
	UNIQUE KEY `LIAWAN_ARTICLE_URL` (`article_url`) USING BTREE,
	KEY `LIAWAN_ARTICLE_USERID` (`user_id`) USING BTREE
) COMMENT = '文章表' ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_article_category
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_article_category`;

CREATE TABLE `liawan_article_category` (
	`article_id` BIGINT (20) NOT NULL,
	`category_id` BIGINT (20) NOT NULL,
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	KEY `LIAWAN_ARTILE_ID` (`article_id`) USING BTREE,
	KEY `LIAWAN_ARTILE_CATEGORY_ID` (`category_id`) USING BTREE
) COMMENT = '文章-分类关系表' ENGINE = INNODB DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_article_tag
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_article_tag`;

CREATE TABLE `liawan_article_tag` (
	`article_id` BIGINT (20) NOT NULL,
	`tag_id` BIGINT (20) NOT NULL,
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	KEY `LIAWAN_ARTILE_ID` (`article_id`) USING BTREE,
	KEY `LIAWAN_ARTILE_TAG_ID` (`tag_id`) USING BTREE
) COMMENT = '文章-标签关系表' ENGINE = INNODB DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_attachment
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_attachment`;

CREATE TABLE `liawan_attachment` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`picture_name` VARCHAR (255) DEFAULT NULL COMMENT '图片名称',
	`picture_path` VARCHAR (255) DEFAULT NULL COMMENT '图片路径',
	`picture_small_path` VARCHAR (255) DEFAULT NULL COMMENT '略缩图',
	`picture_type` VARCHAR (255) DEFAULT NULL COMMENT '图片类型',
	`picture_create_date` VARCHAR (255) DEFAULT NULL COMMENT '上传时间',
	`picture_size` VARCHAR (255) DEFAULT NULL COMMENT '文件大小',
	`picture_suffix` VARCHAR (255) DEFAULT NULL COMMENT '后缀',
	`picture_wh` VARCHAR (255) DEFAULT NULL COMMENT '尺寸',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) COMMENT = '附件表' ENGINE = INNODB AUTO_INCREMENT = 245 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_category
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_category`;

CREATE TABLE `liawan_category` (
	`category_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`category_name` VARCHAR (255) DEFAULT NULL COMMENT '分类名称',
	`category_url` VARCHAR (255) DEFAULT NULL COMMENT '分类路径',
	`category_describe` VARCHAR (255) DEFAULT NULL COMMENT '描述',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`category_id`)
) COMMENT = '文章分类表' ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_link
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_link`;

CREATE TABLE `liawan_link` (
	`link_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`link_name` VARCHAR (255) DEFAULT NULL COMMENT '名称',
	`link_url` VARCHAR (255) DEFAULT NULL COMMENT '路径',
	`link_logo` VARCHAR (255) DEFAULT NULL COMMENT '链接logo',
	`link_describe` VARCHAR (255) DEFAULT NULL COMMENT '描述',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`link_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_logs
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_logs`;

CREATE TABLE `liawan_logs` (
	`log_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`log_title` VARCHAR (255) DEFAULT NULL COMMENT '标题',
	`log_content` VARCHAR (255) DEFAULT NULL COMMENT '内容',
	`log_ip` VARCHAR (255) DEFAULT NULL COMMENT 'ip',
	`log_date` datetime DEFAULT NULL COMMENT '时间',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`log_id`)
) COMMENT = '日志表' ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_menu
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_menu`;

CREATE TABLE `liawan_menu` (
	`menu_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`menu_icon` VARCHAR (255) DEFAULT NULL COMMENT '菜单图标',
	`menu_name` VARCHAR (255) DEFAULT NULL COMMENT '菜单名称',
	`menu_sort` BIGINT (11) DEFAULT NULL COMMENT '排序',
	`menu_target` VARCHAR (255) DEFAULT NULL COMMENT '打开方式',
	`menu_url` VARCHAR (255) DEFAULT NULL COMMENT '菜单路径',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`menu_id`)
) COMMENT = '菜单表' ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_options
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_options`;

CREATE TABLE `liawan_options` (
	`option_name` VARCHAR (255) NOT NULL COMMENT '设置名',
	`option_value` LONGTEXT COMMENT '设置内容',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`option_name`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_tag
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_tag`;

CREATE TABLE `liawan_tag` (
	`tag_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`tag_name` VARCHAR (255) DEFAULT NULL COMMENT '标签名称',
	`tag_url` VARCHAR (255) DEFAULT NULL COMMENT '标签路径',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`tag_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_theme
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_theme`;

CREATE TABLE `liawan_theme` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`theme_name` VARCHAR (255) DEFAULT NULL COMMENT '主题名(url)',
	`theme_describe` VARCHAR (255) DEFAULT NULL COMMENT '主题描述',
	`theme_img` VARCHAR (255) DEFAULT NULL COMMENT '主题预览图',
	`theme_status` INT (11) DEFAULT '0' COMMENT '0未启用1已启用',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for liawan_user
-- ----------------------------
DROP TABLE
IF EXISTS `liawan_user`;

CREATE TABLE `liawan_user` (
	`user_id` BIGINT (20) NOT NULL AUTO_INCREMENT,
	`login_enable` VARCHAR (255) DEFAULT '0' COMMENT '是否禁用登录',
	`login_error` INT (11) DEFAULT NULL COMMENT '登录失败次数',
	`login_last_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
	`user_portrait` VARCHAR (255) DEFAULT NULL COMMENT '头像',
	`user_explain` VARCHAR (255) DEFAULT NULL COMMENT '说明',
	`user_display_name` VARCHAR (255) DEFAULT NULL COMMENT '显示名称',
	`user_email` VARCHAR (255) DEFAULT NULL COMMENT '邮箱',
	`user_name` VARCHAR (255) DEFAULT NULL COMMENT '用户名',
	`user_pwd` VARCHAR (255) DEFAULT NULL COMMENT '密码',
	`is_deleted` TINYINT NOT NULL DEFAULT '0' COMMENT '是否删除，1 表示删除，0 表示未删除',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`user_id`)
) COMMENT = '用户表' ENGINE = INNODB AUTO_INCREMENT = 1 CHARSET = utf8;

