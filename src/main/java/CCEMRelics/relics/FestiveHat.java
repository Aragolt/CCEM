package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class FestiveHat extends CustomRelic {
    public static final String ID = makeID("FestiveHat");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FestiveHat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FestiveHat.png"));

    public FestiveHat() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
        counter = 3;
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        if ((room instanceof MonsterRoom || room instanceof MonsterRoomElite) && counter > 0) {
            RewardItem R = new RewardItem(AbstractCard.CardColor.COLORLESS);
            for (AbstractCard c : R.cards) {
                if (!c.cardID.equals("ReplayTheSpireMod:Necrogeddon")) {
                    c.upgrade();
                }
            }
            AbstractDungeon.getCurrRoom().rewards.add(R);
            counter -= 1;
            if (counter == 0) {
                this.grayscale = true;
                this.counter = -1;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
