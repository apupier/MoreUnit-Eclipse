package org.moreunit;

import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.moreunit.log.LogHandler;

public class LinuxAndWindowsShortcutStrategy extends ShortcutStrategy
{

	@Override
	public void pressMoveShortcut()
	{
		KeyboardFactory.getAWTKeyboard().pressShortcut(SWT.ALT | SWT.SHIFT, 'v');
	}

	@Override
	public void openPreferences() 
	{
	    LogHandler.getInstance().handleInfoLog("Active shell: " + bot.activeShell().getText());
		SWTBotMenu windowMenu = bot.menu("Window");
		LogHandler.getInstance().handleInfoLog("Window menu reached");
		SWTBotMenu preferencesMenu = windowMenu.menu("Preferences");
		LogHandler.getInstance().handleInfoLog("Preferences menu reached");
		preferencesMenu.click();
		LogHandler.getInstance().handleInfoLog("Window -> Preferences menu clicked");
	}

	@Override
	public void pressRenameShortcut() 
	{
		KeyboardFactory.getAWTKeyboard().pressShortcut(SWT.SHIFT | SWT.ALT, 'r');	
	}

	@Override
	public void openProperties(SWTBotTreeItem projectItem)
	{
		//projectItem.setFocus();
		//System.out.println("Bot: "+bot);
		//final org.hamcrest.Matcher<MenuItem> matcher = allOf(widgetOfType(MenuItem.class)); 
		//allOf(widgetOfType(MenuItem.class))
		//bot.widgets();
		/*
		Matcher<MenuItem> matcher = allOf(widgetOfType(MenuItem.class));
		MenuFinder finder = new MenuFinder();
		final List<MenuItem> findMenus = finder.findMenus(matcher);
		//List<? extends MenuItem> widgets = new SWTBot().widgets(matcher);
		System.out.println("widgets: "+findMenus);
			bot.activeShell().display.asyncExec(new Runnable() {
				
				@Override
				public void run() {
					for(MenuItem item : findMenus)
					{
						System.out.println("Item: ["+item.getText()+"] - ID: ["+item.getID()+"]");
					}
					
				}
			});
		//bot.menu("Help").menu("Welcome").click();
		 */
		bot.menu("File").menu("Properties").click();
		//SWTBotMenu contextMenu = projectItem.contextMenu("Properties");
		//bot.sleep(3000);
		//contextMenu.click();
	}
}
