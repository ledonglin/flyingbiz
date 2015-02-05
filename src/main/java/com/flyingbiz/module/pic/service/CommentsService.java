package com.flyingbiz.module.pic.service;

import com.flyingbiz.module.pic.model.Comment;

public interface CommentsService {

	public void addAgree(String CommentId);

	public void addDisagree(String CommentId);

	public void rollAgree(String CommentId);

	public void rollDisAgree(String CommentId);
	
	public void addComment(Comment comment);
	
	public void replayComment(Comment comment, String commentId);
}
