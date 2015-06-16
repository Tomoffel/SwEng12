package de.shelp.dao.local;

import java.util.List;

import javax.ejb.Local;

import de.shelp.entities.ShelpSession;
import de.shelp.entities.User;

@Local
public interface ShelpUserDAOLocal {

    public User findUserByName(String username);

    public ShelpSession createSession(User user);

    public User createUser(String username, String password);

    public boolean closeSession(int sessionId);

    public List<User> searchUsers(String searchText);

    public ShelpSession getSession(int sessionId);

    public void updateSession(ShelpSession session);

}
