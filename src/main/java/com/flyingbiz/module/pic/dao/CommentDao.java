package com.flyingbiz.module.pic.dao;

import java.util.List;

import com.flyingbiz.module.pic.model.Comment;

public interface CommentDao {

	public void addComment(Comment comment);
	public Comment getCommentById(String commentId);
	public List<Comment> getPicComments(String picId);
	public List<Comment> getCommentsByUserId(String userId);
	public List<Comment> getCommentsByFavourite();
	public List<Comment> getCommentsByFavourite(int limit);
	public List<Comment> getPicComments(String picId, String order);
	public List<Comment> getCommentsByUserId(String userId, String order);
	public List<Comment> getCommentsByFavourite(int start, int limit);
}
