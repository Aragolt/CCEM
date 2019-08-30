package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GarbageCannonAction extends AbstractGameAction {
    private int number;
    private AbstractPlayer p;
    private int damage;
    private AbstractMonster m;

    public GarbageCannonAction(int number, AbstractPlayer p, int damage, AbstractMonster m) {
        this.number = number;
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.p = p;
        this.m = m;
        this.damage = damage;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, number));
        for (int i = 0; i < number; i++) {
            if (p.drawPile.getNCardFromTop(i).type == AbstractCard.CardType.STATUS) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(p.drawPile.getNCardFromTop(i), p.hand));
            }
        }
        isDone = true;
    }
}
