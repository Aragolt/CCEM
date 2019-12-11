package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class OldMusket extends CustomRelic {

    public static final String ID = makeID("OldMusket");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OldMusket.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OldMusket.png"));

    public OldMusket() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.type == AbstractCard.CardType.ATTACK) {
            counter++;
        }
        if (counter == 6) {
            counter = 0;
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(9, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
