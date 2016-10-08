package org.queelt.service.impl;

import java.util.Date;

import org.queelt.common.Functions;
import org.queelt.model.entity.ChatMessage;
import org.queelt.model.entity.User;
import org.queelt.model.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChatImpl implements org.queelt.service.Chat {

	@Autowired
	private ChatMessageRepository chatMessageRepository;


	public void createMessage(ChatMessage message, User user, String chatFriendsId) {

		message.setUserId(user.getId());
		message.setUserName(user.getName());
		message.setCreateDate(new Date());
		message.setChatFriendsId(chatFriendsId);
		if (message != null && message.getMessage() != null) {
			message.setMessage(Functions.html2text(message.getMessage()));
		}
		chatMessageRepository.save(message);

	}

	public Page<ChatMessage> findMessageByFriendsId(String chatFriendsId, Pageable pageable) {
		return chatMessageRepository.findByChatFriendsId(chatFriendsId, pageable);
	}
	

}
