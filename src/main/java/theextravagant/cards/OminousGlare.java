package theextravagant.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class OminousGlare extends AbstractEVCard {


    public static final String ID = theextravagant.makeID("OminousGlare");
    public static final String IMG = theextravagant.makeCardPath("OminousGlare.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 8;

    public OminousGlare() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 2);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard C : AbstractDungeon.player.hand.group) {
            if (!(C == this)) {
                AbstractCard d = C.makeStatEquivalentCopy();
                AbstractDungeon.player.drawPile.addToTop(d);
            }
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }
}