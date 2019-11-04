package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class PutInDrawpileForBlockAction extends AbstractGameAction {
    private float startingDuration;
    private boolean isUpgraded;
    private int blockamount;

    public PutInDrawpileForBlockAction(int amount) {
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        blockamount = amount;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            if (AbstractDungeon.player.hand.size() > 0) {
                CardGroup Temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    Temp.addToTop(c);
                }
                AbstractCard c;
                Iterator var1 = Temp.group.iterator();
                while (var1.hasNext()) {
                    c = (AbstractCard) var1.next();
                    AbstractDungeon.player.hand.moveToDeck(c, true);
                    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, blockamount));
                }
            }
        }
        this.isDone = true;
    }
}
