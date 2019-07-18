package aramod.relics;
import aramod.powers.LoseFocusPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class MidasCore extends CustomRelic implements OnChannelRelic {
    public static final String ID = "AraMod:MidasCore";

    public MidasCore(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/midascore.png"), TextureLoader.getTexture("aramod/img/relics/midascore-outline.png"), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public void onChannel(AbstractOrb abstractOrb) {
        CardCrawlGame.sound.play("GOLD_GAIN");
        AbstractDungeon.player.gainGold(7);
        for (int i = 0; i < 7; ++i) {
            AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}