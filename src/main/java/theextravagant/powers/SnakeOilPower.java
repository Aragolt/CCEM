package theextravagant.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theextravagant.theextravagant.makeID;

public class SnakeOilPower extends TwoAmountPower {

    public static final String POWER_ID = makeID("RarePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justapplied;

    public SnakeOilPower(AbstractCreature owner, int newAmount) {
        this.owner = owner;
        this.amount = newAmount;
        this.amount2 = 1;
        this.updateDescription();
        name = NAME;
        ID = POWER_ID;
        this.loadRegion("envenom");
        justapplied = true;

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount), this.amount, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new VulnerablePower(target, this.amount2, false), this.amount2, true));
        }

    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (!justapplied) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID));
        }
        justapplied = false;
    }
}
