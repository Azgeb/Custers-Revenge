package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by Joseph on 10.11.2017.
 */

public class hud {

    // ImageButton imgBtnPause;
    Table tblHud;
    Stage  stgHud;

    TextButton txtBtnHome;
    TextButton.TextButtonStyle txtBtnStyle;
    Skin sknSkin;

    Preferences prf = Gdx.app.getPreferences("prfCuster");

    at.jj_9.load.main main;

    SpriteBatch spbBatch;
    OrthographicCamera orcCamera;


    public hud(Skin sknSkin, OrthographicCamera orcCamera, SpriteBatch spbBatch, at.jj_9.load.main main) {
        this.sknSkin = sknSkin;
        this.spbBatch = spbBatch;
        this.orcCamera = orcCamera;
        this.main = main;

        txtBtnStyle = new TextButton.TextButtonStyle();
        txtBtnStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("home.png"))));
        txtBtnStyle.font = sknSkin.getFont("font");

        txtBtnHome = new TextButton("", txtBtnStyle);
        txtBtnHome.setPosition(10,4);

        tblHud = new Table(sknSkin);
        tblHud.setFillParent(true);
        tblHud.bottom();

        stgHud = new Stage(new FitViewport(orcCamera.viewportWidth, orcCamera.viewportHeight));
        stgHud.addActor(tblHud);
        stgHud.addActor(txtBtnHome);
        Gdx.input.setInputProcessor(stgHud);

    }

    public void render(int score) {
        hud(score);

    }

    public void hud(int score) {
        tblHud.add("" + score, "font", Color.WHITE).expandX().padBottom(4);
        stgHud.draw();
        tblHud.clear();

        if (score > prf.getInteger("highscore")) {
            prf.putInteger("highscore", score);
            prf.flush();
        }

        if(txtBtnHome.isPressed()){
            Gdx.input.setInputProcessor(null);
            main.setScreen(new startScreen(main, main.skn));
        }
    }
}
