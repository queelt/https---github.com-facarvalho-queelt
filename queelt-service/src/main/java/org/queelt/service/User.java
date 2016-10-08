package org.queelt.service;

import java.util.List;

import org.queelt.common.page.PageList;


public interface User {

	org.queelt.model.entity.User create(org.queelt.model.entity.User user);
	
	PageList<org.queelt.model.entity.User> findAllNotEmail(String email, final int page, final int size);
	
	org.queelt.model.entity.User id(String id);
	
	org.queelt.model.entity.User findByEmail(String email);
	
	PageList<org.queelt.model.entity.User> findIds(List<String> ids,final int page, final int size);
	
	PageList<org.queelt.model.entity.User> findEmailOrName(String emailOrName,final int page, final int size);
	
	PageList<org.queelt.model.entity.User> search(String query,final int page, final int size);
	
	void invitation(final org.queelt.model.entity.User user,String userId);
	
	PageList<org.queelt.model.entity.User> invitations(final org.queelt.model.entity.User user,boolean accept,final int page, final int size);
	
	void acceptInvitation(final org.queelt.model.entity.User user,String userId);
	
}
