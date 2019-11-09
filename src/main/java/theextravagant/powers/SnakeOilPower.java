package theextravagant.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theextravagant.theextravagant.makeID;

public class SnakeOilPower extends TwoAmountPower implements NonStackablePower {

    public static final String POWER_ID = makeID("SnakeOilPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justapplied;

    public SnakeOilPower(AbstractCreature owner, int newAmount) {
        this.owner = owner;
        this.amount = newAmount;
        this.amount2 = 3;
        this.updateDescription();
        name = NAME;
        ID = POWER_ID;
        this.loadRegion("fumes");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Slimed) {
            amount2--;
            if (amount2 == 0) {
                AbstractDungeon.actionManager.addToBottom(new HealAction(owner, owner, amount));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            }
        }
    }
}
