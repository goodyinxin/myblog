package com.myx.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**   
 * @ClassName:  Comment   
 * @Description:TODO(评论实体，包括自关联)   
 * @author: 尹欣
 * @date:   2017年12月5日 上午12:16:18     
 * @Copyright: 2017 
 */  
@Entity
@Table(name = "t_comment")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	private String nickname;
	private String email;
	private String avatar;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@ManyToOne
	private Blog blog;
	
	@OneToMany(mappedBy="parentComment")
	private List<Comment> replyComment = new ArrayList<>();
	@ManyToOne
	private Comment parentComment;
	
	
	public Comment() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public List<Comment> getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(List<Comment> replyComment) {
		this.replyComment = replyComment;
	}
	public Comment getParentComment() {
		return parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", nickname=" + nickname + ", email=" + email + ", avatar=" + avatar
				+ ", createTime=" + createTime + "]";
	}

	
	
}
