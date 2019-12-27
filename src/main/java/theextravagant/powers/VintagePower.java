package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theextravagant.actions.UnreturnCardsToHandAction;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;

import static theextravagant.theextravagant.makeID;

public class VintagePower extends TwoAmountPower {
    public static final String POWER_ID = makeID("VintagePower");
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/vintage_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/vintage_power32.png");
    boolean justapplied = true;
    public static ArrayList<AbstractCard> cardsthatshouldnotreturn = new ArrayList<>();

    public VintagePower(int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.updateDescription();
        amount2 = 0;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        justapplied = false;
        amount2 = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!justapplied) {
            if (amount2 < amount) {
                if (card.type != AbstractCard.CardType.POWER && !card.exhaust && !card.exhaustOnUseOnce) {
                    this.flash();
                    if (!card.returnToHand) {
                        cardsthatshouldnotreturn.add(card);
                        card.returnToHand = true;
                    }
                }
                amount2++;
            }
            AbstractDungeon.player.hand.refreshHandLayout();
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.addToBot(new UnreturnCardsToHandAction());
    }
}

