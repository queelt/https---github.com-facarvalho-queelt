package org.queelt.api.resource.v1.chat;

import org.queelt.api.resource.v1.user.UserResponse;
import org.queelt.api.resource.v1.user.mapper.UserResponseMapper;
import org.queelt.common.PageResource;
import org.queelt.common.annotation.RestService;
import org.queelt.common.mapper.ListResponse;
import org.queelt.model.entity.ChatMessage;
import org.queelt.service.Chat;
import org.queelt.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;

@RestService
@RequestMapping("/api/chat")
@Api(value = "Chat endpoint", description = "Chat API")
public class ChatResource {
	@Autowired
	private User user;
	
	@Autowired
	private UserResponseMapper userResponseMapper;
	
	
	@Autowired
	private Chat chat;
	
	@RequestMapping(method = RequestMethod.POST, path = "friend/invitation/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus invitation(@PathVariable String userId, OAuth2Authentication auth) {
		 this.user.invitation(user.findByEmail(auth.getName()), userId);
		 return HttpStatus.OK;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "friend/invitations", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<ListResponse<UserResponse>> invitations(OAuth2Authentication auth,
			@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<ListResponse<UserResponse>>(userResponseMapper.mapList(this.user.invitations(user.findByEmail(auth.getName()),false, page, size)),
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "friend/invitation/{userId}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus acceptInvitation(@PathVariable String userId, OAuth2Authentication auth) {
		 this.user.acceptInvitation(user.findByEmail(auth.getName()), userId);
		 return HttpStatus.OK;
	}

	
	@ApiOperation(value = "Get friends", notes = "Returns the users", response = UserResponse.class, authorizations = {
			@Authorization(value = "oauth2", scopes = @AuthorizationScope(scope = "Oauth2", description = "Oauth2 authentication.") ) })
	@RequestMapping(method = RequestMethod.GET, path = "friends", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<ListResponse<UserResponse>> findAllNoIn(OAuth2Authentication auth,
			@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<ListResponse<UserResponse>>(userResponseMapper.mapList(this.user.invitations(user.findByEmail(auth.getName()),true,page,size)),
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = { "friend/{chatFriendsId}/message" })
	public HttpStatus createMessage(@PathVariable String chatFriendsId,
			@RequestBody ChatMessage message, OAuth2Authentication auth) {
		this.chat.createMessage(message, user.findByEmail(auth.getName()), chatFriendsId);
		return HttpStatus.OK;
	}

	@RequestMapping(value = { "friend/{chatFriendsId}/messages" }, method = { RequestMethod.GET })
	public PageResource<ChatMessage> getMessage(@PathVariable String chatFriendsId,
			@RequestParam int page, @RequestParam int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, new String[] { "createDate" }));

		return new PageResource<ChatMessage>(this.chat.findMessageByFriendsId(chatFriendsId, pageable), "page", "size");
	}
	
	
}
