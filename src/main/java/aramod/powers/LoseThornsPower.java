package aramod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class LoseThornsPower extends AbstractPower implements CloneablePowerInterface{
    public static final String POWER_ID = "AraMod:LoseThornsPower";
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public LoseThornsPower(AbstractPlayer owner, int amtOfFocus){
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amtOfFocus;
        this.type = PowerType.DEBUFF;
        this.updateDescription();
        this.loadRegion("thorns");
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseThornsPower((AbstractPlayer)owner, amount);
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, ThornsPower.POWER_ID, amount));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}