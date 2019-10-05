package theextravagant.vfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import theextravagant.util.TextureLoader;

public class VfxMaster {
    public static final TextureAtlas VFXAtlas = new TextureAtlas();
    public static Texture SixteenTons;
    public static Texture Damnation;
    public static Texture Firework;
    public static Texture Punch;

    public static void initialize() {
        SixteenTons = TextureLoader.getTexture("theextravagantResources/images/vfx/SixteenTons.png");
        Damnation = TextureLoader.getTexture("theextravagantResources/images/vfx/Damnation.png");
        Firework = TextureLoader.getTexture("theextravagantResources/images/vfx/Firework.png");
        Punch = TextureLoader.getTexture("theextravagantResources/images/vfx/Punch.png");
        VFXAtlas.addRegion("SixteenTons", SixteenTons, 0, 0, SixteenTons.getWidth(), SixteenTons.getHeight());
        VFXAtlas.addRegion("Damnation", Damnation, 0, 0, Damnation.getWidth(), Damnation.getHeight());
        VFXAtlas.addRegion("Firework", Firework, 0, 0, Firework.getWidth(), Firework.getHeight());
        VFXAtlas.addRegion("Punch", Punch, 0, 0, Punch.getWidth(), Punch.getHeight());
    }
}
