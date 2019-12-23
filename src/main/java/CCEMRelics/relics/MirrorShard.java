package CCEMRelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class MirrorShard extends CustomRelic {
    public static final String ID = makeID("MirrorShard");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MirrorShard.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MirrorShard.png"));
    private static final CardStrings cardStringsEthereal = CardCrawlGame.languagePack.getCardStrings("etherealcard");
    private AbstractCard card;

    public MirrorShard() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void wasHPLost(int damageAmount) {
        if (damageAmount > 0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.pulse && card != null) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card));
            } else {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card));
            }
        }
    }

    @Override
    public void onPlayerEndTurn() {
        this.stopPulse();
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.pulse = true;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            AbstractCard C = c.makeStatEquivalentCopy();
            card = C;
            if (!card.isEthereal) {
                card.isEthereal = true;
                card.rawDescription += cardStringsEthereal.DESCRIPTION;
                card.initializeDescription();
            }
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        card = null;
        this.flash();
        this.pulse = true;
    }

    @Override
    public void onVictory() {
        this.stopPulse();
    }
}
