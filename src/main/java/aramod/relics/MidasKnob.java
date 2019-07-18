package aramod.relics;
import aramod.powers.ElectrifiedPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.evacipated.cardcrawl.mod.stslib.relics.OnAfterUseCardRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class MidasKnob extends CustomRelic implements OnAfterUseCardRelic {
    public static final String ID = "AraMod:MidasKnob";

    public MidasKnob(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/midasknob.png"), TextureLoader.getTexture("aramod/img/relics/midasknob-outline.png"), RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public void onAfterUseCard(AbstractCard abstractCard, UseCardAction useCardAction) {
        if (abstractCard.type == AbstractCard.CardType.SKILL){
            CardCrawlGame.sound.play("GOLD_GAIN");
            AbstractDungeon.player.gainGold(1);
            AbstractDungeon.effectList.add(new GainPennyEffect(AbstractDungeon.player, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}