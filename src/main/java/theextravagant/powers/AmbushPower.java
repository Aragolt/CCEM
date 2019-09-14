package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class AmbushPower extends AbstractPower {
    public static final String POWER_ID = makeID("AmbushPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/ambush_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/ambush_power32.png");

    public AmbushPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        type = PowerType.BUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }


    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID, 1));
    }
}
