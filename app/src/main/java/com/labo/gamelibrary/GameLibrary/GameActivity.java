package com.labo.gamelibrary.GameLibrary;


import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public abstract class GameActivity extends Activity implements GameViewListener {
	protected GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

		// Hide the status bar.
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// keep display on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// setup gameView
		gameView = new GameView(this);
		gameView.setGameViewListener(this);
		setContentView(gameView);

		setup();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		// Hide the status bar.
		if (hasFocus) getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public void onFrameUpdate(GameView gameView) {
		update();
	}

	public abstract void setup();

	public abstract void update();
}


