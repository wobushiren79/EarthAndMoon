package com.game.apple.earthandmoon;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.game.apple.earthandmoon.data.Camera;
import com.game.apple.earthandmoon.data.Earth;
import com.game.apple.earthandmoon.data.Moon;
import com.game.apple.earthandmoon.data.Sky;
import com.game.apple.earthandmoon.data.Sun;
import com.game.apple.earthandmoon.program.EarthShaderProgram;
import com.game.apple.earthandmoon.program.MoonShaderProgram;
import com.game.apple.earthandmoon.program.SkyShaderProgram;
import com.game.apple.earthandmoon.util.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

/**
 * Created by apple on 2016/3/19.
 */
public class TheRender implements GLSurfaceView.Renderer {
    private Context context;
    private Sky sky;
    private Sky sky2;
    private Earth earth;
    private Moon moon;

    private Sun sun;
    private Camera camera;

    private SkyShaderProgram skyShaderProgram;
    private EarthShaderProgram earthShaderProgram;
    private MoonShaderProgram moonShaderProgram;

    private int dayEarthTexture;
    private int nightEarthTexture;
    private int moonTexture;

    private float[] projectMatrix = new float[16];
    private float[] eyeMatrix = new float[16];
    private float[] itemMatrix = new float[16];

    public TheRender(Context context) {
        this.context = context;
    }

    private float[] eyeLocation = {0f, 1f, 4f};


    public void setAngle(float xmove){
        earthx+=xmove;

    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0, 0, 0, 1);
        glEnable(GL_DEPTH_TEST);
        sky = new Sky(1, 0, 500);
        sky2 = new Sky(2, 0, 1000);
        earth = new Earth();
        moon = new Moon();

        sun = new Sun();
        camera = new Camera(eyeLocation);

        dayEarthTexture = TextureHelper.getTextureID(context, R.drawable.earth);
        nightEarthTexture = TextureHelper.getTextureID(context, R.drawable.earthn);
        moonTexture = TextureHelper.getTextureID(context, R.drawable.moon);

        skyShaderProgram = new SkyShaderProgram(context, R.raw.sky_vertex_shader, R.raw.sky_fragment_shader);
        earthShaderProgram = new EarthShaderProgram(context, R.raw.earth_vertex_shader, R.raw.earth_fragment_shader);
        moonShaderProgram = new MoonShaderProgram(context, R.raw.moon_vertex_shader, R.raw.moon_fragment_shader);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        perspectiveM(projectMatrix, 0, 45, (float) width / (float) height, 1f, 20f);
        setLookAtM(eyeMatrix, 0, eyeLocation[0], eyeLocation[1], eyeLocation[2], 0f, 0f, 0f, 0f, 1f, 0f);
    }

    float earthx = 0;
    float moonx = 0;
    float skyx = 0;

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        multiplyMM(itemMatrix, 0, projectMatrix, 0, eyeMatrix, 0);




        float[] moveMatrix = new float[16];
        setIdentityM(moveMatrix, 0);
        rotateM(moveMatrix, 0, earthx, 0f, 1f, 0f);
        earthx += 0.2;
        float[] temp = new float[16];
        multiplyMM(temp, 0, itemMatrix, 0, moveMatrix, 0);
        earthShaderProgram.useProgram();
        earthShaderProgram.setUniform(temp,
                dayEarthTexture,
                nightEarthTexture,
                sun.getVertexArrary().getVertexData(),
                camera.getVertexArrary().getVertexData());
        earth.bindData(earthShaderProgram);
        earth.draw();


        float[] moonitemMatrix=new float[16];
        setIdentityM(moonitemMatrix, 0);
        translateM(moonitemMatrix, 0, 2f, 0f, 0f);
        rotateM(moonitemMatrix, 0, moonx, 0f, 1f, 0f);

        moonx+=0.5f;
        float[] moonTemp=new float[16];
        multiplyMM(moonTemp, 0, temp, 0, moonitemMatrix, 0);
        moonShaderProgram.useProgram();
        moonShaderProgram.setUniform(moonTemp,
                sun.getVertexArrary().getVertexData(),
                camera.getVertexArrary().getVertexData(), moonTexture);
        moon.bindData(moonShaderProgram);
        moon.draw();

        float[] skyitemMatrix=new float[16];
        setIdentityM(skyitemMatrix, 0);
        rotateM(skyitemMatrix, 0, skyx, 0f, 1f, 0f);

        float[] skyTemp=new float[16];
        multiplyMM(skyTemp, 0, temp, 0, skyitemMatrix, 0);

        skyShaderProgram.useProgram();
        skyShaderProgram.setUniform(skyTemp, 2);
        sky.bindData(skyShaderProgram);
        sky.draw();

        skyShaderProgram.setUniform(skyTemp, 4);
        sky2.bindData(skyShaderProgram);
        sky2.draw();
        skyx+=0.1f;
    }
}
