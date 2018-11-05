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
 * Created by Joseph on 12.11.2017.
 */

public class startScreen implements Screen {
    at.jj_9.load.main main;
    OrthographicCamera orcCamera;
    Viewport vipViewport;
    SpriteBatch spbBatch;
    Texture texImg, texBlue, texButton;

    BitmapFont fnt;
    at.jj_9.hud hud;

    Skin skn;

    Table tblStart;
    Stage stgStart;

    TextButton.TextButtonStyle txtBtnStyle;
    ImageButton imgBtnStart, imgBtnShop;

    Preferences prf = Gdx.app.getPreferences("prfCuster");
    int highscore;

    public startScreen(at.jj_9.load.main main, Skin skn) {
        /*AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
*/
        this.main = main;
        this.skn = skn;
    }

    @Override
    public void show() {
        orcCamera = new OrthographicCamera(920, 513);
        vipViewport = new FitViewport(920, 512, orcCamera);

        spbBatch = new SpriteBatch();
        spbBatch.setProjectionMatrix(orcCamera.combined);

        texImg = new Texture(Gdx.files.internal("start.png"));
        texImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlue = new Texture(Gdx.files.internal("b.jpg"));
        texBlue.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fnt = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

        hud = new hud(skn, orcCamera, spbBatch, main);

        imgBtnStart = new ImageButton(new TextureRegionDrawable(skn.getRegion("btnStart")));
        imgBtnStart.setPosition(500,97);

        imgBtnShop = new ImageButton(new TextureRegionDrawable(skn.getRegion("btnShop")));
        imgBtnShop.setPosition(300,97);


        highscore = prf.getInteger("highscore");

        tblStart = new Table(skn);
        tblStart.setFillParent(true);
        tblStart.bottom();
        tblStart.add("HIGHSCORE " + highscore, "font", Color.WHITE).expandX().left().padLeft(50).padBottom(4);

        stgStart = new Stage(new FitViewport(orcCamera.viewportWidth, orcCamera.viewportHeight));
        stgStart.addActor(imgBtnStart);
        stgStart.addActor(imgBtnShop);
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
        spbBatch.end();
        stgStart.draw();

        if (imgBtnStart.isPressed()){
            Gdx.input.setInputProcessor(null);
            main.setScreen(new game(main, skn));
        }

        if (imgBtnShop.isPressed()){
            Gdx.input.setInputProcessor(null);
            main.setScreen(new shop(main, skn));
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


