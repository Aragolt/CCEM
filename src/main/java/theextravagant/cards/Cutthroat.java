package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

import java.util.Iterator;

public class Cutthroat extends CustomCard {


    public static final String ID = theextravagant.makeID("Cutthroat");
    public static final String IMG = theextravagant.makeCardPath("Cutthroatnew.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 0;
    public static int basebaseDamage = 5;
    public static boolean Hasplayedcardthisturn = false;

    public Cutthroat() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void applyPowers() {
        if (!Hasplayedcardthisturn) {
            baseDamage = basebaseDamage * 3;
        } else {
            baseDamage = basebaseDamage;
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        if (!this.isMultiDamage && mo != null) {
            float tmp = (float) this.baseDamage;
            Iterator var9 = player.relics.iterator();

            while (var9.hasNext()) {
                AbstractRelic r = (AbstractRelic) var9.next();
                tmp = r.atDamageModify(tmp, this);
                if (this.baseDamage != (int) tmp) {
                    this.isDamageModified = true;
                }
            }

            AbstractPower p;
            for (var9 = player.powers.iterator(); var9.hasNext(); tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower) var9.next();
            }

            tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
            if (this.baseDamage != (int) tmp) {
                this.isDamageModified = true;
            }

            for (var9 = mo.powers.iterator(); var9.hasNext(); tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower) var9.next();
            }

            for (var9 = player.powers.iterator(); var9.hasNext(); tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower) var9.next();
            }

            for (var9 = mo.powers.iterator(); var9.hasNext(); tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower) var9.next();
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            if (this.baseDamage != MathUtils.floor(tmp)) {
                this.isDamageModified = true;
            }


            this.damage = MathUtils.floor(tmp);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            basebaseDamage = 6;
            initializeDescription();
        }
    }
}