package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class SpiritsEdge extends CustomRelic {
    public static final String ID = makeID("SpiritsEdge");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SpiritsEdge.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SpiritsEdge.png"));

    public SpiritsEdge() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        for (AbstractCard C : AbstractDungeon.player.masterDeck.group) {
            if (C.type == AbstractCard.CardType.ATTACK) {
                this.addToBot(new DamageRandomEnemyAction(new DamageInfo(null, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }
}


