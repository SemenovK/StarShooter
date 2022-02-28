package com.star.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Texture texture;
    private Vector2 position;
    private float angle;
    private Vector2 lastDisplacement;

    public Asteroid() {
        texture = new Texture("asteroid.png");
        angle = (float) ((Math.random() * 165 - 15) + 15);
        position = new Vector2(128, 128);
        this.lastDisplacement = new Vector2(0, 0);

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 128, position.y - 128, 128, 128,
                256, 256, 1, 1, 1, 0, 0, 256, 256,
                false, false);

        if ((position.x < 0 - 256 || position.x > ScreenManager.SCREEN_WIDTH + 256) ||
                (position.y < 0 - 256) || (position.y > ScreenManager.SCREEN_HEIGHT + 256)) {
            restart(batch);
        }
    }

    public void update(float dt) {
        position.x += MathUtils.cosDeg(angle) * 150f * dt;
        position.y += MathUtils.sinDeg(angle) * 150f * dt;
        lastDisplacement.set(MathUtils.cosDeg(angle) * 150f * dt, MathUtils.sinDeg(angle) * 150f * dt);
    }

    private void restart(SpriteBatch batch) {

        position.x = (float) ((Math.random() * (ScreenManager.SCREEN_WIDTH - 256)) + 256);
        int direction = ((int) (Math.random() * 3));

        if (direction % 2 == 0) {
            position.y = -256;
            angle = (float) ((Math.random() * 125 - 35) + 35);
        } else {
            position.y = ScreenManager.SCREEN_HEIGHT + 256;
            angle = (float) ((Math.random() * 305 - 145) + 145);
        }


        batch.draw(texture, position.x - 128, position.y - 128, position.x, position.y,
                256, 256, 1, 1, 1, 0, 0, 256, 256,
                false, false);
    }
}
