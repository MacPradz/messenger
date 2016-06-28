package org.kou.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kou.javabrains.messenger.database.DatabaseClass;
import org.kou.javabrains.messenger.model.Comment;
import org.kou.javabrains.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = getCommentsForMessage(messageId);
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = getCommentsForMessage(messageId);
		return comments.get(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = getCommentsForMessage(messageId);
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = getCommentsForMessage(messageId);
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = getCommentsForMessage(messageId);
		return comments.remove(commentId);
	}

	private Map<Long, Comment> getCommentsForMessage(long messageId) {
		return messages.get(messageId).getComments();
	}
}
