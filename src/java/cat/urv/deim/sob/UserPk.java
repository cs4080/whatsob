/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob;

import cat.urv.deim.sob.dao.IKey;

/**
 *
 * @author carlos
 */
public class UserPk implements IKey{
    private String id;
    
    public UserPk(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }

    public boolean equals(Object other){
        return this.id.equals(((UserPk)other).getId());
    }
}
