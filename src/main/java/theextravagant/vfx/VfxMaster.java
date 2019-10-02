package theextravagant.vfx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import theextravagant.util.TextureLoader;

public class VfxMaster {
    public static final TextureAtlas VFXAtlas = new TextureAtlas();
    public static Texture SixteenTons;
    public static Texture Damnation;

    public static void initialize() {
        SixteenTons = TextureLoader.getTexture("theextravagantResources/images/vfx/SixteenTons.png");
        Damnation = TextureLoader.getTexture("theextravagantResources/images/vfx/Damnation.png");
        VFXAtlas.addRegion("SixteenTons", SixteenTons, 0, 0, SixteenTons.getWidth(), SixteenTons.getHeight());
        VFXAtlas.addRegion("Damnation", Damnation, 0, 0, Damnation.getWidth(), Damnation.getHeight());
    }
}