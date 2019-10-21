package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class TrickeryPower extends TwoAmountPower {
    public static final String POWER_ID = makeID("TrickeryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/trickery_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/trickery_power32.png");
    private boolean hasPlayedSkill = false;

    public TrickeryPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        amount2 = 0;
        type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
       flash();
        amount2 = 0;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.SKILL && amount2 < amount) {
            AbstractCard a = AbstractDungeon.player.drawPile.getAttacks().getRandomCard(true);
            if (a != null) {
                if (a.costForTurn > 0) {
                    a.setCostForTurn(a.costForTurn - 1);
                }
                AbstractDungeon.player.drawPile.moveToHand(a, AbstractDungeon.player.drawPile);
            }
            amount2 += 1;
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
