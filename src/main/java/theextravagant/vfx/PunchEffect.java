package theextravagant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PunchEffect extends AbstractGameEffect {

    private boolean HasreachedTarget = false;
    private TextureAtlas.AtlasRegion img = VfxMaster.VFXAtlas.findRegion("Punch");
    private TextureRegion region = new TextureRegion(img);
    private float x;
    private float y;
    private float elapsedtime;

    public PunchEffect(float x, float y) {
        this.renderBehind = false;
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.color = Color.WHITE.cpy();
        this.startingDuration = 0.4F;
        this.duration = this.startingDuration;
        elapsedtime = 0;
    }

    @Override
    public void update() {
        elapsedtime += Gdx.graphics.getDeltaTime();
        super.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(region.getTexture(), x, y - 70 * Settings.scale, region.getRegionWidth() / 2, 0.0f, 100.0f * Settings.scale, 1000.0f * Settings.scale * elapsedtime, Settings.scale * 2.0f, Settings.scale * 2.0f, 0.0f, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight() - (int) ((100 - 400 * elapsedtime)), false, true);
    }
    //* elapsedtime * 2

    @Override
    public void dispose() {
    }
}
