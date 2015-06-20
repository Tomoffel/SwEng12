package de.shelp.dto.user;

import java.util.List;

import de.shelp.dto.ReturnCodeResponse;

/**
 * Klasse repräsentiert die Rückgabe für einen Benutzer ({@link UserTO}). Erbt von
 * {@link ReturnCodeResponse}.
 * 
 * @author Jos Werner
 *
 */
public class UsersResponse extends ReturnCodeResponse{

    private static final long serialVersionUID = 5259948611226585889L;
    private List<UserTO> users;

    public void setUserList(List<UserTO> usersTO) {
	users = usersTO;
    }

    public List<UserTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserTO> users) {
        this.users = users;
    }
    
    

}
