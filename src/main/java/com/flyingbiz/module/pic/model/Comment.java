package com.flyingbiz.module.pic.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评论类 如果是回复其他人的评论，则在commentsContext内存在@commentId@链接到原评论。
 * 同时replyToUser将会记录被评论的用户ID；如果是直接回复图片，则replyToUser为null
 * 
 * @author ledong
 *
 */
@Entity
@Table(name = "COMMENT")
public class Comment {

	private String commentId;
	private String commentPic;
	private String commentContext;
	private long agree;
	private long disagree;
	private String replyUser;
	private String replyToUser;
	private Date date;

	public Comment() {
	}

	public Comment(String commentId, String commentPic, String commentContext,
			long agree, long disagree, String replyUser, String replyToUser,
			Date date) {
		super();
		this.commentId = commentId;
		this.commentPic = commentPic;
		this.commentContext = commentContext;
		this.agree = agree;
		this.disagree = disagree;
		this.replyUser = replyUser;
		this.replyToUser = replyToUser;
		this.date = date;
	}

	@Id
	@Column(name = "commentId")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Column(name = "commentPic")
	public String getCommentPic() {
		return commentPic;
	}

	public void setCommentPic(String commentPic) {
		this.commentPic = commentPic;
	}

	@Column(name = "commentContext")
	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	@Column(name = "agree")
	public long getAgree() {
		return agree;
	}

	public void setAgree(long agree) {
		this.agree = agree;
	}

	@Column(name = "disagree")
	public long getDisagree() {
		return disagree;
	}

	public void setDisagree(long disagree) {
		this.disagree = disagree;
	}

	@Column(name = "replyUser")
	public String getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}

	@Column(name = "replyToUser")
	public String getReplyToUser() {
		return replyToUser;
	}

	public void setReplyToUser(String replyToUser) {
		this.replyToUser = replyToUser;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
