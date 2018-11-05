package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Joseph on 08.11.2017.
 */

public class settings implements Screen {

    at.jj_9.load.main main;
    Viewport vipViewport;
    SpriteBatch spbBatch;
    Texture texImg, texBlue, texShop;
    Skin skn;
    Preferences prf = Gdx.app.getPreferences("prfCuster");
    OrthographicCamera otc;

    ImageButton imgBtnBack, imgBtnSfw, imgBtnMute;

    Stage stg;
    Table tbl, tblBtn;

    String strSfw, strMute;

    public settings(at.jj_9.load.main main, Skin skn, OrthographicCamera otc, Viewport vipViewport, SpriteBatch spb) {
        this.main = main;
        this.skn = skn;
        this.otc = otc;
        this.vipViewport = vipViewport;
        this.spbBatch = spb;

        ////////////////////
        prf.putBoolean("secret", true);
        ////////////////////

        texImg = new Texture(Gdx.files.internal("start.png"));
        texImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlue = new Texture(Gdx.files.internal("settingsBackground.png"));
        texBlue.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texShop = new Texture(Gdx.files.internal("shop.png"));
        texShop.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        imgBtnBack = new ImageButton( new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("home.png")))));
        imgBtnBack.setPosition(10, 4);

        imgBtnSfw = new ImageButton(new TextureRegionDrawable(skn.getRegion("smlBtn")));
        imgBtnSfw.setPosition(680, 256);

        imgBtnMute = new ImageButton(new TextureRegionDrawable(skn.getRegion("smlBtn")));
        imgBtnMute.setPosition(680, 256);

        tbl = new Table(skn);
        tbl.setFillParent(true);
        tbl.left().bottom();

        tblBtn = new Table(skn);
        tblBtn.setFillParent(true);
        tblBtn.left().bottom();

        setStrings();

        if (prf.getBoolean("secret")) {
            tbl.add(strSfw, "font", Color.BLACK).expandX().padBottom(50);
            tblBtn.add(imgBtnSfw).padBottom(60).padLeft(675);
            tbl.row();
            tblBtn.row();
        }

        tbl.add(strMute, "font", Color.BLACK).expandX().padBottom(160);
        tblBtn.add(imgBtnMute).padBottom(150).padLeft(675);


        stg = new Stage(vipViewport);
        stg.addActor(imgBtnBack);
        stg.addActor(tbl);
        stg.addActor(tblBtn);

        Gdx.input.setInputProcessor(stg);
    }

    private void setStrings() {
        strSfw = "SFW MODE IS OFF";
        strMute = "SOUND IS NOT MUTED";

        if (prf.getBoolean("sfw")) {
            strSfw = "SFW MODE IS ON";
        }
        if (prf.getBoolean("sound")) {
            strMute = "SOUND IS MUTED";
        }

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spbBatch.begin();
        spbBatch.draw(texImg, -460, -256);
        spbBatch.draw(texShop, -460, -256);
        spbBatch.draw(texBlue, -460, -256);
        spbBatch.end();



        if (imgBtnSfw.isPressed() && Gdx.input.justTouched()) {
            if (prf.getBoolean("sfw")) {
                prf.putBoolean("sfw", false);
            } else {
                prf.putBoolean("sfw", true);
            }

        }

        if (imgBtnMute.isPressed() && Gdx.input.justTouched()) {
            if (prf.getBoolean("sound")) {
                prf.putBoolean("sound", false);
            } else {
                prf.putBoolean("sound", true);
            }

        }

        setStrings();
        if (prf.getBoolean("secret")) {
            tbl.add(strSfw, "font", Color.BLACK).expandX().padBottom(60);
            tbl.row();
        }
        tbl.add(strMute, "font", Color.BLACK).expandX().padBottom(150);


        if (imgBtnBack.isPressed()) {
            Gdx.input.setInputProcessor(null);
            main.setScreen(new startScreen(main, skn));
        }

        prf.flush();
        stg.draw();
        tbl.clear();
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
