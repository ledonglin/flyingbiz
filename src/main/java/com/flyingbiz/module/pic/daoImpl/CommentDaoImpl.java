package com.flyingbiz.module.pic.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.flyingbiz.module.login.dao.BaseHibernateDAO;
import com.flyingbiz.module.pic.dao.CommentDao;
import com.flyingbiz.module.pic.model.Comment;

public class CommentDaoImpl extends BaseHibernateDAO implements CommentDao {

	@Override
	public void addComment(Comment comment) {
		getSession().save(comment);
	}

	@Override
	public Comment getCommentById(String commentId) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("commentId", commentId)).list();
		if (commentList == null || commentList.size() == 0)
			return null;
		else
			return commentList.get(0);
	}

	/**
	 * 通过图片Id查找评论。默认是升序排列
	 */
	@Override
	public List<Comment> getPicComments(String picId) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("picId", picId)).addOrder(Order.asc("date")).list();
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}
	
	/**
	 * 通过图片Id查找评论。Order(asc)升序 Order(desc)降序
	 */
	@Override
	public List<Comment> getPicComments(String picId, String order) {
		
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date") : Order.asc("date");
		
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("picId", picId)).addOrder(orderType).list();
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 通过用户Id查找评论。默认降序
	 */
	@Override
	public List<Comment> getCommentsByUserId(String userId) {
		return getCommentsByUserId(userId,"desc");
	}
	/**
	 * 通过用户Id查找评论。Order(asc)升序 Order(desc)降序
	 */
	@Override
	public List<Comment> getCommentsByUserId(String userId, String order) {
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date") : Order.asc("date");

		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("userId", userId)).addOrder(orderType).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 获取最精彩的评论
	 */
	@Override
	public List<Comment> getCommentsByFavourite() {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.addOrder(Order.desc("agree")).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 获取最精彩的评论
	 * @param start 开始条数
	 * @param limit 限制条数
	 * @return
	 */
	@Override
	public List<Comment> getCommentsByFavourite(int start, int limit) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.addOrder(Order.desc("agree")).setFirstResult(start).setFetchSize(limit).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	@Override
	public List<Comment> getCommentsByFavourite(int limit) {
		return getCommentsByFavourite(0, limit);
	}

}
