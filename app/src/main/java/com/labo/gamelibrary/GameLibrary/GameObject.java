package com.labo.gamelibrary.GameLibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public abstract class GameObject {
	protected float x, y;
	protected Vector2D movement;
	protected Rect movementConstraintBox = new Rect();
	protected boolean isMovementConstraintEnabled;
	protected Context context;
	protected ArrayList<GameObject> environment = null;


	public GameObject(Context context) {
		this.context = context;
		movement = new Vector2D();
		isMovementConstraintEnabled = false;
	}

	public GameObject(Context context, int x, int y) {
		this(context);
		this.x = x;
		this.y = y;
	}

	public GameObject(Context context, int x, int y, Vector2D movement) {
		this(context, x, y);
		this.movement = movement;
	}

	public void enableMovementConstraint() {
		isMovementConstraintEnabled = true;
	}

	public void disableMovementConstraint() {
		isMovementConstraintEnabled = false;
	}

	public abstract void draw(Canvas canvas);

	public boolean move() {
		x += movement.baseX();
		y += movement.baseY();

		if (isMovementConstraintEnabled) {
			if (!movementConstraintBox.contains((int) x, (int) y)) {
				x -= movement.baseX();
				y -= movement.baseY();
				return false;
			}
		}

		return true;
	}

	public double getSpeed() {
		return movement.getLength();
	}

	public void setSpeed(float speed) {
		movement.setLength(speed);
	}

	public void updateSpeed(float ds) {
		movement.setLength(movement.getLength() + ds);
	}

	public double getDirection() {
		return movement.getAngle();
	}

	public void setDirection(float angle) {
		movement.setAngle(angle);
	}

	public void updateDirection(float angle) {
		movement.setAngle(movement.getAngle() + angle);
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Vector2D getMovementVector() {
		return movement;
	}

	public void setMovementConstraintBox(int x, int y, int width, int height) {
		movementConstraintBox.set(RectG.createRect(x, y, width, height));
	}

	public Rect getMovementConstraintBox() {
		return movementConstraintBox;
	}

	public ArrayList<GameObject> getEnvironment() {
		return environment;
	}

	public void setEnvironment(ArrayList<GameObject> environment) {
		this.environment = environment;
	}
}
