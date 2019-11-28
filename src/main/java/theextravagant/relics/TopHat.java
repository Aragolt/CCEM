package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theextravagant.actions.SparkAction;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class TopHat extends CustomRelic {
    public static final String ID = makeID("TopHat");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TopHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TopHat.png"));

    public TopHat() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.counter += 1;
            if (this.counter % 3 == 0) {
                this.counter = 0;
                flash();
                new SparkAction(AbstractCard.CardType.SKILL);
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }
}
