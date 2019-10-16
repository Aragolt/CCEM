package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.cards.RustyBuckler;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class RustyBucklerRetainTriggerAction extends AbstractPower implements InvisiblePower {
    public static final String POWER_ID = makeID("RustyBuckler");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power32.png");
    private boolean hasPlayedSkill = false;

    public RustyBucklerRetainTriggerAction() {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        type = AbstractPower.PowerType.BUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractCard C : AbstractDungeon.player.hand.group) {
                if (C.retain && C instanceof RustyBuckler) {
                    C.triggerOnEndOfPlayerTurn();
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "";
        name = "";
    }
}


