package at.jj_9.load;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


/**
 * Created by Joseph on 28.02.2017.
 */

public class assets {

    public AssetManager manager = new AssetManager();
    int colours; //0, 1, 2 == 2 not 3
    int symbols; //0, 1, 2 == 2 not 3

    public void load() {

        long before = System.currentTimeMillis();

        //SHOP

      /*  manager.load("atlas/.atlas", TextureAtlas.class);
*/
        manager.load("atlas/r.atlas", TextureAtlas.class);
        manager.load("atlas/c.atlas", TextureAtlas.class);
        manager.load("atlas/etc.atlas", TextureAtlas.class);

        System.out.println(System.currentTimeMillis() -before);

    }


}
