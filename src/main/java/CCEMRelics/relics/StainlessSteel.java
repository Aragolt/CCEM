package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class StainlessSteel extends CustomRelic {
    public static final String ID = makeID("StainlessSteel");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StainlessSteel.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("StainlessSteel.png"));

    public StainlessSteel() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        this.counter++;
        if (this.counter % 3 == 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.counter = 0;
            return MathUtils.floor(blockAmount + 5);
        }
        return MathUtils.floor(blockAmount);
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }
}
