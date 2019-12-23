package CCEMRelics.relics;

import CCEMRelics.actions.ForkliftAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class FranticForklift extends CustomRelic {
    public static final String ID = makeID("FranticForklift");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FranticForklift.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FranticForklift.png"));

    public FranticForklift() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onShuffle() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ForkliftAction(AbstractDungeon.player.drawPile, true));
        addToBot(new BetterDiscardPileToHandAction(1));
    }
}
