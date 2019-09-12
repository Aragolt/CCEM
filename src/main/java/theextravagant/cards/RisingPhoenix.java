package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.PheonixEffectAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

import static theextravagant.theextravagant.CardsExhaustedLastTurn;

public class RisingPhoenix extends CustomCard {


    public static final String ID = theextravagant.makeID("RisingPhoenix");
    public static final String IMG = theextravagant.makeCardPath("RisingPhoenix.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 5;
    private static final int DAMAGE = 4;
    private static final int MAGICNUMBER = 5;
    private static final int BLOCK = 0;

    public RisingPhoenix() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int a = 0; a < magicNumber; a++) {
            AbstractDungeon.actionManager.addToBottom(new PheonixEffectAction());
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.costForTurn = (Math.max(0, magicNumber - CardsExhaustedLastTurn));
        if (this.cost != 5) {
            isCostModifiedForTurn = true;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }
}