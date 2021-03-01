package com.labo.gamelibrary.GameLibrary;

import android.graphics.Path;


public class Rectangle {
	private Point2D center;
	private float width;
	private float height;
	private Vector2D top, bottom, left, right;
	private float rotation;

	public Rectangle(float width, float height) {
		center = new Point2D();
		this.height = height;
		this.width = width;
		rotation = 0f;
		recreateWalls();
	}

	public Rectangle(float x, float y, float width, float height) {
		center = new Point2D(x, y);
		this.height = height;
		this.width = width;
		rotation = 0f;
		recreateWalls();
	}

	public Rectangle(float x, float y, float width, float height, float rotation) {
		center = new Point2D(x, y);
		this.height = height;
		this.width = width;
		this.rotation = rotation;
		recreateWalls();
	}

	private void recreateWalls() {
		Point2D bottomLeft, bottomRight, topLeft, topRight;
		float diagonalAngle = (float) Math.toDegrees(Math.atan(height / width));
		float diffH = 180f - 2 * diagonalAngle;
		float diffV = 180f - diffH;
		Vector2D diagonal = new Vector2D((float) (height / (2 * Math.sin(Math.toRadians(diagonalAngle)))),
				diagonalAngle + rotation, center.x, center.y);

		topRight = diagonal.getEndPoint();
		diagonal.updateAngle(diffH);
		topLeft = diagonal.getEndPoint();
		diagonal.updateAngle(diffV);
		bottomLeft = diagonal.getEndPoint();
		diagonal.updateAngle(diffH);
		bottomRight = diagonal.getEndPoint();

		top = Vector2D.fromPoints(topLeft, topRight);
		bottom = Vector2D.fromPoints(bottomRight, bottomLeft);
		left = Vector2D.fromPoints(topLeft, bottomLeft);
		right = Vector2D.fromPoints(bottomRight, topRight);
	}

	public boolean contains(Point2D point) {
		if (point.equals(center) || point.equals(top.getStartPoint()) || point.equals(top.getEndPoint()) ||
				point.equals(bottom.getStartPoint()) || point.equals(bottom.getEndPoint())) return true;

		Vector2D conn = Vector2D.fromPoints(bottom.getStartPoint(), point);
		float angle = conn.getAngle();
		if (right.getAngle() > 270f && angle < 90f) angle += 360f;
		if (angle >= right.getAngle() && angle <= right.getAngle() + 90f) {
			conn = Vector2D.fromPoints(top.getStartPoint(), point);
			angle = conn.getAngle();
			if (left.getAngle() > 270f && angle < 90f) angle += 360f;
			return angle >= left.getAngle() && angle <= left.getAngle() + 90f;
		}

		return false;
	}

	public Path getPath() {
		Path path = new Path();

		//top line
		path.setLastPoint(top.getStartPoint().x, top.getStartPoint().y);
		path.lineTo(right.getStartPoint().x, right.getStartPoint().y);

		//right line
		path.lineTo(bottom.getStartPoint().x, bottom.getStartPoint().y);

		//bottom line
		path.lineTo(left.getStartPoint().x, left.getStartPoint().y);

		//left line
		path.lineTo(top.getStartPoint().x, top.getStartPoint().y);

		return path;
	}


	@Override
	public String toString() {
		return "Rectangle{" + "center=" + center + ", width=" + width + ", height=" + height + ", top=" + top +
				", bottom=" + bottom + ", left=" + left + ", right=" + right + '}';
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		recreateWalls();
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		recreateWalls();
	}

	public float getWidth() {
		return width;
	}

	public float getDiagonalLength() {
		return (float) Math.sqrt(height * height + width * width);
	}

	public void setWidth(float width) {
		this.width = width;
		recreateWalls();
	}

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
		recreateWalls();
	}

	/** returns array of rectangle corners: [topLeft, topRight, bottomRight, bottomLeft]**/
	public Point2D[] getCorners() {
		return new Point2D[] {top.getStartPoint(), top.getEndPoint(), bottom.getStartPoint(), bottom.getEndPoint()};
	}

	public Point2D[] getCornersCanvas() {
		return new Point2D[] {top.getStartPoint(), top.getEndPointCanvas(), bottom.getStartPoint(), bottom.getEndPointCanvas()};
	}
}
