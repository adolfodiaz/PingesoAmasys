/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import entity.UserType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pingeso
 */
@Stateless
public class UserManagementSB implements UserManagementSBLocal {
    @PersistenceContext(unitName = "Amasys-ejbPU")
    private EntityManager em;

    @Override
    public void newUser(Long id, String firstName, String lastName, String email, String homePhone, String cellPhone, int rut, String userTypeName) {
        Query q = this.em.createNamedQuery("UserType.findNameUserType");
        q.setParameter("name", userTypeName);
        UserType userType;
        try {
            userType = (UserType)q.getSingleResult();            
            User newUser = new User();                        
            newUser.setCellPhone(cellPhone);
            newUser.setEmail(email);
            newUser.setFirstName(firstName);
            newUser.setHomePhone(homePhone);
            newUser.setId(id);
            newUser.setLastName(lastName);
            newUser.setRut(rut);
            newUser.setUserType(userType);
            
            em.persist(newUser);
        }
        catch (NoResultException nre) {
            //return null;
        }
        //return res;     
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public User findUserRut(int rut) {
        Query q = this.em.createNamedQuery("User.findByRut");
        q.setParameter("rut", rut);
        User res;
        try {
            res = (User)q.getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }
        return res;                   
    }

}
