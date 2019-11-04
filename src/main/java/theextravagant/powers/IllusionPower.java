package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class IllusionPower extends TwoAmountPower implements NonStackablePower {
    public static final String POWER_ID = makeID("illusionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/illusion_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/illusion_power32.png");
    private boolean upgraded;

    public IllusionPower(Boolean upgraded) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        isTurnBased = false;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.amount2 = 6;
        this.amount = 1;
        if (upgraded) {
            amount = 2;
        }
        this.upgraded = upgraded;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (!upgraded) {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[2];
        }
    }


    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        amount2--;
        if (amount2 == 0) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(makeAlmostStatEquivalentCopy(card), 1, true, true));
            if (upgraded) {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(makeAlmostStatEquivalentCopy(card), 1, true, true));
            }
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
        }
    }

    private AbstractCard makeAlmostStatEquivalentCopy(AbstractCard c) {
        AbstractCard card = c.makeCopy();

        for (int i = 0; i < c.timesUpgraded; ++i) {
            card.upgrade();
        }

        card.name = c.name;
        card.target = c.target;
        card.upgraded = c.upgraded;
        card.timesUpgraded = c.timesUpgraded;
        card.baseDamage = c.baseDamage;
        card.baseBlock = c.baseBlock;
        card.baseMagicNumber = c.baseMagicNumber;
        card.cost = c.cost;
        card.costForTurn = c.costForTurn;
        card.isCostModified = c.isCostModified;
        card.isCostModifiedForTurn = c.isCostModifiedForTurn;
        card.inBottleLightning = c.inBottleLightning;
        card.inBottleFlame = c.inBottleFlame;
        card.inBottleTornado = c.inBottleTornado;
        card.isSeen = c.isSeen;
        card.isLocked = c.isLocked;
        card.misc = c.misc;
        card.freeToPlayOnce = true;
        return card;
    }
}