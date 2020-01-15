package theextravagant.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.characters.TheExtravagant;
import theextravagant.theextravagant;

import java.util.ArrayList;
import java.util.Iterator;

public class Redemption extends CustomCard {


    public static final String ID = theextravagant.makeID("Redemption");
    public static final String IMG = theextravagant.makeCardPath("Redemption.png");
    public static final CardColor COLOR = TheExtravagant.Enums.EV_BLUE;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;
    private static final int DAMAGE = 5;
    private static final int MAGICNUMBER = 0;
    private static final int BLOCK = 0;
    public static int redemptionbonus = 0;

    public Redemption() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void applyPowers() {
        this.applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
        float[] tmp = new float[m.size()];

        int i;
        for (i = 0; i < tmp.length; ++i) {
            tmp[i] = (float) this.baseDamage;
            tmp[i] += redemptionbonus;
        }

        Iterator var5;
        AbstractPower p;
        for (i = 0; i < tmp.length; ++i) {
            var5 = player.relics.iterator();

            while (var5.hasNext()) {
                AbstractRelic r = (AbstractRelic) var5.next();
                tmp[i] = r.atDamageModify(tmp[i], this);
                if (this.baseDamage != (int) tmp[i]) {
                    this.isDamageModified = true;
                }
            }

            for (var5 = player.powers.iterator(); var5.hasNext(); tmp[i] = p.atDamageGive(tmp[i], this.damageTypeForTurn, this)) {
                p = (AbstractPower) var5.next();
            }

            tmp[i] = player.stance.atDamageGive(tmp[i], this.damageTypeForTurn, this);
            if (this.baseDamage != (int) tmp[i]) {
                this.isDamageModified = true;
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            for (var5 = player.powers.iterator(); var5.hasNext(); tmp[i] = p.atDamageFinalGive(tmp[i], this.damageTypeForTurn, this)) {
                p = (AbstractPower) var5.next();
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            if (tmp[i] < 0.0F) {
                tmp[i] = 0.0F;
            }
        }

        this.multiDamage = new int[tmp.length];

        for (i = 0; i < tmp.length; ++i) {
            if (this.baseDamage != (int) tmp[i]) {
                this.isDamageModified = true;
            }

            this.multiDamage[i] = MathUtils.floor(tmp[i]);
        }

        this.damage = this.multiDamage[0];

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
        float[] tmp = new float[m.size()];

        int i;
        for (i = 0; i < tmp.length; ++i) {
            tmp[i] = (float) this.baseDamage;
            tmp[i] += redemptionbonus;
        }

        Iterator var6;
        AbstractPower p;
        for (i = 0; i < tmp.length; ++i) {
            var6 = player.relics.iterator();

            while (var6.hasNext()) {
                AbstractRelic r = (AbstractRelic) var6.next();
                tmp[i] = r.atDamageModify(tmp[i], this);
                if (this.baseDamage != (int) tmp[i]) {
                    this.isDamageModified = true;
                }
            }

            for (var6 = player.powers.iterator(); var6.hasNext(); tmp[i] = p.atDamageGive(tmp[i], this.damageTypeForTurn, this)) {
                p = (AbstractPower) var6.next();
            }

            tmp[i] = player.stance.atDamageGive(tmp[i], this.damageTypeForTurn, this);
            if (this.baseDamage != (int) tmp[i]) {
                this.isDamageModified = true;
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            var6 = m.get(i).powers.iterator();

            while (var6.hasNext()) {
                p = (AbstractPower) var6.next();
                if (!m.get(i).isDying && !m.get(i).isEscaping) {
                    tmp[i] = p.atDamageReceive(tmp[i], this.damageTypeForTurn, this);
                }
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            for (var6 = player.powers.iterator(); var6.hasNext(); tmp[i] = p.atDamageFinalGive(tmp[i], this.damageTypeForTurn, this)) {
                p = (AbstractPower) var6.next();
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            var6 = m.get(i).powers.iterator();

            while (var6.hasNext()) {
                p = (AbstractPower) var6.next();
                if (!m.get(i).isDying && !m.get(i).isEscaping) {
                    tmp[i] = p.atDamageFinalReceive(tmp[i], this.damageTypeForTurn, this);
                }
            }
        }

        for (i = 0; i < tmp.length; ++i) {
            if (tmp[i] < 0.0F) {
                tmp[i] = 0.0F;
            }
        }

        this.multiDamage = new int[tmp.length];

        for (i = 0; i < tmp.length; ++i) {
            if (this.baseDamage != MathUtils.floor(tmp[i])) {
                this.isDamageModified = true;
            }

            this.multiDamage[i] = MathUtils.floor(tmp[i]);
        }

        this.damage = this.multiDamage[0];
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