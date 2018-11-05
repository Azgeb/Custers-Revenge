package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Joseph on 14.11.2017.
 */

public class shop implements Screen {

    at.jj_9.load.main main;
    OrthographicCamera orcCamera;
    Viewport vipViewport;
    SpriteBatch spbBatch;
    Texture texImg, texBlue, texShop, btnShop;

    BitmapFont fnt;
    at.jj_9.hud hud;

    Skin skn;

    Table tblStart;
    Stage stgStart;

    TextButton.TextButtonStyle txtBtnStyle;
    ImageButton imgBtnR1, imgBtnR2, BackR1, BackR2;

    Preferences prf = Gdx.app.getPreferences("prfCuster");
    int highscore;

    public shop(at.jj_9.load.main main, Skin skn) {
        this.main = main;
        this.skn = skn;
    }

    @Override
    public void show() {
        orcCamera = new OrthographicCamera(920, 512);
        vipViewport = new FitViewport(920, 512, orcCamera);

        spbBatch = new SpriteBatch();
        spbBatch.setProjectionMatrix(orcCamera.combined);

        texImg = new Texture(Gdx.files.internal("start.png"));
        texImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlue = new Texture(Gdx.files.internal("b.jpg"));
        texBlue.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texShop = new Texture(Gdx.files.internal("shop.png"));
        texShop.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        imgBtnR1 = new ImageButton(new TextureRegionDrawable(skn.getRegion("r1x0")));
        imgBtnR1.setPosition(500, 97);

        imgBtnR2 = new ImageButton(new TextureRegionDrawable(skn.getRegion("r1x1")));
        imgBtnR2.setPosition(100, 97);

        BackR1 = new ImageButton(new TextureRegionDrawable(skn.getRegion("btnBack")));
        BackR1.setPosition(500, 97);

        BackR2 = new ImageButton(new TextureRegionDrawable(skn.getRegion("btnBack")));
        BackR2.setPosition(100, 97);

        highscore = prf.getInteger("highscore");

        tblStart = new Table(skn);
        tblStart.setFillParent(true);
        tblStart.bottom();
        tblStart.add("HIGHSCORE " + highscore, "font", Color.WHITE).expandX().left().padLeft(50).padBottom(4);

        stgStart = new Stage(new FitViewport(orcCamera.viewportWidth, orcCamera.viewportHeight));
        stgStart.addActor(BackR1);
        stgStart.addActor(BackR2);
        stgStart.addActor(imgBtnR1);
        stgStart.addActor(imgBtnR2);
        stgStart.addActor(tblStart);
        Gdx.input.setInputProcessor(stgStart);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spbBatch.begin();
        spbBatch.draw(texBlue, -460, -256);
        spbBatch.draw(texImg, -460, -256);
        spbBatch.draw(texShop, -460, -256);
        spbBatch.end();

        stgStart.draw();

        if (imgBtnR1.isPressed()) {
            prf.putInteger("r", 0);
            prf.flush();
            Gdx.input.setInputProcessor(null);
            main.setScreen(new startScreen(main, skn));
        }

        if (imgBtnR2.isPressed()) {
            prf.putInteger("r", 1);
            prf.flush();
            Gdx.input.setInputProcessor(null);
            main.setScreen(new startScreen(main, skn));
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


