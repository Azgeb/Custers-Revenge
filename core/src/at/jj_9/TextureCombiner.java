package at.jj_9;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.async.ThreadUtils;

/**
 * Created by Joseph on 18.11.2017.
 */

public class TextureCombiner {

    SpriteBatch spb;
    TextureRegion tex1, tex2;


    public TextureCombiner(SpriteBatch spb, TextureRegion tex1, TextureRegion tex2) {
        this.spb= spb;
        this.tex1 = tex1;
        this.tex2 = tex2;


    }

    public void render(float x, float y, boolean flip){

        if(tex1.isFlipX() && !flip){
            tex1.flip(true, false);
            tex2.flip(true, false);
        } else if (!tex1.isFlipX() && flip){
            tex1.flip(true, false);
            tex2.flip(true, false);
        }



        spb.draw(tex1,x, y);
        spb.draw(tex2,x, y);



    }
}
