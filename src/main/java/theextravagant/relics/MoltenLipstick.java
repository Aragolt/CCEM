package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class MoltenLipstick extends CustomRelic {

    public static final String ID = makeID("MoltenLipstick");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MoltenLipstick.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MoltenLipstick.png"));

    public MoltenLipstick() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.freeToPlayOnce || targetCard.costForTurn == 0) {
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            AbstractCard Burn = new Burn();
            Burn.upgrade();
            AbstractDungeon.player.drawPile.addToTop(Burn);
        }
    }
}
