package theextravagant.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.BetterCopyAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class ManifestIllusion extends AbstractEVCard {


    public static final String ID = theextravagant.makeID("ManifestIllusion");
    public static final String IMG = theextravagant.makeCardPath("ManifestIllusion.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 3;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 2;
    private static final int BLOCK = 0;

    public ManifestIllusion() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 3);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new BetterCopyAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Dazed(), magicNumber, true, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            Secondcost = 2;
            Secondcostforturn = Secondcost;
            initializeDescription();
        }
    }
}