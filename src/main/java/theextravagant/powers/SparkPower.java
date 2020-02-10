package theextravagant.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.actions.EnchantSpecificCardAction;

import static theextravagant.theextravagant.makeID;

public class SparkPower extends AbstractPower {
    public static final String POWER_ID = makeID("SparkPower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final PowerStrings powerStrings;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public SparkPower(AbstractCreature owner, int damageAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = damageAmount;
        this.updateDescription();
        this.loadRegion("combust");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && amount > 0) {
            AbstractDungeon.actionManager.addToBottom(new EnchantSpecificCardAction(card));
            this.amount--;
            if (amount <= 0) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this.ID));
            }
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}