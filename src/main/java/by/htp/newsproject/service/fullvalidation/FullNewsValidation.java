package by.htp.newsproject.service.fullvalidation;

public class FullNewsValidation {
	
	private final boolean title;
	private final boolean brief;
	private final boolean content;

	private FullNewsValidation(FullNewsValidationBuilder obj) {

		this.title = obj.title;
		this.brief = obj.brief;
		this.content = obj.content;
	}
	
	public boolean isTitle() {
		return title;
	}

	public boolean isBrief() {
		return brief;
	}

	public boolean isContent() {
		return content;
	}

	public boolean checkIsAllDataValid() {

		if (title && brief && content) {
			return true;
		}
		return false;
	}

	public static class FullNewsValidationBuilder {

		private boolean title;
		private boolean brief;
		private boolean content;

		public FullNewsValidationBuilder isTitleFillSize(String newsTitle) {

			final int titleSize = 56;

			if (newsTitle.length() > titleSize) {
				title = false;
			} else {
				title = true;
			}
			return this;
		}

		public FullNewsValidationBuilder isBriefFillSize(String newsBrief) {

			final int briefSize = 150;

			if (newsBrief.length() > briefSize) {
				brief = false;
			} else {
				brief = true;
			}
			return this;
		}

		public FullNewsValidationBuilder isNewsContentFillSize(String newsContent) {

			final int contentSize = 3000;

			if (newsContent.length() > contentSize) {
				content = false;
			} else {
				content = true;
			}
			return this;
		}

		public FullNewsValidation build() {

			return new FullNewsValidation(this);
		}

	}
}
