/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.dao;

/**
 *
 * @author carlos
 */
public class DaoEx extends Exception{
    private static final long serialVersionUID = 1L;
    
    public DaoEx(String msg){
        super(msg);
    }
}
