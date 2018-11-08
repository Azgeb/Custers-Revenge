package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Joseph on 08.11.2017.
 */

public class custer {
    float screenWidth;
    float speed;

    float time;
    float max_time;
    float bombTime;

    boolean xxx;
    boolean flip;
    boolean bomb;
    int currentTexture, intSecret;
    game game;
    Vector2 position;

    SpriteBatch spb;

    TextureCombiner sp1, sp2, sp3, sp4, sp5;
    TextureRegion c1, c2, c3, c4, c5, cc1, cc2, cc3, cc4, cc5, cempty;
    Skin skn;

    int intc;
    Preferences prf = Gdx.app.getPreferences("prfCuster");

    public custer(SpriteBatch spb, Integer intc, game game, Skin skn) {
        this.skn = skn;
        this.game = game;
        this.spb = spb;
        this.intc = intc;

        currentTexture = 1;
        screenWidth = Gdx.graphics.getWidth() / 2;
        speed = 10;
        max_time = 1.25f / speed;
        position = new Vector2(-460 + 20, -255 + 55);


        createSprites();
    }

    private void createSprites() {

        c1 = skn.getRegion("c1x" + intc + "0");
        c2 = skn.getRegion("c2x" + intc + "0");
        c3 = skn.getRegion("c3x" + intc + "0");
        c4 = skn.getRegion("c4x" + intc + "0");
        c5 = skn.getRegion("c5x" + intc + "0");

        if (prf.getBoolean("sfw")) {
            cc1 = skn.getRegion("c1x" + intc);
            cc2 = skn.getRegion("c2x" + intc);
            cc3 = skn.getRegion("c3x" + intc);
            cc4 = skn.getRegion("c4x" + intc);
            cc5 = skn.getRegion("c5x" + intc);
        } else {
            cc1 = skn.getRegion("empty");
            cc2 = skn.getRegion("empty");
            cc3 = skn.getRegion("empty");
            cc4 = skn.getRegion("empty");
            cc5 = skn.getRegion("empty");
        }

        sp1 = new TextureCombiner(spb, c1, cc1);
        sp2 = new TextureCombiner(spb, c2, cc2);
        sp3 = new TextureCombiner(spb, c3, cc3);
        sp4 = new TextureCombiner(spb, c4, cc4);
        sp5 = new TextureCombiner(spb, c5, cc5);
    }

    public void render() {
        xxx = false;

        if (time >= max_time) {
            if (currentTexture == 1) {
                currentTexture = 2;
            } else {
                currentTexture = 1;
            }

            time = 0;
        } else {
            time = time + Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.getX() < screenWidth && position.x > -460 + 20 && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.A) && position.x > -460 + 20) {
            position.set(position.x - speed, position.y);
            flip = true;
        }
        if (Gdx.input.getX() > screenWidth && position.x < 460 - 188 - 50 && Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.D) && position.x < 460 - 188 - 50) {
            position.set(position.x + speed, position.y);
            flip = false;
        }

        if (position.x + speed > 460 - 188 - 50) {
            currentTexture = 3;
        }

        if (currentTexture == 3) {
            xxx = true;
        } else {
            xxx = false;
        }

        if(!Gdx.input.isTouched() && !Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            currentTexture = 1;
        }
    }

    public void draw() {
        if (currentTexture == 1) {
            sp1.render(position.x, position.y, flip);
        }
        if (currentTexture == 2) {
            sp2.render(position.x, position.y, flip);
        }
        if (currentTexture == 3) {
            sp3.render(position.x, position.y, flip);
        }
        if (currentTexture == 4) {
            sp4.render(position.x, position.y, flip);
        }
        if (currentTexture == 5) {
            sp5.render(position.x, position.y, flip);
        }

    }
}
