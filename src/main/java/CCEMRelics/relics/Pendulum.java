package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class Pendulum extends CustomRelic {
    public static final String ID = makeID("Pendulum");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Pendulum.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Pendulum.png"));

    public Pendulum() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter += 1;
        if (this.counter >= 8) {
            this.beginLongPulse();
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.pulse) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        this.stopPulse();
        counter = 0;
    }


    @Override
    public void onVictory() {
        this.stopPulse();
        counter = -1;
    }

    @Override
    public void atBattleStartPreDraw() {
        counter = 0;
    }
}
