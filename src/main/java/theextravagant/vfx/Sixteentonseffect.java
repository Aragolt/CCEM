package theextravagant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Sixteentonseffect extends AbstractGameEffect {
    private boolean HasreachedTarget = false;
    private TextureAtlas.AtlasRegion img = VfxMaster.VFXAtlas.findRegion("SixteenTons");

    private float posx;
    private float posy;
    private float targety;

    public Sixteentonseffect(float targetx, float targety) {
        renderBehind = false;
        this.posx = targetx;
        this.targety = targety;
        this.posy = targety + 400.0f;
        this.startingDuration = 0.5f;
        this.duration = startingDuration;
    }


    @Override
    public void update() {
        if (!HasreachedTarget) {
            this.posy -= Gdx.graphics.getDeltaTime() * 2000 * Settings.scale;
            if (this.posy <= targety) {
                HasreachedTarget = true;
            }
        } else {
            this.duration -= Gdx.graphics.getDeltaTime();
            if (this.duration < this.startingDuration / 2.0F) {
            }

            if (this.duration < 0.0F) {
                this.isDone = true;
            }
        }
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.draw(img, this.posx - 100 * Settings.scale, this.posy, 200 * Settings.scale, 200 * Settings.scale);
    }

    @Override
    public void dispose() {
    }
}
