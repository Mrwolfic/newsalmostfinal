package by.htp.newsproject.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.newsproject.controller.impl.Authorize;
import by.htp.newsproject.controller.impl.CancelSaveNews;
import by.htp.newsproject.controller.impl.ChangeLocale;
import by.htp.newsproject.controller.impl.DeleteNews;
import by.htp.newsproject.controller.impl.EditNews;
import by.htp.newsproject.controller.impl.GoToEditNewsPage;
import by.htp.newsproject.controller.impl.GoToAuthorizationPage;
import by.htp.newsproject.controller.impl.GoToBasicLayout;
import by.htp.newsproject.controller.impl.GoToAddNewsPage;
import by.htp.newsproject.controller.impl.GoToErrorPage;
import by.htp.newsproject.controller.impl.GoToPortionNewsPage;
import by.htp.newsproject.controller.impl.GoToSignUpPage;
import by.htp.newsproject.controller.impl.GoToViewOneNewsPage;
import by.htp.newsproject.controller.impl.SaveNews;
import by.htp.newsproject.controller.impl.SignOut;
import by.htp.newsproject.controller.impl.SignUp;

public final class CommandFactory {
	
		private final Map<CommandName, Command> commands = new HashMap<>();
		
		public CommandFactory() {
			
			commands.put(CommandName.GO_TO_AUTHRIZATION_PAGE, new GoToAuthorizationPage());
			commands.put(CommandName.GO_TO_SIGNUP_PAGE, new GoToSignUpPage());
			commands.put(CommandName.AUTHORIZE, new Authorize());
			commands.put(CommandName.SIGNUP, new SignUp());
			commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
			commands.put(CommandName.GO_TO_BASIC_LAYOUT, new GoToBasicLayout());
			commands.put(CommandName.SIGNOUT, new SignOut());
			commands.put(CommandName.SAVE_NEWS, new SaveNews());
			commands.put(CommandName.CANCEL_SAVE_NEWS, new CancelSaveNews());
			commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
			commands.put(CommandName.GO_TO_EDIT_NEWS_PAGE, new GoToEditNewsPage());
			commands.put(CommandName.EDIT_NEWS, new EditNews());
			commands.put(CommandName.GO_TO_VIEW_ONE_NEWS_PAGE, new GoToViewOneNewsPage());
			commands.put(CommandName.DELETE_NEWS, new DeleteNews());
			commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
			commands.put(CommandName.GO_TO_PORTION_NEWS_PAGE, new GoToPortionNewsPage());
		}
		
		public Command getCommands(String name) {
			
			CommandName commandname = CommandName.valueOf(name.toUpperCase());
			Command command = commands.get(commandname);
			return command;
		}
		
	}

