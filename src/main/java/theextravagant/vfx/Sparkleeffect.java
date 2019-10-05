package theextravagant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Sparkleeffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float originx;
    private float originy;
    private Texture img = VfxMaster.VFXAtlas.findRegion("Firework").getTexture();

    public Sparkleeffect(float x, float y) {
        this.x = x + MathUtils.random(-100.0f, 100.0f) * Settings.scale;
        this.y = y + MathUtils.random(-100.0f, 100.0f) * Settings.scale;
        originx = x;
        originy = y;
        renderBehind = false;
        this.duration = 0.3f;
        this.startingDuration = duration;
        int Colorint = MathUtils.random(0, 5);
        switch (Colorint) {
            case 0:
                this.color = Color.BLUE;
                break;
            case 1:
                this.color = Color.GREEN;
                break;
            case 2:
                this.color = Color.YELLOW;
                break;
            case 3:
                this.color = Color.ORANGE;
                break;
            case 4:
                this.color = Color.RED;
                break;
            case 5:
                this.color = Color.PURPLE;
                break;
        }

    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if ((y - originy) != 0) {
            this.x += MathUtils.cos(MathUtils.PI2 * ((x - originx) / (y - originy))) * Settings.scale * Gdx.graphics.getDeltaTime() * 200;
            this.y += MathUtils.sin(MathUtils.PI2 * ((x - originx) / (y - originy))) * Settings.scale * Gdx.graphics.getDeltaTime() * 200;
        }
        if (this.duration < this.startingDuration / 2.0F) {
            this.color.a = this.duration / (this.startingDuration / 2.0F);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
            this.color.a = 0.0F;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(this.color);
        spriteBatch.draw(img, x, y, 50.0f * Settings.scale, 50.0f * Settings.scale);
        spriteBatch.flush();
    }

    @Override
    public void dispose() {
    }
}
