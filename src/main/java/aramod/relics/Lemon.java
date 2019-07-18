package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Lemon extends CustomRelic {
    public static final String ID = "AraMod:Lemon";

    public Lemon(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/lemon.png"), TextureLoader.getTexture("aramod/img/relics/lemon-outline.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(5, true);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new UpgradeRandomCardAction());
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}