package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class CheckTopCardForDexterityAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractMonster m;
    private int draw;

    public CheckTopCardForDexterityAction(AbstractPlayer p, AbstractMonster m, int draw) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.p = p;
        this.m = m;
        this.draw = draw;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
            this.isDone = true;
            return;
        }

        if (AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
            AbstractDungeon.actionManager.addToBottom(new CheckTopCardForDexterityAction(p, m, draw));
            this.isDone = true;
            return;
        }

        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            if (p.drawPile.getTopCard().costForTurn == -2 && !p.drawPile.getTopCard().freeToPlayOnce) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, draw));
            }
        }
        this.isDone = true;
    }
}
