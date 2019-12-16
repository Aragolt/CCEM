package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class EmbroidedHandkerchief extends CustomRelic {

    public static final String ID = makeID("EmbroidedHandkerchief");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("EmbroidedHandkerchief.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("EmbroidedHandkerchief.png"));

    public EmbroidedHandkerchief() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        drawnCard.triggerWhenDrawn();
    }
}