package org.queelt.api.resource.v1.user;

import org.queelt.api.resource.v1.user.mapper.UserRequestMapper;
import org.queelt.api.resource.v1.user.mapper.UserResponseMapper;
import org.queelt.common.annotation.RestService;
import org.queelt.common.mapper.ListResponse;
import org.queelt.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestService
@RequestMapping("/api/user")
@Api(value = "User endpoint", description = "Users API")
public class UserResource {

	@Autowired
	private User user;

	@Autowired
	private UserRequestMapper userRequestMapper;

	@Autowired
	private UserResponseMapper userResponseMapper;

	@ApiOperation(value = "Create a new user for a app", notes = "Returns the created user", response = UserResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<UserResponse> create(@RequestBody final UserRequest userRequest) {

		return new ResponseEntity<UserResponse>(userResponseMapper.map(user.create(userRequestMapper.map(userRequest))),
				HttpStatus.OK);

	}
	@RequestMapping(method = RequestMethod.GET, path = "search/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<ListResponse<UserResponse>> search(@PathVariable String query, OAuth2Authentication auth, @RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<ListResponse<UserResponse>>(userResponseMapper.mapList(this.user.search(query,page,size)),
				HttpStatus.OK);
	}
	
}
