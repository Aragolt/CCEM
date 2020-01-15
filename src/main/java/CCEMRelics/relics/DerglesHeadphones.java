package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnSmithRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class DerglesHeadphones extends CustomRelic implements BetterOnSmithRelic {
    public static final String ID = makeID("DerglesHeadphones");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DerglesHeadphones.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DerglesHeadphones.png"));

    public DerglesHeadphones() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void betterOnSmith(AbstractCard abstractCard) {
        AbstractDungeon.player.heal(12);
    }
}