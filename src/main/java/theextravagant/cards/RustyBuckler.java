package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class RustyBuckler extends CustomCard {


    public static final String ID = theextravagant.makeID("RustyBuckler");
    public static final String IMG = theextravagant.makeCardPath("RustyBuckler.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = -2;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 2;
    private static final int BLOCK = 2;

    public RustyBuckler() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed.costForTurn >= 2) {
            this.retain = true;
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        this.applyPowers();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public void manualTrigger() {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            initializeDescription();
        }
    }
}