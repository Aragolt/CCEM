package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.IncreaseCostAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class Agression extends CustomCard {


    public static final String ID = theextravagant.makeID("Aggression");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = theextravagant.makeCardPath("Agressionnew.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final int COST = 0;
    private static final int DAMAGE = 6;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 0;

    public Agression() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 3);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(uuid, this.baseDamage));
        AbstractDungeon.actionManager.addToBottom(new IncreaseCostAction(uuid, 1));
        applyPowers();
    }

    @Override
    public void atTurnStart() {
        applyPowers();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 4);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 4);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
            initializeDescription();
        }
    }
}