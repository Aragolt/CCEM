package theextravagant.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theextravagant.theextravagant.makeID;

public class DrawReductionOncePower extends AbstractPower {
    public static final String POWER_ID = makeID("DrawReductionOncePower");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private static final PowerStrings powerStrings;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("DrawReductionOncePower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    private boolean justApplied = true;

    public DrawReductionOncePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "DrawReductionOncePower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("lessdraw");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 1;
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }


    public void onRemove() {
        AbstractDungeon.player.gameHandSize += amount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }
}
