//package org.progress.crm.logic;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
////DataTypes id typeName
////DataFields id dataTypesId
////Roles id roleName
////Permissions id accesstype roleNameId dataTypesId

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


//@Entity
//@Table(name = "Permissions")
//public class Permissions implements Serializable {
//
//    private int id;
//    private int idWorker;
//    private int permissions;
//    private Date creationDate;
//
//    public Permissions() {
//    }
//
//    public Permissions(int idWorker, int permissions) {
//        this.idWorker = idWorker;
//        this.permissions = permissions;
//        this.creationDate = new Date();
//    }
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "idWorker")
//    public int getIdWorker() {
//        return idWorker;
//    }
//
//    public void setIdWorker(int idWorker) {
//        this.idWorker = idWorker;
//    }
//
//    @Column(name = "Permissions")
//    public int getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(int permissions) {
//        this.permissions = permissions;
//    }
//
//    @Column(name = "CreationDate")
//    @Temporal(TemporalType.TIMESTAMP)
//    public Date getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//}
