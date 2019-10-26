package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import theextravagant.actions.RavageAction;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

public class Ravage extends CustomCard {


    public static final String ID = theextravagant.makeID("Ravage");
    public static final String IMG = theextravagant.makeCardPath("Ravage.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int MAGICNUMBER = 1;
    private static final int BLOCK = 0;

    public Ravage() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.isMultiDamage = true;
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new DieDieDieEffect(), 0.7F));
        AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
        AbstractDungeon.actionManager.addToBottom(new RavageAction(this, p.hand.getAttacks().size()));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }
}