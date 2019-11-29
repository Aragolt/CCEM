package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class GarbageCannonPower extends TwoAmountPower implements NonStackablePower {
    public static final String POWER_ID = makeID("GarbageCannonPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/garbage_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/garbage_power32.png");
    public boolean upgraded;

    public GarbageCannonPower(int amount2, int damage, AbstractMonster target) {
        name = NAME;
        ID = POWER_ID;
        this.owner = target;
        type = AbstractPower.PowerType.DEBUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.upgraded = upgraded;
        this.amount2 = amount2;
        amount = damage;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount2 > 1) {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }


    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        amount2--;
        if (amount2 == 0) {
            this.addToBot(new PummelDamageAction(owner, new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.NORMAL)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}
