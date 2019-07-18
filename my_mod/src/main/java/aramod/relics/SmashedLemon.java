package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SmashedLemon extends CustomRelic {
    public static final String ID = "AraMod:SmashedLemon";

    public SmashedLemon(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/smashedlemon.png"), TextureLoader.getTexture("aramod/img/relics/smashedlemon-outline.png"), RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(10, true);
    }

    @Override
    public void atTurnStartPostDraw() {
        AbstractDungeon.actionManager.addToBottom(new UpgradeRandomCardAction());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}