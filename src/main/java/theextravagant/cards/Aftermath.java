package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.PheonixEffectAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class Aftermath extends CustomCard {


    public static final String ID = theextravagant.makeID("Aftermath");
    public static final String IMG = theextravagant.makeCardPath("Aftermath.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = -2;
    private static final int DAMAGE = 25;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 0;

    public Aftermath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard) {
            AbstractDungeon.actionManager.addToBottom(new PheonixEffectAction());
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        if (AbstractDungeon.player.hand.size() == 1 && AbstractDungeon.player.hand.contains(this)) {
            this.dontTriggerOnUseCard = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return this.dontTriggerOnUseCard;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(7);
            initializeDescription();
        }
    }
}