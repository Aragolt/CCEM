package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.theextravagant;

public abstract class AbstractEVCard extends CustomCard {
    public int Secondcost;
    public int Secondcostforturn;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractEVCard");

    public static String[] EVCardText = uiStrings.TEXT;

    public AbstractEVCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, int SecondCost) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.Secondcost = SecondCost;
        Secondcostforturn = Secondcost;
    }

    @Override
    public boolean hasEnoughEnergy() {
        if(theextravagant.SecondEnergyOrb.currentEnergy < this.Secondcostforturn)
        {
            this.cantUseMessage = EVCardText[0];
            return false;
        }
        return super.hasEnoughEnergy();
    }



    @Override
    public abstract void upgrade();

    @Override
    public abstract void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);
}
