package theextravagant.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Fireworkeffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int sparklecount;

    public Fireworkeffect(float x, float y) {
        this.duration = 0.6F;
        this.x = x;
        this.y = y;
        this.renderBehind = false;
        this.sparklecount = 0;
    }

    @Override
    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if (sparklecount == 0 || (duration <= 0.3f && sparklecount == 1) || (duration <= 0)) {
            AbstractDungeon.effectsQueue.add(new Sparkleeffect(this.x, this.y));
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
