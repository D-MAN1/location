package MyTexture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MyTexture {
	public static TextButtonStyle textButton;
	public static ButtonStyle button, p_button;
	public static	Skin skin,skin2;
	public static TextureAtlas atlas,atlas2;
	public static BitmapFont font;
	public static void loadTexture() {
		
		atlas= new TextureAtlas(Gdx.files.internal("atlas.txt"));
		atlas2= new TextureAtlas(Gdx.files.internal("Texture.txt"));
		skin= new Skin();
		skin.addRegions(atlas);
		skin2= new Skin();
		skin2.addRegions(atlas2);
		
		p_button = new ButtonStyle();
		p_button.up= skin.getDrawable("launch_stop");
		p_button.down= skin.getDrawable("internal_browser");
	
		font= new BitmapFont();
	FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("times.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	parameter.size = 30;
    parameter.color=Color.BLACK;
    parameter.borderColor=Color.GOLD;
    parameter.borderWidth=2;
    parameter.shadowColor= Color.BLUE;
    parameter.shadowOffsetX=3;
   
    font= gen.generateFont(parameter);
    font.getData().setScale(0.1f,0.1f);
    
    textButton =new TextButtonStyle();
	textButton.up=skin.getDrawable("launch_stop");
	textButton.down=skin.getDrawable("internal_browser");
	textButton.font=font;
	}

}
