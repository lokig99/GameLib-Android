package com.labo.gamelibrary.GameLibrary;

public interface GameRectCollisionListener {

	void onCollision(GameRect self, GameRect colliding, CollisionAxis collisionAxis);
}
