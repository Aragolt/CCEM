package theextravagant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static theextravagant.theextravagant.logger;

public class Damnationeffect extends AbstractGameEffect {
    private float x;
    private float y;
    private boolean playedsound = false;
    private Texture img = VfxMaster.VFXAtlas.findRegion("Damnation").getTexture();

    public Damnationeffect() {
        this.renderBehind = true;
        this.x = Settings.WIDTH / 1.5f;
        this.y = Settings.HEIGHT / 2.0f;
        this.rotation = 0;
        this.color = Color.WHITE.cpy();
        this.startingDuration = 2F;
        this.duration = this.startingDuration;
    }

    @Override
    public void update() {
        if (!playedsound) {
            CardCrawlGame.sound.play("Damnation", 0.1f);
            logger.info("made sound");
            playedsound = true;
        }
        this.duration -= Gdx.graphics.getDeltaTime();
        this.color.a = MathUtils.sin(duration * MathUtils.PI / 2);
        this.scale = 2 - duration;
        if (this.duration < 0.0F) {
            this.isDone = true;
            this.color.a = 0.0F;
        } else {
            this.rotation = (float) Math.floor(duration * 200) % 360;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(this.color);
        spriteBatch.draw(this.img, (float) Settings.WIDTH / 1.75f, (float) Settings.HEIGHT / 3.0f, 200.0F, 200.0F, 400.0F, 400.0F, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation, 0, 0, 400, 400, false, false);
    }

    @Override
    public void dispose() {
        this.img.dispose();
    }
}
