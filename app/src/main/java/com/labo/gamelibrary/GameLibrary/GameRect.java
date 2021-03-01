package com.labo.gamelibrary.GameLibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;


public class GameRect extends GameObject {
	protected final RectG body;
	protected final Paint paint;
	protected GameRectCollisionListener collisionListener = null;
	protected boolean isRigid;
	protected Drawable sprite = null;
	protected boolean isDrawSprite = false;
	protected boolean isDrawBody = true;

	public GameRect(Context context, int x, int y, int width, int height) {
		super(context, x, y);
		isRigid = false;
		body = new RectG(x, y, width, height);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
	}

	public boolean intersects(GameRect gameRect) {
		return RectG.intersects(body, gameRect.body);
	}


	@Override
	public void draw(Canvas canvas) {
		if (isDrawSprite) {
			if (sprite == null) {
				int prevColor = paint.getColor();
				paint.setColor(Color.MAGENTA);
				canvas.drawRect(body.asRect(), paint);
				paint.setColor(prevColor);
			} else {
				sprite.setBounds(body.asRect());
				sprite.draw(canvas);
			}
		}

		if (isDrawBody) canvas.drawRect(body.asRect(), paint);
	}

	@Override
	public boolean move() {
		if (super.move()) {
			body.setPosition((int) x, (int) y);
			if (isRigid && environment != null && collisionListener != null) {
				for (GameObject gameObject : environment) {
					if (gameObject instanceof GameRect) {
						GameRect gameRect = (GameRect) gameObject;
						if (gameRect.isRigid() && intersects(gameRect)) {
							Rect intersection = RectG.intersection(body, gameRect.getBodyCopy());
							CollisionAxis collisionAxis = RectG.getIntersectionAxis(body, gameRect.body);

							// fix position to non overlapping
							double dy = (intersection.height() + 1) * Math.signum(-movement.baseY());
							double dx = (intersection.width() + 1) * Math.signum(-movement.baseX());
							switch (collisionAxis) {
								case HORIZONTAL:
									y += dy;
									break;
								case VERTICAL:
									x += dx;
									break;
								case DIAGONAL:
									x += dx;
									y += dy;
									break;
							}

							body.setPosition((int) x, (int) y);
							collisionListener.onCollision(this, gameRect, collisionAxis);
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	public int getWidth() {
		return body.getDimensions()[0];
	}

	public void setCollisionListener(GameRectCollisionListener collisionListener) {
		this.collisionListener = collisionListener;
	}


	public void setWidth(int width) {
		body.setDimensions(width, body.getDimensions()[1]);
	}

	public int getHeight() {
		return body.getDimensions()[1];
	}

	public void setHeight(int height) {
		body.setDimensions(body.getDimensions()[0], height);
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	public int getColor(int color) {
		return paint.getColor();
	}

	public boolean isRigid() {
		return isRigid;
	}

	public void setRigid(boolean rigid) {
		isRigid = rigid;
	}

	public RectG getBodyCopy() {
		return body.getCopy();
	}

	public void setSprite(int drawableID) {
		sprite = ContextCompat.getDrawable(context, drawableID);
	}

	public Drawable getSprite() {
		return sprite;
	}

	public void showSprite() {
		isDrawSprite = true;
		isDrawBody = false;
	}

	public void hideSprite() {
		isDrawSprite = false;
		isDrawBody = true;
	}

	public void showBody() {isDrawBody = true;}

	public void hideBody() {isDrawBody = false;}
}
