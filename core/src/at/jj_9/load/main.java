package at.jj_9.load;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class main extends Game {

    public at.jj_9.load.assets assets;

    public Skin skn;

    public void create() {
        assets = new assets();
        skn = new Skin();
        setScreen(new loading(this, assets, skn));
    }


}
