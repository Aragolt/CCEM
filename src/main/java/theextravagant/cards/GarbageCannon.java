package theextravagant.cards;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.GarbageCannonAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class GarbageCannon extends AbstractEVCard {


    public static final String ID = theextravagant.makeID("GarbageCannon");
    public static final String IMG = theextravagant.makeCardPath("GarbageCannon.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int MAGICNUMBER = 3;
    private static final int BLOCK = 0;

    public GarbageCannon() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);
        if (p.drawPile.size() >= magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new GarbageCannonAction(magicNumber, p, damage, m));
        } else if (p.drawPile.size() < magicNumber && (p.drawPile.size() + p.discardPile.size()) >= magicNumber) {
            int i = p.drawPile.size();
            AbstractDungeon.actionManager.addToBottom(new GarbageCannonAction(i, p, damage, m));
            if (AbstractDungeon.player.discardPile.size() > 0) {
                AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
                AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
            }
            AbstractDungeon.actionManager.addToBottom(new GarbageCannonAction(magicNumber - i, p, damage, m));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GarbageCannonAction(p.drawPile.size(), p, damage, m));
            if (AbstractDungeon.player.discardPile.size() > 0) {
                AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
                AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
            }
            AbstractDungeon.actionManager.addToBottom(new GarbageCannonAction(p.discardPile.size(), p, damage, m));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }
}