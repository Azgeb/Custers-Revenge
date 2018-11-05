package at.jj_9.load;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.DecimalFormat;

import at.jj_9.startScreen;

/**
 * Created by Joseph on 07.03.2017.
 */

public class loading extends Game implements Screen {

    main main;
    BitmapFont font;
    Table table, tableclick;
    Stage stage;
    Skin skn;
    public at.jj_9.load.assets assets;
    float percent;
    OrthographicCamera orcCamera;
    Viewport vipViewport;

    Texture texImg, texBlue;

    SpriteBatch spbBatch;

    Preferences prf = Gdx.app.getPreferences("prfCuster");

    public loading(at.jj_9.load.main main, at.jj_9.load.assets assets, Skin skn) {
        this.assets = assets;
        this.main = main;
        this.assets.load();
        this.skn = skn;

        orcCamera = new OrthographicCamera(920, 512);
        vipViewport = new FitViewport(920, 512, orcCamera);

        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));

        spbBatch = new SpriteBatch();
        spbBatch.setProjectionMatrix(orcCamera.combined);

        texImg = new Texture(Gdx.files.internal("hintergrund.png"));
        texImg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBlue = new Texture(Gdx.files.internal("b.jpg"));
        texBlue.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        skn.add("font", font);

        table = new Table(skn);
        table.setWidth(vipViewport.getWorldWidth());
        table.setHeight(vipViewport.getWorldHeight());
        table.setFillParent(true);
        table.bottom().right();
        table.add("LOADING... " + assets.manager.getProgress(), "font", Color.BLACK).bottom().right().padBottom(10).padRight(10);
        // table.debugTable();

        tableclick = new Table(skn);
        tableclick.setWidth(vipViewport.getWorldWidth());
        tableclick.setHeight(vipViewport.getWorldHeight());
        tableclick.setFillParent(true);
        //tableclick.debugTable();

        stage = new Stage(vipViewport);
        stage.addActor(table);
        stage.addActor(tableclick);

        prf.putBoolean("sfw", true);
        prf.flush();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        percent = assets.manager.getProgress() * 100;

        spbBatch.begin();
        spbBatch.draw(texBlue, -460, -256);
        spbBatch.draw(texImg, -460, -256);
        spbBatch.end();

        table.setFillParent(true);
        table.bottom().right();
        table.add("LOADING... " + (percent) + " %", "font", Color.WHITE).bottom().right().padBottom(8).padRight(10);
        // table.debugTable();

        stage.draw();
        table.clear();

        if (assets.manager.update()) {
            TextureAtlas r = main.assets.manager.get("atlas/r.atlas");
            TextureAtlas c = main.assets.manager.get("atlas/c.atlas");
            TextureAtlas etc = main.assets.manager.get("atlas/etc.atlas");

            skn.addRegions(r);
            skn.addRegions(c);
            skn.addRegions(etc);

            main.setScreen(new startScreen(main, skn));
        }

    }

    @Override
    public void resize(int width, int height) {
        vipViewport.update(width, height);
    }

    @Override
    public void hide() {

    }

    @Override
    public void create() {

    }

    @Override
    public void dispose() {

    }
}
