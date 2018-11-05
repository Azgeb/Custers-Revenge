package at.jj_9;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Joseph on 09.11.2017.
 */

public class arrow {

    SpriteBatch spbBatch;

    Sprite arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8, arrow9;
    Array arrArrow, arrName, arrRectangle;

    Random r;

    int intArraySize;
    float custerWidth, lastArrowPosX;
    boolean bomb;
    String strBombArrName;

    TextureRegion texArr, texBomb;

    Vector2 vecArrowSpeed;

    public arrow(float custerWidth, Skin skn) {
        this.custerWidth = custerWidth;

        texArr = skn.getRegion("arrow");
        texBomb = skn.getRegion("bomb");

        arrow1 = new Sprite(texArr);
        arrow2 = new Sprite(texArr);
        arrow3 = new Sprite(texArr);
        arrow4 = new Sprite(texArr);
        arrow5 = new Sprite(texArr);
        arrow6 = new Sprite(texArr);
        arrow7 = new Sprite(texArr);
        arrow8 = new Sprite(texArr);
        arrow9 = new Sprite(texBomb);


        arrArrow = new Array();
        arrRectangle = new Array();

        r = new Random();

        vecArrowSpeed = new Vector2(3, 4);
        lastArrowPosX = 490;

        restar();

    }

    private void restar() {
        strBombArrName = "";

        arrRectangle.size = 1;
        arrArrow.size = 1;

        arrRectangle.set(0, null);
        arrArrow.set(0, null);

        arrow1.setPosition(lastArrowPosX - custerWidth * 2.4f - texArr.getRegionWidth() * (1.75f * 8), 255);
        arrow2.setPosition(lastArrowPosX - custerWidth * 2.4f - texArr.getRegionWidth() * (1.75f * 7), 255);
        arrow3.setPosition(lastArrowPosX - custerWidth * 1.2f - texArr.getRegionWidth() * (1.75f * 6), 255);
        arrow4.setPosition(lastArrowPosX - custerWidth * 1.2f - texArr.getRegionWidth() * (1.75f * 5), 255);
        arrow5.setPosition(lastArrowPosX - custerWidth * 1.2f - texArr.getRegionWidth() * (1.75f * 4), 255);
        arrow6.setPosition(lastArrowPosX - texArr.getRegionWidth() * (1.75f * 3), 255);
        arrow7.setPosition(lastArrowPosX - texArr.getRegionWidth() * (1.75f * 2), 255);
        arrow8.setPosition(lastArrowPosX, 255);
        arrow9.setPosition(0, 255);

        int arraypos = 0;

        if (r.nextInt(4) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow1);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(4) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow2);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(4) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow3);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(4) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow4);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(4) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow5);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(5) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow6);
            arraypos = arraypos + 1;
        }
        if (r.nextInt(5) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow7);
            arraypos = arraypos + 1;
        }

        // Death by F**K
        if (r.nextInt(3) == 1) {
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow8);
            arraypos = arraypos + 1;
        }


        /*if(bomb){
            arrArrow.size = arrArrow.size + 1;
            arrArrow.set(arraypos, arrow9);
            strBombArrName = arrow9.toString();
            arraypos = arraypos + 1;
        }
*/
        if (arrArrow.size > 1) {
            arrArrow.size = arrArrow.size - 1; // da de letzte wert immer null ist
        }


        arrRectangle.size = arrArrow.size;
        intArraySize = arrArrow.size;
    }


    public void render(SpriteBatch spbBatch, boolean bomb) {

        this.bomb = bomb;

        if (arrArrow.get(0) == null) {
            restar();
        } else {
            for (int s = 0; s < intArraySize; s++) {
                Sprite arrow = (Sprite) arrArrow.get(s);


                System.out.println(strBombArrName);

                if (arrow.getY() < -255 + 40 + 180) {
                    restar();
                    this.bomb = false;
                    break;
                } else {
                    /*if (arrow.getTexture() == texBomb.getTexture()){
                        arrow.setPosition(arrow.getX(), arrow.getY() - vecArrowSpeed.y);

                    } else {*/
                      arrow.setPosition(arrow.getX() - vecArrowSpeed.x, arrow.getY() - vecArrowSpeed.y);
                    //}
                }

                Rectangle r = new Rectangle(arrow.getX(), arrow.getY(), 5, 15);
                arrRectangle.set(s, r);
                spbBatch.draw(arrow, arrow.getX(), arrow.getY());
            }

        }
    }
}
