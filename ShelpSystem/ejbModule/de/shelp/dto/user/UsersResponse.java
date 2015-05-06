package de.shelp.dto.user;

import java.util.List;

public class UsersResponse {

    List<UserTO> users;

    public void addUserList(List<UserTO> usersTO) {
	users = usersTO;
    }

}
