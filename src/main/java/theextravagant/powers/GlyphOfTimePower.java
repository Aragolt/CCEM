package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class GlyphOfTimePower extends TwoAmountPower {
    public static final String POWER_ID = makeID("GlyphOfTimePower");
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/time_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/time_power32.png");

    public GlyphOfTimePower(int amount) {
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
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        amount2--;
        if (amount2 == 0) {
            amount2 = 10;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, amount)));
        }
    }

    @Override
    public void onInitialApplication() {
        amount2 = 10;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
