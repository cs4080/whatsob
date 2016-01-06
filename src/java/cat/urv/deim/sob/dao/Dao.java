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
public interface Dao {
    public void add(IObject object) throws DaoEx;
    public IObject findRegistered(IKey key) throws DaoEx;
    public void addContact(IObject object1, IObject object2) throws DaoEx;
    public void removeContact(IObject object1, IObject object2) throws DaoEx;
    public String getFriends(IKey key) throws DaoEx;
    public String sendMessage(IObject object1, IObject object2, String message) throws DaoEx;
    public String selectUser(IObject object1, IObject object2) throws DaoEx;
}
