package theextravagant.cards;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.PluckAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class Pluck extends AbstractEVCard {


    public static final String ID = theextravagant.makeID("Pluck");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = theextravagant.makeCardPath("Pluck.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final int COST = 0;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 2;
    private static final int BLOCK = 0;

    public Pluck() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.drawPile.size() >= magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new PluckAction(magicNumber, p));
        } else if (p.drawPile.size() < magicNumber && (p.drawPile.size() + p.discardPile.size()) >= magicNumber)
        {
            int i = p.drawPile.size();
            AbstractDungeon.actionManager.addToTop(new PluckAction(i, p));
            if (AbstractDungeon.player.discardPile.size() > 0) {
                AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
                AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25f));
            }
            AbstractDungeon.actionManager.addToBottom(new PluckAction(magicNumber - i, p));
        } else {
            AbstractDungeon.actionManager.addToBottom(new PluckAction(p.drawPile.size(), p));
            if (AbstractDungeon.player.discardPile.size() > 0) {
                AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
                AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25f));
            }
            AbstractDungeon.actionManager.addToBottom(new PluckAction(p.discardPile.size(), p));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            Secondcost = 0;
            Secondcostforturn = Secondcost;
            initializeDescription();
        }
    }
}