package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class JumpCutPower extends AbstractPower implements OnReceivePowerPower {
    public static final String POWER_ID = makeID("JumpCutPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/jumpcut_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/jumpcut_power32.png");

    public JumpCutPower(int amount) {
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
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if (abstractPower.type == PowerType.BUFF) {
            abstractPower.atStartOfTurn();
            abstractPower.atStartOfTurnPostDraw();
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
        }
        return true;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
