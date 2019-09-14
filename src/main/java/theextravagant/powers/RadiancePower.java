package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class RadiancePower extends AbstractPower {
    public static final String POWER_ID = makeID("RadiancePower");
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power32.png");

    public RadiancePower(int amount) {
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
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            for (int i = 0; i < amount; i++) {
                AbstractDungeon.player.drawPile.addToTop(new Dazed());
                AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                if (m != null && m.currentHealth > 0) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, owner, new StrengthPower(m, -1)));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }
}
