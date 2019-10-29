package theextravagant.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theextravagant.theextravagant.makeID;

public class LifeCharmPower extends AbstractPower {
    public static final String POWER_ID = makeID("LifeCharmPower");
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean isUpgraded;

    public LifeCharmPower(boolean isUpgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = -1;
        this.updateDescription();
        if (owner.hasPower(this.ID) && isUpgraded) {
            ((LifeCharmPower) owner.getPower(this.ID)).isUpgraded = true;
        } else {
            this.isUpgraded = isUpgraded;
        }
        this.isUpgraded = isUpgraded;
        this.loadRegion("artifact");

    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!this.isUpgraded && isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }
}
