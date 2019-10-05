package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class BetterSFXAction extends AbstractGameAction {
    private float pitch;
    private String key;

    public BetterSFXAction(float pitch, String key) {
        this.key = key;
        this.pitch = pitch;
    }

    @Override
    public void update() {
        CardCrawlGame.sound.playA(key, pitch);
        isDone = true;
    }
}
