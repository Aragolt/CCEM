package CCEMRelics.relics;

import CCEMRelics.actions.PutAtBottomOfDrawPileAndApplyChainAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class OldTooth extends CustomRelic {
    public static final String ID = makeID("OldTooth");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OldTooth.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OldTooth.png"));

    public OldTooth() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new PutAtBottomOfDrawPileAndApplyChainAction(1));
    }
}


