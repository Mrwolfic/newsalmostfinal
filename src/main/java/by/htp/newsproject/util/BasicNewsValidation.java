package by.htp.newsproject.util;

import java.time.LocalDateTime;

public class BasicNewsValidation {
	private final boolean title;
	private final boolean brief;
	private final boolean date;
	private final boolean content;

	private BasicNewsValidation(BasicValidationBuilder obj) {

		this.title = obj.title;
		this.brief = obj.brief;
		this.date = obj.date;
		this.content = obj.content;
	}

	public boolean isTitle() {
		return title;
	}

	public boolean isBrief() {
		return brief;
	}

	public boolean isDate() {
		return date;
	}

	public boolean isContent() {
		return content;
	}

	public boolean checkIsAllDataValid() {

		if (title && date && brief && content) {
			return true;
		}
		return false;
	}

	public static class BasicValidationBuilder {

		private boolean title;
		private boolean brief;
		private boolean date;
		private boolean content;

		public BasicValidationBuilder isTitleEmptyOrNull(String newsTitle) {

			if (newsTitle != null) {
				title = !newsTitle.isBlank();
			} else {
				title = false;
			}
			return this;
		}

		public BasicValidationBuilder isBriefEmptyOrNull(String newsBrief) {

			if (newsBrief != null) {
				brief = !newsBrief.isBlank();
			} else {
				brief = false;
			}
			return this;
		}
		
		public BasicValidationBuilder isDateEmptyOrNull(LocalDateTime newsDate) {

			if (newsDate != null) {
				date = true;
			} else {
				date = false;
			}
			return this;
		}

		public BasicValidationBuilder isNewsContentEmptyOrNull(String newsContent) {

			if (newsContent != null) {
				content = !newsContent.isBlank();
			} else {
				content = false;
			}
			return this;
		}

		public BasicNewsValidation build() {

			return new BasicNewsValidation(this);
		}
	}
}
