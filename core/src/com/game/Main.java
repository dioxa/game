package com.game;

import com.badlogic.gdx.Game;
import com.game.Screens.MainScreen;


public class Main extends Game {

	@Override
	public void create() {
		setScreen(new MainScreen(this));
	}
}
