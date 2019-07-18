package aramod.relics;
import aramod.powers.LoseFocusPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

public class LostWallet extends CustomRelic {
    public static final String ID = "AraMod:LostWallet";

    public LostWallet(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/lostwallet.png"), TextureLoader.getTexture("aramod/img/relics/lostwallet-outline.png"), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(200);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}