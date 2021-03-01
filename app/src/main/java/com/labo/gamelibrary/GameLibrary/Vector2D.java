package com.labo.gamelibrary.GameLibrary;


public class Vector2D {
	private static final float ZERO = 1E-6f;
	private float length;
	private float angle;
	private Point2D startPoint;

	public static final float ANGLE_DIAGONAL_NE = 45f;
	public static final float ANGLE_DIAGONAL_NW = 135f;
	public static final float ANGLE_DIAGONAL_SE = -45f;
	public static final float ANGLE_DIAGONAL_SW = -135f;
	public static final float ANGLE_HORIZONTAL_W = 180f;
	public static final float ANGLE_HORIZONTAL_E = 0f;
	public static final float ANGLE_VERTICAL_N = 90f;
	public static final float ANGLE_VERTICAL_S = -90f;

	public Vector2D() {
		length = angle = 0f;
		startPoint = new Point2D();
	}

	public Vector2D(float length, float angle) {
		this.length = length;
		this.angle = angle;
		startPoint = new Point2D();
	}

	public Vector2D(float length, float angle, float x, float y) {
		this.length = length;
		this.angle = angle;
		startPoint = new Point2D(x, y);
	}

	public static Vector2D sumVectors(Vector2D v1, Vector2D v2) {
		Point2D newEnd = new Point2D(v1.getEndPoint().x + v2.baseX(), v1.getEndPoint().y + v2.baseY());
		return fromPoints(v1.startPoint, newEnd);
	}

	public static Vector2D fromPoints(Point2D start, Point2D end) {
		float dist = Point2D.distance(start, end);
		float baseX = Math.abs(end.x - start.x) > ZERO ? end.x - start.x : 0f;
		float baseY = Math.abs(end.y - start.y) > ZERO ? end.y - start.y : 0f;
		float angle = (float) Math.toDegrees(Math.asin(Math.abs(baseY) / dist));

		if (baseX < 0f && baseY >= 0f) angle = 180f - angle;
		else if (baseX < 0f && baseY < 0f) angle += 180f;
		else if (baseX >= 0f && baseY < 0f) angle = 360f - angle;

		return new Vector2D(dist, roundAngle(angle), start.x, start.y);
	}

	public static Vector2D inverted(Vector2D vector2D) {
		return new Vector2D(vector2D.length, vector2D.angle + 180f, vector2D.startPoint.x, vector2D.startPoint.y);
	}

	private static float roundAngle(float angle) {
		return 1 - (angle - ((int) angle)) < ZERO ? (int) angle + 1f : angle;
	}

	private static float normalizeAngle(float angle) {
		float tmp = angle - ((int) (angle / 360f)) * 360f;
		return tmp >= 0f ? tmp : tmp + 360f;
	}

	public float baseX() {
		float x = (float) Math.cos(Math.toRadians(angle)) * length;
		return Math.abs(x) > ZERO ? x : 0f;
	}

	public float baseY() {
		float y = (float) Math.sin(Math.toRadians(angle)) * length;
		return Math.abs(y) > ZERO ? y : 0f;
	}

	public void mirrorHorizontal() {
		angle = -angle;
	}

	public void mirrorVertical() {
		angle = 180f - angle;
	}

	public float getAngle() {
		return normalizeAngle(angle);
	}

	public float getAngleRadians() {
		return (float) Math.toRadians(angle);
	}

	public float getLength() {
		return length;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public Point2D getStartPoint() {
		return startPoint;
	}

	public Point2D getEndPoint() {
		return new Point2D(startPoint.x + baseX(), startPoint.y + baseY());
	}

	public Point2D getEndPointCanvas() {return new Point2D(startPoint.x + baseX(), startPoint.y - baseY());}

	public void setStartPoint(Point2D startPoint) {
		this.startPoint = startPoint;
	}

	public void updateAngle(float da) {
		angle += da;
	}

	@Override
	public String toString() {
		return "Vector2D{" + "length=" + length + ", angle=" + normalizeAngle(angle) + ", startPoint=" + startPoint +
				'}';
	}
}

