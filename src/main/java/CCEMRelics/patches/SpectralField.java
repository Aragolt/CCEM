package CCEMRelics.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = SpirePatch.CLASS
)
public class SpectralField {
    public static SpireField<Boolean> spectral = new SpireField<>(() -> false);

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            spectral.set(__result, spectral.get(__instance));
            return __result;
        }
    }
}