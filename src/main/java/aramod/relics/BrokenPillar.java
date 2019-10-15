/*package aramod.relics;
import aramod.actions.DamageRandomEnemyFasterAction;
import aramod.powers.LoseFocusPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.evacipated.cardcrawl.mod.stslib.relics.OnLoseBlockRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BrokenPillar extends CustomRelic {
    public static final String ID = "AraMod:BrokenPillar";
    public int dmg;

    public BrokenPillar(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/brokenpillar.png"), TextureLoader.getTexture("aramod/img/relics/brokenpillar-outline.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
        dmg = 0;
    }

    @Override
    public void atTurnStartPostDraw() {
        for (int x = 0; x < dmg; x++){
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyFasterAction(new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        dmg = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}*/