package de.shelp.dto.user;

import java.util.List;

public class UsersResponse {

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
