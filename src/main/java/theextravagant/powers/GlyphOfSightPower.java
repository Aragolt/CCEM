package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.cards.Invocation;
import theextravagant.theextravagant;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.SecondEnergyOrb;
import static theextravagant.theextravagant.makeID;

public class GlyphOfSightPower extends AbstractPower {
    public static final String POWER_ID = makeID("GlyphOfSightPower");
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theDefaultResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theDefaultResources/images/powers/placeholder_power32.png");

    public GlyphOfSightPower(int amount) {
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
    public void atStartOfTurnPostDraw() {
        for (int i = 0; i < amount; i++) {
            if (theextravagant.SecondEnergyOrb.currentEnergy > 0) {
                SecondEnergyOrb.currentEnergy -= 1;
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Invocation(), amount));
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
