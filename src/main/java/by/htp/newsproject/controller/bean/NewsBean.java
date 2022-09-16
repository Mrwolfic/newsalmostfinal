package by.htp.newsproject.controller.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class NewsBean implements Serializable {

	private static final long serialVersionUID = -4651063336408917289L;

	private String title;
	private LocalDateTime date;
	private String brief;
	private String content;
	private int userID;
	private int newsID;

	public NewsBean() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getNewsID() {
		return newsID;
	}

	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brief, content, date, newsID, title, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsBean other = (NewsBean) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date) && Objects.equals(newsID, other.newsID)
				&& Objects.equals(title, other.title) && userID == other.userID;
	}

	@Override
	public String toString() {
		return "NewsBean [title=" + title + ", date=" + date + ", brief=" + brief + ", content=" + content + ", userID="
				+ userID + ", newsID=" + newsID + "]";
	}

}
