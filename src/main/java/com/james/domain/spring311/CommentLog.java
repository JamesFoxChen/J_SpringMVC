package com.james.domain.spring311;

import javax.persistence.*;

@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@Table(name = "t_comment_log")
public class CommentLog extends BaseDomain {
	
	/**
	 * 想去
	 */
	public static final int WANT_TO_GO = 1;
	
	/**
	 * 已经去过了
	 */
	public static final int HAVE_BEAN_TO = 2;
	
	/**
	 * 不想去
	 */
	public static final int DONT_WANT_TO_GO = 3;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	protected int logId;

	@ManyToOne
	@JoinColumn(name = "space_id")
	protected ViewSpace viewSpace;

	protected String ip;

	@Column(name = "comment_type")
	protected int commentType;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public ViewSpace getViewSpace() {
		return viewSpace;
	}

	public void setViewSpace(ViewSpace viewSpace) {
		this.viewSpace = viewSpace;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCommentType() {
		return commentType;
	}

	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}
}