package aramod.relics;
import aramod.powers.ElectrifiedPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.evacipated.cardcrawl.mod.stslib.relics.OnAfterUseCardRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FuseBox extends CustomRelic implements OnAfterUseCardRelic {
    public static final String ID = "AraMod:FuseBox";

    public FuseBox(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/fusebox.png"), TextureLoader.getTexture("aramod/img/relics/fusebox-outline.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void onAfterUseCard(AbstractCard abstractCard, UseCardAction useCardAction) {
        if (abstractCard.hasTag(AbstractCard.CardTags.STRIKE)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(useCardAction.target, AbstractDungeon.player, new ElectrifiedPower(useCardAction.target, 3), 3));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}