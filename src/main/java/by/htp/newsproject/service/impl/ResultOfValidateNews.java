package by.htp.newsproject.service.impl;

import by.htp.newsproject.service.fullvalidation.FullNewsValidation;
import by.htp.newsproject.util.BasicNewsValidation;

public class ResultOfValidateNews {

	public final FullNewsValidation fullNewsValidationResult;
	public final BasicNewsValidation basicNewsValidationResult;
	public final boolean newsStatus;

	public ResultOfValidateNews(ResultOfValidateNewsBuilder obj) {
		this.fullNewsValidationResult = obj.fullNewsValidationResult;
		this.basicNewsValidationResult = obj.basicNewsValidationResult;
		this.newsStatus = obj.newsStatus;
	}

	public FullNewsValidation getFullNewsValidationResult() {
		return fullNewsValidationResult;
	}

	public BasicNewsValidation getBasicNewsValidationResult() {
		return basicNewsValidationResult;
	}

	public boolean isNewsStatus() {
		return newsStatus;
	}

	public static class ResultOfValidateNewsBuilder {

		private FullNewsValidation fullNewsValidationResult;
		private BasicNewsValidation basicNewsValidationResult;
		private boolean newsStatus;
		
		public ResultOfValidateNewsBuilder resultFullValidate(FullNewsValidation resultFullValidate) {

			this.fullNewsValidationResult = resultFullValidate;
			return this;
		}

		public ResultOfValidateNewsBuilder resultValidateBasic(BasicNewsValidation resultValidateBasic) {

			this.basicNewsValidationResult = resultValidateBasic;
			return this;
		}
		
		public ResultOfValidateNewsBuilder checkNewsStatus(boolean newsStatus) {

			this.newsStatus = newsStatus;
			return this;
		}

		public ResultOfValidateNews build() {

			return new ResultOfValidateNews(this);
		}
	}
}
