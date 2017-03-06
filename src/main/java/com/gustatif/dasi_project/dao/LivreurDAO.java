/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gustatif.dasi_project.dao;

import com.gustatif.dasi_project.metier.modele.Livreur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author carhiliuc
 */
public class LivreurDAO extends CRUDDAo<Livreur>{

    @Override
    protected Class<Livreur> getEntityClass() {
        return Livreur.class;
    }
    
}
