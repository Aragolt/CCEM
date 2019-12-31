package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class TinyMinotavrHorn extends CustomRelic {
    public static final String ID = makeID("TinyMinotavrHorn");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TinyMinotavrHorn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TinyMinotavrHorn.png"));

    public TinyMinotavrHorn() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK && (c.costForTurn == 1 || c.costForTurn == 0 || c.freeToPlayOnce)) {
            counter++;
        }
    }

    @Override
    public void atTurnStart() {
        for (int i = 0; i < counter; i++) {
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        }
        counter = 0;
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}