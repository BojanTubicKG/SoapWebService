/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author User
 */
@WebService
public interface Translate {
     
    @WebMethod
    String translate(String rec,String izvorniJezik,String trazeniJezik);
}
