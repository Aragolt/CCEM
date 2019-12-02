package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.actions.PlayCardAction;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class CuffLinks extends CustomRelic {

    public static final String ID = makeID("CuffLinks");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Cufflinks.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Cufflinks.png"));

    public CuffLinks() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        AbstractCard c = AbstractDungeon.player.drawPile.getRandomCard(AbstractCard.CardType.POWER, true);
        if (c != null) {
            AbstractDungeon.player.drawPile.removeCard(c);
            AbstractDungeon.actionManager.addToBottom(new PlayCardAction(c, new UseCardAction(c)));
        }
    }
}
