package de.shelp.dao;

import javax.ejb.Local;

import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

@Local
public interface ShelpUserDAOLocal {

    User findUserByName(String username);

    ShelpSession createSession(User user);

		
}
