package aramod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.powers.FocusPower;

public class LoseFocusPower extends AbstractPower implements CloneablePowerInterface{
    public static final String POWER_ID = "AraMod:LoseFocusPower";
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public LoseFocusPower(AbstractPlayer owner, int amtOfFocus){
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amtOfFocus;
        this.type = PowerType.DEBUFF;
        this.updateDescription();
        this.loadRegion("flex");
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseFocusPower((AbstractPlayer)owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, FocusPower.POWER_ID, amount));
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}