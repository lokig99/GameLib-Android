package com.labo.gamelibrary.GameLibrary;

import android.graphics.Rect;

public class RectG {
	private Rect rect;
	private int centerX, centerY;
	private int width, height;

	public RectG getCopy() {
		return new RectG(centerX, centerY, width, height);
	}

	public RectG(int centerX, int centerY, int width, int height) {
		set(centerX, centerY, width, height);
	}

	public void set(int centerX, int centerY, int width, int height) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.height = height;
		rect = createRect(centerX, centerY, width, height);
	}

	public Rect asRect() {
		return copy(rect);
	}

	public void setPosition(int centerX, int centerY) {
		set(centerX, centerY, width, height);
	}

	public void setDimensions(int width, int height) {
		set(centerX, centerY, width, height);
	}

	/**
	 * get position [centerX, centerY]
	 **/
	public int[] getPosition() {
		return new int[]{centerX, centerY};
	}

	/**
	 * get dimensions [width, height]
	 **/
	public int[] getDimensions() {
		return new int[]{width, height};
	}

	public static boolean intersects(RectG a, RectG b) {
		return Rect.intersects(a.rect, b.rect);
	}

	public static Rect intersection(RectG a, RectG b) {
		Rect aRectCopy = copy(a.rect);
		if (aRectCopy.intersect(b.rect)) return aRectCopy;
		return new Rect();
	}

	public static CollisionAxis getIntersectionAxis(RectG a, RectG b) {
		Rect intersection = intersection(a, b);
		int interWidth = intersection.width();
		int interHeight = intersection.height();

		if (interHeight == interWidth && interHeight == 0) return CollisionAxis.NONE;

		if (interHeight > interWidth) return CollisionAxis.VERTICAL;
		else if (interHeight < interWidth) return CollisionAxis.HORIZONTAL;
		else return CollisionAxis.DIAGONAL;
	}


	public static Rect createRect(int centerX, int centerY, int width, int height) {
		int left, top, right, bottom;
		left = centerX - width / 2;
		top = centerY - height / 2;
		right = centerX + width / 2;
		bottom = centerY + height / 2;

		return new Rect(left, top, right, bottom);
	}

	private static Rect copy(Rect source) {
		return new Rect(source.left, source.top, source.right, source.bottom);
	}

}
