package org.queelt.api.resource.v1.user.mapper;

import java.util.Date;

import org.queelt.api.resource.v1.user.UserRequest;
import org.queelt.common.annotation.Mapper;
import org.queelt.common.mapper.RequestMapper;
import org.queelt.model.entity.User;


@Mapper
public final class UserRequestMapper implements RequestMapper<User, UserRequest> {

	@Override
	public User map(final UserRequest userRequest) {
		
		User user = new User();
		user.setCreateDate(new Date());
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setPassword(userRequest.getPassword());
		user.setEmail(userRequest.getEmail());
		user.setUpdateDate(new Date());
		return user;
	}

}
