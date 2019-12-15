package CCEMRelics.patches;

import CCEMRelics.relics.Icicle;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EvokeFrostPatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "removeNextOrb"
    )
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "evokeOrb"
    )
    public static class AbstractPlayerRemoveNextOrbHook {
        @SpirePrefixPatch
        public static void Patch(AbstractPlayer __instance) {
            if (!__instance.orbs.isEmpty() && !(__instance.orbs.get(0) instanceof EmptyOrbSlot)) {
                doStuff.triggerOnRemoveOrEvokeOrb(__instance.orbs.get(0));
            }
        }
    }

    public static class doStuff {
        public static void triggerOnRemoveOrEvokeOrb(AbstractOrb orb) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof Icicle) {
                    ((Icicle) r).onRemoveOrEvokeOrb(orb);
                }
            }
        }
    }
}
