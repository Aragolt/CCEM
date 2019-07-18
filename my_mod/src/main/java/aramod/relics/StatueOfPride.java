package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class StatueOfPride extends CustomRelic {
    public static final String ID = "AraMod:StatueOfPride";

    public StatueOfPride(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/statueofpride.png"), TextureLoader.getTexture("aramod/img/relics/statueofpride-outline.png"), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void onVictory() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        final AbstractPlayer p = AbstractDungeon.player;
        int x = p.relics.size() - 1;
        if (p.currentHealth > 0) {
            p.heal(x);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}