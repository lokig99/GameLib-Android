package com.labo.gamelibrary.GameLibrary;

import java.util.Objects;

public class Point2D {
	public float x, y;

	public static float distance(Point2D p1, Point2D p2) {
		return (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public Point2D() {
		x = y = 0f;
	}

	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point2D point2D = (Point2D) o;
		return Float.compare(point2D.x, x) == 0 && Float.compare(point2D.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Point2D{" + "x=" + x + ", y=" + y + '}';
	}
}
