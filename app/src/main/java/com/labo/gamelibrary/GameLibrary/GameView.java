package com.labo.gamelibrary.GameLibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.labo.gamelibrary.R;

import java.util.ArrayList;

@SuppressLint("ViewConstructor") public class GameView extends View {
	private final ArrayList<GameObject> gameObjects;
	private GameViewListener gameViewListener = null;
	private int backgroundColor;
	private Drawable backgroundImage = null;
	private boolean isShowImage = false;

	public final int CANVAS_WIDTH;
	public final int CANVAS_HEIGHT;

	private float i = 0;
	long timer = 0;
	float x = 0f;


	public GameView(Context context) {
		super(context);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		CANVAS_HEIGHT = displayMetrics.heightPixels;
		CANVAS_WIDTH = displayMetrics.widthPixels;
		setMinimumHeight(CANVAS_HEIGHT);
		setMinimumWidth(CANVAS_WIDTH);

		gameObjects = new ArrayList<>();
		backgroundColor = Color.WHITE;
	}

	public GameView(Context context, int gameWidth, int gameHeight) {
		super(context);
		gameObjects = new ArrayList<>();
		backgroundColor = Color.WHITE;

		CANVAS_HEIGHT = gameHeight;
		CANVAS_WIDTH = gameWidth;
		setMinimumHeight(CANVAS_HEIGHT);
		setMinimumWidth(CANVAS_WIDTH);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.scale(1f, -1f, CANVAS_WIDTH / 2f, CANVAS_HEIGHT / 2f);

		if (isShowImage) {
			if (backgroundImage == null) canvas.drawColor(Color.MAGENTA);
			else backgroundImage.draw(canvas);
		} else canvas.drawColor(backgroundColor);

		for (GameObject gameObject : gameObjects)
			gameObject.draw(canvas);

		if (gameViewListener != null) gameViewListener.onFrameUpdate(this);


		// TEMPORARY ////////////////////////////////


//		canvas.save();
//
//
//		Drawable sprite = ContextCompat.getDrawable(getContext(), R.drawable.img);
//
//		Rectangle rectangle = new Rectangle(CANVAS_WIDTH / 2f, CANVAS_HEIGHT / 2f, 300, 700);
//		rectangle.setRotation(i);
//
//		if (timer++ % 1 == 0) {
//			i += 0.5;
//			Log.i("ANGLE", rectangle.getRotation() + "");
//		}
//
//
//		canvas.translate(rectangle.getCenter().x, rectangle.getCenter().y);
//		canvas.rotate(rectangle.getRotation());
//
//		Rect r = RectG.createRect(0, 0, (int) rectangle.getWidth(), (int) rectangle.getHeight());
//
//		//	sprite.setBounds(r);
//		//	sprite.draw(canvas);
//
//		x += 0.05f;
//
//		Point2D point = new Point2D(rectangle.getCenter().x + (float) Math.sin(x / 10f) * 900f, rectangle.getCenter().y);
//		Paint p = new Paint();
//		//p.setStyle(Paint.Style.STROKE);
//		//	p.setStrokeWidth(10);
//
//
//		if (rectangle.contains(point)) p.setColor(Color.GREEN);
//		else p.setColor(Color.RED);
//		canvas.drawRect(r, p);
//
//
//		canvas.restore();
//
//
//		p.setColor(Color.BLACK);
//		canvas.drawRect(RectG.createRect((int) point.x, (int) point.y, 25, 25), p);
//
//		//draw vectors
//		Point2D[] corners = rectangle.getCorners();
//
//		p.setColor(Color.BLUE);
//		p.setStrokeWidth(3);
//		canvas.drawLine(point.x, point.y, corners[0].x, corners[0].y, p);
//		canvas.drawLine(point.x, point.y, corners[1].x, corners[1].y, p);
//		canvas.drawLine(point.x, point.y, corners[2].x, corners[2].y, p);
//		canvas.drawLine(point.x, point.y, corners[3].x, corners[3].y, p);


		//////////////////////////////////////////////


		invalidate();
	}


	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundImage(int drawableID) {
		this.backgroundImage = ContextCompat.getDrawable(getContext(), drawableID);
		backgroundImage.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
	}

	public Drawable getBackgroundImage() {
		return backgroundImage;
	}

	public void showBackgroundImage() {
		isShowImage = true;
	}

	public void hideBackgroundImage() {
		isShowImage = false;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void setGameViewListener(GameViewListener gameViewListener) {
		this.gameViewListener = gameViewListener;
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}
}
