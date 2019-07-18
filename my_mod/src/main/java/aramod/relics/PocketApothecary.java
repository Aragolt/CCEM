package aramod.relics;

import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

public class PocketApothecary extends CustomRelic {
    public static final String ID = "AraMod:PocketApothecary";

    public PocketApothecary(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/pocketapothecary.png"), TextureLoader.getTexture("aramod/img/relics/pocketapothecary-outline.png"), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        ArrayList<AbstractPotion> pots = AbstractDungeon.player.potions;
        for (int x = 0; x < pots.size(); x++){
            AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.miscRng);
            pots.get(x).use(mon);
            AbstractDungeon.player.removePotion(pots.get(x));
        }
    }

    @Override
    public void onVictory() {
        for (int i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
            AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
        }
    }
}