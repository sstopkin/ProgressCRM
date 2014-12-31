package org.progress.crm.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;

public interface  Command<T> {
    T execute(Session session) throws CustomException, SQLException, NoSuchAlgorithmException,
            IOException, InterruptedException, ExecutionException;
}

//CREATE  TABLE IF NOT EXISTS `progresscrm`.`DataTypes` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `typeName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`DataFields` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `idDataTypes` INT NOT NULL,
//  FOREIGN KEY (idDataTypes) REFERENCES DataTypes(id),
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`Roles` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `roleName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
//  PRIMARY KEY (`id`));
//  
//CREATE  TABLE IF NOT EXISTS `progresscrm`.`DataFields` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `accessType` INT NOT NULL,
//  `idRoleName` INT NOT NULL,
//  `idDataTypes` INT NOT NULL,
//  FOREIGN KEY (idDataTypes) REFERENCES DataTypes(id),
//  FOREIGN KEY (idRoleName) REFERENCES Roles(id),
//  PRIMARY KEY (`id`));
