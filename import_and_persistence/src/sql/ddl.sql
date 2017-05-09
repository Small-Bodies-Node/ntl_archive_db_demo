SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE nasa_pds;

-- -----------------------------------------------------
-- Table `target`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `target` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mission`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NOT NULL ,
  `start_date` DATETIME NULL ,
  `end_date` DATETIME NULL ,
  `description` MEDIUMTEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instrument`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_text_id` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(256) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `description` MEDIUMTEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `target_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `target_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `keyword`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `keyword` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `name_index` (`name`)  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookup_value`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookup_value` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `keyword_id` BIGINT NOT NULL ,
  `value` MEDIUMTEXT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_lookup_keyword` (`keyword_id` ASC) ,
  CONSTRAINT `fk_lookup_keyword`
    FOREIGN KEY (`keyword_id` )
    REFERENCES `keyword` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `reference_key_text_id` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(2000) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `object_alias`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `object_alias` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `alias` VARCHAR(45) NOT NULL ,
  `full_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `element_alias`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `element_alias` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `full_name` VARCHAR(45) NOT NULL ,
  `alias` VARCHAR(45) NOT NULL ,
  `another_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `unit_alias`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `unit_alias` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `alias` VARCHAR(45) NOT NULL ,
  `full_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `object_definition`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `object_definition` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  `globally_allowed_elements` BOOL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `object_validation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `object_validation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `object_id` BIGINT NOT NULL ,
  `inner_object_id` BIGINT NOT NULL ,
  `required` BOOL  NOT NULL ,
  PRIMARY KEY (`id`, `object_id`, `inner_object_id`) ,
  INDEX `fk_object_validation_object` (`object_id` ASC) ,
  INDEX `fk_object_validation_inner_object` (`inner_object_id` ASC) ,
  CONSTRAINT `fk_object_validation_object`
    FOREIGN KEY (`object_id` )
    REFERENCES `object_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_object_validation_inner_object`
    FOREIGN KEY (`inner_object_id` )
    REFERENCES `object_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `element_definition`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `element_definition` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `element_validation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `element_validation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `object_id` BIGINT NOT NULL ,
  `inner_element_id` BIGINT NOT NULL ,
  `required` BOOL  NOT NULL ,
  PRIMARY KEY (`id`, `object_id`, `inner_element_id`) ,
  INDEX `fk_element_validation_object` (`object_id` ASC) ,
  INDEX `fk_element_validation_element` (`inner_element_id` ASC) ,
  CONSTRAINT `fk_element_validation_object`
    FOREIGN KEY (`object_id` )
    REFERENCES `object_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_element_validation_element`
    FOREIGN KEY (`inner_element_id` )
    REFERENCES `element_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `data_set_text_id` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(256) NOT NULL ,
  `start_time` DATETIME NULL ,
  `stop_time` DATETIME NULL ,
  `description` MEDIUMTEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NULL ,
  `product_text_id` VARCHAR(45) NULL ,
  `start_time` DATETIME NULL ,
  `stop_time` DATETIME NULL ,
  `description` MEDIUMTEXT NULL ,
  `record_type` VARCHAR(45) NOT NULL ,
  `record_byte_size` INT NULL ,
  `record_count` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_index`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product_index` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `data_product_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `data_product_id`) ,
  INDEX `fk_dataset_has_data_product_data_product1` (`data_product_id` ASC) ,
  INDEX `fk_dataset_has_data_product_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_data_product_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_data_product_data_product1`
    FOREIGN KEY (`data_product_id` )
    REFERENCES `product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reference_catalog`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_catalog` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `reference_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `reference_id`) ,
  INDEX `fk_dataset_has_reference_reference1` (`reference_id` ASC) ,
  INDEX `fk_dataset_has_reference_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_reference_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_reference_reference1`
    FOREIGN KEY (`reference_id` )
    REFERENCES `reference` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instrument_host`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_host` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_host_text_id` VARCHAR(100) NOT NULL ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instrument_catalog`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_catalog` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `instrument_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `instrument_id`) ,
  INDEX `fk_dataset_has_instrument_instrument1` (`instrument_id` ASC) ,
  INDEX `fk_dataset_has_instrument_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_instrument_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_instrument_instrument1`
    FOREIGN KEY (`instrument_id` )
    REFERENCES `instrument` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `table` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `table_name` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `data_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `data_table` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `table_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_data_table_has_table`
    FOREIGN KEY (`table_id` )
    REFERENCES `table` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `table_counter`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `table_counter` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `count` INT NOT NULL,
  PRIMARY KEY (`id`)  )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `product_table`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product_table` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT NOT NULL ,
  `data_table_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `product_id`, `data_table_id`) ,
  INDEX `fk_product_has_data_table_product1` (`product_id` ASC) ,
  CONSTRAINT `fk_product_has_data_table_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION ,
  CONSTRAINT `fk_product_has_data_table`
    FOREIGN KEY (`data_table_id` )
    REFERENCES `data_table` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `data_file`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `data_file` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `path` VARCHAR(255) NULL ,
  `content` MEDIUMTEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_file`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product_file` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT NOT NULL ,
  `data_file_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `product_id`, `data_file_id`) ,
  INDEX `fk_product_has_data_file_data_file1` (`data_file_id` ASC) ,
  INDEX `fk_product_has_data_file_product1` (`product_id` ASC) ,
  CONSTRAINT `fk_product_has_data_file_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_data_file_data_file1`
    FOREIGN KEY (`data_file_id` )
    REFERENCES `data_file` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `dataset_file`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_file` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `data_file_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `data_file_id`) ,
  INDEX `fk_dataset_has_data_file_data_file1` (`data_file_id` ASC) ,
  INDEX `fk_dataset_has_data_file_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_data_file_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_data_file_data_file1`
    FOREIGN KEY (`data_file_id` )
    REFERENCES `data_file` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_table`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_table` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `data_table_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `data_table_id`) ,
  INDEX `fk_dataset_has_data_table_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_data_table_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_data_table`
    FOREIGN KEY (`data_table_id` )
    REFERENCES `data_table` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `volume`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `volume` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(256) NOT NULL ,
  `description` VARCHAR(2000) NOT NULL ,
  `volume_text_id` VARCHAR(45) NOT NULL ,
  `volume_set_text_id` VARCHAR(45) NOT NULL ,
  `volume_set_name` VARCHAR(1024) NOT NULL ,
  `volume_series_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `lookup_value_id`) ,
  INDEX `fk_dataset_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_dataset_has_lookup_value_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_lookup_value_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_volume`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_volume` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `dataset_id` BIGINT NOT NULL ,
  `volume_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `dataset_id`, `volume_id`) ,
  INDEX `fk_dataset_has_volume_volume1` (`volume_id` ASC) ,
  INDEX `fk_dataset_has_volume_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_has_volume_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_has_volume_volume1`
    FOREIGN KEY (`volume_id` )
    REFERENCES `volume` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_rating`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_rating` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `rating` DOUBLE  NOT NULL ,
  `dataset_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_dataset_rank_dataset1` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_rank_dataset1`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `correlation_group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `correlation_group` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(1000) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `object_type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `object_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `correlated_object`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `correlated_object` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `object_id` BIGINT NOT NULL ,
  `object_type_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_correlated_object_object_type1` (`object_type_id` ASC) ,
  CONSTRAINT `fk_correlated_object_object_type1`
    FOREIGN KEY (`object_type_id` )
    REFERENCES `object_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `correlation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `correlation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `correlation_group_id` BIGINT NOT NULL ,
  `correlated_object_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `correlation_group_id`, `correlated_object_id`) ,
  INDEX `fk_correlation_group_has_correlated_object_correlated_object1` (`correlated_object_id` ASC) ,
  INDEX `fk_correlation_group_has_correlated_object_correlation_group1` (`correlation_group_id` ASC) ,
  CONSTRAINT `fk_correlation_group_has_correlated_object_correlation_group1`
    FOREIGN KEY (`correlation_group_id` )
    REFERENCES `correlation_group` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_correlation_group_has_correlated_object_correlated_object1`
    FOREIGN KEY (`correlated_object_id` )
    REFERENCES `correlated_object` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `predefined_query`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `predefined_query` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `query` VARCHAR(4000) NOT NULL ,
  `description` VARCHAR(4000) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `popular_query`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `popular_query` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `query` VARCHAR(4000) NOT NULL ,
  `usage_frequency` DOUBLE  NOT NULL ,
  `usage_count` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `object_definition_lookup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `object_definition_lookup` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `object_id` BIGINT NOT NULL ,
  `lookup_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_object_definition_lookup_lookup` (`lookup_id` ASC) ,
  INDEX `fk_object_definition_lookup_object` (`object_id` ASC) ,
  CONSTRAINT `fk_object_definition_lookup_lookup`
    FOREIGN KEY (`lookup_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_object_definition_lookup_object`
    FOREIGN KEY (`object_id` )
    REFERENCES `object_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `element_definition_lookup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `element_definition_lookup` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `element_id` BIGINT NOT NULL ,
  `lookup_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_element_definition_lookup_element` (`element_id` ASC) ,
  INDEX `fk_element_definition_lookup_lookup` (`lookup_id` ASC) ,
  CONSTRAINT `fk_element_definition_lookup_element`
    FOREIGN KEY (`element_id` )
    REFERENCES `element_definition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_element_definition_lookup_lookup`
    FOREIGN KEY (`lookup_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `help_file`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `help_file` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `path` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookup_value_xref`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookup_value_xref` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `parent_id` BIGINT NOT NULL ,
  `child_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_parent_lookup` (`parent_id` ASC) ,
  INDEX `fk_child_lookup` (`child_id` ASC) ,
  CONSTRAINT `fk_parent_lookup`
    FOREIGN KEY (`parent_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_child_lookup`
    FOREIGN KEY (`child_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mission_reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mission_reference` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `mission_id` BIGINT NOT NULL ,
  `reference_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `mission_id`, `reference_id`) ,
  INDEX `fk_mission_has_reference_reference1` (`reference_id` ASC) ,
  INDEX `fk_mission_has_reference_mission` (`mission_id` ASC) ,
  CONSTRAINT `fk_mission_has_reference_mission1`
    FOREIGN KEY (`mission_id` )
    REFERENCES `mission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mission_has_reference_reference1`
    FOREIGN KEY (`reference_id` )
    REFERENCES `reference` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_mission`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_mission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `mission_id` BIGINT NOT NULL ,
  `dataset_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_dataset_mission_mission` (`mission_id` ASC) ,
  INDEX `fk_dataset_mission_dataset` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_mission_mission`
    FOREIGN KEY (`mission_id` )
    REFERENCES `mission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_mission_dataset`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dataset_target`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dataset_target` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `target_id` BIGINT NOT NULL ,
  `dataset_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_target_target_type1` (`target_id` ASC) ,
  INDEX `fk_dataset_target_dataset` (`dataset_id` ASC) ,
  CONSTRAINT `fk_dataset_target_target`
    FOREIGN KEY (`target_id` )
    REFERENCES `target` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dataset_target_dataset`
    FOREIGN KEY (`dataset_id` )
    REFERENCES `dataset` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `target_type_target`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `target_type_target` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `target_type_id` BIGINT NOT NULL ,
  `target_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_target_target_type1` (`target_type_id` ASC) ,
  INDEX `fk_target_type_target_target` (`target_id` ASC) ,
  CONSTRAINT `fk_target_target_type10`
    FOREIGN KEY (`target_type_id` )
    REFERENCES `target_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_target_type_target_target`
    FOREIGN KEY (`target_id` )
    REFERENCES `target` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instrument_host_instrument`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_host_instrument` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_host_id` BIGINT NOT NULL ,
  `instrument_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_instrument_host_instrument_host` (`instrument_host_id` ASC) ,
  INDEX `fk_instrument_host_instrument_instrument` (`instrument_id` ASC) ,
  CONSTRAINT `fk_instrument_host_instrument_host`
    FOREIGN KEY (`instrument_host_id` )
    REFERENCES `instrument_host` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instrument_host_instrument_instrument`
    FOREIGN KEY (`instrument_id` )
    REFERENCES `instrument` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `volume_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `volume_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `volume_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `volume_id`, `lookup_value_id`) ,
  INDEX `fk_volume_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_volume_has_lookup_value_volume1` (`volume_id` ASC) ,
  CONSTRAINT `fk_volume_has_lookup_value_volume1`
    FOREIGN KEY (`volume_id` )
    REFERENCES `volume` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_volume_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `instrument_host_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_host_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_host_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `instrument_host_id`, `lookup_value_id`) ,
  INDEX `fk_instrument_host_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_instrument_host_has_lookup_value_instrument_host1` (`instrument_host_id` ASC) ,
  CONSTRAINT `fk_instrument_host_has_lookup_value_instrument_host1`
    FOREIGN KEY (`instrument_host_id` )
    REFERENCES `instrument_host` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instrument_host_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `instrument_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `instrument_id`, `lookup_value_id`) ,
  INDEX `fk_instrument_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_instrument_has_lookup_value_instrument1` (`instrument_id` ASC) ,
  CONSTRAINT `fk_instrument_has_lookup_value_instrument1`
    FOREIGN KEY (`instrument_id` )
    REFERENCES `instrument` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instrument_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mission_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mission_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `mission_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `mission_id`, `lookup_value_id`) ,
  INDEX `fk_mission_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_mission_has_lookup_value_mission1` (`mission_id` ASC) ,
  CONSTRAINT `fk_mission_has_lookup_value_mission1`
    FOREIGN KEY (`mission_id` )
    REFERENCES `mission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mission_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `product_metadata`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product_metadata` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT NOT NULL ,
  `lookup_value_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `product_id`, `lookup_value_id`) ,
  INDEX `fk_product_has_lookup_value_lookup_value1` (`lookup_value_id` ASC) ,
  INDEX `fk_product_has_lookup_value_product1` (`product_id` ASC) ,
  CONSTRAINT `fk_product_has_lookup_value_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_lookup_value_lookup_value1`
    FOREIGN KEY (`lookup_value_id` )
    REFERENCES `lookup_value` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `product_properties`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `product_properties` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT NOT NULL ,
  `properties_ids` TEXT NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_properties_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `instrument_host_reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_host_reference` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_host_id` BIGINT NOT NULL ,
  `reference_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `instrument_host_id`, `reference_id`) ,
  INDEX `fk_instrument_host_has_reference_reference1` (`reference_id` ASC) ,
  INDEX `fk_instrument_host_has_reference_instrument_host1` (`instrument_host_id` ASC) ,
  CONSTRAINT `fk_instrument_host_has_reference_instrument_host1`
    FOREIGN KEY (`instrument_host_id` )
    REFERENCES `instrument_host` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instrument_host_has_reference_reference1`
    FOREIGN KEY (`reference_id` )
    REFERENCES `reference` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `instrument_reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `instrument_reference` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `instrument_id` BIGINT NOT NULL ,
  `reference_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `instrument_id`, `reference_id`) ,
  INDEX `fk_instrument_has_reference_reference1` (`reference_id` ASC) ,
  INDEX `fk_instrument_has_reference_instrument1` (`instrument_id` ASC) ,
  CONSTRAINT `fk_instrument_has_reference_instrument1`
    FOREIGN KEY (`instrument_id` )
    REFERENCES `instrument` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instrument_has_reference_reference1`
    FOREIGN KEY (`reference_id` )
    REFERENCES `reference` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `target_reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `target_reference` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `target_id` BIGINT NOT NULL ,
  `reference_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`, `target_id`, `reference_id`) ,
  INDEX `fk_target_has_reference_reference1` (`reference_id` ASC) ,
  INDEX `fk_target_has_reference_target` (`target_id` ASC) ,
  CONSTRAINT `fk_target_has_reference_target1`
    FOREIGN KEY (`target_id` )
    REFERENCES `target` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_target_has_reference_reference1`
    FOREIGN KEY (`reference_id` )
    REFERENCES `reference` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_instrument`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_instrument` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(64) NOT NULL ,

  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_observation_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_observation_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_creation_time` DATETIME NULL ,
  `instrument_host_name` VARCHAR(128) NULL ,
  `instrument_host_id` VARCHAR(128) NULL ,
  `instrument_name` VARCHAR(128) NULL ,
  `instrument_id` VARCHAR(128) NULL ,

  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_observation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_observation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `ring_observation_id` VARCHAR(128) NOT NULL ,
  `cassini_observation_info_id` BIGINT NULL ,

  PRIMARY KEY (`id`) ,
  INDEX `fk_cassini_observation_ring` (`ring_observation_id` ASC) ,

  CONSTRAINT `fk_cassini_observation_info_id`
    FOREIGN KEY (`cassini_observation_info_id`)
    REFERENCES `cassini_observation_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_observation_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_observation_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `cassini_observation_id` BIGINT NOT NULL ,
  `product_id` BIGINT NOT NULL ,

  PRIMARY KEY (`id`, `cassini_observation_id`, `product_id`) ,
  INDEX `fk_cassini_observation_product_observation` (`cassini_observation_id` ASC) ,
  INDEX `fk_cassini_observation_product_product` (`product_id` ASC) ,

  CONSTRAINT `fk_cassini_observation_product_observation`
    FOREIGN KEY (`cassini_observation_id`)
    REFERENCES `cassini_observation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cassini_observation_product_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_instrument_observation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_instrument_observation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `cassini_instrument_id` BIGINT NOT NULL ,
  `cassini_observation_id` BIGINT NOT NULL ,

  PRIMARY KEY (`id`, `cassini_instrument_id`, `cassini_observation_id`),
  INDEX `fk_cassini_instrument_observation_instrument` (`cassini_instrument_id` ASC) ,
  INDEX `fk_cassini_instrument_observation_observation` (`cassini_observation_id` ASC) ,

  CONSTRAINT `fk_cassini_instrument_observation_instrument`
    FOREIGN KEY (`cassini_instrument_id`)
    REFERENCES `cassini_instrument` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cassini_instrument_observation_observation`
    FOREIGN KEY (`cassini_observation_id`)
    REFERENCES `cassini_observation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_observation_target`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_observation_target` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `cassini_observation_id` BIGINT NOT NULL ,
  `target_id` BIGINT NOT NULL ,

  PRIMARY KEY (`id`, `cassini_observation_id`, `target_id`) ,  
  INDEX `fk_cassini_observation_target_observation` (`cassini_observation_id` ASC) ,
  INDEX `fk_cassini_observation_target_target` (`target_id` ASC) ,

  CONSTRAINT `fk_cassini_observation_target_observation`
    FOREIGN KEY (`cassini_observation_id` )
    REFERENCES `cassini_observation` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cassini_observation_target_target`
    FOREIGN KEY (`target_id` )
    REFERENCES `target` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_body_summary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_body_summary` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `VOLUME_ID` VARCHAR(10) NOT NULL ,
  `FILE_SPECIFICATION_NAME` VARCHAR(45) NOT NULL ,
  `RING_OBSERVATION_ID` VARCHAR(25) NOT NULL ,
  `TARGET_NAME` VARCHAR(10) NOT NULL ,
  `MINIMUM_PLANETOCENTRIC_LATITUDE` DOUBLE ,
  `MAXIMUM_PLANETOCENTRIC_LATITUDE` DOUBLE ,
  `MINIMUM_PLANETOGRAPHIC_LATITUDE` DOUBLE ,
  `MAXIMUM_PLANETOGRAPHIC_LATITUDE` DOUBLE ,
  `MINIMUM_IAU_LONGITUDE` DOUBLE ,
  `MAXIMUM_IAU_LONGITUDE` DOUBLE ,
  `MINIMUM_LOCAL_HOUR_ANGLE` DOUBLE ,
  `MAXIMUM_LOCAL_HOUR_ANGLE` DOUBLE ,
  `MINIMUM_LONGITUDE_WRT_OBSERVER` DOUBLE ,
  `MAXIMUM_LONGITUDE_WRT_OBSERVER` DOUBLE ,
  `MINIMUM_FINEST_SURFACE_RESOLUTION` DOUBLE ,
  `MAXIMUM_FINEST_SURFACE_RESOLUTION` DOUBLE ,
  `MINIMUM_COARSEST_SURFACE_RESOLUTION` DOUBLE ,
  `MAXIMUM_COARSEST_SURFACE_RESOLUTION` DOUBLE ,
  `MINIMUM_SURFACE_DISTANCE` DOUBLE ,
  `MAXIMUM_SURFACE_DISTANCE` DOUBLE ,
  `MINIMUM_PHASE_ANGLE` DOUBLE ,
  `MAXIMUM_PHASE_ANGLE` DOUBLE ,
  `MINIMUM_INCIDENCE_ANGLE` DOUBLE ,
  `MAXIMUM_INCIDENCE_ANGLE` DOUBLE ,
  `MINIMUM_EMISSION_ANGLE` DOUBLE ,
  `MAXIMUM_EMISSION_ANGLE` DOUBLE ,
  `SUB_SOLAR_PLANETOCENTRIC_LATITUDE` DOUBLE ,
  `SUB_SOLAR_PLANETOGRAPHIC_LATITUDE` DOUBLE ,
  `SUB_OBSERVER_PLANETOCENTRIC_LATITUDE` DOUBLE ,
  `SUB_OBSERVER_PLANETOGRAPHIC_LATITUDE` DOUBLE ,
  `SUB_SOLAR_IAU_LONGITUDE` DOUBLE ,
  `SUB_OBSERVER_IAU_LONGITUDE` DOUBLE ,
  `CENTER_RESOLUTION` DOUBLE ,
  `CENTER_DISTANCE` DOUBLE ,
  `CENTER_PHASE_ANGLE` DOUBLE ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cassini_ring_summary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cassini_ring_summary` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `VOLUME_ID` VARCHAR(10) NOT NULL ,
  `FILE_SPECIFICATION_NAME` VARCHAR(45) NOT NULL ,
  `RING_OBSERVATION_ID` VARCHAR(25) NOT NULL ,
  `MINIMUM_RIGHT_ASCENSION` DOUBLE ,
  `MAXIMUM_RIGHT_ASCENSION` DOUBLE ,
  `MINIMUM_DECLINATION` DOUBLE ,
  `MAXIMUM_DECLINATION` DOUBLE ,
  `MINIMUM_RING_RADIUS` DOUBLE ,
  `MAXIMUM_RING_RADIUS` DOUBLE ,
  `FINEST_RING_INTERCEPT_RESOLUTION` DOUBLE ,
  `COARSEST_RING_INTERCEPT_RESOLUTION` DOUBLE ,
  `FINEST_RADIAL_RESOLUTION` DOUBLE ,
  `COARSEST_RADIAL_RESOLUTION` DOUBLE ,
  `MINIMUM_RING_DISTANCE` DOUBLE ,
  `MAXIMUM_RING_DISTANCE` DOUBLE ,
  `MINIMUM_RING_LONGITUDE` DOUBLE ,
  `MAXIMUM_RING_LONGITUDE` DOUBLE ,
  `MINIMUM_SOLAR_HOUR_ANGLE` DOUBLE ,
  `MAXIMUM_SOLAR_HOUR_ANGLE` DOUBLE ,
  `MINIMUM_RING_LONGITUDE_WRT_OBSERVER` DOUBLE ,
  `MAXIMUM_RING_LONGITUDE_WRT_OBSERVER` DOUBLE ,
  `MINIMUM_RING_AZIMUTH` DOUBLE ,
  `MAXIMUM_RING_AZIMUTH` DOUBLE ,
  `MINIMUM_RING_PHASE_ANGLE` DOUBLE ,
  `MAXIMUM_RING_PHASE_ANGLE` DOUBLE ,
  `MINIMUM_RING_INCIDENCE_ANGLE` DOUBLE ,
  `MAXIMUM_RING_INCIDENCE_ANGLE` DOUBLE ,
  `MINIMUM_NORTH_BASED_INCIDENCE_ANGLE` DOUBLE ,
  `MAXIMUM_NORTH_BASED_INCIDENCE_ANGLE` DOUBLE ,
  `MINIMUM_RING_EMISSION_ANGLE` DOUBLE ,
  `MAXIMUM_RING_EMISSION_ANGLE` DOUBLE ,
  `MINIMUM_NORTH_BASED_EMISSION_ANGLE` DOUBLE ,
  `MAXIMUM_NORTH_BASED_EMISSION_ANGLE` DOUBLE ,
  `MINIMUM_SOLAR_RING_ELEVATION` DOUBLE ,
  `MAXIMUM_SOLAR_RING_ELEVATION` DOUBLE ,
  `MINIMUM_OBSERVER_RING_ELEVATION` DOUBLE ,
  `MAXIMUM_OBSERVER_RING_ELEVATION` DOUBLE ,
  `MINIMUM_EDGE_ON_RING_RADIUS` DOUBLE ,
  `MAXIMUM_EDGE_ON_RING_RADIUS` DOUBLE ,
  `MINIMUM_EDGE_ON_RING_ALTITUDE` DOUBLE ,
  `MAXIMUM_EDGE_ON_RING_ALTITUDE` DOUBLE ,
  `FINEST_EDGE_ON_RADIAL_RESOLUTION` DOUBLE ,
  `COARSEST_EDGE_ON_RADIAL_RESOLUTION` DOUBLE ,
  `MINIMUM_EDGE_ON_INTERCEPT_DISTANCE` DOUBLE ,
  `MAXIMUM_EDGE_ON_INTERCEPT_DISTANCE` DOUBLE ,
  `MINIMUM_EDGE_ON_RING_LONGITUDE` DOUBLE ,
  `MAXIMUM_EDGE_ON_RING_LONGITUDE` DOUBLE ,
  `MINIMUM_EDGE_ON_SOLAR_HOUR_ANGLE` DOUBLE ,
  `MAXIMUM_EDGE_ON_SOLAR_HOUR_ANGLE` DOUBLE ,
  `RING_CENTER_DISTANCE` DOUBLE ,
  `SUB_SOLAR_RING_LONGITUDE` DOUBLE ,
  `SUB_OBSERVER_RING_LONGITUDE` DOUBLE ,
  `RING_CENTER_PHASE_ANGLE` DOUBLE ,
  `RING_CENTER_INCIDENCE_ANGLE` DOUBLE ,
  `RING_CENTER_NORTH_BASED_INCIDENCE_ANGLE` DOUBLE ,
  `RING_CENTER_EMISSION_ANGLE` DOUBLE ,
  `RING_CENTER_NORTH_BASED_EMISSION_ANGLE` DOUBLE ,
  `SOLAR_RING_OPENING_ANGLE` DOUBLE ,
  `OBSERVER_RING_OPENING_ANGLE` DOUBLE ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
