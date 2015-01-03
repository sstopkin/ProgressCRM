package org.progress.crm.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;

public interface Command<T> {

    T execute(Session session) throws CustomException, SQLException, NoSuchAlgorithmException,
            IOException, InterruptedException, ExecutionException;
}

//CREATE  TABLE IF NOT EXISTS `progresscrm`.`Entities` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `entityName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`AccessTypes` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `accessTypeName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`AccessCategories` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `categoryName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`ACLList` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `idEntity` INT NOT NULL,
//  `idAccessType` INT NOT NULL,
//  `idWorker` INT NOT NULL,
//  `idAccessCategory` INT NOT NULL,
//  FOREIGN KEY (idEntity) REFERENCES Entities(id),
//  FOREIGN KEY (idAccessType) REFERENCES AccessTypes(id),
//  FOREIGN KEY (idWorker) REFERENCES Workers(id),
//  FOREIGN KEY (idAccessCategory) REFERENCES AccessCategories(id),
//  UNIQUE uc_ACLid (idEntity, idAccessType, idWorker, idAccessCategory),
//  PRIMARY KEY (`id`));
