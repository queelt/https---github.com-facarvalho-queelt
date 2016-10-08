package org.queelt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Chat {
	
	void createMessage(org.queelt.model.entity.ChatMessage message, org.queelt.model.entity.User user, String chatGroupId);
	Page<org.queelt.model.entity.ChatMessage> findMessageByFriendsId(String chatGroupId,  Pageable pageable);
}
