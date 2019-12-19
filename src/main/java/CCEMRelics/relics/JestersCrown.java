package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class JestersCrown extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("JestersCrown");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("JestersCrown.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("JestersCrown.png"));

    public JestersCrown() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public float atDamageModify(float damage, AbstractCard c) {
        return c.rarity == AbstractCard.CardRarity.COMMON ? damage + 2.0F : damage;
    }
}
