package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class RiskyStrike extends CustomCard {


    public static final String ID = theextravagant.makeID("RiskyStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = theextravagant.makeCardPath("riskystrikenew.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final int COST = 1;
    private static final int DAMAGE = 18;
    private static final int MAGICNUMBER = 2;
    private static final int BLOCK = 0;

    public RiskyStrike() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        for(int i = 0; i < magicNumber; i++)
        {
            AbstractDungeon.player.drawPile.addToTop(new Wound());
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
            initializeDescription();
        }
    }
}