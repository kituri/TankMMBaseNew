package com.kituri.tankmmdatabase.ui.common;

import android.view.View;

public interface ActionBarInterface {
	
	public void hideActionBar();
	
	public void setHomeAction(boolean isHome);
	
	public void setTitle(int resid);
	
	public void setTitle(String text);
	
	public void setImageLogo(int resid);
	
	public View setMyView(int layoutid);
	
	public void addAction(int view_id, View item);
	
	public void addAction(int view_id, int res_id);
	public void addRefreshAction(int view_id, int res_id);
	public void addButtonAction(int view_id, int res_id);
	public void addButtonAction(int view_id, int res_id, int bg_id);
	
	public void onActionBarItem(int itemid);
	
	public void removeAllActions();
	
	public void removeActionAt(int index);
	
	public void setProgressBarVisibility(int visibility);
	
}
