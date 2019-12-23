package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class WrappedPresent extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("WrappedPresent");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WrappedPresent.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WrappedPresent.png"));
    private boolean cardsReceived = true;


    public WrappedPresent() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        cardsReceived = false;
    }

    public void update() {
        super.update();
        if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            int chance = AbstractDungeon.relicRng.random(0, 99);
            if (chance >= 74) {
                AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
            } else {
                AbstractDungeon.player.heal(5);
                AbstractDungeon.player.increaseMaxHp(5, true);
            }
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.RARE)));
            AbstractDungeon.combatRewardScreen.positionRewards();
            AbstractDungeon.overlayMenu.proceedButton.setLabel(this.DESCRIPTIONS[1]);
            this.cardsReceived = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
        }
    }

    @Override
    public int getPrice() {
        return 250;
    }
}