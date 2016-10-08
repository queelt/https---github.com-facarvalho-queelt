package org.queelt.api.resource.v1.user.mapper;

import org.queelt.api.resource.v1.user.UserResponse;
import org.queelt.common.annotation.Mapper;
import org.queelt.common.mapper.ListResponse;
import org.queelt.common.mapper.ResponseMapper;
import org.queelt.common.page.PageList;
import org.queelt.model.entity.User;

@Mapper
public final class UserResponseMapper implements ResponseMapper<UserResponse, User> {

	@Override
	public UserResponse map(User user) {
		
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getId());
		userResponse.setEmail(user.getEmail());
		userResponse.setName(user.getName());
		return userResponse;
	}

	@Override
	public ListResponse<UserResponse> mapList(PageList<org.queelt.model.entity.User> input) {
		ListResponse<UserResponse> usersResponse = new ListResponse<UserResponse>(input);
        for (org.queelt.model.entity.User user : input.getList()) {
        	usersResponse.addItem(this.map(user));
        }
        return usersResponse;
	}

	
	

}
