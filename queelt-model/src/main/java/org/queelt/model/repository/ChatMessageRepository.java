package org.queelt.model.repository;

import org.queelt.model.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage, String> {

	@Query(value = "{chatFriendsId:?0}")
	public Page<ChatMessage> findByChatFriendsId(String chatFriendsId, Pageable pageable);

}