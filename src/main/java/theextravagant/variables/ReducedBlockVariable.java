package theextravagant.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theextravagant.theextravagant.makeID;

public class ReducedBlockVariable extends DynamicVariable {

    @Override
    public String key() {
        return makeID("REDUCED_BLOCK");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return card.isBlockModified;
    }

    @Override
    public int value(AbstractCard card) {
        return card.block - card.magicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return card.baseBlock - card.baseMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card.upgradedBlock;
    }
}