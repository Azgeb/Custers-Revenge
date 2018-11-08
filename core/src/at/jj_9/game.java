package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Joseph on 08.11.2017.
 */

public class game implements Screen {

    at.jj_9.load.main main;
    OrthographicCamera orcCamera;
    Viewport vipViewport;
    SpriteBatch spbBatch;
    Texture texImg, texRed, texBlue, texButton, texBlack;

    Sprite revenge;

    at.jj_9.custer custer;

    Animation animation;
    TextureRegion current;

    public TextureRegion r1, r2, c1, c2, c3, c4, c5;
    Texture tlives;
    at.jj_9.arrow arrow;

    Texture tex;

    boolean gameover, red, booScore;
    boolean bomb;

    float max, time, bombTime;
    int durchlauf, lives, score, intc, intr;
    int revengeTexture, intSecret;

    Music n, f, s;

    BitmapFont fnt;
    hud hud;

    Skin skn;
    Preferences prf = Gdx.app.getPreferences("prfCuster");


    public game(at.jj_9.load.main main, Skin skn) {
        this.main = main;
        this.skn = skn;
    }

    @Override
    public void show() {
        orcCamera = new OrthographicCamera(920, 512);
        vipViewport = new FitViewport(920, 512, orcCamera);

        spbBatch = new SpriteBatch();
        spbBatch.setProjectionMatrix(orcCamera.combined);

        texImg = new Texture(Gdx.files.internal("hintergrund.png"));
        texImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlue = new Texture(Gdx.files.internal("b.jpg"));
        texBlue.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texRed = new Texture(Gdx.files.internal("r.jpg"));
        texRed.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlack = new Texture(Gdx.files.internal("black.png"));
        texBlack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texButton = new Texture(Gdx.files.internal("button.png"));
        texButton.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        intr = prf.getInteger("r");
        intc = prf.getInteger("c");

        r1 = skn.getRegion("r1x" + intr);
        r2 = skn.getRegion("r2x" + intr);


        tlives = new Texture(Gdx.files.internal("lives.png"));

        revenge = new Sprite(r1);
        revenge.setPosition(460 - 188 - 50, -255 + 55);

        arrow = new arrow(94, skn); // 94 Fixe breite von custer

        max = 0.75f;
        lives = 3;
        time = 1.5f;
        score = 0;

        n = Gdx.audio.newMusic(Gdx.files.internal("n.wav"));
        f = Gdx.audio.newMusic(Gdx.files.internal("f.wav"));
        s = Gdx.audio.newMusic(Gdx.files.internal("s.wav"));

        fnt = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));


        hud = new hud(skn, orcCamera, spbBatch, main);

        booScore = true;

        custer = new custer(spbBatch,intc, this, skn);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spbBatch.begin();

        if (red) {
            spbBatch.draw(texRed, -460, -256);
        } else {
            spbBatch.draw(texBlue, -460, -256);
        }

        spbBatch.draw(texImg, -460, -256);


        if (gameover) {
            gameover();
        } else {
            arrow.render(spbBatch, bomb);
            custer.render();
            if (custer.currentTexture ==  3) {
                revenge.setRegion(r2);
                revengeTexture = 2;
                if (booScore) {
                    n.stop();
                    score = score + 1;
                    if (!s.isPlaying()) {
                        s.play();
                    }
                }
                booScore = false;
            } else {
                revenge.setRegion(r1);
                revengeTexture = 1;
                booScore = true;
                if (!n.isPlaying()) {
                    n.play();
                }
            }


            collision();
        }

        custer.draw();
        revenge.draw(spbBatch);

        if(revengeTexture == 2){
            spbBatch.draw(texBlack, 295,  -150);
        }
        cLives();

        spbBatch.draw(texButton, -460, -256);
        spbBatch.end();

        hud.render(score);
        isSecret();
        isBomb();

    }

    private void isSecret() {

        if(Gdx.input.justTouched() && custer.position.x <= -440 || Gdx.input.isKeyJustPressed(Input.Keys.A) && custer.position.x <= -440){
            if(intSecret > 15){
                intSecret = 0;
                main.setScreen(new settings(main,skn,orcCamera,vipViewport,spbBatch));
            } else {
                intSecret = intSecret +1;
            }
        }
    }

    private void cLives() {
        if (lives == 3) {
            spbBatch.draw(tlives, 300, -255);
            spbBatch.draw(tlives, 300 + tlives.getWidth() + 10, -255);
            spbBatch.draw(tlives, 300 + tlives.getWidth() * 2 + 10 * 2, -255);
        } else if (lives == 2) {
            spbBatch.draw(tlives, 300, -255);
            spbBatch.draw(tlives, 300 + tlives.getWidth() + 10, -255);
        } else if (lives == 1) {
            spbBatch.draw(tlives, 300, -255);
        }
    }

    public void collision() {
        Rectangle recPlayer = new Rectangle(custer.position.x + 25, custer.position.y, 35, 181); // 35 breite des Hut , 181 Fixe custer Höhe

        if (arrow.arrRectangle.get(0) == null) {

        } else {
            int arrArrowSize = arrow.arrRectangle.size;
            for (int s = 0; s < arrArrowSize; s++) {
                Rectangle helpRectangle = (Rectangle) arrow.arrRectangle.get(s);
                if (recPlayer.overlaps(helpRectangle)) {
                    gameover = true;
                }
            }
        }
    }

    private void gameover() {

        if (!f.isPlaying()) {
            f.play();
        }

        revenge.setRegion(r1);
        revengeTexture = 1;
        if (time > max) {
            if (custer.currentTexture == 1 || custer.currentTexture == 5) {
                custer.currentTexture = 4;
                red = false;
            } else {
                custer.currentTexture = 5;
                red = true;
            }
            time = 0;
            durchlauf = durchlauf + 1;
        } else {
            time = time + Gdx.graphics.getDeltaTime();
        }

        custer.draw();

        if (durchlauf == 5) {
            durchlauf = 0;
            lives = lives - 1;
            gameover = false;
            custer.position.set(-460 + 20, -255 + 55);
            custer.flip = false;
            red = false;
            bomb = false;
            if (lives == 0) {
                main.setScreen(new startScreen(main, main.skn));
            }
        }

    }


    public void isBomb() {
        if (custer.position.x > 460 - 188 - 60) {
            if(bombTime > 1.5f){
                bomb = true;
                bombTime = 0;
                System.out.println("Bomb");
            } else {
                bombTime = bombTime + Gdx.graphics.getDeltaTime();
            }
        }
        else {
            bombTime = 0;
            bomb = arrow.bomb;
        }
    }

    @Override
    public void resize(int width, int height) {
        vipViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
