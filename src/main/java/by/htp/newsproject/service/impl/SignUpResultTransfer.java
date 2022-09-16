package by.htp.newsproject.service.impl;

import by.htp.newsproject.service.fullvalidation.FullNewUserValidation;
import by.htp.newsproject.util.BasicNewUserValidation;

public class SignUpResultTransfer {
	
	private final boolean newUser;
	private final FullNewUserValidation resultFullValidate;
	private final BasicNewUserValidation resultValidateBasic;
	
	public SignUpResultTransfer(SignUpResultTransferBuilder obj) {
		this.newUser = obj.newUser;
		this.resultFullValidate = obj.resultFullValidate;
		this.resultValidateBasic = obj.resultValidateBasic;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public FullNewUserValidation getResultFullValidate() {
		return resultFullValidate;
	}

	public BasicNewUserValidation getResultValidateBasic() {
		return resultValidateBasic;
	}
	
	public static class SignUpResultTransferBuilder {
		
		private boolean newUser;
		private FullNewUserValidation resultFullValidate;
		private BasicNewUserValidation resultValidateBasic;
		
		public SignUpResultTransferBuilder newUser(boolean newUser) {
		
			this.newUser = newUser;		
			return this;
		}
		
		public SignUpResultTransferBuilder resultFullValidate(FullNewUserValidation resultFullValidate) {
			
			this.resultFullValidate = resultFullValidate;		
			return this;
		}
		
		public SignUpResultTransferBuilder resultValidateBasic(BasicNewUserValidation resultValidateBasic) {
			
			this.resultValidateBasic = resultValidateBasic;		
			return this;
		}
		
		public SignUpResultTransfer build() {

			return new SignUpResultTransfer(this);
		}
		
	}
	
}
