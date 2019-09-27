package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theextravagant.actions.ExhaustTopCardAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class CuttingEdge extends CustomCard {


    public static final String ID = theextravagant.makeID("CuttingEdge");
    public static final String IMG = theextravagant.makeCardPath("CuttingEdge.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 0;
    private static final int DAMAGE = 2;
    private static final int MAGICNUMBER = 1;
    private static final int BLOCK = 0;

    public CuttingEdge() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        AbstractDungeon.actionManager.addToBottom(new ExhaustTopCardAction());
        AbstractCard C = p.hand.getRandomCard(CardType.ATTACK, true);
        if (C != null) {
            AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(C.uuid, 2));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDamage(1);
            initializeDescription();
        }
    }
}