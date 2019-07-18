package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class ChunkOfWall extends CustomRelic {
    public static final String ID = "AraMod:ChunkOfWall";

    public ChunkOfWall(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/chunkofwall.png"), TextureLoader.getTexture("aramod/img/relics/chunkofwall-outline.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}