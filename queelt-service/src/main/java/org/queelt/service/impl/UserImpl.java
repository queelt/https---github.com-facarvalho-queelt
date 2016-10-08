package org.queelt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.queelt.common.page.PageList;
import org.queelt.common.page.PageListFactory;
import org.queelt.model.entity.User;
import org.queelt.model.entity.UserFriends;
import org.queelt.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements org.queelt.service.User {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PageListFactory<org.queelt.model.entity.User> pageListFactory;

	public org.queelt.model.entity.User create(org.queelt.model.entity.User user) {

		if (user != null && user.getName() != null) {
			user.setName(user.getName().toLowerCase().trim());
		}

		if (repository.findByName(user.getName()) == null) {
			repository.save(user);
		}

		return repository.findByName(user.getName());
	}

	public org.queelt.model.entity.User id(String id) {
		return repository.findById(id);
	}

	public org.queelt.model.entity.User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public PageList<User> findAll(final int page, final int size) {
		Page<User> pageUsers = repository.findAll(new PageRequest(page, size));
		return pageListFactory.getPageList(pageUsers, pageUsers.getContent());
	}

	public PageList<User> findAllNotEmail(String email, final int page, final int size) {
		Page<User> pageUsers = repository.findAllNotEmail(email, new PageRequest(page, size));
		return pageListFactory.getPageList(pageUsers, pageUsers.getContent());
	}

	@Override
	public PageList<User> findIds(List<String> ids, final int page, final int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList<User> findEmailOrName(String emailOrName, final int page, final int size) {
		return null;
	}

	@Override
	public void invitation(User user, String userId) {
		User guestUser = repository.findOne(userId);
		UserFriends userFriends = new UserFriends();
		userFriends.setUserId(new ArrayList<String>());
		userFriends.getUserId().add(guestUser.getId());
		userFriends.setDateInvitation(new Date());
		if (user.getFriends() == null) {
			user.setFriends(new ArrayList<UserFriends>());
		}

		UserFriends userFriendsExists = user.getFriends().stream().filter(x -> x.getUserId().equals(userId)).findFirst()
				.get();
		if (userFriendsExists != null) {
			user.getFriends().remove(userFriendsExists);
		}
		user.getFriends().add(userFriends);
		repository.save(user);
	}

	@Override
	public PageList<User> invitations(final User user,boolean accept, int page, final int size) {

		List<String> userIds = new ArrayList<String>();
		if (user.getFriends() != null) {
			for (List<String> list : user.getFriends().stream().filter(x -> x.getAccept() == accept)
					.map(p -> p.getUserId()).collect(Collectors.toList())) {
				userIds.addAll(list);
			}
		}
		Page<User> pageUsers = repository.findIds(userIds, new PageRequest(page, size));
		return pageListFactory.getPageList(pageUsers, pageUsers.getContent());
	}

	@Override
	public void acceptInvitation(User user, String userId) {
		User guestUser = repository.findOne(userId);
		
		UserFriends userFriendsExists = user.getFriends().stream().filter(x -> x.getUserId().contains(guestUser.getId()))
				.findFirst().get();
		if (userFriendsExists != null) {
			userFriendsExists.setAccept(true);
			userFriendsExists.setInvitationConfirmationDate(new Date());
		}
		repository.save(user);
	}

	@Override
	public PageList<User> search(String query, int page, int size) {
		Page<User> pageUsers = repository.search(query, new PageRequest(page, size));
		return pageListFactory.getPageList(pageUsers, pageUsers.getContent());
	}

}